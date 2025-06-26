package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.domain.PostVO;
import org.scoula.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostPageController {

    private final PostService postService;

    // 게시글 목록 페이지
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PostVO post = postService.get(id);
        model.addAttribute("post", post);
        return "post/detail";
    }

    // 등록 폼 페이지
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("post", new PostVO());
        return "post/form";
    }

    // 수정 폼 페이지
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.get(id));
        return "post/form";
    }
}