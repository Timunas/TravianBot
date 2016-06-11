import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;


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
        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumDrivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
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


    /** Retrieves Hashmap of all field elements and each one level **/
    public HashMap<WebElement,String> getFields(String fieldType){
        List<WebElement> listOfFieldElements = webDriver.findElements(By.xpath("//area[contains(@alt,'"+fieldType+"')]"));
        HashMap<WebElement,String> fieldMap = new HashMap<WebElement, String>();
        for (WebElement element : listOfFieldElements){
            String level = element.getAttribute("alt");
            fieldMap.put(element,level.substring(level.lastIndexOf(" ")+1));
        }
        return fieldMap;
    }



}
