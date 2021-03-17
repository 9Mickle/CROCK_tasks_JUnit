package ru.croc.javaschool.task3;

import ru.croc.javaschool.task3.classShells.Engine;
import ru.croc.javaschool.task3.classShells.StatusRent;
import ru.croc.javaschool.task3.model.*;

import java.util.ArrayList;

/**
 * Точка входа в программу.
 */
public class Application {

    private static ArrayList<Bicycle> bicycles;
    private static ArrayList<Scooter> scooters;
    private static ArrayList<Auto> autos;
    private static ArrayList<Plane> planes;
    private static ArrayList<Yacht> yachts;
    private static ArrayList<MotorBike> motorBikes;

    public static void main(String[] args) {

        /**
         * Создаем списки транспорта имеющегося в наличии.
         */
        bicycles = new ArrayList<>(); // Велосипеды.
        scooters = new ArrayList<>(); // Самокаты.
        autos = new ArrayList<>(); // Машины.
        planes = new ArrayList<>(); // Самолеты.
        yachts = new ArrayList<>(); // Яхты.
        motorBikes = new ArrayList<>(); // Мотоциклы.

        bicycles.add(new Bicycle("Велосипед", "Forward", 1, StatusRent.ONSTOCK));

        scooters.add(new Scooter("Самокат", "Novatrack" , 50, StatusRent.ONSTOCK));

        planes.add(new Plane("Самолет", "Фалкон", 400, StatusRent.ONSTOCK));

        yachts.add(new Yacht("Яхта", "LVMH", 300, StatusRent.ONSTOCK));

        autos.add(new Auto("Электрокар","Tesla", 200, Engine.ELECTRIC, StatusRent.ONSTOCK));
        autos.add(new Auto("Машина","Lada", 201, Engine.ISE, StatusRent.ONSTOCK));

        motorBikes.add(new MotorBike("Электроцикл", "Honda", 100, Engine.ELECTRIC, StatusRent.ONSTOCK));
        motorBikes.add(new MotorBike("Мотоцикл", "Yamaha", 101, Engine.ISE, StatusRent.ONSTOCK));

        bicycles.get(0).rentOut(); //Сдали велосипед в аренду
        bicycles.get(0).drive();

        planes.get(0).rentOut(); //Сдали самолёт в аренду
        planes.get(0).drive();

        yachts.get(0).rentOut(); //Сдали яхту в аренду
        yachts.get(0).drive();

        autos.get(0).rentOut(); //Сдали машину в аренду
        autos.get(0).drive();

        motorBikes.get(1).rentOut(); //Сдали мотоцикл в аренду
        motorBikes.get(0).drive();

        System.out.println("----------------------------------------------------------");

        motorBikes.get(1).removeFromRent(); //Сняли мотоцикл с аренды
        motorBikes.get(1).fix(); //Починили мотоцикл

        System.out.println("----------------------------------------------------------");

        autos.get(0).removeFromRent(); //Сняли машину с аренды
        autos.get(0).fix(); //Починили машину

        System.out.println("----------------------------------------------------------");

        planes.get(0).removeFromRent(); //Сняли самолёт с аренды
        planes.get(0).fix(); //Починили самолёт

        System.out.println("----------------------------------------------------------");

        bicycles.get(0).rentOut(); //Попытались снова снять велосипед(0) в аренду, но его ещё не вернули, поэтому снять его нельзя

        System.out.println("----------------------------------------------------------");

        System.out.println(scooters.get(0).getFullName()); //Получили полную информацию о самокате(0)
    }
}
