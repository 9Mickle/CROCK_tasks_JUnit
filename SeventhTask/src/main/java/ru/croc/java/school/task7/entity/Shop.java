package ru.croc.java.school.task7.entity;

import java.util.Date;

/**
 * Магазин.
 */
public class Shop {

//    /** Название.*/
//    private String name;
//
//    /** Площадь магазина.*/
//    private Integer storeArea;
//
//    /** Статус открыт/закрыт.*/
//    private Boolean statusWork;
//
//    /** Время, когда магазин открыт/закрыт.*/
//    private Date date;

    private Integer id;
    private String title;

    public Shop(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

//    public Shop(String name, Integer storeArea, Boolean statusWork, Date date) {
//        this.name = name;
//        this.storeArea = storeArea;
//        this.statusWork = statusWork;
//        this.date = date;
//    }
//
//    public Integer getStoreArea() {
//        return storeArea;
//    }
//
//    public void setStoreArea(Integer storeArea) {
//        this.storeArea = storeArea;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Boolean getStatusWork() {
//        return statusWork;
//    }
//
//    public void setStatusWork(Boolean statusWork) {
//        this.statusWork = statusWork;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    @Override
//    public String toString() {
//        return "Shop{" +
//                "name='" + name + '\'' +
//                ", storeArea=" + storeArea +
//                ", statusWork=" + statusWork +
//                ", date=" + date +
//                '}';
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
