package model.data.source.template;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Map;

public class Entities implements Serializable {
    public static final long serialVersionUID = 1L;

    public Map<String, Entity> entities;

    @Override
    public @NotNull String toString() {
        return "Entities{entities=" + entities + '}';
    }
}
