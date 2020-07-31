package model.data.source.template;

public class Snak {
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
