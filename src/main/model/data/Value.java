package model.data;

import model.data.additional.*;
import model.data.additional.helpers.*;
import model.data.pages.Item;
import model.data.pages.Property;
import model.data.pages.language.Form;
import model.data.pages.language.Lexeme;
import model.data.pages.language.Sense;
import model.data.source.template.DataValue;
import ui.cli.ItemView;
import ui.cli.StatementList;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public abstract class Value extends JComponent {
    /*
     * Class Description:
     *
     */
    protected DatumQueryService queryService;
    protected ItemView view = null;
    protected final String id;
    protected boolean initialized;
    protected JButton button;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    protected Value(DatumQueryService queryService, String id) {
        this.id = id;
        this.queryService = queryService;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    /*
     * REQUIRES:
     * MODIFIES:
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
     * REQUIRES:
     * MODIFIES:
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
     * REQUIRES:
     * MODIFIES:
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
     * REQUIRES:
     * MODIFIES:
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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract String getTitle();

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract String getDescription();

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public String getID() {
        return id;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract StatementList getStatements();

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract Boolean parse(List<String> subList);

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void setView(ItemView view) {
        this.view = view;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean needsSearchBar() {
        return false;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean needsRightArrow() {
        return true;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public DatumQueryService getQuery() {
        return queryService;
    }

    /*
     * REQUIRES:
     * MODIFIES:
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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public int hashCode() {
        int result = getDescription().hashCode();
        result = 31 * result;
        return result;
    }

    /*
     * REQUIRES:
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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void addStatement(Value value) {
        getStatements().add(value);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public String getImage() {
        return "";
    }
}
