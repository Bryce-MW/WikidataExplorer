package model.prefrences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreferenceManagerTest {
    PreferenceManager preferenceManager;
    private ArrayList<UserPreference> prefs;

    @BeforeEach
    void setUp() {
        prefs = new ArrayList<>(1);
        prefs.add(new UserPreference() {
            @Override
            public String toString() {
                return "Test";
            }
        });
        preferenceManager = new PreferenceManager(prefs);
    }

    @Test
    void getPreferences() {
        assertEquals(prefs, preferenceManager.getPreferences());
    }

    @Test
    void testToString() {
        assertEquals("E: Preferences", preferenceManager.toString());
    }

    @Test
    void parse() {
        assertTrue(preferenceManager.parse(new ArrayList<>())); //Always true for any arguments
    }
}