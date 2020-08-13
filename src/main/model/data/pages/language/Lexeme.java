package model.data.pages.language;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;

public class Lexeme extends Datum {
    /*
     * Class Description:
     * A lexeme is similar to a word in English. Some languages don't have words in the same way that english does so
     *  the definition is a bit more precise. To be specific, a lexeme is the smallest unit of language which has some
     *  meaning on its own. Even conceptual word like the work "the" are lexemes but parts of words that are not
     * words on their own are not such as "un" (in English, it is a lexeme in French for example). This is a type of
     * grapheme though which is the smallest unit of language since "un" can be prepended to some words to invert
     * their meaning however it does not have any meaning when used on its own. Graphemes are not included in Wikidata
     *  because they are a bit more of debated topic on if they exist, how to define them, and what they actually are
     *  in various languages.
     */
    private final String name;
    private final String description;

    /*
     * REQUIRES: id is a valid Wikidata lexeme ID, queryService is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public Lexeme(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        name = queryService.getNameByID(id);
        description = queryService.getDescriptionByID(id);
    }

    /*
     * REQUIRES: data is valid EntityData for a Lexeme, queryService is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public Lexeme(EntityData data, DatumQueryService queryService) throws NotFoundException {
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
