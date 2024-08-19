package entity;

import api.OpenAI;
import api.YelpFusion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CommonRefreshBusinessFactoryTest {

    private YelpFusion yelpFusion;
    private YelpBusinessFactory yelpBusinessFactory;
    private OpenAI openAI;
    private CommonRefreshBusinessFactory commonRefreshBusinessFactory;
    private Dayplan dayplan;
    private User testUser;
    private CommonDayplanFactory commonDayplanFactory;
    private String businessName;

    @BeforeEach
    void setUp() {
        commonDayplanFactory = new CommonDayplanFactory();
        openAI = new OpenAI();
        yelpFusion = new YelpFusion();
        yelpBusinessFactory = new YelpBusinessFactory(yelpFusion);
        commonDayplanFactory.setBusinessFactory(yelpBusinessFactory);
        commonDayplanFactory.setOpenApi(openAI);
        commonDayplanFactory.setYelpApi(yelpFusion);
        commonRefreshBusinessFactory = new CommonRefreshBusinessFactory(openAI,yelpFusion, yelpBusinessFactory);
        testUser = new User("test","test",new ArrayList<>(2){{add(0.0);add(0.0);}});
        ArrayList<Double> location = new ArrayList<>() {{
            add(0.0);
            add(0.0);
        }};
        dayplan = commonDayplanFactory.create(testUser, location, "Toronto", 1, 0, "Fun");
        String businessName = dayplan.getBusinessNames().getFirst();
    }

    @Test
    void testRefreshBusiness() {
        Map.Entry<Business, String> newBusiness= commonRefreshBusinessFactory.generateNewBusiness(dayplan, "Meal");
        assertNotEquals(businessName, newBusiness.getValue());
    }


    @Test
    void setOpenApi() {
        openAI = new OpenAI();
        commonRefreshBusinessFactory.setOpenApi(openAI);
        assertNotNull(commonRefreshBusinessFactory.getOpenApi());
    }

    @Test
    void setYelpApi() {
        yelpFusion = new YelpFusion();
        commonRefreshBusinessFactory.setYelpApi(yelpFusion);
        assertNotNull(commonRefreshBusinessFactory.getYelpApi());
    }


    @Test
    void isMeal() {
        String str = "meal";
        assertTrue(commonRefreshBusinessFactory.isMeal(str));

    }
}