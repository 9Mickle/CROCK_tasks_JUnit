package ru.croc.java.school.task7.service;

import ru.croc.java.school.task7.repository.PrisonerRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс для работы с датой, расчета срока заключенного и выпуска его из тюрьмы.
 */
public class Calculations {

    private final PrisonerRepository prisonerRepository;

    public Calculations(PrisonerRepository prisonerRepository) throws IOException {
        this.prisonerRepository = prisonerRepository;
    }

    /**
     * Расчитать срок заключенному.
     * Метод принимает дату начала срока, прибавляет к году этой даты приговор(verdict), вынесенный в годах,
     * и возвращает мапу с двумя датами, началом срока и его концом.
     *
     * @param id идентификатор.
     * @param year год начала срока.
     * @param month месяц начала срока.
     * @param day день начала срока.
     * @return мапу с двумя датами,
     */
    public Map<String, String> calcTerm(Integer id, Integer year, Integer month, Integer day) throws SQLException {
        LocalDate incarcerationDate = LocalDate.of(year, month, day); // Установил дату начала срока.
        LocalDate releaseDate = LocalDate.of(year + getVerdict(id), month, day); // Установил дату конца срока.

        String startDate = incarcerationDate.toString();
        String endDate = releaseDate.toString();

        Boolean checkStartDate = checkString(startDate);
        Boolean checkEndDate = checkString(endDate);
        // Обновление таблицы
        prisonerRepository.updateTable("startDate", startDate, checkStartDate, id);
        prisonerRepository.updateTable("endDate", endDate, checkEndDate, id);

        Map<String, String> dates = new HashMap<>();
        dates.put("start", startDate);
        dates.put("end", endDate);
        return dates;
    }

    /**
     * Метод рассчета и удаления заключенного из БД.
     * Метод принимает id заключенного, получает: 'сегодняшнюю' дату и дату конца срока у заключенного.
     * Производится грубое сравнение дат: если даты равны, то происходит освобождение заключенного,
     * т.е. статус inJail = false, к его возрасту добавляется значение приговора(verdict) и verdict = 0.
     *
     * @param id идентификатор.
     */
    public void releasePrisoner(Integer id) throws SQLException {
        LocalDate ldt = LocalDate.now(); // Создаем дату сегодняшнего дня.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formatter = format.format(ldt); // Парсим её в строку.
        String endDate = getEndDate(id); // Получаем дату конца срока.
        if (formatter.equals(endDate)) {
                String statusJail = "false";
                String age = getAge(id) + getVerdict(id).toString();
                String verdict = "0";

                Boolean checkStatus = checkString(statusJail);
                Boolean checkAge = checkString(age);
                Boolean checkVerdict = checkString(verdict);

            prisonerRepository.updateTable("inJail", statusJail, checkStatus, id);
            prisonerRepository.updateTable("age", age, checkAge, id);
            prisonerRepository.updateTable("verdict", verdict, checkVerdict, id);

            System.out.println("Заключенный освобожден!");
        } else {
            System.out.println("Заключенный должен ещё отсидеть!");
        }
    }

    /**
     * Метод, проверяющий находится ли заключенный в тюрьме.
     *
     * @param id идентификатор.
     * @return true - если заключенный в тюрьме, false - если заключенный освобожден.
     */
    public Boolean checkInJail(Integer id) {
        return prisonerRepository.getPrisoner(id).get(0).getInJail();
    }

    /**
     * Метод, возвращающий возраст заключенного.
     *
     * @param id идентификатор.
     * @return возраст.
     */
    public Integer getAge(Integer id) {
        return prisonerRepository.getPrisoner(id).get(0).getAge();
    }

    /**
     * Метод возвращающий приговор заключенного.
     *
     * @param id идентификатор.
     * @return срок.
     */
    public Integer getVerdict(Integer id) {
        return prisonerRepository.getPrisoner(id).get(0).getVerdict();
    }

    /**
     * Метод, возвращающий имя заключенного.
     *
     * @param id идентификатор.
     * @return имя.
     */
    public String getName(Integer id) {
        return prisonerRepository.getPrisoner(id).get(0).getName();
    }

    /**
     * Метод, возвращающий дату начала срока заключенного.
     *
     * @param id идентификатор.
     * @return строку в виде даты начала срока.
     */
    public String getStartDate(Integer id) {
        return prisonerRepository.getPrisoner(id).get(0).getStartDate();
    }

    /**
     * Метод, возвращающий дату конца срока заключенного.
     *
     * @param id идентификатор.
     * @return строку в виде даты конца срока.
     */
    public String getEndDate(Integer id) {
        return prisonerRepository.getPrisoner(id).get(0).getEndDate();
    }

    private Boolean checkString(String someString) {
        try {
            Integer.parseInt(someString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}