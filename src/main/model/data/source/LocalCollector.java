package model.data.source;

import com.google.gson.Gson;
import model.data.*;
import model.data.source.template.Entities;

import java.util.ArrayList;
import java.util.List;

public class LocalCollector extends Collector {
    private static final ArrayList<Entities> seen = new ArrayList<>(30);
    private final Gson gson;

    public LocalCollector(LocalRepository repository) {
        super(repository);
        gson = new Gson();
    }

    @Override
    public String getEntityName(String property) {
        return null;
    }

    @Override
    public ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery) {
        return null;
    }

    @Override
    public ArrayList<Reference> getReferences(List<String> tree) {
        return null;
    }

    @Override
    public String getEntityDescription(String id) {
        return null;
    }

    @Override
    public ArrayList<String> getStatementList(String id) {
        return null;
    }

    @Override
    public Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService) {
        return null;
    }

    @Override
    public ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree, Statement about, DatumQueryService queryService) {
        return null;
    }

    @Override
    public Boolean triggerSave() {
        return null;
    }

    @Override
    public Boolean triggerLoad() {
        return null;
    }
}
