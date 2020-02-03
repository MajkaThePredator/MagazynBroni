package utils;

import database.dbutils.SelectManager;
import model.Account;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class ReportManager {
    SelectManager selectManager = new SelectManager();
    public static String report;

    public String createReport(String person){
        Timestamp timestamp = selectManager.selectLastRowFromNotes().getDate();
        String time = new SimpleDateFormat("'dnia 'dd.MM.yyyy ' o godz. ' HH:mm").format(timestamp);
        String uwaga = selectManager.selectLastRowFromNotes().getNote();

            if(!uwaga.isEmpty()){
                report = "W celu kontroli magazynu broni, "+time+" otworzono pododdziałowy magazyn broni." +
                        " \nMagaztn zamknął i oplombował "+person+ "\nUwagi: \n"+ uwaga;
            }
            else if(uwaga.isEmpty()){
                report = "W celu kontroli magazynu broni, "+time+" otworzono pododdziałowy magazyn broni." +
                        " \nMagaztn zamknął i oplombował "+person+ "\nBrak uwag.";
            }

        return report;
    }


    public String createReport(Account presentNcoAccount, Account newNcoAccount){
        try {
            Timestamp timestamp = selectManager.selectLastRowFromNotes().getDate();
            String time = new SimpleDateFormat("'dnia 'dd.MM.yyyy ' o godz. ' HH:mm").format(timestamp);
            String note = selectManager.selectLastRowFromNotes().getNote();
            String presentNco = presentNcoAccount.toString();
            String newNco = newNcoAccount.toString();
            String person = "st. chor. sztab. Marcin KAZIMIEROWSKI referentka nr ZE 148";

            if(!note.isEmpty()){
                report = "W celu przekazania magazynu broni, "+time+" otworzono pododdziałowy magazyn broni." +
                        " \nMagaztn zamknął i oplombował "+person+ "\nUwagi: \n"+ note + "\n\nZdający służbę:          "+ presentNco + "\nObejmujący służbę:   "+ newNco;
            }
            else if(note.isEmpty()){
                report = "W celu przekazania magazynu broni, "+time+" otworzono pododdziałowy magazyn broni." +
                        " \nMagaztn zamknął i oplombował "+person+ "\nBrak uwag" + "\n\nZdający służbę:          "+ presentNco + "\nObejmujący służbę:   "+ newNco;
            }
        }catch (Exception e){
            System.err.println("create report in meldunekManager");
        }
    return report;
    }
}
