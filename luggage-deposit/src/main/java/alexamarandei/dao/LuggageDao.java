package alexamarandei.dao;

import java.util.Scanner;

import alexamarandei.models.Luggage;
import alexamarandei.models.Pricing;

/**
 * A Data Access Object Interface for managing multiple luggages found
 * in storage.
 */
public interface LuggageDao {
    /**
     * @param scanner   Provides input from the user.
     * @param luggageId The id the new luggage will have if the data provided in the
     *                  process is valid.
     * 
     * @return Whether or not the luggage adding (creating) process was successful.
     */
    public boolean addLuggage(Scanner scanner, int luggageId);

    /**
     * @param luggage The luggage for which to calculate the accumulated cost.
     * @param pricing The price for the first hour of storage and for
     *                the rest of the hours.
     * 
     * @return The accumulated cost of storing the luggage, given the pricing
     *         structure.
     */
    public int getCost(Luggage luggage, Pricing pricing);

    /**
     * @return The number of luggages currently in storage.
     */
    public int getCount();

    /**
     * @param scanner  Provides input from the user.
     * @param pricing  The price for the first hour of storage and for
     *                 the rest of the hours.
     * @param retrieve Whether or not the luggage is to be retrieved after getting
     *                 information about it.
     */
    public void inspectLuggage(Scanner scanner, Pricing pricing, boolean retrieve);
}
