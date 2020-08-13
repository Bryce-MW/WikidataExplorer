package model.data.source.template;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Reference implements Serializable {
    /*
     * Class Description:
     * This is one of the many classes which is used by GSON to read the Wikidata JSON format. See
     * https://www.mediawiki.org/wiki/Wikibase/DataModel/JSON for more details on exactly how this format works.
     */
    public static final long serialVersionUID = 1L;

    public String hash;
    public Map<String, List<Snak>> snaks;
    @SerializedName("snaks-order")
    public List<String> snaksOrder;

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "Reference{" + "hash='" + hash + '\'' + ", snaks=" + snaks + ", snaksOrder=" + snaksOrder + '}';
    }
}
