package ru.croc.javaschool.task3.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.task3.classShells.StatusRent;

import static org.junit.jupiter.api.Assertions.*;

class YachtTest {
    Yacht yacht;

    @BeforeEach
    public void setup() {
        yacht = new Yacht("Яхта", "LVMH", 300, StatusRent.ONSTOCK);
    }

    @Test
    @DisplayName("Тест метода получения полной информации о велосипеде")
    public void testGetFullName() {
        Assertions.assertEquals("Яхта LVMH(300) ONSTOCK", yacht.getFullName());
    }

}