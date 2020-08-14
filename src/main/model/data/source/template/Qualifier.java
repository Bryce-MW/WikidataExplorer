package model.data.source.template;

import java.io.Serializable;

public class Qualifier implements Serializable {
    /*
     * Class Description:
     * This is one of the many classes which is used by GSON to read the Wikidata JSON format. See
     * https://www.mediawiki.org/wiki/Wikibase/DataModel/JSON for more details on exactly how this format works.
     */
    public static final long serialVersionUID = 1L;

    public String hash;
    public String snaktype;
    public String property;
    public String datatype;
    public DataValue datavalue;

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns a string for debugging purposes
     */
    @Override
    public String toString() {
        return "Qualifier{"
                + "hash='" + hash + '\''
                + ", snaktype='" + snaktype + '\''
                + ", property='" + property + '\''
                + ", datatype='" + datatype + '\''
                + ", datavalue=" + datavalue
                + '}';
    }
}
