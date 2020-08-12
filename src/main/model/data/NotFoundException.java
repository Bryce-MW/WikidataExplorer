package model.data;

public class NotFoundException extends Exception {
    /*
     * Class Description:
     *
     */
    public static final long serialVersionUID = 1L;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public NotFoundException(String urlStr) {
        super("Entity not found: " + urlStr);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public NotFoundException(String... tree) {
        super(buildErrorString(tree));
    }

    /*
     * REQUIRES:
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
