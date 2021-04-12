package ru.croc.java.school.task7.service;

import ru.croc.java.school.task7.entity.Shop;
import ru.croc.java.school.task7.repository.ShopRepository;

import java.sql.SQLException;
import java.util.List;

/** Выполнение запросов в БД.*/
public class ShopService<T> {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    public Shop createNew(Shop shop) {
        shopRepository.createNew(shop);
        return shop;
    }

    public List<Shop> delete(Integer id) {
        return shopRepository.deleteRecord(id);
    }

    public List<Shop> update(String columnTitle, T result, Integer id) {
        return shopRepository.updateTable(columnTitle, result, id);
    }

    public void edit() {

    }
}
