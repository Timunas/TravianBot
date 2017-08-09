import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test for resources broker methods
 * ______________________
 * created by Joao Suzana
 */

public class ResourcesTest {

    private static Logger logger = LogManager.getLogger(ResourcesTest.class);

    private TravianBot bot;

    @Before
    public void initBot(){
        this.bot = new TravianBot("http://ts6.travian.com");

        logger.info("Initializing...");
        this.bot.init();
    }

    @Test
    public void coreTest(){
        //Validate initialization
        WebElement element = this.bot.getDriver().findElement(By.className("login"));
        assertThat("Didn't initialized properly!",element,is(notNullValue()));

        //Validate Login
        logger.info("Login in...");
        this.bot.login("Vlad", "1234cenas");
        element = this.bot.getDriver().findElement(By.className("village1"));
        assertThat("Didn't logged in properly!",element,is(notNullValue()));


        this.bot.getNavigationBroker().goToResourcesView();
        element = this.bot.getDriver().findElement(By.className("village1"));
        assertThat("Didn't change to resources view!",element,is(notNullValue()));

        List<String> woodCutterLevels = this.bot.getResourcesBroker().getWoodCutterLevels();
        assertThat("Incorrect woodcutter number!",woodCutterLevels, hasSize(4));
        assertThat("Incorrect woodcutter levels!",woodCutterLevels, containsInAnyOrder("2","0","0","0"));
    }

    @After
    public void destroyBot(){
        logger.info("Destroying Bot...");
        this.bot.close();
        logger.info("Finished Test!");
    }
}
