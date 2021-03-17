package ru.croc.javaschool.task3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.task3.classShells.Engine;
import ru.croc.javaschool.task3.classShells.StatusRent;
import ru.croc.javaschool.task3.classShells.StatusRepair;

import static org.junit.jupiter.api.Assertions.*;

class MotorBikeTest {
    MotorBike motorBike;

    @BeforeEach
    public void setup() {
        motorBike = new MotorBike("Мотоцикл", "Yamaha", 101, Engine.ISE, StatusRent.ONSTOCK, StatusRepair.GOOD);
    }

    @Test
    @DisplayName("Тест метода получения полной информации об автомобиле")
    public void testGetFullName() {
        Assertions.assertEquals("Информация о транспорте: \n" +
                "\tНазвание: Мотоцикл\n" +
                "\tФирма: Yamaha\n" +
                "\tНомер: 101\n" +
                "\tСтатус арнеды: ONSTOCK\n" +
                "\tСостояние транспорта: GOOD\n" +
                "\tТип двигателя: ISE", motorBike.getFullName());
    }
}