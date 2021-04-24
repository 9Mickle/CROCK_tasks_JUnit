package ru.croc.java.school.task5.managers;

import ru.croc.java.school.task5.task.Task;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Операции над списком задач.
 */
public class TaskManager {

    private final Scanner scanner = new Scanner(System.in);
    private HashMap<String,Task> map = new HashMap<>(); // Список с задачами.
    private final File file = new File("save.ser");

    /**
     * Запись в файл.
     *
     * @param map список с задачачи.
     */
    private void record(Map map) {

        try {
            // создаем 2 потока для сериализации объекта и сохранения его в файл.
            FileOutputStream fos = new FileOutputStream("save.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // сохраняем задачу в файл.
            oos.writeObject(map);

            // закрываем поток и освобождаем ресурсы.
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение данных из файла.
     */
    private Map getRecordFromFile() {

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

    /**
     * Ввести данные задачи и сохранить в файл.
     */
    public void addTask() {

        System.out.println("Введите информацию о задаче: ");
        System.out.print("  Код: ");
        String id = scanner.nextLine();
        System.out.print("  Наименование: ");
        String title = scanner.nextLine();
        System.out.print("  Описание: ");
        String description = scanner.nextLine();
        System.out.print("  Исполнитель: ");
        String executor = scanner.nextLine();
        System.out.print("  Статус: ");
        String statusTask = scanner.nextLine();

        Task task = new Task(id, title, description, executor, statusTask);

        map.put(task.getId(), task);
        record(map);
    }

    /**
     * Достать задачу из файла и вывести информацию о ней.
     */
    public void getTask(String id) {

        try {
            FileInputStream fis = new FileInputStream("save.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            map = (HashMap<String, Task>) ois.readObject();

            System.out.println(map.get(id));

            fis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление задачи, осуществляется через поиск задачи по её id.
     *
     * @param id код задачи.
     */
    public void deleteTask(String id) {

        map.remove(id);

        //Перезапись файла после удаления задачи.
        record(map);
    }

    /**
     * Изменить что-то конкретное в задаче.
     *
     * @param id код задачи.
     */
    public void editTask(String id) {
        System.out.print("Что будем менять в задаче?\n" +
                "1) Наименование\n" +
                "2) Описание\n" +
                "3) Исполнителя\n" +
                "4) Статус\n" +
                "Выбор: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Съесть оставшуюся строку.

        switch (choice) {
            //Редактирование наименования.
            case 1:
                System.out.print("  Введите наименование: ");
                map.get(id).setTitle(scanner.nextLine());break;

            case 2:
                //Редактирование описания.
                System.out.print("  Введите описание: ");
                map.get(id).setDescription(scanner.nextLine());break;

            case 3:
                //Редактирование исполнителя.
                System.out.print("  Введите исполнителя: ");
                map.get(id).setExecutor(scanner.nextLine());break;

            case 4:
                //Редактирование статуса.
                System.out.print("  Введите статус: ");
                map.get(id).setStatusTask(scanner.nextLine());break;
        }
        //Перезапись файла после редактирования одного из полей задачи.
        record(map);
    }

    /**
     * Получить полный список задач.
     */
    public void getInfo() {
        System.out.println(getRecordFromFile());
    }

    /**
     * Проверка. Если файл пустой возвращает true, иначе false.
     *
     * @return true/false.
     */
    public boolean checkFile() {
        return file.length() == 0;
    }

    /**
     * Проверка. Если в списке есть значение с ключом id, то возвращает true, иначе false.
     *
     * @return true/false.
     */
    public boolean checkKey(String id) {
        getRecordFromFile();
        return map.containsKey(id);
    }
}
