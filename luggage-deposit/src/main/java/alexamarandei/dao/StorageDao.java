package alexamarandei.dao;

import java.util.Scanner;

import alexamarandei.models.Pricing;

/**
 * A Data Access Object Interface for managing a storage facility.
 */
public interface StorageDao {
    /**
     * @param scanner Provides input for the user.
     */
    public void accessAdminMode(Scanner scanner);

    /**
     * Prints information about the storage facility in the console:
     * - current vacancy
     * - pricing structure
     */
    public void getInfo();

    /**
     * @return Returns the Luggage Data Access Object Interface implementation for
     *         the luggage found in the storage.
     */
    public LuggageDao getLuggageDao();

    /**
     * @return The pricing structure of the storage facility.
     */
    public Pricing getPricing();
}
