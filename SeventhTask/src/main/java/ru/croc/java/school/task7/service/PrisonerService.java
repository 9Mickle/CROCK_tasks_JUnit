package ru.croc.java.school.task7.service;

import ru.croc.java.school.task7.entity.Prisoner;
import ru.croc.java.school.task7.repository.PrisonerRepository;

import java.util.*;

public class PrisonerService {

    private final PrisonerRepository prisonerRepository;

    public PrisonerService(PrisonerRepository prisonerRepository) {
        this.prisonerRepository = prisonerRepository;
    }

    public List<Prisoner> getAll() {
        return prisonerRepository.findAll();
    }

    public Prisoner createNew(Prisoner prisoner) {
        prisonerRepository.createNew(prisoner);
        return prisoner;
    }

    public void delete(Integer id) {
        prisonerRepository.deleteRecord(id);
    }

    public void update(String columnTitle, String result, Integer id) {
        Boolean check = checkString(result); // Записываем результат проверки на число и отдаем в метод updateTable.
        prisonerRepository.updateTable(columnTitle, result, check, id);
    }

    /**
     * Метод проверки строки, является ли она числом.
     * Необходим для корректного формирования SQL запроса в классе PrisonerRepository.
     *
     * @param someString строка.
     * @return true - если строка является числом, false - если строка не является числом.
     */
    private Boolean checkString(String someString) {
        try {
            Integer.parseInt(someString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
