package ru.croc.java.school.task5.managers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.task5.task.Task;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TestTaskManager {

    Task task = new Task("123", "Тест", "Для теста", "JUnit", "В процессе");
    HashMap<String, Task> map = new HashMap<>();

    public void record(HashMap map) {

        try {
            FileOutputStream fos = new FileOutputStream("save.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(map);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap getRecordFromFile() {
        try {
            FileInputStream fis = new FileInputStream("save.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            map = (HashMap<String, Task>) ois.readObject();

            fis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public void putMap() {
        map.put("123", task);
    }

    public void editTaskStatus() {
        map.get("123").setStatusTask("Решена");
        record(map);
    }

    @Test
    public void testGetInfo () {
        putMap();
        editTaskStatus();
        Assertions.assertEquals(map, getRecordFromFile());
    }

}
