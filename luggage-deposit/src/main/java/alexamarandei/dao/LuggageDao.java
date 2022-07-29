package alexamarandei.dao;

import java.util.Scanner;

import alexamarandei.models.Luggage;
import alexamarandei.models.Pricing;

public interface LuggageDao {
    public void addLuggage(Scanner scanner, int luggageId);

    public int getCost(Luggage luggage, Pricing pricing);

    public int getCount();

    public void inspectLuggage(Scanner scanner, Pricing pricing, boolean retrieve);
}
