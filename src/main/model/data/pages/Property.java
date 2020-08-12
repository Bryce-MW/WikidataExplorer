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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Property(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        this.title = queryService.getNameByID(id);
        this.description = queryService.getDescriptionByID(id);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Property(EntityData data, DatumQueryService queryService) throws NotFoundException {
        this(data.id, queryService);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return title;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return description;
    }
}
