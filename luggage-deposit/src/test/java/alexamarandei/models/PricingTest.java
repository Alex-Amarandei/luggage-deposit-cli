package alexamarandei.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

public class PricingTest {
    protected int baseHourPrice;
    protected int firstHourPrice;

    protected void setUp() {
        SecureRandom secureRandom = new SecureRandom();

        baseHourPrice = secureRandom.nextInt();
        firstHourPrice = secureRandom.nextInt();
    }

    @Test
    public void testConstructor() {
        Pricing pricing = new Pricing(firstHourPrice, baseHourPrice);

        assertEquals(firstHourPrice, pricing.getFirstHourPrice());
        assertEquals(baseHourPrice, pricing.getBaseHourPrice());
    }

    @Test
    public void testSetters() {
        Pricing pricing = new Pricing(firstHourPrice, baseHourPrice);

        SecureRandom secureRandom = new SecureRandom();

        int newBaseHourPrice = secureRandom.nextInt();
        int newFirstHourPrice = secureRandom.nextInt();

        pricing.setBaseHourPrice(newBaseHourPrice);
        pricing.setFirstHourPrice(newFirstHourPrice);

        assertEquals(newFirstHourPrice, pricing.getFirstHourPrice());
        assertEquals(newBaseHourPrice, pricing.getBaseHourPrice());
    }

}
