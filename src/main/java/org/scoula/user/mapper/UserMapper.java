package org.scoula.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.scoula.user.dto.UserDTO;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO dto);

    UserDTO selectUserById(Long id);

    void updateUser(UserDTO dto);

    void deleteUser(Long id);
}
