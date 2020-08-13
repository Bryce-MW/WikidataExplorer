package model.data;

import model.data.additional.*;
import model.data.additional.helpers.*;
import model.data.pages.Item;
import model.data.pages.Property;
import model.data.pages.language.Form;
import model.data.pages.language.Lexeme;
import model.data.pages.language.Sense;
import model.data.source.template.DataValue;
import ui.GUInterface;
import ui.cli.ItemView;
import ui.cli.StatementList;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public abstract class Value extends JPanel {
    /*
     * Class Description:
     * This is the abstract class for any "Value". Originally, this was basically just going to be any Wikidata
     * datatype, but my poor planning meant that it ended up also including things that should really be left to
     * rendering or a different type of class entirely. It works well enough. This class also handles converting from
     *  a "snak" to an actual Value which is a reasonable static function for this class to have. This class also
     * does its own rendering for the GUI but it does it in a very weird way since it's not actually as a panel but
     * as an item in a statement list. This makes it very confusing to debug and use. I would definitely write this
     * class very differently if I was doing this again.
     */
    protected DatumQueryService queryService;
    protected ItemView view = null;
    protected final String id;
    protected boolean initialized;
    protected JButton button;

    /*
     * REQUIRES: queryService is not null, id is a valid Wikidata ID
     * MODIFIES: this
     * EFFECTS :
     */
    protected Value(DatumQueryService queryService, String id) {
        this.id = id;
        this.queryService = queryService;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBackground(GUInterface.midGray);
    }

    /*
     * REQUIRES: dataType is a valid Wikidata datatype which has LiteralString as its backing, queryService is not
     * null, stringValue is not null
     * MODIFIES: none
     * EFFECTS :
     */
    private static LiteralString getLiteralString(String dataType, DatumQueryService queryService, String stringValue) {
        switch (dataType) {
            case "commonsMedia":
                return new CommonsMedia(stringValue, queryService);
            case "geographic-shape":
                return new GeographicShape(stringValue, queryService);
            case "string":
                return new LiteralString(stringValue, queryService);
            case "tabular-data":
                return new TabularData(stringValue, queryService);
            case "url":
                return new URL(stringValue, queryService);
            case "external-id":
                return new ExternalIdentifier(stringValue, queryService);
            case "musical-notation":
                return new MusicalNotation(stringValue, queryService);
            case "mathematical-expression":
                return new MathematicalExpression(stringValue, queryService);
        }
        throw new Error("Datatype: " + dataType + " not found");
    }

    /*
     * REQUIRES: value is a valid Wikidata datatype, value is a valid DataValue corresponding to the datatype,
     * queryService is not null
     * MODIFIES: none
     * EFFECTS :
     */
    public static Value parseData(DataValue value, String dataType, DatumQueryService queryService) {
        switch (value.type) {
            case "string":
                String stringValue = (String) value.value;
                return getLiteralString(dataType, queryService, stringValue);
            case "globecoordinate":
                GlobeCoordinateData globeCoordinateValue = new GlobeCoordinateData((Map<String, Object>) value.value);
                return new GlobeCoordinate(globeCoordinateValue, queryService);
            case "monolingualtext":
                MonolingualTextData monolingualTextValue = new MonolingualTextData((Map<String, String>) value.value);
                return new MonolingualText(monolingualTextValue, queryService);
            case "quantity":
                QuantityData quantityValue = new QuantityData((Map<String, String>) value.value);
                return new Quantity(quantityValue, queryService);
            case "time":
                TimeData timeValue = new TimeData((Map<String, Object>) value.value);
                return new Time(timeValue, queryService);
            case "wikibase-entityid":
                EntityData entityValue = new EntityData((Map<String, Object>) value.value);
                return getDatum(dataType, queryService, entityValue);
        }

        throw new Error("Datatype: " + dataType + " not found");
    }

    /*
     * REQUIRES: dataType is a valid Wikidata datatype with an item as its backing, queryService is not null,
     * entityValue is not a valid EntityData corresponding to the dataType given
     * MODIFIES: none
     * EFFECTS :
     */
    private static Datum getDatum(String dataType, DatumQueryService queryService, EntityData entityValue) {
        try {
            switch (dataType) {
                case "wikibase-item":
                    return new Item(entityValue, queryService);
                case "wikibase-property":
                    return new Property(entityValue, queryService);
                case "wikibase-lexeme":
                    return new Lexeme(entityValue, queryService);
                case "wikibase-form":
                    return new Form(entityValue, queryService);
                case "wikibase-sense":
                    return new Sense(entityValue, queryService);
            }
        } catch (NotFoundException e) {
            throw new Error("ID: " + entityValue.id + " not found", e);
        }
        throw new Error("Datatype: " + dataType + " not found");
    }

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS :
     */
    @Override
    public void addNotify() {
        super.addNotify();
        if (!initialized) {
            initialized = true;
            Value thisValue = this;
            button = new JButton("◄");
            button.addActionListener(i -> {
                Container parent = getParent();
                if (parent instanceof StatementList) {
                    StatementList guInterface = (StatementList) parent;
                    guInterface.toggle(new ItemView(thisValue));
                }
            });
            add(button);
            JLabel idComp = new JLabel(getID());
            idComp.setForeground(Color.WHITE);
            add(idComp);
            JLabel separator = new JLabel(": ");
            separator.setForeground(Color.WHITE);
            add(separator);
            JLabel titleComp = new JLabel(getTitle());
            titleComp.setForeground(Color.WHITE);
            add(titleComp);
        }
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public abstract String getTitle();

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public abstract String getDescription();

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public String getID() {
        return id;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public abstract StatementList getStatements();

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS :
     */
    public abstract Boolean parse(List<String> subList);

    /*
     * REQUIRES: view is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public void setView(ItemView view) {
        this.view = view;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public boolean needsSearchBar() {
        return false;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public boolean needsRightArrow() {
        return true;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public DatumQueryService getQuery() {
        return queryService;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Value)) {
            return false;
        }

        Value value = (Value) o;

        return getID().equals(value.getID());
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public int hashCode() {
        int result = getDescription().hashCode();
        result = 31 * result;
        return result;
    }

    /*
     * REQUIRES: value is not null
     * MODIFIES:
     * EFFECTS :
     */
    protected Boolean toggleLeft(Value value) {
        if (this.view == null) {
            return false;
        }
        return view.toggleLeft(value);
    }

    /*
     * REQUIRES: value is not null and does not already exist
     * MODIFIES:
     * EFFECTS :
     */
    public void addStatement(Value value) {
        getStatements().add(value);
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    public String getImage() {
        return "";
    }
}
