package model.data.source.template;

import java.io.Serializable;

public class Description implements Serializable {
    /*
     * Class Description:
     * This is one of the many classes which is used by GSON to read the Wikidata JSON format. See
     * https://www.mediawiki.org/wiki/Wikibase/DataModel/JSON for more details on exactly how this format works.
     */
    public static final long serialVersionUID = 1L;

    public String language;
    public String value;


    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "Description{"
                + "language='" + language + '\''
                + ", value='" + value + '\''
                + '}';
    }
}
