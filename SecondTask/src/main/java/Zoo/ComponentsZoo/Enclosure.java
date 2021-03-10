package Zoo.ComponentsZoo;


import java.sql.SQLOutput;

/**
 * Вольер
 */
public class Enclosure {
    private String name;
    private boolean status;

    public Enclosure(String name) {
        this.name = name;
    }

    public void setCleanStatus(boolean status) {
        this.status = status;
    }

    public boolean isCleanStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
