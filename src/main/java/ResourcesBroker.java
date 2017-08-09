import Enums.ResourceEN;
import Enums.ResourcePT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements methods that help to get and set resources data through user account
 * ______________________
 * created by Joao Suzana
 */
public class ResourcesBroker {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * New ResourcesBroker instance
     *
     * @param driver main webdriver created by TravianBot class
     * @param wait   main webdriverwait created by TravianBot class
     */
    public ResourcesBroker(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Get WoodCutter levels list
     *
     * @return returns a list of woodcutter levels
     */
    public List<String> getWoodCutterLevels() {
        //Find resources content
        WebElement content = this.driver.findElement(By.id("content"));

        //Find number of elements inside map
        List<WebElement> elements = content.findElements(By.xpath(".//map[@id='rx']/area"));

        return elements.stream().filter(
                e -> ( ResourcePT.containedInString(e.getAttribute("alt")) != null
                        && ResourcePT.containedInString(e.getAttribute("alt")).equals(ResourcePT.Woodcutter) )
                        || ( ResourceEN.containedInString(e.getAttribute("alt")) != null
                        && ResourceEN.containedInString(e.getAttribute("alt")).equals(ResourceEN.Woodcutter) )
        ).map(e -> {
            if(ResourcePT.containedInString(e.getAttribute("alt")) != null)
                return e.getAttribute("alt").split(ResourcePT.Woodcutter.label())[1];
            else
                return e.getAttribute("alt").split(ResourceEN.Woodcutter.label())[1];

        }).collect(Collectors.toList());
    }


}
