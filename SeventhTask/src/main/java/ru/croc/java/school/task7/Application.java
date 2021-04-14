package ru.croc.java.school.task7;

import ru.croc.java.school.task7.dbprovider.DataSourceProvider;
import ru.croc.java.school.task7.entity.Prisoner;
import ru.croc.java.school.task7.repository.PrisonerRepository;
import ru.croc.java.school.task7.service.Calculations;
import ru.croc.java.school.task7.service.PrisonerService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application {

    public static void main(String[] args) throws IOException, ParseException {

        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        PrisonerRepository prisonerRepository = new PrisonerRepository(dataSourceProvider.getDataSource());
        PrisonerService prisonerService = new PrisonerService(prisonerRepository);
        Calculations calculations = new Calculations(prisonerRepository);

        Map<String, String> dates = new HashMap<>();
        //Prisoner prisoner1 = prisonerService.createNew(new Prisoner(1, "John", 30, true, 2));
//        Prisoner prisoner2 = prisonerService.createNew(new Prisoner(2, "Victor", 24, true, 10));
//        Prisoner prisoner3 = prisonerService.createNew(new Prisoner(3, "Alex", 56, true, 20));
//        Prisoner prisoner4 = prisonerService.createNew(new Prisoner(4, "Roman", 23, true, 4));
        //dates = calculations.calcTerm(1, 2019, 3, 14); // Вышел
        //dates = calculations.calcTerm(2, 2017, 4, 14); // нет
        //dates = calculations.calcTerm(3, 2020, 5, 13); // нет
        //dates = calculations.calcTerm(4, 2018, 4, 13); // Вышел
        //prisonerService.getAll().forEach(System.out::println);
        //prisonerService.update("startDate", dates.get("start"), 1);
        //prisonerService.update("startDate", dates.get("start"), 2);
        //prisonerService.update("startDate", dates.get("start"), 3);
        //prisonerService.update("startDate", dates.get("start"), 4);
        //prisonerService.update("endDate", dates.get("end"), 1);
        //prisonerService.update("endDate", dates.get("end"), 2);
        //prisonerService.update("endDate", dates.get("end"), 3);
        //prisonerService.update("endDate", dates.get("end"), 4);
        //prisonerService.delete(1);
        calculations.releasePrison(1);
//        calculations.releasePrison(2);
//        calculations.releasePrison(3);
//        calculations.releasePrison(4);
//        System.out.println();
        prisonerService.getAll().forEach(System.out::println);
    }
}
