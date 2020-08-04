package model.data;

import org.jetbrains.annotations.NotNull;

public class NotFoundException extends Exception {
    public static final long serialVersionUID = 1L;

    public NotFoundException(String urlStr) {
        super("Entity not found: " + urlStr);
    }

    public NotFoundException(String @NotNull ... tree) {
        super(buildErrorString(tree));
    }

    private static @NotNull String buildErrorString(String @NotNull [] tree) {
        StringBuilder errorString = new StringBuilder(tree.length * 5 + 28);
        errorString.append("Entity component not found: ");
        for (String s : tree) {
            errorString.append(s).append(".");
        }
        return errorString.toString();
    }
}
