package test;

import main.Length;
import main.Length.LengthUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(0.666667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length result = new Length(2.0, LengthUnit.YARDS).add(new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length result = new Length(2.0, LengthUnit.YARDS).add(new Length(3.0, LengthUnit.FEET), LengthUnit.FEET);
        assertEquals(9.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);
        Length result1 = a.add(b, LengthUnit.YARDS);
        Length result2 = b.add(a, LengthUnit.YARDS);
        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        Length result = new Length(5.0, LengthUnit.FEET).add(new Length(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(1.666667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length result = new Length(5.0, LengthUnit.FEET).add(new Length(-2.0, LengthUnit.FEET), LengthUnit.INCHES);
        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class, () -> l1.add(l2, null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length result = new Length(1000.0, LengthUnit.FEET).add(new Length(500.0, LengthUnit.FEET), LengthUnit.INCHES);
        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length result = new Length(12.0, LengthUnit.INCHES).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(0.666667, result.getValue(), EPSILON);
    }
}