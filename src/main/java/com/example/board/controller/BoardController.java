package com.example.board.controller;

import com.example.board.domain.Post;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;

    // 목록 화면
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "list";
    }

    // 작성 폼 화면
    @GetMapping("/new")
    public String createForm() {
        return "form";
    }

    // 작성 처리
    @PostMapping
    public String create(@RequestParam String title,
                         @RequestParam String content) {
        postService.create(title, content);
        return "redirect:/board";
    }

    // 상세 화면
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findOne(id));
        return "detail";
    }

    // 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/board";
    }
}