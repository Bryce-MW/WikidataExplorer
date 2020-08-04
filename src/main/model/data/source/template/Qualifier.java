package model.data.source.template;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Qualifier implements Serializable {
    public static final long serialVersionUID = 1L;

    public String hash;
    public String snaktype;
    public String property;
    public String datatype;
    public DataValue datavalue;

    @Override
    public @NotNull String toString() {
        return "Qualifier{"
                + "hash='" + hash + '\''
                + ", snaktype='" + snaktype + '\''
                + ", property='" + property + '\''
                + ", datatype='" + datatype + '\''
                + ", datavalue=" + datavalue
                + '}';
    }
}
