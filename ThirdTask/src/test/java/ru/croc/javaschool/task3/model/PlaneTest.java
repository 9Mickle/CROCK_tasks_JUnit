package ru.croc.javaschool.task3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.task3.classShells.Engine;
import ru.croc.javaschool.task3.classShells.StatusRent;
import ru.croc.javaschool.task3.classShells.StatusRepair;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    Plane plane;

    @BeforeEach
    public void setup() {
        plane = new Plane("Самолет", "Фалкон", 400, StatusRent.ONSTOCK, StatusRepair.GOOD, Engine.DTRD);
    }

    @Test
    @DisplayName("Тест метода получения полной информации о велосипеде")
    public void testGetFullName() {
        Assertions.assertEquals("Информация о транспорте: \n" +
                "\tНазвание: Самолет\n" +
                "\tФирма: Фалкон\n" +
                "\tНомер: 400\n" +
                "\tСтатус арнеды: ONSTOCK\n" +
                "\tСостояние транспорта: GOOD\n" +
                "\tТип двигателя: DTRD", plane.getFullName());
    }
}