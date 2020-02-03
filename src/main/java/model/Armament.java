package model;

public class Armament {
    private int id;
    private String model;
    private String number;
    private String rank;
    private String name;
    private String surname;
    private String note;
    private int platoon;
    private String state;
    private String storageLocation;
    private boolean phisicalCondition;

    public Armament(int id, String model, String number, String rank, String name, String surname, int platoon, String state, String note){
        this.id=id;
        this.model=model;
        this.number = number;
        this.rank = rank;
        this.name = name;
        this.surname = surname;
        this.platoon = platoon;
        this.state = state;
        this.note = note;

    }

    @Override
    public String toString() {
        return "Armament{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", number='" + number + '\'' +
                ", rank='" + rank + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", platoon=" + platoon + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public String getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPlatoon() {
        return platoon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPlatoon(int platoon) {
        this.platoon = platoon;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String isStan() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getState() {
        return state;
    }

    public boolean isPhisicalCondition() {
        return phisicalCondition;
    }

    public void setPhisicalCondition(boolean phisicalCondition) {
        this.phisicalCondition = phisicalCondition;
    }
}
