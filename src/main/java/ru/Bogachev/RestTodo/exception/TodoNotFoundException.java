package ru.Bogachev.RestTodo.exception;

public class TodoNotFoundException extends Exception{
    public TodoNotFoundException(String message) {
        super(message);
    }
}
