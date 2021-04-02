package ru.croc.java.school.task5.managers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.task5.task.Task;

import java.util.HashMap;

public class TestTaskManager {

    Task task;

    //Создаем экземпляр задачи.
    @BeforeEach
    public void addTask() {
        task = new Task("123", "Тест", "Для теста", "JUnit", "В процессе");
    }

    @Test
    public void editTask() {
        task.setStatusTask("Решена");
        Assertions.assertEquals("{123=Задача{Код='123', Наименование='Тест', Описание='Для теста', Исполнитель='JUnit', Статус=Решена}}", editTask());
    }



}
