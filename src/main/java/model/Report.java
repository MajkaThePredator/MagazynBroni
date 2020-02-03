package model;
import java.sql.Timestamp;

public class Report {
    private int id;
    private Timestamp date;
    private String report;

    public Report(int id, Timestamp date, String report){
        this.id = id;
        this.date = date;
        this.report = report;
    }

    public Report(int id, String report){
        this.id = id;
        this.report = report;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = this.date;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
