package Zoo.ComponentsZoo;


/**
 * Сотрудник
 */
public class Employee {
    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public void feed(String animal) {
        System.out.println("Сотрудник " + this.name + " покормил животного " + animal);
    }

    @Override
    public String toString() {
        return name;
    }
}
