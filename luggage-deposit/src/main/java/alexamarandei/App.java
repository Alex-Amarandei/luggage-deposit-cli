package alexamarandei;

import java.util.Scanner;

import alexamarandei.dao.LuggageDao;
import alexamarandei.dao.StorageDao;
import alexamarandei.dao.impl.StorageDaoImpl;
import alexamarandei.exceptions.InvalidOptionException;
import alexamarandei.models.Pricing;

/**
 * Entry point for the StorageManager 1.0 App.
 */
public class App {
    /**
     * The number of luggages ever deposited in the storage facility.
     * Only incremented and never decremented.
     * Used in creating the unique client id.
     */
    public static int luggageCounter = 0;

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        boolean sessionIsActive = true;
        StorageDao storageDao = new StorageDaoImpl("@DM1n", 100, new Pricing(10, 5));
        LuggageDao luggageDao = storageDao.getLuggageDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n---- Hello and welcome to StorageManager 1.0! ----\n");

        while (sessionIsActive) {
            System.out.println("\n--- Option Menu (Type the corresponding number) ---\n");
            System.out.println("1. Get Storage Info");
            System.out.println("2. Add Luggage");
            System.out.println("3. Get Luggage Info");
            System.out.println("4. Retrieve Luggage");
            System.out.println("5. Access Admin Mode");
            System.out.println("6. Exit StorageManager");

            System.out.print("\n> ");

            String command = scanner.nextLine();
            int choice = 0;

            try {
                choice = Integer.parseInt(command);

                switch (choice) {
                    case 1: {
                        storageDao.getInfo();
                        break;
                    }
                    case 2: {
                        if (luggageDao.addLuggage(scanner, luggageCounter))
                            ++luggageCounter;
                        break;
                    }
                    case 3: {
                        luggageDao.inspectLuggage(scanner, storageDao.getPricing(), false);
                        break;
                    }
                    case 4: {
                        luggageDao.inspectLuggage(scanner, storageDao.getPricing(), true);
                        break;
                    }
                    case 5: {
                        storageDao.accessAdminMode(scanner);
                        break;
                    }
                    case 6: {
                        sessionIsActive = false;
                        break;
                    }
                    default: {
                        throw new InvalidOptionException("\nPlease provide a valid number from the options above!\n");
                    }
                }

                Thread.sleep(2000);
            } catch (NumberFormatException | InvalidOptionException e) {
                System.err.println(e.getMessage());
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        scanner.close();
        System.out.println("\n----  Bye, Bye! ----\n");
    }
}
