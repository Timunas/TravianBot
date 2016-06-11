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

            travianBot.construcField("Bosque");

        } catch (MalformedURLException e) {
            LOG.error("Invalid address!", e .getMessage());
        }
    }
}
