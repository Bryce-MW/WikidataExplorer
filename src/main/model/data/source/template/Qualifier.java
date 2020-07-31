package model.data.source.template;

public class Qualifier {
    public String hash;
    public String snaktype;
    public String property;
    public String datatype;
    public DataValue datavalue;

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
