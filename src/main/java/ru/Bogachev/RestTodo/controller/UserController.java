package ru.Bogachev.RestTodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Bogachev.RestTodo.entity.UserEntity;
import ru.Bogachev.RestTodo.exception.UserAlreadyExistException;
import ru.Bogachev.RestTodo.exception.UserNotFoundException;
import ru.Bogachev.RestTodo.model.UserModel;
import ru.Bogachev.RestTodo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ExceptionHandler({UserAlreadyExistException.class, UserNotFoundException.class})
    public ResponseEntity<String> handleUserExceptions(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.badRequest().body("Error!");
    }
    @PostMapping
    public ResponseEntity<String> registration(@RequestBody UserEntity user) throws UserAlreadyExistException {
        userService.registration(user);
        return ResponseEntity.ok("Пользователь сохранен !");
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping
    public ResponseEntity<UserModel> getOne(@RequestParam Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getOne(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
