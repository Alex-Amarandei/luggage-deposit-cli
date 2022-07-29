package alexamarandei.models;

import alexamarandei.exceptions.AccessNotAllowedException;

public final class Storage {
    private static volatile Storage instance;

    private String adminCode;
    private int numberOfStorageUnits;
    private Pricing pricing;

    //// Constructor ////

    private Storage(
            String adminCode,
            int numberOfStorageUnits,
            Pricing pricing) {
        this.adminCode = adminCode;
        this.numberOfStorageUnits = numberOfStorageUnits;
        this.pricing = pricing;
    }

    //// Singleton Instance Provider ////

    public static Storage getInstance(
            String adminCode,
            int numberOfStorageUnits,
            Pricing pricing) {
        Storage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Storage.class) {
            if (instance == null) {
                instance = new Storage(
                        adminCode,
                        numberOfStorageUnits,
                        pricing);
            }
            return instance;
        }
    }

    //// Getters ////

    public int getNumberOfStorageUnits() {
        return numberOfStorageUnits;
    }

    public Pricing getPricing() {
        return pricing;
    }

    //// Setters ////

    public void setNumberOfStorageUnits(String adminCode, int numberOfStorageUnits) {
        if (isAdmin(adminCode))
            this.numberOfStorageUnits = numberOfStorageUnits;
    }

    public void setPricing(String adminCode, Pricing pricing) {
        if (isAdmin(adminCode))
            this.pricing = pricing;
    }

    //// Private Methods ////

    private boolean isAdmin(String adminCode) {
        try {
            if (!this.adminCode.equals(adminCode))
                throw new AccessNotAllowedException("\nYou are not allowed to perform this kind of operation!\n");

            return true;

        } catch (AccessNotAllowedException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
