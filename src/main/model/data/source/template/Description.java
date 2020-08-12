package model.data.source.template;

import java.io.Serializable;

public class Description implements Serializable {
    /*
     * Class Description:
     *
     */
    public static final long serialVersionUID = 1L;

    public String language;
    public String value;


    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "Description{"
                + "language='" + language + '\''
                + ", value='" + value + '\''
                + '}';
    }
}
