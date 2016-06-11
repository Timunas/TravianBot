import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by MSI on 10/06/2016.
 */
public class TravianBot{

    /** LOGGER **/
    private Logger LOG = LogManager.getLogger(getClass().getSimpleName());

    /** Private Variables **/
    private WebDriver webDriver;

    /** Constructor **/
    public TravianBot(String serverAddress) throws MalformedURLException {
        LOG.info("Bot initiated!");
        //webDriver = new FirefoxDriver(); NOT WROKING FOR NOW
        //Setting edge driver
        System.setProperty("webdriver.ie.driver", "D:\\SeleniumDrivers\\IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("requireWindowFocus", true);
        webDriver = new InternetExplorerDriver(capabilities);
        webDriver.navigate().to(new URL(serverAddress));
    }

    /** Close Browser **/
    public void closeBrowser(){
        webDriver.close();
    }

    /** Login into Account **/
    public void login(String username, String password){
        webDriver.findElement(By.name("name")).sendKeys(username);
        LOG.info("Username inserted: "+ username);
        webDriver.findElement(By.name("password")).sendKeys(password);
        LOG.info("Password written!");
        webDriver.findElement(By.id("s1")).click();
        LOG.info("Login button clicked!");
    }





}
