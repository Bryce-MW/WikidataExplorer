package model.data.source.template;

import java.io.Serializable;

public class DataValue implements Serializable {
    public static final long serialVersionUID = 1L;

    public Object value;
    public String type;

    @Override
    public String toString() {
        return "DataValue{"
                + "value=" + value
                + ", type='" + type + '\''
                + '}';
    }
}
