package model.data.source.template;

import java.util.List;
import java.util.Map;

public class Entity {
    public String id;
    public String type;
    public Map<String, Label> labels;
    public Map<String, Description> descriptions;
    public Map<String, List<Alias>> aliases;
    public Map<String, List<Claim>> claims;
    public Map<String, SiteLink> sitelinks;
    public int lastrevid;
    public String modified;

    @Override
    public String toString() {
        return "Entity{"
                + "id='" + id + '\''
                + ", type='" + type + '\''
                + ", labels=" + labels
                + ", descriptions=" + descriptions
                + ", aliases=" + aliases
                + ", claims=" + claims
                + ", sitelinks=" + sitelinks
                + ", lastrevid=" + lastrevid
                + ", modified='" + modified + '\''
                + '}';
    }
}
