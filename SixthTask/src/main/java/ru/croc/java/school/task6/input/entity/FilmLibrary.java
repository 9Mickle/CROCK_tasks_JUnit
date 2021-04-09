package ru.croc.java.school.task6.input.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Библиотека с фильмами.
 */
@XmlRootElement
public class FilmLibrary {

    /** Лист с фильмами.*/
    @XmlElementWrapper(name = "film")
    @XmlElement(name = "film")
    private List<Film> films = new ArrayList<>();

    public FilmLibrary() {
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmLibrary that = (FilmLibrary) o;
        return Objects.equals(films, that.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(films);
    }
}
