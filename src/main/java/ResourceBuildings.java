/**
 * Created by MSI on 15/06/2016.
 */
public enum ResourceBuildings {

    Mill("Moinho"),
    Foundry("Fundição"),
    Masonry("Alvenaria"),
    Sawmill("Serração"),
    Bakery("Padaria");

    private String searchWord;

    private ResourceBuildings(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String toString() {
        return searchWord;
    }
}
