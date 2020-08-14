package model.data.additional.helpers;

import java.util.Map;

public class GlobeCoordinateData {
    /*
     * Class Description:
     * This is the JSON format of a GlobeCoordinate. This class is just used to convert the raw Map<String, Object> into
     * the proper format to easily create a globe coordinate value. An example of what the JSON looks like is below.
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
     * REQUIRES: value is valid globe coordinate data in the format specified above
     * MODIFIES: this
     * EFFECTS : creates position data from the given JSON map
     */
    public GlobeCoordinateData(Map<String, Object> value) {
        latitude = (double) value.get("latitude");
        longitude = (double) value.get("longitude");
        precision = (double) value.get("precision");
        globe = (String) value.get("globe");
    }
}
