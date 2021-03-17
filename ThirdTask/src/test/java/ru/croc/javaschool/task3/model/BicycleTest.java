package ru.croc.javaschool.task3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.task3.classShells.Engine;
import ru.croc.javaschool.task3.classShells.StatusRent;

import static org.junit.jupiter.api.Assertions.*;

class BicycleTest {
    Bicycle bicycle;

    @BeforeEach
    public void setup() {
        bicycle = new Bicycle("Велосипед", "Forward", 1, StatusRent.ONSTOCK);
    }

    @Test
    @DisplayName("Тест метода получения полной информации о велосипеде")
    public void testGetFullName() {
        Assertions.assertEquals("Велосипед Forward(1) ONSTOCK", bicycle.getFullName());
    }
}