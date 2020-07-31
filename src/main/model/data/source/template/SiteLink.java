package model.data.source.template;

import java.util.List;

public class SiteLink {
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
