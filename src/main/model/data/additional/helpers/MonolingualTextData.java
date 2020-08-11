package model.data.additional.helpers;

import java.util.Map;

public class MonolingualTextData {
    public final String language;
    public final String value;

    public MonolingualTextData(Map<String, String> value) {
        language = value.get("language");
        this.value = value.get("value"); // I know, confusing, but I want to keep the names similar to in the format
    }
}