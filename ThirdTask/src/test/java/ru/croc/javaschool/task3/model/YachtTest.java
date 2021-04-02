package ru.croc.javaschool.task3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.task3.classShells.Engine;
import ru.croc.javaschool.task3.classShells.StatusRent;
import ru.croc.javaschool.task3.classShells.StatusRepair;

import static org.junit.jupiter.api.Assertions.*;

class YachtTest {
    Yacht yacht;

    @BeforeEach
    public void setup() {
        yacht = new Yacht("Яхта", "LVMH", 300, StatusRent.ONSTOCK, StatusRepair.GOOD, Engine.ELECTRIC);
    }


    @Test
    @DisplayName("Тест метода получения полной информации о велосипеде")
    public void testGetFullName() {
        Assertions.assertEquals("Информация о транспорте: \n" +
                "\tНазвание: Яхта\n" +
                "\tФирма: LVMH\n" +
                "\tНомер: 300\n" +
                "\tСтатус арнеды: ONSTOCK\n" +
                "\tСостояние транспорта: GOOD\n" +
                "\tТип двигателя: ELECTRIC", yacht.getFullName());
    }

}