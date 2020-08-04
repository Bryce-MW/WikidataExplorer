package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CLInterfaceTest {

    @Test
    public void testMain() {
        CLInterface.main(new String[]{"exit"});
        // The only way this should fail is by an exception
    }

    @Test
    public void testCLInterface() {
        assertThrows(Error.class, CLInterface::new);
    }
}