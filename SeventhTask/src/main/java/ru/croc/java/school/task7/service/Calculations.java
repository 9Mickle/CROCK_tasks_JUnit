package ru.croc.java.school.task7.service;

import ru.croc.java.school.task7.repository.PrisonerRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public Map<String, String> calcTerm(Integer id, Integer year, Integer month, Integer day) {
        int verdict = getVerdict(id);
        Calendar startDate = new GregorianCalendar(); // Дата начала срока заключения.
        Calendar endDate; // Дата конца срока заключения.
        startDate.set(Calendar.YEAR, year);
        startDate.set(Calendar.MONTH, month);
        startDate.set(Calendar.DAY_OF_MONTH, day);

        String incarcerationDate = startDate.getTime().toString(); // Перевод даты в строковый формат.
        startDate.roll(Calendar.YEAR, + verdict); // Прибавление verdict'а к году начала заключения.
        endDate = startDate;
        String releaseDate = endDate.getTime().toString(); // Перевод даты в строковый формат.

        Map<String, String> dates = new HashMap<>();
        dates.put("start", incarcerationDate);
        dates.put("end", releaseDate);
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
    public void releasePrison(Integer id) throws ParseException, SQLException {
        DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Calendar todayDate = new GregorianCalendar(); // Сегодняшняя дата.
        Calendar endDate = new GregorianCalendar();

        String releaseDate; // Дата выхода.
        String date = todayDate.getTime().toString(); // Перевод даты в строку.
        todayDate.setTime(format.parse(date)); // Парсинг сегодняшней даты в нужный формат.
        releaseDate = getEndDate(id); // Получить дату конца срока.
        endDate.setTime(format.parse(releaseDate));

        int todayYear = todayDate.get(Calendar.YEAR);
        int todayMonth = todayDate.get(Calendar.MONTH);
        int todayDay = todayDate.get(Calendar.DAY_OF_MONTH);

        int endYear = endDate.get(Calendar.YEAR);
        int endMonth = endDate.get(Calendar.MONTH);
        int endDay = endDate.get(Calendar.DAY_OF_MONTH);

        // Сравнение дат, если даты равны, то меняем информацию в столбцах заключенного.
        if ((todayYear == endYear && todayMonth == endMonth && todayDay == endDay)) {
            String statusJail = "false";
            String age = getAge(id) + getVerdict(id).toString();
            String verdict = "0";

            Boolean checkStatus = checkString(statusJail);
            Boolean checkAge = checkString(age);
            Boolean checkVerdict = checkString(verdict);

            prisonerRepository.updateTable("inJail", statusJail, checkStatus, id);
            prisonerRepository.updateTable("age", age, checkAge, id);
            prisonerRepository.updateTable("verdict", verdict, checkVerdict, id);

            System.out.println("Заключенный №" + id + " завершил срок, можно удалить из БД");
        } else {
            System.out.println("Заключенный № " + id + " ещё должен отсидеть");
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