package ru.croc.java.school.task7;

import ru.croc.java.school.task7.dbprovider.DataSourceProvider;
import ru.croc.java.school.task7.entity.Shop;
import ru.croc.java.school.task7.repository.ShopRepository;
import ru.croc.java.school.task7.service.ShopService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application {

    public static void main(String[] args) throws IOException {

        //TODO добавить свойства магазина

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = timeFormat.format(date);
        String nowDate = dateFormat.format(date);

        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        ShopRepository shopRepository = new ShopRepository(dataSourceProvider.getDataSource());
        ShopService shopService = new ShopService(shopRepository);

        //Shop shop2 = shopService.createNew(new Shop(1, "Magnit", 3000, true, nowDate +" "+ nowTime));
        //Shop shop = shopService.createNew(new Shop(2, "Unker", 3000, true, nowDate +" "+ nowTime));
        List<Shop> shopList;
        //shopList = shopService.update("name", "Unker", 1);
        //shopService.getAll().forEach(System.out::println);
        shopList = shopService.delete(2);
        //shopService.getAll().forEach(System.out::println);
        System.out.println(shopList);
    }
}
