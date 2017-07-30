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
 * Test for login method
 * ______________________
 * created by Joao Suzana
 */

public class LoginTest {

    private static Logger logger = LogManager.getLogger(LoginTest.class);

    private TravianBot bot;

    @Before
    public void initBot(){
        this.bot = new TravianBot("http://ts6.travian.com");

        logger.info("Initializing...");
        this.bot.init();
    }

    @Test
    public void loginTest(){

        WebElement element = this.bot.getDriver().findElement(By.className("login"));
        assertThat("Didn't initialized properly!",element,is(notNullValue()));

        logger.info("Login in...");
        this.bot.login("Vlad", "1234cenas");

        element = this.bot.getDriver().findElement(By.className("village1"));
        assertThat("Didn't logged in properly!",element,is(notNullValue()));
    }

    @After
    public void destroyBot(){
        logger.info("Destroying Bot...");
        this.bot.close();
        logger.info("Finished Test!");
    }
}
