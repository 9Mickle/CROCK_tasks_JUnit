package ru.croc.java.school.task7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.task7.dbprovider.DataSourceProvider;
import ru.croc.java.school.task7.entity.Prisoner;
import ru.croc.java.school.task7.repository.PrisonerRepository;
import ru.croc.java.school.task7.service.Calculations;
import ru.croc.java.school.task7.service.PrisonerService;

import java.io.IOException;

public class TestPrisonerService {

    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    PrisonerRepository prisonerRepository = new PrisonerRepository(dataSourceProvider.getDataSource());
    PrisonerService prisonerService = new PrisonerService(prisonerRepository);
    Calculations calculations = new Calculations(prisonerRepository);
    Prisoner prisoner = new Prisoner(1, "Jhon", 37, true, 2);

    public TestPrisonerService() throws IOException {
    }

    @Test
    void createNew() {
        prisonerService.createNew(prisoner);
    }

}
