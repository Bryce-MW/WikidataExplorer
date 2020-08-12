package model.data.additional.helpers;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class EntityData {
    /*
     * Class Description:
     * This is the JSON format of an entity. This class is just used to convert the raw Map<String, Object> into the
     * proper format to easily create a entity value. An example of what the JSON looks like is below.
     */
    /*
    "datavalue": {
            "value": {
              "entity-type": "item",
              "id": "Q30",
              "numeric-id": 30
            },
            "type": "wikibase-entityid"
          }
     */

    @SerializedName("entity-type")
    public final String entityType;
    public final String id;
    @SerializedName("numeric-id")
    public final double numericId;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public EntityData(Map<String, Object> value) {
        entityType = (String) value.get("entity-type");
        id = (String) value.get("id");
        numericId = (double) value.get("numeric-id");
    }
}
