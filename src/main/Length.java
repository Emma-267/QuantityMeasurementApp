package main;

public class Length {
    private final double value;
    private final LengthUnit unit;
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);
        final double toInchesFactor;
        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }
        public double toBaseUnit(double value) {
            return value * toInchesFactor;
        }
    }
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }
    public double getValue() {
        return value;
    }
    public LengthUnit getUnit() {
        return unit;
    }
    private double toInches() {
        return unit.toBaseUnit(value);
    }
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double valueInInches = this.toInches();
        double convertedValue = valueInInches / targetUnit.toInchesFactor;
        return new Length(convertedValue, targetUnit);
    }
    public Length add(Length thatLength) {
        return add(thatLength, this.unit); // delegate to UC7
    }
    public Length add(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null) {
            throw new IllegalArgumentException("Length to add cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!Double.isFinite(this.value) || !Double.isFinite(thatLength.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        double sumInInches = this.toInches() + thatLength.toInches();
        double resultValue = convertFromBaseToTargetUnit(sumInInches, targetUnit);
        resultValue = Math.round(resultValue * 1e6) / 1e6;
        return new Length(resultValue, targetUnit);
    }
    private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
        return lengthInInches / targetUnit.toInchesFactor;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.name() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length that = (Length) o;
        double EPSILON = 1e-6;
        return Math.abs(this.toInches() - that.toInches()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toInches());
    }
}