package ru.croc.java.school.task5.menu;

import ru.croc.java.school.task5.managers.TaskManager;

import java.io.IOException;
import java.util.Scanner;

/**
 * Меню.
 */
public class Menu {

    /** Выбор пользователя.*/
    private String userChoice;
    private int key;

    private String id;

    private final TaskManager taskManager = new TaskManager();

    private final Scanner scanner = new Scanner(System.in);


    public void menuInfo() throws IOException, ClassNotFoundException {
        while (!"6".equals(userChoice)) {
            System.out.print("\n----------Меню----------\n" +
                    "Выберите пункт:\n" +
                    "1) Получить задачу\n" +
                    "2) Изменить задачу\n" +
                    "3) Удалить задачу\n" +
                    "4) Добавить задачу\n" +
                    "5) Вывести список всех задач\n" +
                    "6) Выход\n" +
                    "Выбор: ");

            userChoice = scanner.next();

            // Если введено что-то вместо цифры.
            try {
                key = Integer.parseInt(userChoice);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод!");
            }

            actionOnTask(key);
        }
        System.out.println("Выход");
    }

    /** Обработка выбора пользователя.*/
    public void actionOnTask(int key) throws IOException, ClassNotFoundException {

        System.out.print("\nПолучите: ");
        switch (key) {
            //Получение задачи по её id.
            case 1:
                putId();
                if (taskManager.checkFile()) {
                    System.out.println("Файл пуст");
                } else if (!taskManager.checkKey(id)) {
                    System.out.println("Задача с таким кодом не найдена.");
                } else {
                    taskManager.getTask(id);
                }break;

            case 2:
                //Редактирование полей задачи, с дальнейшем выбором какое поле редактировать.
                putId();
                if (taskManager.checkFile()) {
                    System.out.println("Файл пуст");
                } else if (!taskManager.checkKey(id)) {
                    System.out.println("Задача с таким кодом не найдена.");
                } else {
                    taskManager.editTask(id);
                }break;

            case 3:
                //Удаление задачи.
                putId();
                taskManager.deleteTask(id);break;

            case 4:
                //Добавление задачи.
                taskManager.addTask();break;

            case 5:
                //Вывод всех задач в задачнике.
                if (taskManager.checkFile()) {
                    System.out.print("Файл пуст");
                } else {
                    taskManager.getInfo();
                }break;

            default:
                break;
        }
    }

    /**
     * Вспомогательный метод, с вводом кода задачи.
     */
    private void putId() {
        System.out.print("\nВведите код задачи: ");
        id = scanner.next();
    }
}
