package model.data.source.template;

import java.io.Serializable;

public class Label implements Serializable {
    public static final long serialVersionUID = 1L;

    public String language;
    public String value;

    @Override
    public String toString() {
        return "Label{" + "language='" + language + '\'' + ", value='" + value + '\'' + '}';
    }
}
