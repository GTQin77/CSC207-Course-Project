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
        YelpInterface YelpAPI = new YelpFusion();
        OpenInterface OpenAI = new OpenAI();
        BusinessFactory YelpBusinessFactory = new YelpBusinessFactory((YelpFusion) YelpAPI);
        CommonDayplanFactory commonDayplanFactoryTest = new CommonDayplanFactory();
        ArrayList<Double> location = new ArrayList<>();
        location.add(0.0);
        location.add(0.0);
        User testUser = new User("testJosh", "testPass",location);
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
        CommonDayplanFactory testFactory = new CommonDayplanFactory();
        testFactory.setYelpApi(YelpAPI);
        testFactory.setOpenApi(OpenAI);
        testFactory.setBusinessFactory(YelpBusinessFactory);
        Dayplan testDayplan = testFactory.create(testUser,location,"Toronto",1,0,
                "wahahahah im so funny");
        assertEquals(testDayplan.getNumMeals(), testDayplan.getPlan().size());
    }

    @Test
    void createZeroMealOneActivity() {
        CommonDayplanFactory testFactory = new CommonDayplanFactory();
        testFactory.setYelpApi(YelpAPI);
        testFactory.setOpenApi(OpenAI);
        testFactory.setBusinessFactory(YelpBusinessFactory);
        Dayplan testDayplan = testFactory.create(testUser,location,"Toronto",0,1,
                "wahahahah im so funny");
        assertEquals(testDayplan.getnumActivities(), testDayplan.getPlan().size());
    }
}