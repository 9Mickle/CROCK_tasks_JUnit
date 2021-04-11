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

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = timeFormat.format(date);
        String nowDate = dateFormat.format(date);

        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        ShopRepository shopRepository = new ShopRepository(dataSourceProvider.getDataSource());
        ShopService shopService = new ShopService(shopRepository);

        Shop shop = shopService.createNew(new Shop(1, "Magnit", 2000, false, nowDate +" "+ nowTime));
        //Shop shop1 = shopService.createNew(new Shop(3, "test2", false));
        //List<Shop> shops = new ArrayList<>();
        //shops = shopService.getAll();
        shopService.getAll().forEach(System.out::println);
        //shopService.update();
        //shopService.delete();
        //shopService.getAll().forEach(System.out::println);
        //shopService.getAll().forEach(System.out::println);
    }
}
