package alexamarandei;

import java.util.Scanner;

import alexamarandei.dao.LuggageDao;
import alexamarandei.dao.StorageDao;
import alexamarandei.dao.impl.StorageDaoImpl;
import alexamarandei.models.Pricing;

public class App {
    static public int LUGGAGE_COUNTER = 0;

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
            } catch (NumberFormatException e) {
                System.err.println("Please provide a valid number from the options above!");
                continue;
            }

            switch (choice) {
                case 1: {
                    storageDao.getInfo();
                    break;
                }
                case 2: {
                    luggageDao.addLuggage(scanner, ++LUGGAGE_COUNTER);
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
                    System.err.println("Please provide a valid number from the options above!");
                }
            }
        }

        scanner.close();
        System.out.println("----  Bye, Bye! ----");
    }
}
