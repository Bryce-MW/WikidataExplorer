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

    /*
     * REQUIRES: quantityValue is valid QuantityData, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new quantity from the given quantity data
     */
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
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the title to be displayed
     */
    @Override
    public String getTitle() {
        return amount;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the description to be displayed
     */
    @Override
    public String getDescription() {
        return unit.getTitle();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the ID (just the amount)
     */
    @Override
    public String getID() {
        return amount;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns null as quantities have no statements
     */
    @Override
    public StatementList getStatements() {
        return null;
    }
}
