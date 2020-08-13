package model.data.source.template;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Entity implements Serializable {
    /*
     * Class Description:
     * This is one of the many classes which is used by GSON to read the Wikidata JSON format. See
     * https://www.mediawiki.org/wiki/Wikibase/DataModel/JSON for more details on exactly how this format works.
     */
    public static final long serialVersionUID = 1L;

    public String id;
    public String type;
    public Map<String, Label> labels;
    public Map<String, Label> representations;
    public Map<String, Description> descriptions;
    public Map<String, List<Alias>> aliases;
    public Map<String, List<Claim>> claims;
    public Map<String, SiteLink> sitelinks;
    public int lastrevid;
    public String modified;

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "Entity{"
                + "id='" + id + '\''
                + ", type='" + type + '\''
                + ", labels=" + labels
                + ", representations=" + representations
                + ", descriptions=" + descriptions
                + ", aliases=" + aliases
                + ", claims=" + claims
                + ", sitelinks=" + sitelinks
                + ", lastrevid=" + lastrevid
                + ", modified='" + modified + '\''
                + '}';
    }
}
