package model.data.pages.language;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;

public class Sense extends Datum {
    /*
     * Class Description:
     * A sense applies to one lexeme (a word in English). It's sort of like a definition but it is more about the
     * senses in which a word can be interpreted. This is one of the entity types on Wikidata though I never really
     * showed it off because it is not implemented properly and is not particularly interesting.
     */
    private final String name;
    private final String description;

    /*
     * REQUIRES: id is a valid Wikidata sense id, queryService is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public Sense(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        name = queryService.getNameByID(id);
        description = queryService.getDescriptionByID(id);
    }

    /*
     * REQUIRES: data is valid EntityData for a sense, queryService is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public Sense(EntityData data, DatumQueryService queryService) throws NotFoundException {
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
