package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post create(String title, String content) {
        return postRepository.save(new Post(title, content));
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> search(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    public Post findOne(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다. id=" + id));
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Post update(Long id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다. id=" + id));
        post.update(title, content);
        return post;
    }
}