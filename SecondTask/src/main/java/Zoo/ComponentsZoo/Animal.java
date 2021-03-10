package Zoo.ComponentsZoo;

/**
 * Животное {@link Zoo}
 */
public class Animal {
    private final String name; // Название животного
    private boolean feedStatus; // Статус голода
    private boolean diseaseStatus; // Статус болезни

    /**
     * Создаем навзание животного
     *
     * @param name
     */
    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFeedStatus(boolean feedStatus) {
        this.feedStatus = feedStatus;
    }

    public boolean isFeedStatus() {
        return feedStatus;
    }

    public boolean isDiseaseStatus() {
        return diseaseStatus;
    }

    public void setDiseaseStatus(boolean diseaseStatus) {
        this.diseaseStatus = diseaseStatus;
    }

    /**
     * Возвращает текстовое представление класса.
     *
     * @return текст
     */
    @Override
    public String toString() {
        return  name;
    }
}
