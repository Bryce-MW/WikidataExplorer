package model.data.source.template;

import java.io.Serializable;

public class Snak implements Serializable {
    public static final long serialVersionUID = 1L;

    public String snaktype;
    public String property;
    public String datatype;
    public DataValue datavalue;

    @Override
    public String toString() {
        return "Snak{"
                + "snaktype='" + snaktype + '\''
                + ", property='" + property + '\''
                + ", datatype='" + datatype + '\''
                + ", datavalue=" + datavalue
                + '}';
    }
}
