package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.MonolingualTextData;
import ui.cli.StatementList;

public class MonolingualText extends AbstractAdditional {
    private final String text;
    private final String language;

    public MonolingualText(String text, String language, DatumQueryService queryService) {
        super(queryService);
        this.text = text;
        this.language = language;
    }

    public MonolingualText(MonolingualTextData monolingualTextValue, DatumQueryService queryService) {
        super(queryService);
        this.text = monolingualTextValue.value;
        this.language = monolingualTextValue.language;
    }


    @Override
    public String getTitle() {
        return text;
    }

    @Override
    public String getDescription() {
        return language;
    }

    @Override
    public String getID() {
        return text;
    }

    @Override
    public StatementList getStatements() {
        return null;
    }
    //TODO: Implement
}
