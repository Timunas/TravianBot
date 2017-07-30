import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Travian Bot - main class
 * ______________________
 * created by Joao Suzana
 */
public class TravianBot {

    private final long TIMEOUT_IN_SECONDS = 30;

    private String serverAddress;
    private WebDriver driver;
    private boolean loggedIn;
    private WebDriverWait wait;
    private NavigationBroker navigator;


    /**
     * New Travian Bot instance
     *
     * @param serverAddress travian server address
     */
    public TravianBot(String serverAddress) {
        this.serverAddress = serverAddress;
        this.loggedIn = false;
    }

    /**
     * Get firefox driver
     *
     * @return returns null if no driver have been initialized
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * Creates firefox driver and tries to go to travian server
     */
    public void init() {

        //Setting driver path
        System.setProperty("webdriver.gecko.driver", getClass().getResource("geckodriver.exe").getPath());
        //Initializing driver and driver wait strategy
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, TIMEOUT_IN_SECONDS);
        //Loading server page
        this.driver.get(serverAddress);

        //If no login element is present is because page couldn't be loaded
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));

        //Set navigation broker
        this.navigator = new NavigationBroker(this.driver, this.wait);

    }

    /**
     * Close firefox driver
     */
    public void close() {
        this.driver.quit();
        this.loggedIn = false;
    }

    /**
     * Log into account
     *
     * @param username travian account username
     * @param password travian account password
     */
    public void login(String username, String password) {

        //Check login box existence
        WebElement accountElement = this.driver.findElement(By.className("account"));
        WebElement passElement = this.driver.findElement(By.className("pass"));

        //Check login input text fields existence
        WebElement user = accountElement.findElement(By.name("name"));
        WebElement pass = passElement.findElement(By.name("password"));

        //Fill username
        user.sendKeys(username);
        //Fill password
        pass.sendKeys(password);

        //Check login button box existence
        WebElement loginButtonBox = this.driver.findElement(By.className("loginButtonRow"));

        //Check login button box existence
        WebElement loginButton = loginButtonBox.findElement(By.className("green"));

        //Click button
        loginButton.click();

        //Wait for login successful - In this case we wait till contentContainer div (container having village fields)
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("village1")));

        this.loggedIn = true;
    }


    /**
     * Returns Navigation Broker used to navigate through user account
     *
     * @return returns null if no NavigationBroker was initialized
     */
    public NavigationBroker getNavigationBroker() {
        return this.navigator;
    }
}
