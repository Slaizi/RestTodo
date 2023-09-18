package ru.Bogachev.RestTodo.exception;

public class TodoAlreadyExistException extends Exception {
    public TodoAlreadyExistException(String message) {
        super(message);
    }
}
