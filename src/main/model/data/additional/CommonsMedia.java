package model.data.additional;

import model.data.DatumQueryService;

public class CommonsMedia extends LiteralString {
    /*
     * Class Description:
     * This class has a link to an image or other media type in Wikimedia Commons. There is a special getImage
     * function which returns a link to an image on Wikimedia Commons with a given filename and the fixed width of
     * 500px (the Wikimedia Commons servers do the resizing for us).
     */

    public CommonsMedia(String value, DatumQueryService queryService) {
        super(value, queryService);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getImage() {
        return "https://commons.wikimedia.org/w/index.php?title=Special:Redirect/file/"
                + value.replace(' ', '_')
                + "&width=500";
    }
}
