package ui;

import model.data.Datum;
import model.data.ScopedSearch;
import model.data.Value;
import model.util.StringBuilderUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemView {
    //TODO: Implement
    private final Value item; // Q42, P137, L23 . . .

    // The following will need language support!
    private String title; // Douglas Addams
    private String description; // English writer and humorist

    private String photoRef; // May not implement depending on time
    private SearchBar searchBar; // Only present for Datum! TODO: (add needsSearchBar() to Value, true for Datum,
    // false otherwise.)
    private StatementList statements;

    public ItemView(Datum value) {
        this.item = value;
        // TODO: Probably won't add language support in first round (Just use english or first language listed)
        title = item.getTitle();
        description = item.getDescription();
        searchBar = new SearchBar(new ScopedSearch(item));
    }

    public void updateLanguage(String lang) {}

    public List<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(16);

        // First solid line
        result.add(new StringBuilder(item.getID()).insert(0, '┃'));
        result.add(new StringBuilder(title).insert(0, '┃'));
        result.add(new StringBuilder(description).insert(0, '┃')); // Will we want to format multi-line?
        // Light line
        result.add(new StringBuilder(searchBar.toString()).insert(0, '┃'));
        // Light line
        List<StringBuilder> statementLines = statements.toStringArray();
        statementLines.forEach((i) -> i.insert(0, '┃'));
        result.addAll(statements.toStringArray());
        // Last solid line

        StringBuilderUtil.padAll(result, ' ', 0);
        result.forEach((i) -> i.append('┃'));

        int maxLength = result.get(0).length();
        StringBuilder topLine = new StringBuilder(maxLength);
        StringBuilderUtil.pad(topLine, '━', maxLength);
        topLine.setCharAt(0, '┏');
        topLine.setCharAt(topLine.length() - 1, '┏');
        result.add(0, topLine);

        StringBuilder midLine = new StringBuilder(maxLength);
        StringBuilderUtil.pad(midLine, '─', maxLength);
        midLine.setCharAt(0, '┠');
        midLine.setCharAt(midLine.length() - 1, '┨');
        result.add(4, midLine);
        result.add(6, midLine);

        StringBuilder botLine = new StringBuilder(maxLength);
        StringBuilderUtil.pad(botLine, '━', maxLength);
        botLine.setCharAt(0, '┗');
        botLine.setCharAt(botLine.length() - 1, '┛');
        result.add(botLine);

        return result;
    }

    //TODO: Rendering Stuff
}
