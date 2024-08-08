package data_access;

import entity.Dayplan;
import entity.User;

import java.io.*;
import java.io.File;
import java.util.ArrayList;

public class PrevPlanDataAccessObject implements PrevPlanDataAccessInterface{

    private File csvFile;
    private String csvPath;
    private User user;

    // csvFile attribute has no individual setter
    public File getcsvFile(){return this.csvFile;}

    // Method is a setter for both csvPath and csvFile
    public void setcsvPathAndcsvFile(String csvPath){
        this.csvPath = csvPath;
        this.csvFile = new File(csvPath);
    }

    public void setUser(User user){this.user = user;}

    public User getUser(){return this.user;}

    @Override
    public String getPreviousDayplan(User user) {
        String separator = ";";
        String userName = user.getUserName();
        String prevDayplan = "";

        try (BufferedReader br = new BufferedReader(new FileReader(this.getcsvFile()))) {
            String line = br.readLine();
            while (line != null) {
                line = br.readLine();
                String[] values = line.split(separator);
                if (userName.equals(values[0])) {
                    String value = values[3];
                    System.out.println(value);
                    prevDayplan = value;
                    return prevDayplan;
                }
            }
        } catch (IOException ioException) {
            System.out.println("File not found");
        }
        return prevDayplan;
    }
}
