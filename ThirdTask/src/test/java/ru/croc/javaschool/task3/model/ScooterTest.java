package ru.croc.javaschool.task3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.task3.classShells.StatusRent;

import static org.junit.jupiter.api.Assertions.*;

class ScooterTest {
    Scooter scooter;

    @BeforeEach
    public void setup() {
        scooter = new Scooter("Самокат", "Novatrack" , 50, StatusRent.ONSTOCK);
    }

    @Test
    @DisplayName("Тест метода получения полной информации о велосипеде")
    public void testGetFullName() {
        Assertions.assertEquals("Самокат Novatrack(50) ONSTOCK", scooter.getFullName());
    }
}