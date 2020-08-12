package model.data.additional;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.QuantityData;
import model.data.pages.Item;
import ui.cli.StatementList;

public class Quantity extends AbstractAdditional {
    /*
     * Class Description:
     * This is some sort of numerical value associated with a unit (though the unit could be the "Dimensionless" unit
     * which is the default).
     */
    private final String amount;
    private final Item unit;

    public Quantity(QuantityData quantityValue, DatumQueryService queryService) {
        super(queryService);
        amount = quantityValue.amount;
        Item dimensionless;
        Item possibleUnit;
        try {
            dimensionless = new Item("Q126818", queryService); // Dimensionless
        } catch (NotFoundException e) {
            throw new Error("Q126818 not found", e);
        }
        try {
            possibleUnit = new Item(quantityValue.unit.substring(31), queryService);
        } catch (NotFoundException e) {
            possibleUnit = null;
        }
        unit = possibleUnit != null ? possibleUnit : dimensionless;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return amount;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return unit.getTitle();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getID() {
        return amount;
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
