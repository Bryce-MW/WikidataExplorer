package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CLInterface;

import static org.junit.jupiter.api.Assertions.*;

class CLInterfaceTest {

    @BeforeEach
    public void before() {
        // Nothing needs to happen
    }

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