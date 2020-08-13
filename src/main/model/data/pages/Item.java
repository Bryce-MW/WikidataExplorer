package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;

public class Item extends Datum {
    /*
     * Class Description:
     * An Item is anything which is a concept on Wikidata, i.e. anything which has an ID starting with Q. This can
     * represent a person, place, physical or theoretical object, philosophical concept, really anything which could
     * be a noun in English.
     */
    private final String name;
    private final String description;

    /*
     * REQUIRES: id is a valid Wikidata concept ID, queryService is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public Item(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        name = queryService.getNameByID(id);
        description = queryService.getDescriptionByID(id);
    }

    /*
     * REQUIRES: data is valid concept EntityData, queryService is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public Item(EntityData data, DatumQueryService queryService) throws NotFoundException {
        this(data.id, queryService);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return name;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return description;
    }
}
