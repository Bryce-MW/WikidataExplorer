package model.data.source;

import model.data.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Collector {
    LocalRepository repository;

    public Collector(LocalRepository repository) {
        this.repository = repository;
    }

    public abstract String getEntityName(String property);

    public abstract ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery);

    public abstract ArrayList<Reference> getReferences(List<String> tree);

    public abstract String getEntityDescription(String id);

    public abstract ArrayList<String> getStatementList(String id);

    public abstract Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService);

    public abstract ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree, Statement about, DatumQueryService queryService);

    public abstract Boolean triggerSave();

    public abstract Boolean triggerLoad();
}
