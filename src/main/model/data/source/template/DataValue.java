package model.data.source.template;

import java.io.Serializable;

public class DataValue implements Serializable {
    /*
     * Class Description:
     *
     */
    public static final long serialVersionUID = 1L;

    public Object value;
    public String type;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "DataValue{"
                + "value=" + value
                + ", type='" + type + '\''
                + '}';
    }
}
