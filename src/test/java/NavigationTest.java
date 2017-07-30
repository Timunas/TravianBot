import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test for navigation methods
 * ______________________
 * created by Joao Suzana
 */

public class NavigationTest {

    private static Logger logger = LogManager.getLogger(NavigationTest.class);

    private TravianBot bot;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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

        this.bot.getNavigationBroker().goToInsideVillageView();
        element = this.bot.getDriver().findElement(By.className("village2"));
        assertThat("Didn't change to inside village view!",element,is(notNullValue()));

        this.bot.getNavigationBroker().goToMap();
        element = this.bot.getDriver().findElement(By.className("map"));
        assertThat("Didn't change to Map!",element,is(notNullValue()));

        this.bot.getNavigationBroker().goToReports();
        element = this.bot.getDriver().findElement(By.className("reports"));
        assertThat("Didn't change to reports view!",element,is(notNullValue()));

        this.bot.getNavigationBroker().goToStatistics();
        element = this.bot.getDriver().findElement(By.className("statistics"));
        assertThat("Didn't change to statistics view!",element,is(notNullValue()));

        this.bot.getNavigationBroker().goToMessages();
        element = this.bot.getDriver().findElement(By.className("messages"));
        assertThat("Didn't change to messages!",element,is(notNullValue()));

        this.bot.getNavigationBroker().goToReports();
        element = this.bot.getDriver().findElement(By.className("reports"));
        assertThat("Didn't change to reports view!",element,is(notNullValue()));

        this.bot.getNavigationBroker().goToReports();
        element = this.bot.getDriver().findElement(By.className("reports"));
        assertThat("Didn't change to reports view!",element,is(notNullValue()));

        int number = this.bot.getNavigationBroker().getVillageNumber();
        assertThat("Wrong village number",number,is(1));

        this.bot.getNavigationBroker().goToMainVillage();
    }

    @After
    public void destroyBot(){
        logger.info("Destroying Bot...");
        this.bot.close();
        logger.info("Finished Test!");
    }
}
