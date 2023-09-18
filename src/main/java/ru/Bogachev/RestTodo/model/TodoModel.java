package ru.Bogachev.RestTodo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.Bogachev.RestTodo.entity.TodoEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoModel {
    private Long id;
    private String title;
    private Boolean completed;

    public static TodoModel toModel (TodoEntity entity) {
        TodoModel model = new TodoModel();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setCompleted(entity.getCompleted());
        return model;
    }
}
