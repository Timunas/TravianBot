import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;

/**
 * Created by MSI on 10/06/2016.
 */
public class BotRunner {

    /** LOGGER **/
    private static Logger LOG = LogManager.getLogger(BotRunner.class);

    public static void main(String[] args){
        TravianBot travianBot=null;
        try {
            travianBot = new TravianBot("http://ts2.travian.pt");
            travianBot.login("jenkins","pilas");
            Thread.sleep(2000);
            travianBot.goToBuildingsView();
            travianBot.goToEmptyBuildingSlot();
            travianBot.newBuilding(Infrastructure.Barn);
            Thread.sleep(10000);
            travianBot.closeBrowser();
        } catch (MalformedURLException e) {
            LOG.error("Invalid address!", e .getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
