package com.example.board.controller;

import com.example.board.domain.Post;
import com.example.board.dto.PostCreateRequest;
import com.example.board.dto.PostResponse;
import com.example.board.dto.PostUpdateRequest;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //반환값을 JSON으로 변환해서 응답 본문에 담음
@RequestMapping("/posts") //이 클래스의 모든 URL 앞에 /posts가 붙음
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 생성, @RequestBody => 요청 본문의 JSON → 자바 객체로 변환
    @PostMapping //POST /posts 요청 처리
    public PostResponse create(@RequestBody PostCreateRequest request) {
        Post post = postService.create(request.getTitle(), request.getContent());
        return new PostResponse(post);
    }


    // 전체 조회
    @GetMapping
    public List<PostResponse> findAll() {
        return postService.findAll().stream()
                .map(PostResponse::new)
                .toList();
    }

    // 검색: GET /posts/search?keyword=공지
    @GetMapping("/search")
    public List<PostResponse> search(@RequestParam String keyword) {
        return postService.search(keyword).stream()
                .map(PostResponse::new)
                .toList();
    }

    // 단건 조회, @PathVariable => URL의 {id} 부분을 파라미터로 꺼냄
    @GetMapping("/{id}") ////GET /posts/1 요청 처리
    public PostResponse findOne(@PathVariable Long id) {
        return new PostResponse(postService.findOne(id));
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public PostResponse update(@PathVariable Long id,
                               @RequestBody PostUpdateRequest request) {
        Post post = postService.update(id, request.getTitle(), request.getContent());
        return new PostResponse(post);
    }
}