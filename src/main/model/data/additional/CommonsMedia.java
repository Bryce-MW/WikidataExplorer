package model.data.additional;

import model.data.DatumQueryService;

public class CommonsMedia extends LiteralString {

    public CommonsMedia(String value, DatumQueryService queryService) {
        super(value, queryService);
    }

    @Override
    public String getImage() {
        // https://magnus-toolserver.toolforge.org/commonsapi.php?image=Sa-warthog.jpg&thumbwidth=150&thumbheight=150&versions&meta
        return "https://commons.wikimedia.org/w/index.php?title=Special:Redirect/file/"
                + value.replace(' ', '_')
                + "&width=500";
    }
}
