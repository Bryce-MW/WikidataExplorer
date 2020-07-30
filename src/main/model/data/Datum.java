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

    public boolean toggleLeft(Statement statement) {
        if (this.view == null) {
            return false;
        }
        return view.toggleLeft(statement);
    }
    //TODO: Implement
}
