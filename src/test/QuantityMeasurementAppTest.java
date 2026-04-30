package test;

import main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testArithmeticOperation_Add_EnumComputation() {
        assertEquals(15.0, Quantity.ArithmeticOperation.ADD.compute(10, 5));
    }

    @Test
    public void testArithmeticOperation_Subtract_EnumComputation() {
        assertEquals(5.0, Quantity.ArithmeticOperation.SUBTRACT.compute(10, 5));
    }

    @Test
    public void testArithmeticOperation_Divide_EnumComputation() {
        assertEquals(2.0, Quantity.ArithmeticOperation.DIVIDE.compute(10, 5));
    }

    @Test
    public void testArithmeticOperation_DivideByZero_EnumThrows() {
        assertThrows(ArithmeticException.class, () -> Quantity.ArithmeticOperation.DIVIDE.compute(10, 0));
    }

    @Test
    public void testAdd_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        assertEquals(new Quantity<>(11, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    public void testDivide_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    public void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
    }

    @Test
    public void testValidation_CrossCategory_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> q2 = new Quantity<>(5, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> q1.add((Quantity) q2));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract((Quantity) q2));
        assertThrows(IllegalArgumentException.class, () -> q1.divide((Quantity) q2));
    }

    @Test
    public void testEnumConstant_ADD_CorrectlyAdds() {
        assertEquals(10.0, Quantity.ArithmeticOperation.ADD.compute(7, 3));
    }

    @Test
    public void testEnumConstant_SUBTRACT_CorrectlySubtracts() {
        assertEquals(4.0, Quantity.ArithmeticOperation.SUBTRACT.compute(7, 3));
    }

    @Test
    public void testEnumConstant_DIVIDE_CorrectlyDivides() {
        assertEquals(3.5, Quantity.ArithmeticOperation.DIVIDE.compute(7, 2));
    }

    @Test
    public void testImplicitTargetUnit_AddSubtract() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testImmutability_AfterAdd() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);
        q1.add(q2);
        assertEquals(10.0, q1.getValue());
    }

    @Test
    public void testImmutability_AfterSubtract() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);
        q1.subtract(q2);
        assertEquals(10.0, q1.getValue());
    }

    @Test
    public void testArithmetic_Chain_Operations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2).subtract(q3);
        assertEquals(7.0, result.getValue());
    }

    @Test
    public void testAllOperations_AcrossAllCategories() {
        Quantity<LengthUnit> length = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5, WeightUnit.KILOGRAM);
        Quantity<VolumeUnit> volume = new Quantity<>(1, VolumeUnit.LITRE);
        assertThrows(IllegalArgumentException.class, () -> length.add((Quantity) weight));
        assertThrows(IllegalArgumentException.class, () -> length.subtract((Quantity) volume));
    }
}