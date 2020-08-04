package model.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public class Statement extends Value {
    private final String name;
    private final Datum about;
    private @Nullable StatementList statements = null;

    public Statement(Datum item, String property, @NotNull DatumQueryService queryService) throws NotFoundException {
        super(queryService, property);
        this.queryService = queryService;
        this.name = queryService.getNameByID(property);
        this.about = item;
    }

    private ArrayList<Value> findStatements() {
        return queryService.getDatumLinkListByStatement(this);
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public @NotNull String getDescription() {
        return "";
    }

    @Override
    public @Nullable StatementList getStatements() {
        if (statements == null) {
            statements = new StatementList(this, queryService, findStatements());
        }
        return statements;
    }

    @Override
    public Boolean parse(@NotNull List<String> subList) {
        if (subList.size() == 0) {
            return about.toggleLeft(this);
        }
        return statements.parse(subList);
    }
    //TODO: Implement

    public @Nullable String getParentID() {
        return about.getID();
    }
}
