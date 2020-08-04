package model.data;

import model.data.pages.Property;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.cli.StatementList;

import java.util.List;

public class Reference extends Value {
    //TODO: Implement
    private final @NotNull Property property;
    private final Value value;

    public Reference(@NotNull Property property, Value value, DatumQueryService queryService) {
        super(queryService, property.getID());
        this.property = property;
        this.value = value;
    }

    public @NotNull Property getProperty() {
        return property;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String getTitle() {
        return property.getTitle();
    }

    @Override
    public @Nullable String getDescription() {
        return value.getTitle();
    }

    @Override
    public @Nullable StatementList getStatements() {
        return null;
    }

    @Override
    public @NotNull Boolean parse(List<String> subList) {
        return false;
    }
}
