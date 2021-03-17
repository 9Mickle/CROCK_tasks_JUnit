package ru.croc.javaschool.task3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.task3.classShells.Engine;
import ru.croc.javaschool.task3.classShells.StatusRent;

import static org.junit.jupiter.api.Assertions.*;

class AutoTest {
    Auto auto;

    @BeforeEach
    public void setup() {
        auto = new Auto("Электрокар","Tesla", 200, Engine.ELECTRIC, StatusRent.ONSTOCK);
    }

    @Test
    @DisplayName("Тест метода получения полной информации об автомобиле")
    public void testGetFullName() {
        Assertions.assertEquals("Электрокар Tesla(200) ONSTOCK ELECTRIC", auto.getFullName());
    }
}