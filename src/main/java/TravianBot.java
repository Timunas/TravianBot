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
    private String serverAddress;

    /** Constructor **/
    public TravianBot(String serverAddress) throws MalformedURLException {
        LOG.info("Bot initiated!");
        //webDriver = new FirefoxDriver(); NOT WROKING FOR NOW
        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumDrivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.navigate().to(new URL(serverAddress));
        this.serverAddress=serverAddress;
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
    public HashMap<WebElement,Integer> getFields(String fieldType){
        List<WebElement> listOfFieldElements = webDriver.findElements(By.xpath("//area[contains(@alt,'"+fieldType+"')]"));
        HashMap<WebElement,Integer> fieldMap = new HashMap<WebElement, Integer>();
        for (WebElement element : listOfFieldElements){
            String level = element.getAttribute("alt");
            fieldMap.put(element,Integer.valueOf(level.substring(level.lastIndexOf(" ")+1)));
        }
        return fieldMap;
    }


    /** Start construction of lowest level field **/
    public void construcField(String fieldType){
        LOG.info("Construction event: "+fieldType);
        HashMap<WebElement,Integer> fieldMap = getFields(fieldType);
        boolean found = false;
        String link="error";
        for(int level=0 ; level<21 ; level++){
            for(WebElement key : fieldMap.keySet()){
                if(fieldMap.get(key)==level){
                    found = true;
                    link = key.getAttribute("href");
                    break;
                }
            }
            if(found)
                break;
        }

        try {
            webDriver.navigate().to(new URL(link));
        } catch (MalformedURLException e) {
            LOG.error("Failed to change to field construction page");
        }

        if(isConstructionPossible()) {
            webDriver.findElement(By.xpath("//button[@class='green build']")).click();
            LOG.info("Construction started...");
        }

    }

    /** Evaluate if construction is possible **/
    public boolean isConstructionPossible(){
        try {
            webDriver.findElement(By.xpath("//button[@class='green build']"));
        }catch(Exception e){
            LOG.info("Can't construct now...");
            return false;
        }
        return true;
    }

}
