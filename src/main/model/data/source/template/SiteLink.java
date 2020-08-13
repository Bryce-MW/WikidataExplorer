package model.data.source.template;

import java.io.Serializable;
import java.util.List;

public class SiteLink implements Serializable {
    /*
     * Class Description:
     * This is one of the many classes which is used by GSON to read the Wikidata JSON format. See
     * https://www.mediawiki.org/wiki/Wikibase/DataModel/JSON for more details on exactly how this format works.
     */
    public static final long serialVersionUID = 1L;

    public String site;
    public String title;
    public List<String> badges;
    public String url;

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "SiteLink{"
                + "site='" + site + '\''
                + ", title='" + title + '\''
                + ", badges=" + badges
                + ", url='" + url + '\''
                + '}';
    }
}
