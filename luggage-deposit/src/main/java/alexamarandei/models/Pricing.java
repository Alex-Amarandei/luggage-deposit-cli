package alexamarandei.models;

public class Pricing {
    private int baseHourPrice;
    private int firstHourPrice;

    //// Constructor ////

    public Pricing(int firstHourPrice, int baseHourPrice) {
        this.firstHourPrice = firstHourPrice;
        this.baseHourPrice = baseHourPrice;
    }

    //// Getters ////

    public int getBaseHourPrice() {
        return baseHourPrice;
    }

    public int getFirstHourPrice() {
        return firstHourPrice;
    }

    //// Setters ////

    public void setBaseHourPrice(int baseHourPrice) {
        this.baseHourPrice = baseHourPrice;
    }

    public void setFirstHourPrice(int firstHourPrice) {
        this.firstHourPrice = firstHourPrice;
    }
}
