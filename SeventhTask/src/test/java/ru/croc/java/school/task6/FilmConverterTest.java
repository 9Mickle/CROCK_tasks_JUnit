package ru.croc.java.school.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.task6.input.entity.Film;
import ru.croc.java.school.task6.input.entity.FilmLibrary;
import ru.croc.java.school.task6.input.entity.Person;
import ru.croc.java.school.task6.parser.JaxbConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmConverterTest {

    /**
     * Конвертация класса InfoAboutFilm в xml.
     */
    @Test
    public void testConvertInfoToXml() throws IOException {

        final FilmLibrary filmLibrary = new FilmLibrary();
        final Film film = new Film();

        List<Film> films = new ArrayList<>();
        List<Person> screenwriter = new ArrayList<>();
        List<Person> director = new ArrayList<>();

        film.setTitle("Название 1");
        film.setDescription("Описание 1");
        screenwriter.add(new Person("Человек 1"));
        director.add(new Person("Человек 2"));
        film.setScreenwriter(screenwriter);
        film.setDirector(director);

        film.setFilms(films);

        final JaxbConverter converter = new JaxbConverter();
        final String xml = converter.toXml(film);
        System.out.println(xml);

        Assertions.assertEquals(film, converter.fromXml(xml, Film.class));
    }
}
