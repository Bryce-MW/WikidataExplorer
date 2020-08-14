package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;

public class Property extends Datum {
    /*
     * Class Description:
     * This is a property. It represents anything that is a property on Wikidata, i.e. something with an ID that
     * starts with P. A property is used to describe a relationship. It could be something like instance of, mother,
     * video, image, notable work, and many many more.
     */
    private final String title;
    private final String description;

    /*
     * REQUIRES: id is a valid Wikidata ID, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new property from the given ID
     */
    public Property(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        this.title = queryService.getNameByID(id);
        this.description = queryService.getDescriptionByID(id);
    }

    /*
     * REQUIRES: data is valid EntityData, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new property form the given entity data
     */
    public Property(EntityData data, DatumQueryService queryService) throws NotFoundException {
        this(data.id, queryService);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the title to be displayed
     */
    @Override
    public String getTitle() {
        return title;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the description to be displayed
     */
    @Override
    public String getDescription() {
        return description;
    }
}
