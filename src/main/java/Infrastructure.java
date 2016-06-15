/**
 * Created by MSI on 15/06/2016.
 */
public enum Infrastructure {

    Hideout("Esconderijo"),
    Warehouse("Armazém"),
    Barn("Celeiro"),
    Embassy("Embaixada"),
    Marketplace("Mercado"),
    Residence("Residência"),
    Palace("Palácio"),
    Mason("Pedreiro"),
    Treasury("Tesouraria"),
    Brewery("Cervejaria"),
    PeoplesHouse("Casa do Povo"),
    CompanyTrade("Companhia do Comércio"),
    BigBarn("Grande Celeiro"),
    BigWarehouse("Grande armazém");

    private String searchWord;

    private Infrastructure(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String toString() {
        return searchWord;
    }
}
