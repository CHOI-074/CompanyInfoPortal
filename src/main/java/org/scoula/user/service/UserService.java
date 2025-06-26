package org.scoula.user.service;

import org.scoula.user.dto.UserDTO;

public interface UserService {
    void createUser(UserDTO dto);
    UserDTO getUserById(Long id);
    void updateUser(UserDTO dto);
    void deleteUser(Long id);
}
