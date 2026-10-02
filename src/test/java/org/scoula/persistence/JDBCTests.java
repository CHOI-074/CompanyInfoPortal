package org.scoula.persistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.scoula.config.RootConfig;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringJUnitConfig(RootConfig.class)
class JDBCTests {
    @Autowired DataSource dataSource;
    @Test void isolatedDatabaseConnection() throws Exception {
        try (var connection = dataSource.getConnection()) {
            assertTrue(connection.isValid(2));
        }
    }
}
