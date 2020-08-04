package model.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public class DatumLink extends Value {
    private final @NotNull Statement property;
    private final ArrayList<Qualifier> qualifiers;
    private final ArrayList<Reference> references;
    private final @Nullable String name;
    private final @Nullable String description;
    private @Nullable StatementList statementList = null;

    public DatumLink(@NotNull DatumQueryService queryService, @NotNull Statement property, @NotNull Value value) {
        super(queryService, value.getID());
        this.property = property;
        name = value.getTitle();
        description = value.getDescription();
        qualifiers = queryService.getQualifiersByStatement(property, value);
        references = queryService.getReferencesByStatement(property, value);
    }

    @Override
    public @Nullable String getTitle() {
        return name;
    }

    @Override
    public @Nullable String getDescription() {
        return description;
    }

    @Override
    public @Nullable StatementList getStatements() {
        if (statementList == null) {
            ArrayList<Value> statements = new ArrayList<>(qualifiers.size() + references.size());
            statements.addAll(qualifiers);
            statements.addAll(references);
            statementList = new StatementList(this, queryService, statements);
        }
        return statementList;
    }

    @Override
    public Boolean parse(List<String> subList) {
        return property.toggleLeft(this);
    }
    //TODO: Implement
}
