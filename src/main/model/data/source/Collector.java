package model.data.source;

import model.data.Qualifier;
import model.data.Reference;

import java.util.ArrayList;
import java.util.List;

public interface Collector {
    String getEntityName(String property);

    ArrayList<Qualifier> getQualifiers(List<String> tree);

    ArrayList<Reference> getReferences(List<String> tree);

    String getEntityDescription(String id);

}
