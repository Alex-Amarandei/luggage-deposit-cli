package alexamarandei.models;

public final class Storage {
    private static volatile Storage instance;

    private String adminCode;
    private int numberOfStorageUnits;
    private Pricing pricing;

    private Storage(
            String adminCode,
            int numberOfStorageUnits,
            Pricing pricing) {
        this.adminCode = adminCode;
        this.numberOfStorageUnits = numberOfStorageUnits;
        this.pricing = pricing;
    }

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

    public int getNumberOfStorageUnits() {
        return numberOfStorageUnits;
    }

    public void setNumberOfStorageUnits(String adminCode, int numberOfStorageUnits) {
        if (isAdmin(adminCode))
            this.numberOfStorageUnits = numberOfStorageUnits;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(String adminCode, Pricing pricing) {
        if (isAdmin(adminCode))
            this.pricing = pricing;
    }

    private boolean isAdmin(String adminCode) {
        if (this.adminCode.equals(adminCode))
            return true;

        System.err.println("You are not allowed to perform this kind of operation!");
        return false;
    }

}
