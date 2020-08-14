package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.MonolingualTextData;
import ui.cli.StatementList;

public class MonolingualText extends AbstractAdditional {
    /*
     * Class Description:
     * This is a string of text in a single language.
     */
    private final String text;
    private final String language;

    /*
     * REQUIRES: text is not null, language is a valid ISO something language ID. queryService is not null
     * MODIFIES: this
     * EFFECTS : creates monolingual text from the given text and language
     */
    public MonolingualText(String text, String language, DatumQueryService queryService) {
        super(queryService);
        this.text = text;
        this.language = language;
    }

    /*
     * REQUIRES: monolingualTextValue is valid MonolingualTextData, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates monolingual text from from the data
     */
    public MonolingualText(MonolingualTextData monolingualTextValue, DatumQueryService queryService) {
        super(queryService);
        this.text = monolingualTextValue.value;
        this.language = monolingualTextValue.language;
    }


    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the title to be displayed
     */
    @Override
    public String getTitle() {
        return text;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the description to be displayed
     */
    @Override
    public String getDescription() {
        return language;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the ID (just the text)
     */
    @Override
    public String getID() {
        return text;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns null as text has no statements
     */
    @Override
    public StatementList getStatements() {
        return null;
    }
}
