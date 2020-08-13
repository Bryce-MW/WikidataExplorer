package model.data.pages.language;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;

public class Form extends Datum {
    /*
     * Class Description:
     * To be honest, I don't entirely know what a Form is, I just know that it is a type of page on Wikidata. I think
     *  it goes a lexeme has one or more forms and a form has one or more senses.
     */
    private final String name;
    private final String description;

    /*
     * REQUIRES: id is a valid Wikidata form id, queryService is not null
     * MODIFIES:
     * EFFECTS :
     */
    public Form(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        name = queryService.getNameByID(id);
        description = queryService.getDescriptionByID(id);
    }

    /*
     * REQUIRES: data is valid EntityData for a Form, queryService is not null
     * MODIFIES:
     * EFFECTS :
     */
    public Form(EntityData data, DatumQueryService queryService) throws NotFoundException {
        this(data.id, queryService);
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return name;
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return description;
    }
}
