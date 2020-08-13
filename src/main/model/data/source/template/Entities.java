package model.data.source.template;

import java.io.Serializable;
import java.util.Map;

public class Entities implements Serializable {
    /*
     * Class Description:
     * This is one of the many classes which is used by GSON to read the Wikidata JSON format. See
     * https://www.mediawiki.org/wiki/Wikibase/DataModel/JSON for more details on exactly how this format works.
     */
    public static final long serialVersionUID = 1L;

    public Map<String, Entity> entities;

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "Entities{entities=" + entities + '}';
    }
}
