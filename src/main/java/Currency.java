public enum Currency {
    RUPEE(1),
    DOLLAR(75);

    private final double CONVERSION_FACTOR;

    Currency(double conversion_factor) {
        CONVERSION_FACTOR = conversion_factor;
    }

    public double getConvertedAmountToBase(double magnitude) {
        return magnitude * this.CONVERSION_FACTOR;
    }

    public double getConvertedAmountToSpecificCurrency(double magnitude, Currency currency) {
        return magnitude / this.CONVERSION_FACTOR;
    }
}
