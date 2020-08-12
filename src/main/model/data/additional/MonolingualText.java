package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.MonolingualTextData;
import ui.cli.StatementList;

public class MonolingualText extends AbstractAdditional {
    /*
     * Class Description:
     *
     */
    private final String text;
    private final String language;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public MonolingualText(String text, String language, DatumQueryService queryService) {
        super(queryService);
        this.text = text;
        this.language = language;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public MonolingualText(MonolingualTextData monolingualTextValue, DatumQueryService queryService) {
        super(queryService);
        this.text = monolingualTextValue.value;
        this.language = monolingualTextValue.language;
    }


    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return text;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return language;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getID() {
        return text;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public StatementList getStatements() {
        return null;
    }
}
