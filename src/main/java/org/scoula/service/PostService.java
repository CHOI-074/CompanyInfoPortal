package org.scoula.service;

import org.scoula.domain.PostVO;

import java.util.List;

public interface PostService {

    // 게시글 등록
    void register(PostVO post);

    // 게시글 단건 조회
    PostVO get(Long id);

    // 게시글 전체 목록 조회
    List<PostVO> getAll();

    // 게시글 수정
    boolean modify(PostVO post);

    // 게시글 삭제
    boolean remove(Long id);
}