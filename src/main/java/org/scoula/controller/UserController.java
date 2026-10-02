package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.user.dto.UserDTO;
import org.scoula.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // 🔥 인터페이스 타입으로 주입!

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO dto) {
        userService.createUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserDTO dto) {
        return userService.updateUser(dto) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return userService.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
