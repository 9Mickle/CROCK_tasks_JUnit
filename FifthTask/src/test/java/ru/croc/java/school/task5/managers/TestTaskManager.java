package ru.croc.java.school.task5.managers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.task5.task.Task;

import java.util.HashMap;

public class TestTaskManager {

    Task task;
    HashMap<String, Task> map = new HashMap<>();

    //Создаем экземпляр задачи.
    @BeforeEach
    public void addTask() {
        task = new Task("123", "Тест", "Для теста", "JUnit", "В процессе");
        map.put("123", task);
    }

    public String editTaskStatus() {
        task.setStatusTask("Решена");
        return task.getStatusTask();
    }

    public String editTaskTitle() {
        task.setTitle("Аквариум");
        return task.getTitle();
    }

    public String editTaskExecutor() {
        task.setExecutor("Рыба");
        return task.getExecutor();
    }

    public String editTaskDescription() {
        task.setDescription("Рыба будет плавать в аквариуме");
        return task.getDescription();
    }

    @Test
    public void testSetStatus() {
        editTaskStatus();
        Assertions.assertEquals(map.get("123").getStatusTask(), editTaskStatus());
    }

    @Test
    public void testSetTitle() {
        editTaskTitle();
        Assertions.assertEquals(map.get("123").getTitle(), editTaskTitle());
    }

    @Test
    public void testSetExecutor() {
        editTaskExecutor();
        Assertions.assertEquals(map.get("123").getExecutor(), editTaskExecutor());
    }

    @Test
    public void testSetDescription() {
        editTaskDescription();
        Assertions.assertEquals(map.get("123").getDescription(), editTaskDescription());
    }
}
