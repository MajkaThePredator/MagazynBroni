package model;

public class Account {
    private int id;
    private String rank;
    private String name;
    private String surname;
    private String password;


    public Account(int id, String rank, String name, String surname, String password){
        this.id=id;
        this.rank = rank;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    @Override
    public String toString() {
        return rank +" "+ name +" "+ surname;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
