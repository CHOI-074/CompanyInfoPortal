package org.scoula.user.service;

import lombok.RequiredArgsConstructor;
import org.scoula.user.dto.UserDTO;
import org.scoula.user.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwords = new BCryptPasswordEncoder();

    private void validate(UserDTO dto) {
        if (dto.getUserId() == null || dto.getUserId().isBlank() || dto.getUserId().length() > 100
                || dto.getName() == null || dto.getName().isBlank() || dto.getName().length() > 100
                || dto.getNickname() == null || dto.getNickname().isBlank() || dto.getNickname().length() > 100
                || dto.getPassword() == null || dto.getPassword().isBlank()
                || dto.getPassword().getBytes(java.nio.charset.StandardCharsets.UTF_8).length > 72) {
            throw new IllegalArgumentException("userId, name, nickname, password are required and must fit their limits");
        }
        dto.setPassword(passwords.encode(dto.getPassword()));
    }

    @Override
    public void createUser(UserDTO dto) {
        validate(dto);
        userMapper.insertUser(dto);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public boolean updateUser(UserDTO dto) {
        if (dto.getId() == null) throw new IllegalArgumentException("id is required");
        validate(dto);
        return userMapper.updateUser(dto) > 0;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteUser(id) > 0;
    }
}
