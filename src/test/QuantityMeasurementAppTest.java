package test;

import main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        IMeasurable unit = LengthUnit.FEET;
        assertEquals(1.0, unit.getConversionFactor());
        assertEquals("FEET", unit.getUnitName());
        assertEquals(12.0, unit.convertToBaseUnit(12.0));
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        IMeasurable unit = WeightUnit.KILOGRAM;
        assertEquals(1.0, unit.getConversionFactor());
        assertEquals("KILOGRAM", unit.getUnitName());
        assertEquals(1.0, unit.convertToBaseUnit(1.0));
    }

    @Test
    void testIMeasurableInterface_ConsistentBehavior() {
        IMeasurable l = LengthUnit.INCHES;
        IMeasurable w = WeightUnit.GRAM;
        assertNotNull(l.getConversionFactor());
        assertNotNull(w.getConversionFactor());
    }

    @Test
    void testGenericQuantity_LengthOperations_Equality() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES));
    }

    @Test
    void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testGenericQuantity_WeightOperations_Equality() {
        assertEquals(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testCrossCategoryPrevention_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testBackwardCompatibility_AllUC1Through9Tests() {
        Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(l, new Quantity<>(12.0, LengthUnit.INCHES));
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals(w, new Quantity<>(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a, b);
    }

    @Test
    void testEnumAsUnitCarrier_BehaviorEncapsulation() {
        assertEquals(1.0, LengthUnit.FEET.convertToBaseUnit(1.0));
    }

    @Test
    void testImmutability_GenericQuantity() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = q.convertTo(LengthUnit.INCHES);
        assertNotSame(q, converted);
        assertEquals(12.0, converted.getValue(), EPSILON);
    }
}