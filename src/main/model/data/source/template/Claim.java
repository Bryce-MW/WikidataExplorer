package model.data.source.template;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Claim implements Serializable {
    /*
     * Class Description:
     * This is one of the many classes which is used by GSON to read the Wikidata JSON format. See
     * https://www.mediawiki.org/wiki/Wikibase/DataModel/JSON for more details on exactly how this format works.
     */
    public static final long serialVersionUID = 1L;

    public String id;
    public Snak mainsnak;
    public String type;
    public String rank;
    public Map<String, List<Qualifier>> qualifiers;
    public List<Reference> references;

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns a string for debugging purposes
     */
    @Override
    public String toString() {
        return "Claim{"
                + "id='" + id + '\''
                + ", mainsnak=" + mainsnak
                + ", type='" + type + '\''
                + ", rank='" + rank + '\''
                + ", qualifiers=" + qualifiers
                + ", references=" + references
                + '}';
    }
}
