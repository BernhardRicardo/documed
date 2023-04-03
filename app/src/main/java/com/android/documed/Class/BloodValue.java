package com.android.documed.Class;

public class BloodValue {
    private final double leucocytes;
    private final double lymphocytesPercent;
    private final double lymphocytesAbsolut;

    public double getLeucocytes() {
        return leucocytes;
    }

    public double getLymphocytesPercent() {
        return lymphocytesPercent;
    }

    public double getLymphocytesAbsolut() {
        return lymphocytesAbsolut;
    }

    public BloodValue(double leucocytes, double lymphocytesPercent, double lymphocytesAbsolut) {
        this.leucocytes = leucocytes;
        this.lymphocytesPercent = lymphocytesPercent;
        this.lymphocytesAbsolut = lymphocytesAbsolut;
    }
}
