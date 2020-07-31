package model.data.source.template;

public class DataValue {
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
