/**
 * Created by MSI on 15/06/2016.
 */
public enum MilitaryBuilding {

    GatheringPoint("Ponto de reunião militar"),
    Barrier("Barreira"),
    Barrack("Quartel"),
    Academy("Academia"),
    HeroMansion("Mansão do Herói"),
    ArmorHouse("Casa de Ferragens"),
    Stable("Cavalariça"),
    TournamentsSquare("Praça de torneios");

    private String searchWord;

    private MilitaryBuilding(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String toString() {
        return searchWord;
    }
}
