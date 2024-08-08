package entity;

import api.OpenAI;
import api.OpenInterface;
import api.YelpFusion;
import api.YelpInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommonDayplanFactoryTest {

    YelpInterface YelpAPI;
    OpenInterface OpenAI;
    BusinessFactory YelpBusinessFactory;
    CommonDayplanFactory commonDayplanFactoryTest;
    User testUser;
    ArrayList<Double> location;

    @BeforeEach
    void setUp() {
        YelpAPI = new YelpFusion();
        OpenAI = new OpenAI();
        YelpBusinessFactory = new YelpBusinessFactory((YelpFusion) YelpAPI);
        commonDayplanFactoryTest = new CommonDayplanFactory();
        location = new ArrayList<>();
        location.add(0.0);
        location.add(0.0);
        testUser = new User("testJosh", "testPass", location);
    }

    @Test
    void setOpenApi() {
        commonDayplanFactoryTest.setOpenApi(OpenAI);
        assertNotNull(commonDayplanFactoryTest.getOpenApi());
    }

    @Test
    void setYelpApi() {
        commonDayplanFactoryTest.setYelpApi(YelpAPI);
        assertNotNull(commonDayplanFactoryTest.getYelpApi());
    }

    @Test
    void setBusinessFactory() {
        commonDayplanFactoryTest.setBusinessFactory(YelpBusinessFactory);
        assertNotNull(commonDayplanFactoryTest.getBusinessFactory());
    }

    @Test
    void createOneMealZeroActivity() {
        OpenInterface OpenAI2 = new OpenAI();
        YelpInterface YelpAPI2 = new YelpFusion();
        commonDayplanFactoryTest.setYelpApi(YelpAPI2);
        commonDayplanFactoryTest.setOpenApi(OpenAI2);
        commonDayplanFactoryTest.setBusinessFactory(YelpBusinessFactory);
        //RUNTIME EXCEPTION WITH THIS ONE TOO FIX SOMETIME
        Dayplan testDayplan = commonDayplanFactoryTest.create(testUser, location, "Toronto", 1, 0,
                "test");
        assertEquals(testDayplan.getNumMeals(), testDayplan.getPlan().size());
    }

    @Test
    void createZeroMealOneActivity() {
        OpenInterface OpenAI2 = new OpenAI();
        YelpInterface YelpAPI2 = new YelpFusion();
        commonDayplanFactoryTest.setYelpApi(YelpAPI2);
        commonDayplanFactoryTest.setOpenApi(OpenAI2);
        commonDayplanFactoryTest.setBusinessFactory(YelpBusinessFactory);
        //RUNTIME EXCEPTION FROM THIS FIX SOMETIME
        Dayplan testDayplan = commonDayplanFactoryTest.create(testUser, location, "Toronto", 0, 1,
                "wahahahah im so funny");
        assertEquals(testDayplan.getnumActivities(), testDayplan.getPlan().size());
    }
}