package model.data.source;

import model.data.*;

import java.util.ArrayList;
import java.util.List;

public interface Collector {
    String getEntityName(String property);

    ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery);

    ArrayList<Reference> getReferences(List<String> tree);

    String getEntityDescription(String id);

    ArrayList<String> getStatementList(String id);

    Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService);

    ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree, Statement about, DatumQueryService queryService);
}
