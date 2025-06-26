package org.scoula.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.scoula.domain.PostVO;

import java.util.List;

@Mapper
public interface PostMapper {

    // 게시글 등록
    void insert(PostVO post);

    // 게시글 단건 조회
    PostVO getById(Long id);

    // 게시글 전체 목록 조회
    List<PostVO> getAll();

    // 게시글 수정
    boolean update(PostVO post);

    // 게시글 삭제
    boolean delete(Long id);
}