package model.data.additional.helpers;

import java.util.Map;

public class QuantityData {
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

    public QuantityData(Map<String, String> value) {
        amount = value.get("amount");
        upperBound = value.get("upperBound");
        lowerBound = value.get("lowerBound");
        unit = value.get("unit");
    }
}
