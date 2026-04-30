package main;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }
    public double getValue() {
        return value;
    }
    public U getUnit() {
        return unit;
    }
    public enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });
        private final java.util.function.DoubleBinaryOperator operator;
        ArithmeticOperation(java.util.function.DoubleBinaryOperator operator) {
            this.operator = operator;
        }
        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }
        if (this.unit == null || other.unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot operate on different categories");
        }
        if (Double.isNaN(this.value) || Double.isInfinite(this.value) || Double.isNaN(other.value) || Double.isInfinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return operation.compute(thisBase, otherBase);
    }
    public Quantity<U> add(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double converted = this.unit.convertFromBaseUnit(resultBase);
        return new Quantity<>(converted, this.unit);
    }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double converted = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(converted, targetUnit);
    }
    public Quantity<U> subtract(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double converted = this.unit.convertFromBaseUnit(resultBase);
        return new Quantity<>(round(converted), this.unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double converted = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(round(converted), targetUnit);
    }
    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
        return resultBase;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Quantity)) return false;
        Quantity<?> other = (Quantity<?>) obj;
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }
        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return Math.abs(thisBase - otherBase) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }
    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}