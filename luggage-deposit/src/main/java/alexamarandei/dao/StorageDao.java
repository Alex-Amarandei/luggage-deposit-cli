package alexamarandei.dao;

import java.util.Scanner;

import alexamarandei.models.Pricing;

public interface StorageDao {
    public void accessAdminMode(Scanner scanner);

    public void getInfo();

    public LuggageDao getLuggageDao();

    public Pricing getPricing();
}
