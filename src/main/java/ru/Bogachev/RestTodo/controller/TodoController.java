package ru.Bogachev.RestTodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Bogachev.RestTodo.entity.TodoEntity;
import ru.Bogachev.RestTodo.exception.TodoAlreadyExistException;
import ru.Bogachev.RestTodo.exception.TodoNotFoundException;
import ru.Bogachev.RestTodo.exception.UserNotFoundException;
import ru.Bogachev.RestTodo.model.TodoModel;
import ru.Bogachev.RestTodo.service.TodoService;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @ExceptionHandler({UserNotFoundException.class, TodoAlreadyExistException.class, TodoNotFoundException.class})
    public ResponseEntity<String> handleUserExceptions(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.badRequest().body("Error!");
    }
    @PostMapping
    public ResponseEntity<TodoModel> createTodo (@RequestBody TodoEntity todo,
                                         @RequestParam Long userId) throws UserNotFoundException, TodoAlreadyExistException {
     return ResponseEntity.ok(todoService.creteTodo(todo, userId));
    }

    @PutMapping
    public ResponseEntity<TodoModel> completeTodo (@RequestParam Long id) throws TodoNotFoundException {
        return ResponseEntity.ok(todoService.completeTodo(id));
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo (@PathVariable Long todoId) throws TodoNotFoundException {
        return ResponseEntity.ok(todoService.deleteTodo(todoId));
    }
}
