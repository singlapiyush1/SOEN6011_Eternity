import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerCalculatorGUITest {

    @Test
    public void testPositiveBasePositiveExponent() {
        assertEquals(8.0, PowerCalculatorGUI.pow(2, 3), 0.0001);
    }

    @Test
    public void testPositiveBaseZeroExponent() {
        assertEquals(1.0, PowerCalculatorGUI.pow(5, 0), 0.0001);
    }

    @Test
    public void testPositiveBaseNegativeExponent() {
        assertEquals(0.25, PowerCalculatorGUI.pow(2, -2), 0.0001);
    }

    @Test
    public void testZeroBasePositiveExponent() {
        assertEquals(0.0, PowerCalculatorGUI.pow(0, 3), 0.0001);
    }

    @Test
    public void testZeroBaseZeroExponent() {
        assertEquals(1.0, PowerCalculatorGUI.pow(0, 0), 0.0001);
    }

}
