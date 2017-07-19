import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class used for Travian Bot Test
 * ______________________
 * created by Joao Suzana
 */
public class BotRunner {

    private static Logger logger = LogManager.getLogger(BotRunner.class);

    public static void main(String[] args){
        TravianBot bot = new TravianBot("http://ts6.travian.com");

        logger.info("Initializing...");
        bot.init();
        logger.info("Login in...");
        bot.login("Vlad","1234cenas");
        logger.info("Logged in...");
        logger.info("Closing browser");
        bot.close();
        logger.info("Closed...");
    }
}
