package main;

public class Length {
    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 1e-6;
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
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        converted = Math.round(converted * 1e6) / 1e6;
        return new Length(converted, targetUnit);
    }
    public Length add(Length thatLength) {
        return add(thatLength, this.unit);
    }
    public Length add(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double sumBase = this.toBaseUnit() + thatLength.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);
        result = Math.round(result * 1e6) / 1e6;
        return new Length(result, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length that = (Length) o;
        return Math.abs(this.toBaseUnit() - that.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}