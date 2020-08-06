package model.data.additional;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.QuantityData;
import model.data.pages.Item;
import ui.cli.StatementList;

public class Quantity extends AbstractAdditional {
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

    @Override
    public String getTitle() {
        return amount;
    }

    @Override
    public String getDescription() {
        return unit.getTitle();
    }

    @Override
    public String getID() {
        return amount;
    }

    @Override
    public StatementList getStatements() {
        return null;
    }
    //TODO: Implement
}
