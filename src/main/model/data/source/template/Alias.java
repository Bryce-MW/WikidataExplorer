package model.data.source.template;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Alias implements Serializable {
    public static final long serialVersionUID = 1L;

    public String language;
    public String value;

    @Override
    public @NotNull String toString() {
        return "Alias{" + "language='" + language + '\'' + ", value='" + value + '\'' + '}';
    }
}
