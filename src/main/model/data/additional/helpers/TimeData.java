package model.data.additional.helpers;

import java.util.Map;

public class TimeData {
    /*
     * Class Description:
     *
     */
    /*
    "datavalue": {
            "value": {
              "time": "+2001-12-31T00:00:00Z",
              "timezone": 0,
              "before": 0,
              "after": 0,
              "precision": 11,
              "calendarmodel": "http:\/\/www.wikidata.org\/entity\/Q1985727"
            },
            "type": "time"
          }
     */
    public final String time;
    public final double timezone; //GSON seems to make all numbers be doubles;
    public final double before;
    public final double after;
    public final double precision;
    public final String calendarmodel;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public TimeData(Map<String, Object> value) {
        time = (String) value.get("time");
        timezone = (double) value.get("timezone");
        before = (double) value.get("before");
        after = (double) value.get("after");
        precision = (double) value.get("precision");
        calendarmodel = (String) value.get("calendarmodel");
    }
}
