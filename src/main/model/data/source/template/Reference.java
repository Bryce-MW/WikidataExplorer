package model.data.source.template;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Reference implements Serializable {
    public static final long serialVersionUID = 1L;

    public String hash;
    public Map<String, List<Snak>> snaks;
    @SerializedName("snaks-order")
    public List<String> snaksOrder;

    @Override
    public @NotNull String toString() {
        return "Reference{" + "hash='" + hash + '\'' + ", snaks=" + snaks + ", snaksOrder=" + snaksOrder + '}';
    }
}
