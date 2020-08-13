package model.data.additional.helpers;

import java.util.Map;

public class QuantityData {
    /*
     * Class Description:
     * This is the JSON format of a quantity. This class is just used to convert the raw Map<String, Object> into the
     * proper format to easily create a quantity value. An example of what the JSON looks like is below.
     */
    /*
    "datavalue": {
            "value":{
              "amount":"+10.38",
              "upperBound":"+10.375",
              "lowerBound":"+10.385",
              "unit":"http://www.wikidata.org/entity/Q712226"
            },
            "type":"quantity"
          }
     */
    public final String amount;
    public final String upperBound;
    public final String lowerBound;
    public final String unit;

    /*
     * REQUIRES: value is valid quantity data in the format specified above
     * MODIFIES:
     * EFFECTS :
     */
    public QuantityData(Map<String, String> value) {
        amount = value.get("amount");
        upperBound = value.get("upperBound");
        lowerBound = value.get("lowerBound");
        unit = value.get("unit");
    }
}
