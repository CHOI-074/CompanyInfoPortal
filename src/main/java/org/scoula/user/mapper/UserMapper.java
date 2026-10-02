package org.scoula.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.scoula.user.dto.UserDTO;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO dto);

    UserDTO selectUserById(Long id);

    int updateUser(UserDTO dto);

    int deleteUser(Long id);
}
