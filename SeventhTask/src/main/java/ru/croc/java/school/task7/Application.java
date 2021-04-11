package ru.croc.java.school.task7;

import ru.croc.java.school.task7.dbprovider.DataSourceProvider;
import ru.croc.java.school.task7.entity.Shop;
import ru.croc.java.school.task7.repository.ShopRepository;
import ru.croc.java.school.task7.service.ShopService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {

        Date date = new Date();

        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        ShopRepository shopRepository = new ShopRepository(dataSourceProvider.getDataSource());
        ShopService shopService = new ShopService(shopRepository);

        //Shop shop = shopService.createNew(new Shop(2, "test"));
        List<Shop> shops = new ArrayList<>();
        shops = shopService.getAll();
        shopService.getAll().forEach(System.out::println);
    }
}
