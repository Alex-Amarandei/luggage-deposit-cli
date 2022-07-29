package alexamarandei.models;

/**
 * A Model for a Pricing Structure, encapsulating:
 * - the price of the first hour
 * - the price of all subsequent hours
 */
public class Pricing {
    private int baseHourPrice;
    private int firstHourPrice;

    //// Constructor ////

    /**
     * @param firstHourPrice The price of the first hour of luggage storage
     * @param baseHourPrice  The price of all subsequent hours of luggage storage
     */
    public Pricing(int firstHourPrice, int baseHourPrice) {
        this.firstHourPrice = firstHourPrice;
        this.baseHourPrice = baseHourPrice;
    }

    //// Getters ////

    /**
     * @return The price of all subsequent hours of luggage storage
     */
    public int getBaseHourPrice() {
        return baseHourPrice;
    }

    /**
     * @return The price of the first hour of luggage storage
     */
    public int getFirstHourPrice() {
        return firstHourPrice;
    }

    //// Setters ////

    /**
     * @param baseHourPrice The price of all subsequent hours of luggage storage
     */
    public void setBaseHourPrice(int baseHourPrice) {
        this.baseHourPrice = baseHourPrice;
    }

    /**
     * @param firstHourPrice The price of the first hour of luggage storage
     */
    public void setFirstHourPrice(int firstHourPrice) {
        this.firstHourPrice = firstHourPrice;
    }
}
