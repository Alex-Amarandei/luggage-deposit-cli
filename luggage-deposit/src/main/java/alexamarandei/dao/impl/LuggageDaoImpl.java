package alexamarandei.dao.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import alexamarandei.dao.LuggageDao;
import alexamarandei.exceptions.InvalidCodeFormatException;
import alexamarandei.exceptions.LuggageNotFoundException;
import alexamarandei.models.Luggage;
import alexamarandei.models.Pricing;

public class LuggageDaoImpl implements LuggageDao {
    private List<Luggage> luggages;

    //// Constructor ////

    public LuggageDaoImpl() {
        luggages = new ArrayList<>();
    }

    //// Overridden Methods ////

    @Override
    public boolean addLuggage(Scanner scanner, int luggageId) {
        System.out.println("\n-- Luggage Deposit --\n");
        System.out.println("- Please provide your last name: ");

        System.out.print("> ");

        try {
            String lastName = scanner.nextLine();

            if (lastName.length() == 0 || !lastName.matches("[a-zA-Z]+")) {
                throw new InvalidCodeFormatException("\nPlease provide a valid last name!\n");
            }

            luggages.add(new Luggage(luggageId + 1, lastName));
            System.out.println("\nYour unique code is: " + lastName.toUpperCase() + "#" + (luggageId + 1));
            System.out.println("\nNote: Don't lose it as you may not be able to retrieve your luggage!");

        } catch (InvalidCodeFormatException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public int getCost(Luggage luggage, Pricing pricing) {
        Duration duration = Duration.between(luggage.getTimeOfDeposit(), LocalDateTime.now());
        long hours = duration.toHours() + (duration.toMinutes() % 60 > 0 ? 1 : 0);

        int cost = pricing.getFirstHourPrice();
        hours--;

        if (hours > 0) {
            cost += hours * pricing.getBaseHourPrice();
        }

        return cost;
    }

    @Override
    public int getCount() {
        return luggages.size();
    }

    @Override
    public void inspectLuggage(Scanner scanner, Pricing pricing, boolean retrieve) {
        Optional<Luggage> luggage = getLuggage(scanner, retrieve);

        try {
            if (!luggage.isPresent()) {
                throw new LuggageNotFoundException("\nThis code has no associated luggage!\n");
            }

            System.out.println(luggage.get().toString());
            StringBuilder stringBuilder = new StringBuilder("- Accumulated Cost: ")
                    .append(getCost(luggage.get(), pricing))
                    .append("\n");
            System.out.println(stringBuilder.toString());

            if (retrieve) {
                luggages.remove(luggage.get());
                System.out.println("Luggage Retrieved!");
            }
        } catch (LuggageNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    //// Private Methods ////

    private int binarySearch(List<Luggage> luggages, int luggageId, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midId = luggages.get(mid).getLuggageId();

        if (luggageId == midId) {
            return mid;
        }

        if (luggageId < midId) {
            return binarySearch(luggages, luggageId, left, mid - 1);
        }

        return binarySearch(luggages, luggageId, mid + 1, right);
    }

    private boolean checkOwnership(Luggage luggage, String providedId) {
        String requiredId = luggage.getOwnerLastName().toUpperCase() + "#" + luggage.getLuggageId();

        return requiredId.equals(providedId);
    }

    private Optional<Luggage> getLuggage(Scanner scanner, boolean retrieve) {
        System.out.println("\n-- Luggage Management --\n");
        System.out.println("- Please provide your access code: ");

        System.out.print("> ");
        String providedId = scanner.nextLine();

        int potentialIndex = getLuggageIndexById(providedId);

        if (potentialIndex == -1) {
            return Optional.empty();
        }

        Luggage potentialLuggage = luggages.get(potentialIndex);

        if (checkOwnership(potentialLuggage, providedId)) {
            if (retrieve)
                luggages.remove(potentialIndex);
            return Optional.of(potentialLuggage);
        }

        return Optional.empty();
    }

    private int getLuggageIndexById(String providedId) {
        try {
            String[] parts = providedId.split("#", 2);
            int luggageId = Integer.parseInt(parts[1]);

            if (!parts[0].matches("[A-Z]+"))
                throw new InvalidCodeFormatException("\nThe last name part of the code is invalid!\n");

            return binarySearch(luggages, luggageId, 0, luggages.size() - 1);

        } catch (NumberFormatException | InvalidCodeFormatException e) {
            System.err.println(e.getMessage());
            return -1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("\nMissing # separator!");
            return -1;
        }
    }
}
