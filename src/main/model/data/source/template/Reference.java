package model.data.source.template;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Reference {
    public String hash;
    public Map<String, List<Snak>> snaks;
    @SerializedName("snaks-order")
    public List<String> snaksOrder;

    @Override
    public String toString() {
        return "Reference{" + "hash='" + hash + '\'' + ", snaks=" + snaks + ", snaksOrder=" + snaksOrder + '}';
    }
}
