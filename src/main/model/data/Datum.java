package model.data;

import java.util.List;

public abstract class Datum extends Value {
    protected Datum(DatumQueryService queryService) {
        super(queryService);
    }

    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }
    //TODO: Implement
}
