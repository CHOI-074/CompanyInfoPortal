package org.scoula.service;


import lombok.RequiredArgsConstructor;
import org.scoula.domain.PostVO;
import org.scoula.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    // 게시글 등록
    @Override
    public void register(PostVO post) {
        postMapper.insert(post);
    }

    // 게시글 건단 조회
    @Override
    public PostVO get(Long id) {
        return postMapper.getById(id);
    }

    // 게시글 전체 조회
    @Override
    public List<PostVO> getAll() {
        return postMapper.getAll();
    }

    //게시글 수정
    @Override
    public boolean modify(PostVO post) {
        return postMapper.update(post);
    }

    // 게시글 삭제
    @Override
    public boolean remove(Long id) {
        return postMapper.delete(id);
    }
}