package Zoo.ComponentsZoo;

/**
 * Вольер {@link Zoo}
 */
public class Enclosure {
    private String name; // Название вольера
    private boolean status; // Статус загрязнения вольера

    /**
     * Создаем название клетки
     *
     * @param name
     */
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

    /**
     * Возвращает текствове представление класса.
     *
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

}
