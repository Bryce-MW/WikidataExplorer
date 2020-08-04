package model.data.source.template;

import java.io.Serializable;
import java.util.List;

public class SiteLink implements Serializable {
    public static final long serialVersionUID = 1L;

    public String site;
    public String title;
    public List<String> badges;
    public String url;

    @Override
    public String toString() {
        return "SiteLink{"
                + "site='" + site + '\''
                + ", title='" + title + '\''
                + ", badges=" + badges
                + ", url='" + url + '\''
                + '}';
    }
}
