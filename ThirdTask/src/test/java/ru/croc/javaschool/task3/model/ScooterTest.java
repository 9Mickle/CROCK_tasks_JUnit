package ru.croc.javaschool.task3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.task3.classShells.Engine;
import ru.croc.javaschool.task3.classShells.StatusRent;
import ru.croc.javaschool.task3.classShells.StatusRepair;

import static org.junit.jupiter.api.Assertions.*;

class ScooterTest {
    Scooter scooter;

    @BeforeEach
    public void setup() {
        scooter = new Scooter("Электросамокат", "Novatrack" , 51,StatusRent.ONSTOCK, StatusRepair.GOOD, Engine.ELECTRIC);
    }

    @Test
    @DisplayName("Тест метода получения полной информации о велосипеде")
    public void testGetFullName() {
        Assertions.assertEquals("Информация о транспорте: \n" +
                "\tНазвание: Электросамокат\n" +
                "\tФирма: Novatrack\n" +
                "\tНомер: 51\n" +
                "\tСтатус арнеды: ONSTOCK\n" +
                "\tСостояние транспорта: GOOD\n" +
                "\tТип двигателя: ELECTRIC", scooter.getFullName());
    }
}