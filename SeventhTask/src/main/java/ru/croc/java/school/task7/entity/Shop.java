package ru.croc.java.school.task7.entity;

import java.util.Date;

/**
 * Магазин.
 */
public class Shop {

    /** Идентификатор.*/
    private Integer id;

    /** Название.*/
    private String name;

    /** Площадь магазина.*/
    private Integer storeArea;

    /** Статус, нужда в товарах.*/
    private Boolean fullness;

    /** Дата поступления товара в магазин.*/
    private String date;

    public Shop(Integer id, String name, Integer storeArea, Boolean fullness, String date) {
        this.id = id;
        this.name = name;
        this.storeArea = storeArea;
        this.fullness = fullness;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(Integer storeArea) {
        this.storeArea = storeArea;
    }

    public Boolean getFullness() {
        return fullness;
    }

    public void setFullness(Boolean fullness) {
        this.fullness = fullness;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", storeArea=" + storeArea +
                ", fullness=" + fullness +
                ", date='" + date + '\'' +
                '}';
    }
}
