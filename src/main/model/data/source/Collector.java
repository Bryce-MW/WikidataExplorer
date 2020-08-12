package model.data.source;

import model.data.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Collector {
    /*
     * Class Description:
     *
     */
    protected final LocalRepository repository;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Collector(LocalRepository repository) {
        this.repository = repository;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract String getEntityName(String property) throws NotFoundException;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery);

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract ArrayList<Reference> getReferences(List<String> tree, DatumQueryService refQueryService);

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract String getEntityDescription(String id) throws NotFoundException;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract ArrayList<String> getStatementList(String id) throws NotFoundException;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService)
            throws NotFoundException;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree, Statement about,
                                                            DatumQueryService queryService);

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract Boolean triggerSave();

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract Boolean triggerLoad();
}
