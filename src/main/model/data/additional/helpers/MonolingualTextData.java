package model.data.additional.helpers;

import java.util.Map;

public class MonolingualTextData {
    /*
     * Class Description:
     * This is the JSON format of a MonolingualText. This class is just used to convert the raw Map<String,
     * Object> into the
     * proper format to easily create a MonolingualText value.
     */
    public final String language;
    public final String value;

    /*
     * REQUIRES: value is valid monolingual text data in the format specified above
     * MODIFIES: this
     * EFFECTS :
     */
    public MonolingualTextData(Map<String, String> value) {
        language = value.get("language");
        this.value = value.get("value"); // I know, confusing, but I want to keep the names similar to in the format
    }
}
