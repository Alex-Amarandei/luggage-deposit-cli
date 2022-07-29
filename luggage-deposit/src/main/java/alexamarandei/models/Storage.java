package alexamarandei.models;

import alexamarandei.exceptions.AccessNotAllowedException;

/**
 * A Model for a Storage facility, encapsulating:
 * - the Admin's access code
 * - The total number of spots available for luggage depositing
 * - the pricing structure of the facility
 */
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

    /**
     * @param adminCode            The Admin's access code
     * @param numberOfStorageUnits The total number of spots available for luggage
     *                             depositing
     * @param pricing              The pricing structure of the facility
     * 
     * @return The Singleton instance of the storage
     */
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

    /**
     * @return The total number of spots available for luggage
     *         depositing
     */
    public int getNumberOfStorageUnits() {
        return numberOfStorageUnits;
    }

    /**
     * @return The pricing structure of the facility
     */
    public Pricing getPricing() {
        return pricing;
    }

    //// Setters ////

    /**
     * @param adminCode            The code used to prove whether or not the person
     *                             trying to alter the storage data is the admin.
     * @param numberOfStorageUnits Total number of spots available for luggage
     *                             depositing.
     */
    public void setNumberOfStorageUnits(String adminCode, int numberOfStorageUnits) {
        if (isAdmin(adminCode))
            this.numberOfStorageUnits = numberOfStorageUnits;
    }

    /**
     * @param adminCode The code used to prove whether or not the person
     *                  trying to alter the storage data is the admin.
     * @param pricing   The price for the first hour of storage and for
     *                  the rest of the hours.
     */
    public void setPricing(String adminCode, Pricing pricing) {
        if (isAdmin(adminCode))
            this.pricing = pricing;
    }

    //// Private Methods ////

    /**
     * @param adminCode The code used to prove whether or not the person
     *                  trying to alter the storage data is the admin.
     * 
     * @return Whether or not the code provided is the Admin access code.
     */
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
