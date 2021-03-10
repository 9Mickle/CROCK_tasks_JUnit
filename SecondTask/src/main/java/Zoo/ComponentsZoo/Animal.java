package Zoo.ComponentsZoo;

/**
 * Животное
 */
public class Animal {
    private final String name;
    private boolean feedStatus;
    private boolean diseaseStatus;

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

    @Override
    public String toString() {
        return  name;
    }
}
