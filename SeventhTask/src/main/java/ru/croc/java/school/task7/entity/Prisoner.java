package ru.croc.java.school.task7.entity;

public class Prisoner {

    /** Идентификатор.*/
    private Integer id;

    /** Имя.*/
    private String name;

    /** Возраст.*/
    private Integer age;

    /** Статус, в тюрьме или на свободе.*/
    private Boolean inJail;

    /** Дата попадания в тюрьму.*/
    private String startDate;

    /** Дата выхода из тюрьмы.*/
    private String endDate;

    /** Приговор заключенному в годах.*/
    private Integer verdict;

    /**
     * Конструктор с уже извевестными датами начала и конца срока.
     *
     * @param id идентификатор заключенного.
     * @param name имя.
     * @param age возраст.
     * @param inJail статус.
     * @param startDate дата начала срока.
     * @param endDate дата конца срока.
     * @param verdict вынесенный вердикт в годах.
     */
    public Prisoner(Integer id, String name, Integer age, Boolean inJail, String startDate, String endDate, Integer verdict) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.inJail = inJail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.verdict = verdict;
    }

    /**
     * Конструктор для необходимости ввести новоприбывших в тюрьму с ещё пока неизвестной датой начала и конца срока.
     *
     * @param id идентификатор.
     * @param name имя.
     * @param age возраст.
     * @param inJail статус.
     * @param verdict приговор.
     */
    public Prisoner(Integer id, String name, Integer age, Boolean inJail, Integer verdict) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.inJail = inJail;
        this.verdict = verdict;
    }

    public Integer getVerdict() {
        return verdict;
    }

    public void setVerdict(Integer verdict) {
        this.verdict = verdict;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getInJail() {
        return inJail;
    }

    public void setInJail(Boolean inJail) {
        this.inJail = inJail;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Prisoner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", inJail=" + inJail +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", verdict=" + verdict +
                '}';
    }
}
