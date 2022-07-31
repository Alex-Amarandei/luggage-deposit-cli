package alexamarandei.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.security.SecureRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class StorageTest {
    private static volatile Storage instance;

    private String adminCode = "@DM1n";
    private int numberOfStorageUnits;
    private Pricing pricing;

    @BeforeEach
    protected void setUp() {
        SecureRandom secureRandom = new SecureRandom();

        numberOfStorageUnits = secureRandom.nextInt();
        pricing = new Pricing(secureRandom.nextInt(), secureRandom.nextInt());
    }

    @Test
    @Order(1)
    public void testConstructorGivenNoInstance() {
        Storage storage = Storage.getInstance(adminCode, numberOfStorageUnits, pricing);

        assertEquals(numberOfStorageUnits, storage.getNumberOfStorageUnits());
        assertEquals(pricing.getBaseHourPrice(), storage.getPricing().getBaseHourPrice());
        assertEquals(pricing.getFirstHourPrice(), storage.getPricing().getFirstHourPrice());
    }

    @Test
    @Order(2)
    public void testConstructorGivenInstance() {
        Storage storage = Storage.getInstance(adminCode, numberOfStorageUnits, pricing);

        setUp();

        Storage sameStorage = Storage.getInstance(adminCode, numberOfStorageUnits, pricing);

        assertEquals(storage, sameStorage);
    }

    @Test
    @Order(3)
    public void testSettersGivenAdminCode() {
        SecureRandom secureRandom = new SecureRandom();

        int newNumberOfStorageUnits = secureRandom.nextInt();
        Pricing newPricing = new Pricing(secureRandom.nextInt(), secureRandom.nextInt());

        Storage storage = Storage.getInstance(adminCode, numberOfStorageUnits, pricing);

        storage.setNumberOfStorageUnits(adminCode, newNumberOfStorageUnits);
        storage.setPricing(adminCode, newPricing);

        assertEquals(newNumberOfStorageUnits, storage.getNumberOfStorageUnits());
        assertEquals(newPricing, storage.getPricing());

    }

    @Test
    @Order(4)
    public void testSettersGivenNoAdminCode() {
        SecureRandom secureRandom = new SecureRandom();

        int newNumberOfStorageUnits = secureRandom.nextInt();
        Pricing newPricing = new Pricing(secureRandom.nextInt(), secureRandom.nextInt());

        Storage storage = Storage.getInstance(adminCode, numberOfStorageUnits, pricing);

        storage.setNumberOfStorageUnits("adminCode", newNumberOfStorageUnits);
        storage.setPricing("adminCode", newPricing);

        assertNotEquals(newNumberOfStorageUnits, storage.getNumberOfStorageUnits());
        assertNotEquals(newPricing, storage.getPricing());

    }
}
