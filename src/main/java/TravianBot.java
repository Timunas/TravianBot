import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by MSI on 10/06/2016.
 */
public class TravianBot{

    /** LOGGER **/
    private Logger LOG = LogManager.getLogger(getClass().getSimpleName());

    /** Private Variables **/
    private final WebDriver webDriver;

    /** Constructor **/
    public TravianBot(String serverAddress) throws MalformedURLException {
        LOG.info("Bot initiated!");
//        System.setProperty("webdriver.gecko.driver","D:\\SeleniumDrivers\\wires.exe");
//        LOG.info(System.getProperty("webdriver.gecko.driver"));
//        webDriver=new MarionetteDriver();
        webDriver = new FirefoxDriver();
        //Set implicit wait for elements
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.navigate().to(new URL(serverAddress));
    }

    /** Close Browser **/
    public void closeBrowser(){
        webDriver.close();
        webDriver.quit();
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

    /** Change to inside Village - Buildings View **/
    public void goToBuildingsView(){
        webDriver.findElement(By.xpath("//li[@class='villageBuildings']/a")).click();
        LOG.info("Changed to Buildings view!");
    }

    /** Change to outside Village - Resources View **/
    public void goToResourcesView(){
        webDriver.findElement(By.xpath("//li[@class='villageResources']/a")).click();
        LOG.info("Changed to Resources view!");
    }

    /** Message Box **/
    public void goToMessageBox(){
        webDriver.findElement(By.xpath("//li[@class='messages']/a")).click();
        LOG.info("Changed to Message Box!");
    }


    /**
     * ===========================================FIELDS================================================================
     */


    /** Retrieves Hashmap of all field elements and each one level **/
    public HashMap<WebElement,Integer> getFields(FieldType fieldType){
        List<WebElement> listOfFieldElements = webDriver.findElements(By.xpath("//area[contains(@alt,'"+fieldType.toString()+"')]"));
        HashMap<WebElement,Integer> fieldMap = new HashMap<WebElement, Integer>();
        for (WebElement element : listOfFieldElements){
            String level = element.getAttribute("alt");
            fieldMap.put(element,Integer.valueOf(level.substring(level.lastIndexOf(" ")+1)));
        }
        return fieldMap;
    }


    /** Start construction of lowest level field **/
    public void construcField(FieldType fieldType){
        LOG.info("Construction event: "+fieldType.toString());
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

    /**
     * ===========================================Buildings=============================================================
     */

    /** Go to View that lists all infrastructures that could be built **/
    public void goToInfrastructures(){
        webDriver.findElement(By.xpath("//div[contains(@class, 'container infrastructure')]//div[@class='content']/a")).click();
        LOG.info("Changed to infrastructure list!");
    }

    /** Go to View that lists all military buildings that could be built **/
    public void goToMilitaryBuildings(){
        webDriver.findElement(By.xpath("//div[contains(@class, 'container military')]//div[@class='content']/a")).click();
        LOG.info("Changed to military buildings list!");
    }

    /** Go to View that lists all resource buildings that could be built **/
    public void goToResourceBuildings(){
        webDriver.findElement(By.xpath("//div[contains(@class, 'container resources')]//div[@class='content']/a")).click();
        LOG.info("Changed to resource buildings list!");
    }

    /** Move to one Empty building Slot **/
    public boolean goToEmptyBuildingSlot(){
        try{
            String link = webDriver.findElement(By.xpath("//map[@id='clickareas']/area[contains(@alt,'zona para construção')]")).getAttribute("href");
            webDriver.navigate().to(new URL(link));
        }catch(Exception e){
            LOG.info("There is no empty slot for building construction...");
            return false;
        }
        return true;
    }

    /** Method that starts construction of a new building **/
    public boolean newBuilding(Object building){

        if(building instanceof Infrastructure)
            goToInfrastructures();
        else if( building instanceof  MilitaryBuilding)
            goToMilitaryBuildings();
        else
            goToResourceBuildings();
        try{
            webDriver.findElement(By.xpath("//div[@class='buildingWrapper']/h2[contains(text(), '"+building.toString()+"')]/../div[@class='contract contractNew contractWrapper']/div[@class='contractLink']/button[@class='green new']")).click();
        }catch(Exception e){
            LOG.info("Couldn't start construction of building: "+building.toString());
            return false;
        }
        return true;
    }

    /** Method that starts to upgrade one building **/
    public boolean upgradeBuilding(Object building){

        try{
            String link = webDriver.findElement(By.xpath("//map[@id='clickareas']/area[contains(@alt,'"+building.toString()+"')]")).getAttribute("href");
            webDriver.navigate().to(new URL(link));
        }catch(Exception e){
            LOG.info("Couldn't start construction of building: "+building.toString());
            return false;
        }

        if(isConstructionPossible()) {
            webDriver.findElement(By.xpath("//button[@class='green build']")).click();
            LOG.info("Started {} construction...",building);
        }
        return true;
    }

}
