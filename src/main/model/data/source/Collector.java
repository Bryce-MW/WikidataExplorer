package model.data.source;

import model.data.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Collector {
    protected final LocalRepository repository;

    public Collector(LocalRepository repository) {
        this.repository = repository;
    }

    public abstract String getEntityName(String property) throws NotFoundException;

    public abstract ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery);

    public abstract ArrayList<Reference> getReferences(List<String> tree, DatumQueryService refQueryService);

    public abstract String getEntityDescription(String id) throws NotFoundException;

    public abstract ArrayList<String> getStatementList(String id) throws NotFoundException;

    public abstract Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService)
            throws NotFoundException;

    public abstract ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree, Statement about,
                                                            DatumQueryService queryService);

    public abstract Boolean triggerSave();

    public abstract Boolean triggerLoad();
}
