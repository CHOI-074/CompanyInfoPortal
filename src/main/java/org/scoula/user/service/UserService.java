package org.scoula.user.service;

import org.scoula.user.dto.UserDTO;

public interface UserService {
    void createUser(UserDTO dto);
    UserDTO getUserById(Long id);
    boolean updateUser(UserDTO dto);
    boolean deleteUser(Long id);
}
