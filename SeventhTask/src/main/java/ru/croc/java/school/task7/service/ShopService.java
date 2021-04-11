package ru.croc.java.school.task7.service;

import ru.croc.java.school.task7.entity.Shop;
import ru.croc.java.school.task7.repository.ShopRepository;

import java.sql.SQLException;
import java.util.List;

/** Выполнение запросов в БД.*/
public class ShopService {

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

    public void delete() {
        shopRepository.delete();
    }

    public void update() {
        shopRepository.update();
    }
}
