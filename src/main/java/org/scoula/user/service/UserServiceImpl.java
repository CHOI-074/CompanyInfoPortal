package org.scoula.user.service;

import lombok.RequiredArgsConstructor;
import org.scoula.user.dto.UserDTO;
import org.scoula.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public void createUser(UserDTO dto) {
        userMapper.insertUser(dto);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public void updateUser(UserDTO dto) {
        userMapper.updateUser(dto);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
}
