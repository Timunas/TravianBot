import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class implements methods that help to navigate through user account
 * ______________________
 * created by Joao Suzana
 */
public class NavigationBroker {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * New NavigationBroker instance
     *
     * @param driver main webdriver created by TravianBot class
     * @param wait   main webdriverwait created by TravianBot class
     */
    public NavigationBroker(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Changes to resources view showing cropland, iron mine, clay pit and woodcutter levels
     */
    public void goToResourcesView() {
        //Find resources button class
        WebElement resourcesClass = this.driver.findElement(By.className("villageResources"));

        //Find link button
        WebElement resourcesButton = resourcesClass.findElement(By.xpath("//a[@accesskey=1]"));

        //Click and wait
        resourcesButton.click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("village1")));
    }

    /**
     * Changes to inside village showing all buildings
     */
    public void goToInsideVillageView() {
        //Find village button class
        WebElement villageClass = this.driver.findElement(By.className("villageBuildings"));

        //Find link button
        WebElement villageButton = villageClass.findElement(By.xpath("//a[@accesskey=2]"));

        //Click and wait
        villageButton.click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("village2")));
    }

    /**
     * Changes to map
     */
    public void goToMap() {
        //Find map button class
        WebElement mapClass = this.driver.findElement(By.className("map"));

        //Find link button
        WebElement mapButton = mapClass.findElement(By.xpath("//a[@accesskey=3]"));

        //Click and wait
        mapButton.click();
        this.wait.until(ExpectedConditions.
                visibilityOfNestedElementsLocatedBy(By.className("contentContainer"), By.className("map")));
    }

    /**
     * Changes to Statistics View
     */
    public void goToStatistics() {
        //Find statistics button class
        WebElement statisticsClass = this.driver.findElement(By.className("statistics"));

        //Find link button
        WebElement statsButton = statisticsClass.findElement(By.xpath("//a[@accesskey=4]"));

        //Click and wait
        statsButton.click();
        this.wait.until(ExpectedConditions.
                visibilityOfNestedElementsLocatedBy(By.className("contentContainer"), By.className("statistics")));
    }


    /**
     * Changes to Report View
     */
    public void goToReports() {
        //Find reports button class
        WebElement reportsClass = this.driver.findElement(By.className("statistics"));

        //Find link button
        WebElement reportsButton = reportsClass.findElement(By.xpath("//a[@accesskey=5]"));

        //Click and wait
        reportsButton.click();
        this.wait.until(ExpectedConditions.
                visibilityOfNestedElementsLocatedBy(By.className("contentContainer"), By.className("reports")));
    }

    /**
     * Changes to Mail Box
     */
    public void goToMessages() {
        //Find messages button class
        WebElement messagesClass = this.driver.findElement(By.className("messages"));

        //Find link button
        WebElement messagesButton = messagesClass.findElement(By.xpath("//a[@accesskey=6]"));

        //Click and wait
        messagesButton.click();
        this.wait.until(ExpectedConditions.
                visibilityOfNestedElementsLocatedBy(By.className("contentContainer"), By.className("messages")));
    }

}
