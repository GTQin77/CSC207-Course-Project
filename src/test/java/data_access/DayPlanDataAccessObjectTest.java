package data_access;

import entity.Business;
import entity.User;
import entity.Dayplan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DayPlanDataAccessObjectTest {

    private DayPlanDataAccessObject dayDAO;
    private File tempFile;
    private User mockUser;
    private Dayplan mockDayplan;
    private Business mockBusiness;

    @BeforeEach
    void setUp() throws IOException {
        dayDAO = new DayPlanDataAccessObject();

        // Create a temporary file to use as the csv file
        tempFile = File.createTempFile("test", ".csv");
        dayDAO.setcsvPathAndcsvFile(tempFile.getAbsolutePath());

        mockUser = new User("testuser", "test", new ArrayList<>());
        mockBusiness = new Business("testbusiness", new ArrayList<>(), 10.0, "1234567890", "100.0", 5.0f, "hello?");
        ArrayList<Business> businessList = new ArrayList<>();
        businessList.add(mockBusiness);

        ArrayList<Double> locationFormat = new ArrayList<>();
        locationFormat.add(0.1);
        locationFormat.add(0.2);
        mockDayplan = new Dayplan();
        mockDayplan.setUser(mockUser);
        mockDayplan.setLocation(locationFormat);
        mockDayplan.setDescription("awdawd");
        mockDayplan.setVibe("AWDAWD");
        mockDayplan.setPlan(businessList);
    }

    @Test
    void testSaveDayPlan() throws IOException {
        try (FileWriter fw = new FileWriter(tempFile)) {
            fw.write("userName,location,vibe,Dayplan" + "\n");
        }

        dayDAO.saveDayPlan(mockDayplan);

        StringBuilder actualContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                actualContent.append(line).append(System.lineSeparator());
            }
        }

        String expectedContent = "\ntestuser;\"0.1,0.2\";AWDAWD;testbusiness,,10.0,1234567890,100.0,5.0";

        assertEquals(expectedContent.trim(), actualContent.toString().trim());
    }

    @Test
    void testBusinessToString() {
        String expected = "testbusiness,[],10.0,1234567890,100.0,5.0";
        String actual = dayDAO.businessToString(mockBusiness);

        assertEquals(expected, actual);
    }
}