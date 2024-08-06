package data_access;

import entity.Dayplan;
import entity.User;

import java.io.*;
import java.io.File;
import java.util.ArrayList;

public class PrevPlanDataAccessObject {

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

    public String getPreviousDayplan(User user) {
        String separator = ";";
        String userName = user.getUserName();
        String prevDayplan = "";

        try (BufferedReader br = new BufferedReader(new FileReader(this.getcsvFile()))) {
            String line = br.readLine();
            //System.out.println(line);
            while (line != null) {
                line = br.readLine();
                //System.out.println(line);
                // parse csv into its separator
                String[] values = line.split(separator);
                if (userName.equals(values[0])) {
                    String value = values[3];
                    System.out.println(value);
                    prevDayplan = value;
                }
            }
        } catch (IOException ioException) {
            System.out.println("File not found");
        }
        return prevDayplan;
    }

    public static void main(String[] args) {

        ArrayList<Double> location = new ArrayList<>(2);

        User user = new User("amelia", "hello",location);
        PrevPlanDataAccessObject prevPlanDataAccessObject = new PrevPlanDataAccessObject();
        prevPlanDataAccessObject.setcsvPathAndcsvFile("src/main/resources/DayplanDatabase.csv");
        System.out.println(prevPlanDataAccessObject.getPreviousDayplan(user));
    }
}
