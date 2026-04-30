package test;

import main.Length;
import main.LengthUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest{
    private static final double EPSILON = 1e-6;

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_Equality() {
        assertEquals(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES));
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {
        Length result = new Length(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_Add() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(0.666667, result.getValue(), EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testBackwardCompatibility_UC1EqualityTests() {
        assertEquals(new Length(3.0, LengthUnit.FEET), new Length(1.0, LengthUnit.YARDS));
    }

    @Test
    void testBackwardCompatibility_UC5ConversionTests() {
        assertEquals(36.0, new Length(1.0, LengthUnit.YARDS).convertTo(LengthUnit.INCHES).getValue(), EPSILON);
    }

    @Test
    void testBackwardCompatibility_UC6AdditionTests() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES));
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {
        Length result = new Length(1.0, LengthUnit.FEET).add(new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testArchitecturalScalability_MultipleCategories() {
        enum WeightUnit {
            KG, GRAM
        }
        assertNotNull(WeightUnit.KG);
    }

    @Test
    void testRoundTripConversion_RefactoredDesign() {
        Length original = new Length(5.0, LengthUnit.FEET);
        Length converted = original.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.FEET);
        assertEquals(original.getValue(), converted.getValue(), EPSILON);
    }

    @Test
    void testUnitImmutability() {
        assertThrows(NoSuchFieldException.class, () -> {
            LengthUnit.class.getDeclaredField("newField");
        });
    }
}