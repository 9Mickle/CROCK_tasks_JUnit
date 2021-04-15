package ru.croc.java.school.task7;

import org.junit.jupiter.api.*;
import ru.croc.java.school.task7.dbprovider.DataSourceProvider;
import ru.croc.java.school.task7.entity.Prisoner;
import ru.croc.java.school.task7.repository.PrisonerRepository;
import ru.croc.java.school.task7.service.PrisonerService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тест класса PrisonerService.
 */
public class TestPrisonerService {

    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    PrisonerRepository prisonerRepository = new PrisonerRepository(dataSourceProvider.getDataSource());
    PrisonerService prisonerService = new PrisonerService(prisonerRepository);

    Prisoner prisoner = new Prisoner(1, "Jhon", 37, true, 2); // Тестовый заключенный.
    List<Prisoner> expectedPrisoners; // Лист с ожидаемым заключенным (используется в TestGetAll).
    List<Prisoner> actualPrisoners; // Лист с актуальным заключенным (используется в TestGetAll).

    public TestPrisonerService() throws IOException {
    }

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
        prisonerService.deleteAll(); // Очистить таблицу после теста.
    }

    /**
     * Добавить в лист тестового заключенного.
     */
    public void setList() {
        expectedPrisoners = new ArrayList<>();
        expectedPrisoners.add(prisoner);
    }

    /**
     * Опсиание теста: перед тестом создаётся заключенный, пытаемся создать ещё одного такого же заключенного
     * после чего получаем исключение "дубликатный ключ", иначе удаляем того самого заключенного и создаем его снова.
     */
    @Test
    public void testCreateNew() throws SQLException {
        assertThrows(
                SQLException.class,
                () -> prisonerService.createNew(prisoner)); // Выбросилось ожидаемое исключение: duplicate key(id).
        prisonerService.delete(prisoner.getId()); // Удалил заключенного.
        assertDoesNotThrow(() -> prisonerService.createNew(prisoner)); // Ситуация, при которой не выбросится исключение.
    }

    /**
     * Опсиание теста: передать вместо имени число, начнется парсинг строки в методе checkString(класс PrisonService)
     * и если окажется, что это число,тогда выбросится исключение SQLSyntaxErrorException, иначе передать строку,
     * состоящую из символов, и всё будет ок.
     */
    @Test
    public void testUpdate() {
        assertThrows(
                SQLException.class,
                () -> prisonerService.update("name", "3", prisoner.getId()));
        assertDoesNotThrow(() -> prisonerService.update("name", "Sugar", prisoner.getId()));
    }

    /**
     * Опсиание теста: получить из БД заключенного и занести его в лист actualPrisoners, после создаётся список actual,
     * в который вносятся все данные заключенного в виде строки и затем сравнивается со значениями в листе expected.
     */
    @Test
    public void testGetAll() {
        setList();
        actualPrisoners = prisonerService.getAll(); // Вызываю тестируемый метод, должен получить тестового заключенного.
        List<String> expected = new ArrayList<>(); // Ожидаемый результат.
        List<String> actual = new ArrayList<>(); // Актуальный результат.
        expected.add(expectedPrisoners.get(0).getId().toString());
        expected.add(expectedPrisoners.get(0).getName());
        expected.add(expectedPrisoners.get(0).getAge().toString());
        expected.add(expectedPrisoners.get(0).getInJail().toString());
        expected.add(expectedPrisoners.get(0).getStartDate());
        expected.add(expectedPrisoners.get(0).getEndDate());
        expected.add(expectedPrisoners.get(0).getVerdict().toString());

        actual.add(actualPrisoners.get(0).getId().toString());
        actual.add(actualPrisoners.get(0).getName());
        actual.add(actualPrisoners.get(0).getAge().toString());
        actual.add(actualPrisoners.get(0).getInJail().toString());
        actual.add(actualPrisoners.get(0).getStartDate());
        actual.add(actualPrisoners.get(0).getEndDate());
        actual.add(actualPrisoners.get(0).getVerdict().toString());
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Описание теста: Перед тестом создается 1 запись о заключенно затем удаляется методом delete и проверка на
     * выброс исключения IndexOutOfBoundsException.
     */
    @Test
    public void testDelete() throws SQLException {
        prisonerService.delete(prisoner.getId()); // Вызов тестируемого метода.
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> prisonerService.getAll().get(0));
        prisonerService.createNew(prisoner);
        assertDoesNotThrow(() -> prisonerService.getAll().get(0));
    }

    @Test
    public void testCheckString() {
        String someStringChar = "Name";
        String someStringNumber = "3";

        Assertions.assertEquals(false, prisonerService.checkString(someStringChar)); // Если строка представлена символами, то возвращается false.
        Assertions.assertEquals(true, prisonerService.checkString(someStringNumber)); // Если строка представлена числом типа int, то возвращается true.
    }
}
