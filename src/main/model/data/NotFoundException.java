package model.data;

public class NotFoundException extends Exception {
    public static final long serialVersionUID = 1L;

    public NotFoundException(String urlStr) {
        super("Entity not found: " + urlStr);
    }

    public NotFoundException(String... tree) {
        super(buildErrorString(tree));
    }

    private static String buildErrorString(String[] tree) {
        StringBuilder errorString = new StringBuilder(tree.length * 5 + 28);
        errorString.append("Entity component not found: ");
        for (String s : tree) {
            errorString.append(s).append(".");
        }
        return errorString.toString();
    }
}
