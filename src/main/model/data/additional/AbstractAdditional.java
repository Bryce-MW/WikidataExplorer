package model.data.additional;

import model.data.DatumQueryService;
import model.data.Value;

import java.util.List;

public abstract class AbstractAdditional extends Value {
    /*
     * Class Description:
     *
     */

    protected AbstractAdditional(DatumQueryService queryService) {
        super(queryService, "");
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }
}
