package model.data.additional;

import model.data.DatumQueryService;
import model.data.Value;

import java.util.List;

public abstract class AbstractAdditional extends Value {
    /*
     * Class Description:
     * This is an abstract class that allows me to not have to implement redundant functionality in all of the
     * "additional" datatypes since they are all pretty similar.
     */

    protected AbstractAdditional(DatumQueryService queryService) {
        super(queryService, "");
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }
}
