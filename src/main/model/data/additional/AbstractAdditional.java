package model.data.additional;

import model.data.DatumQueryService;
import model.data.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractAdditional extends Value {

    protected AbstractAdditional(DatumQueryService queryService) {
        super(queryService, "");
    }

    @Override
    public @NotNull Boolean parse(List<String> subList) {
        return false;
    }
}
