package model.data.source.template;

import java.util.List;
import java.util.Map;

public class Claim {
    public String id;
    public Snak mainsnak;
    public String type;
    public String rank;
    public Map<String, List<Qualifier>> qualifiers;
    public List<Reference> references;

    @Override
    public String toString() {
        return "Claim{"
                + "id='" + id + '\''
                + ", mainsnak=" + mainsnak
                + ", type='" + type + '\''
                + ", rank='" + rank + '\''
                + ", qualifiers=" + qualifiers
                + ", references=" + references
                + '}';
    }
}
