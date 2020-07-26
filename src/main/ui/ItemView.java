package ui;

import model.data.Value;

public class ItemView {
    //TODO: Implement
    private Value item;
    private String id; // Q42, P137, L23 . . .

    // The following will need language support!
    private String title; // Douglas Addams
    private String description; // English writer and humorist

    private String photoRef; // May not implement depending on time
    private SearchBar searchBar; // Only present for Datum! TODO: (add needsSearchBar() to Value, true for Datum,
    // false otherwise.)
    private StatementList statements;

    public ItemView(String id) {
        this.id = id;
        //TODO: Actually get the data
    }

    public void updateLanguage(String lang) {}

    //TODO: Rendering Stuff
}
