package org.scoula;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scoula.config.RootConfig;
import org.scoula.config.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@Sql(scripts = "/schema.sql")
@Sql(statements = {"DELETE FROM post", "DELETE FROM app_user"})
class PortalApiTest {
    @Autowired WebApplicationContext context;
    @Autowired DataSource dataSource;
    MockMvc mvc;
    JdbcTemplate db;
    ObjectMapper json = new ObjectMapper();

    @BeforeEach void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        db = new JdbcTemplate(dataSource);
    }

    @Test void postCrudAndMissingResources() throws Exception {
        mvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"title":"첫 글","writer":"tester"}
                    """)).andExpect(status().isOk());
        long id = db.queryForObject("SELECT id FROM post", Long.class);
        String result = mvc.perform(get("/api/posts/" + id)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertFalse(json.readTree(result).get("createdDate").isNull());
        mvc.perform(put("/api/posts/" + id).contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"title":"updated","writer":"tester"}
                    """)).andExpect(status().isOk());
        assertEquals("updated", db.queryForObject("SELECT title FROM post WHERE id=?", String.class, id));
        mvc.perform(get("/api/posts")).andExpect(status().isOk());
        mvc.perform(delete("/api/posts/" + id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/posts/" + id)).andExpect(status().isNotFound());
        mvc.perform(delete("/api/posts/" + id)).andExpect(status().isNotFound());
    }

    @Test void userCrudHashesPasswordAndKeepsItOutOfResponses() throws Exception {
        String body = """
            {"userId":"tester","password":"test-only-pass","name":"Test","nickname":"t"}
            """;
        mvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk());
        long id = db.queryForObject("SELECT id FROM app_user", Long.class);
        String hashed = db.queryForObject("SELECT password FROM app_user", String.class);
        assertTrue(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches("test-only-pass", hashed));
        String response = mvc.perform(get("/api/user/" + id)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertFalse(json.readTree(response).has("password"));
        assertEquals("tester", json.readTree(response).get("userId").asText());
        mvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isConflict());
        var update = json.readTree(body);
        ((com.fasterxml.jackson.databind.node.ObjectNode) update).put("id", id);
        mvc.perform(put("/api/user").contentType(MediaType.APPLICATION_JSON).content(update.toString()))
                .andExpect(status().isOk());
        mvc.perform(delete("/api/user/" + id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/user/" + id)).andExpect(status().isNotFound());
        mvc.perform(delete("/api/user/" + id)).andExpect(status().isNotFound());
    }

    @Test void invalidInputAndForeignKeyAreRejected() throws Exception {
        mvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content("{"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/posts/not-a-number")).andExpect(status().isBadRequest());
        mvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest());
        mvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest());
        mvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"title":"test","writer":"t","userId":999999}
                    """)).andExpect(status().isConflict());
    }
}
