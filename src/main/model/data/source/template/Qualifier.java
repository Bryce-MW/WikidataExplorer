package model.data.source.template;

import java.io.Serializable;

public class Qualifier implements Serializable {
    /*
     * Class Description:
     *
     */
    public static final long serialVersionUID = 1L;

    public String hash;
    public String snaktype;
    public String property;
    public String datatype;
    public DataValue datavalue;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "Qualifier{"
                + "hash='" + hash + '\''
                + ", snaktype='" + snaktype + '\''
                + ", property='" + property + '\''
                + ", datatype='" + datatype + '\''
                + ", datavalue=" + datavalue
                + '}';
    }
}
