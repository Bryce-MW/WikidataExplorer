package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CLInterfaceTest {

    @Test
    public void testMain() {
        assertDoesNotThrow(() -> CLInterface.main(new String[]{"exit"}));
        assertDoesNotThrow(() -> CLInterface.main(new String[]{"IntelliJ", "exit"}));
        assertDoesNotThrow(() -> CLInterface.main(new String[]{"web", "exit"}));
        // The only way this should fail is by an exception
    }

    @Test
    public void testCLInterface() {
        assertThrows(Error.class, CLInterface::new);
    }

    @Test
    void testParse() {
        assertDoesNotThrow(() -> CLInterface.main(new String[]{" ", "exit"}));
        assertDoesNotThrow(() -> CLInterface.main(new String[]{"S", "exit"}));
        assertDoesNotThrow(() -> CLInterface.main(new String[]{"save", "exit", "web"}));
        assertDoesNotThrow(() -> CLInterface.main(new String[]{"save", "exit"}));
        assertDoesNotThrow(() -> CLInterface.main(new String[]{"load", "exit", "web"}));
    }
}