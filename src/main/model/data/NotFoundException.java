package model.data;

public class NotFoundException extends Exception {
    /*
     * Class Description:
     * This exception is thrown when information for a specific ID is attempted to be gathered but it could not be.
     * This is usually due to an ID not existing but it could also be due to the local database not having the item
     * and the web collector is not enabled. There could be other IO issues as well which cause this. I mostly handle
     *  the catching of this correctly however it is not perfect and it is not thrown in call of the cases where it
     * should be which can cause some NullPointerExceptions since I don't always check things to make suure that they
     *  actually exist.
     */
    public static final long serialVersionUID = 1L;

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    public NotFoundException(String urlStr) {
        super("Entity not found: " + urlStr);
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    public NotFoundException(String... tree) {
        super(buildErrorString(tree));
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    private static String buildErrorString(String[] tree) {
        StringBuilder errorString = new StringBuilder(tree.length * 5 + 28);
        errorString.append("Entity component not found: ");
        for (String s : tree) {
            errorString.append(s).append(".");
        }
        return errorString.toString();
    }
}
