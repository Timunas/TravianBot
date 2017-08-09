package Enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * Portuguese Enum for resources label - used for source search
 * ______________________
 * created by Joao Suzana
 */
public enum ResourcePT {

    Woodcutter("Bosque nível "),
    IronMine("Mina de ferro nível "),
    ClayPit("Poço de barro nível "),
    Cropland("Campo de cereais nível ");


    private String label;

    ResourcePT(String label){
        this.label =  label;
    }

    public String label(){
        return label;
    }

    public static ResourcePT containedInString(String label){
        Optional<ResourcePT> optional = Arrays.stream(ResourcePT.values())
                .filter(r -> label.contains(r.label)).findFirst();
        return optional.orElse(null);
    }
}
