package model.data.additional;

import model.data.DatumQueryService;
import model.data.Value;

import java.util.List;

public abstract class AbstractAdditional extends Value {

    protected AbstractAdditional(DatumQueryService queryService) {
        super(queryService);
    }

    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }
}
