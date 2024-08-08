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

        mockUser = new User("testuser", "password", new ArrayList<>());
        ArrayList<Double> testLocation = new ArrayList<Double>();
        testLocation.add(1.0);
        testLocation.add(2.0);
        mockBusiness = new Business("testbusiness", testLocation, 10.0, "1234567890", "100.0", 5.0f, "meal");
        ArrayList<Business> businessList = new ArrayList<>();
        businessList.add(mockBusiness);

        ArrayList<Double> locationFormat = new ArrayList<>();
        locationFormat.add(0.1);
        locationFormat.add(0.2);
        mockDayplan = new Dayplan();
        mockDayplan.setUser(mockUser);
        mockDayplan.setLocation(locationFormat);
        mockDayplan.setDescription("description");
        mockDayplan.setVibe("vibe");
        mockDayplan.setPlan(businessList);
    }

    @Test
    void testSaveDayPlan() throws IOException {
        File tempFile = new File("src/test/test_resources/tempDB.csv");
        try (FileWriter fw = new FileWriter(tempFile)) {
            fw.write("userName,location,vibe,Dayplan" + "\n");
        }

        dayDAO.saveDayPlan(mockDayplan);

        String last = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line = reader.readLine();
            while (line != null) {
                last = line;
                line = reader.readLine();
            }
        }

        String expectedContent = "testuser;0.1,0.2;vibe;testbusiness,1.0,2.0,10.0,1234567890,100.0,5.0";

        assertEquals(last, expectedContent);
    }

    @Test
    void testBusinessToString() {
        String expected = "testbusiness,1.0,2.0,10.0,1234567890,100.0,5.0";
        String actual = dayDAO.businessToString(mockBusiness);

        assertEquals(expected, actual);
    }
}