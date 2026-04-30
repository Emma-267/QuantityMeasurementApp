package main;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value = value;
        this.unit = unit;
    }
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }
    private double fromBase(double baseValue, U targetUnit) {
        return targetUnit.convertFromBaseUnit(baseValue);
    }
    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }
        double thisBase = this.toBase();
        double otherBase = other.toBase();
        return Math.abs(thisBase - otherBase) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), round(toBase()));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
    public Quantity<U> convertTo(U targetUnit) {
        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(converted), targetUnit);
    }
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateSameCategory(other);
        double resultBase = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(round(result), targetUnit);
    }
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateSameCategory(other);
        double resultBase = this.toBase() - other.toBase();
        double result = targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(round(result), targetUnit);
    }
    public double divide(Quantity<U> other) {
        validateSameCategory(other);
        double divisor = other.toBase();
        if (Math.abs(divisor) < 0.0001) {
            throw new ArithmeticException("Cannot divide by zero quantity");
        }
        return this.toBase() / divisor;
    }
    private void validateSameCategory(Quantity<?> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories not allowed");
        }
    }
}