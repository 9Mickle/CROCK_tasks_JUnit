package ru.croc.java.school.task7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.task7.dbprovider.DataSourceProvider;
import ru.croc.java.school.task7.entity.Prisoner;
import ru.croc.java.school.task7.repository.PrisonerRepository;
import ru.croc.java.school.task7.service.Calculations;
import ru.croc.java.school.task7.service.PrisonerService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * Тест класса Calculations.
 */
public class TestCalculations {

    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    PrisonerRepository prisonerRepository = new PrisonerRepository(dataSourceProvider.getDataSource());
    PrisonerService prisonerService = new PrisonerService(prisonerRepository);
    Calculations calculations = new Calculations(prisonerRepository);

    public TestCalculations() throws IOException {
    }

    Prisoner prisoner = new Prisoner(1, "Jhon", 37, true, 1); // Тестовый заключенный.
    Map<String, String> dates;
    String incarceration ;
    String release;

    /**
     * Занести заключенного в БД перед тестом.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        prisonerService.createNew(prisoner);
    }

    /**
     * Удаление заключенного из БД после теста.
     */
    @AfterEach
    void clearTable() throws SQLException {
        prisonerService.deleteAll(); // Очистить таблицу перед тестом.
    }

    @Test
    public void testCalcTerm() throws SQLException {
        dates = calculations.calcTerm(prisoner.getId(), 2020, 04, 15); // Занести даты в мапу.
        LocalDate date1 = LocalDate.of(2020, 04, 15); // Ожидаемая дата начала срока.
        LocalDate date2 = LocalDate.of(2021, 04, 15); // Ожидаемая дата конца срока.
        // Преобразование дат в строку.
        incarceration = date1.toString();
        release = date2.toString();
        Assertions.assertEquals(incarceration, dates.get("start"));
        Assertions.assertEquals(release, dates.get("end"));
    }

    /**
     * Описание теста: Перед тестом создается заключенный1 с приговором 1 год, внутри теста создается заключенный2
     * с приговором в 10 лет, задаётся одинаковая дата начала срока у обоих заключенных,
     * идет расчет сроков обоих заключенных, затем идет проверка - можно ли выпустить заключенных из тюрьмы.
     */
    @Test
    public void testReleasePrisoner() throws SQLException {
        Prisoner testPrisoner = new Prisoner(2, "Whity", 20, true, 10); // Тестовый заключенный2.
        prisonerService.createNew(testPrisoner);
        // Установил дату начала срока обоих заключенных.
        calculations.calcTerm(prisoner.getId(), 2020, 04, 15);
        calculations.calcTerm(testPrisoner.getId(), 2020, 04, 15);
        // Расчитал.
        calculations.releasePrisoner(prisoner.getId());
        calculations.releasePrisoner(testPrisoner.getId());
        // Записал их.
        Prisoner prisoner = prisonerService.getAll().get(0);
        Prisoner prisoner2 = prisonerService.getAll().get(1);
        // Сравнил.
        Assertions.assertEquals(false, prisoner.getInJail()); // Здесь тест будет выполнен корректно.
        Assertions.assertEquals(false, prisoner2.getInJail()); // Здесь тест упадет, т.к ожидается false.
    }
}
