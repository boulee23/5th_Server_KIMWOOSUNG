package com.example.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequest {
    private String title;
    private String content;
}

//요청용
//@NoArgsConstructor + @Getter 필수
// JSON → 객체 변환(역직렬화)할 때 Jackson이 기본 생성자로 객체를 만든 뒤 값을 채워넣기 때문