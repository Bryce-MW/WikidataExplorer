package model.data.source;

import model.data.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Collector {
    /*
     * Class Description:
     * This class is supposed to be the superclass of LocalCollector and WebCollector but it didn't end up working
     * that way. Basically just lays out how any data source should work.
     */
    protected final LocalRepository repository;

    /*
     * REQUIRES: repository is not null
     * MODIFIES: this
     * EFFECTS : creats a new collector
     */
    public Collector(LocalRepository repository) {
        this.repository = repository;
    }

    /*
     * REQUIRES: property is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS : gets the name of an entity by its ID
     */
    public abstract String getEntityName(String property) throws NotFoundException;

    /*
     * REQUIRES: tree is a valid Wikidata tree, qualifierQuery is not null
     * MODIFIES: none
     * EFFECTS : gets the qualifiers for a statement by its tree
     */
    public abstract ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery);

    /*
     * REQUIRES: tree is a valid Wikidata tree, qualifierQuery is not null
     * MODIFIES: none
     * EFFECTS : gets the references for a statement by its tree
     */
    public abstract ArrayList<Reference> getReferences(List<String> tree, DatumQueryService refQueryService);

    /*
     * REQUIRES: id is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS : gets the description for an entity by its ID
     */
    public abstract String getEntityDescription(String id) throws NotFoundException;

    /*
     * REQUIRES: id is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS : gets a list of statements for an ID
     */
    public abstract ArrayList<String> getStatementList(String id) throws NotFoundException;

    /*
     * REQUIRES: tree is a valid Wikidata tree about item, statementService is not null
     * MODIFIES: none
     * EFFECTS : gets a single statement by the tree and the item it is about
     */
    public abstract Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService)
            throws NotFoundException;

    /*
     * REQUIRES: tree is a valid Wikidata tree about about, statementService is not null
     * MODIFIES: none
     * EFFECTS : gets a single datum link by its tree and the statement which it is about
     */
    public abstract ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree, Statement about,
                                                            DatumQueryService queryService);

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS : triggers a save to the local cache
     */
    public abstract Boolean triggerSave();

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS : triggers a load from the local cache
     */
    public abstract Boolean triggerLoad();
}
