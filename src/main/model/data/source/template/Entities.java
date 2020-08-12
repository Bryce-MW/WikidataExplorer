package model.data.source.template;

import java.io.Serializable;
import java.util.Map;

public class Entities implements Serializable {
    /*
     * Class Description:
     *
     */
    public static final long serialVersionUID = 1L;

    public Map<String, Entity> entities;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "Entities{entities=" + entities + '}';
    }
}
