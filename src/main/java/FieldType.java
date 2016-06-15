/**
 * Created by MSI on 15/06/2016.
 */
public enum FieldType {

    Woods("Bosque"),
    IronMine("Mina de ferro"),
    CropField("Campo de cereais"),
    ClayPit("Poço de barro");

    private String searchWord;

    private FieldType(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String toString() {
        return searchWord;
    }
}
