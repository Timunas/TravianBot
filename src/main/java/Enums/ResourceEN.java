package Enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * English Enum for resources label - used for source search
 * ______________________
 * created by Joao Suzana
 */
public enum ResourceEN {

    Woodcutter("Woodcutter Level "),
    IronMine("Iron Mine Level "),
    ClayPit("Clay Pit Level "),
    Cropland("Cropland Level ");


    private String label;

    ResourceEN(String label){
        this.label =  label;
    }

    public String label(){
        return label;
    }

    public static ResourceEN containedInString(String label){
        Optional<ResourceEN> optional = Arrays.stream(ResourceEN.values())
                .filter(r -> label.contains(r.label)).findFirst();
        return optional.orElse(null);
    }
}
