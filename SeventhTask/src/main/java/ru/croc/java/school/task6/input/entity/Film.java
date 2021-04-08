package ru.croc.java.school.task6.input.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Фильм.*/
@XmlRootElement(name = "films")
@XmlType(propOrder = {"title","description","screenwriter", "director"})
public class Film {

    /** Название.*/
    private String title;

    /** Описание.*/
    private String description;

    /** Лист с сценаристами.*/
    private List<Person> screenwriter = new ArrayList<>();

    /** Лист с режиссёрами.*/
    private List<Person> director = new ArrayList<>();

    /** Лист с фильмами.*/
    private List<Film> films = new ArrayList<>();

    public Film(String title, List<Person> screenwriter, List<Person> director) {
        this.title = title;
        this.screenwriter = screenwriter;
        this.director = director;
    }

    public Film() {
    }

    @XmlElementWrapper
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<Film> getFilms() {
        return films;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getScreenwriter() {
        return screenwriter;
    }

    @XmlElement(name = "screenwriter")
    @XmlElementWrapper
    public void setScreenwriter(List<Person> screenwriter) {
        this.screenwriter = screenwriter;
    }

    public List<Person> getDirector() {
        return director;
    }

    @XmlElement(name = "director")
    @XmlElementWrapper
    public void setDirector(List<Person> director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(screenwriter, film.screenwriter) && Objects.equals(director, film.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, screenwriter, director);
    }
}
