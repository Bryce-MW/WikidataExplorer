package model.data.additional.helpers;

import java.util.Map;

public class GlobeCoordinateData {
    /*
     * Class Description:
     *
     */
    /*
    "datavalue": {
            "value": {
              "latitude": 52.516666666667,
              "longitude": 13.383333333333,
              "altitude": null,
              "precision": 0.016666666666667,
              "globe": "http:\/\/www.wikidata.org\/entity\/Q2"
            },
            "type": "globecoordinate"
          }
     */
    public final double latitude;
    public final double longitude;
    public final double precision;
    public final String globe;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public GlobeCoordinateData(Map<String, Object> value) {
        latitude = (double) value.get("latitude");
        longitude = (double) value.get("longitude");
        precision = (double) value.get("precision");
        globe = (String) value.get("globe");
    }
}
