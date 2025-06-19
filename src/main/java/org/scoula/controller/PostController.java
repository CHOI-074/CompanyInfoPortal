package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.domain.PostVO;
import org.scoula.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // REST API 응답 반환 (@ResponseBody 포함)
@RequestMapping("/api/posts") // 모든 경로 앞에 /api/posts 포함
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 등록 (POST)
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostVO post) {
        postService.register(post);
        return ResponseEntity.ok().build(); // 200 OK 반환
    }

    // 게시글 단건 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<PostVO> getPost(@PathVariable Long id) {
        PostVO post = postService.get(id);
        if (post == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(post); // 200 OK + 본문에 post 포함
    }

    // 게시글 전체 조회 (GET)
    @GetMapping
    public ResponseEntity<List<PostVO>> getAllPosts() {
        List<PostVO> list = postService.getAll();
        return ResponseEntity.ok(list); // 200 OK + 전체 리스트 반환
    }

    // 게시글 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody PostVO post) {
        post.setId(id); // 경로에서 받은 id를 post 객체에 설정
        boolean updated = postService.modify(post);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build(); // 수정 실패 시 404 반환
        }
    }

    // 게시글 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean deleted = postService.remove(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 삭제 실패 시 404
        }
    }
}