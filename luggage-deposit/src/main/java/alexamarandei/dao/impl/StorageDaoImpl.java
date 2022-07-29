package alexamarandei.dao.impl;

import java.util.Scanner;

import alexamarandei.dao.LuggageDao;
import alexamarandei.dao.StorageDao;
import alexamarandei.exceptions.AccessNotAllowedException;
import alexamarandei.exceptions.InvalidOptionException;
import alexamarandei.exceptions.InvalidValueException;
import alexamarandei.models.Pricing;
import alexamarandei.models.Storage;

/**
 * @see alexamarandei.dao.StorageDao
 *      Implementation of the Storage Data Access Object Interface
 */
public class StorageDaoImpl implements StorageDao {
    private Storage storage;
    private String adminCode;
    private LuggageDao luggageDao;

    //// Constructor ////

    /**
     * @param adminCode            The code used to prove whether or not the person
     *                             trying to alter the storage data is the admin.
     * @param numberOfStorageUnits Total number of spots available for luggage
     *                             depositing.
     * @param pricing              The price for the first hour of storage and for
     *                             the rest of the hours.
     */
    public StorageDaoImpl(
            String adminCode,
            int numberOfStorageUnits,
            Pricing pricing) {
        this.adminCode = adminCode;
        this.storage = Storage.getInstance(
                adminCode,
                numberOfStorageUnits,
                pricing);
        this.luggageDao = new LuggageDaoImpl();
    }

    //// Overridden Methods ////

    /**
     * @see alexamarandei.dao.StorageDao#accessAdminMode(java.util.Scanner)
     */
    @Override
    public void accessAdminMode(Scanner scanner) {
        System.out.println("\n-- Luggage Management --\n");
        System.out.println("- Please provide your access code: ");

        System.out.print("> ");
        String providedId = scanner.nextLine();

        try {
            if (!providedId.equals(adminCode)) {
                throw new AccessNotAllowedException("\nWrong Admin Access Code!\n");
            }

            boolean adminIsActive = true;
            while (adminIsActive) {
                System.out.println("\n-- Admin Option Menu --\n");
                System.out.println("1. Modify Number of Storage Units");
                System.out.println("2. Modify Pricing");
                System.out.println("3. Exit Admin Mode");

                System.out.print("\n> ");

                String command = scanner.nextLine();
                int choice = Integer.parseInt(command);

                switch (choice) {
                    case 1: {
                        modifyStorageUnits(scanner, adminCode);
                        break;
                    }
                    case 2: {
                        modifyPricing(scanner, adminCode);
                        break;
                    }
                    case 3: {
                        adminIsActive = false;
                        System.out.println("Exiting...");
                        break;
                    }
                    default: {
                        throw new InvalidOptionException(
                                "\nPlease provide a valid number from the options above!\n");
                    }
                }

                Thread.sleep(2000);
            }
        } catch (NumberFormatException | InvalidOptionException | AccessNotAllowedException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * @see alexamarandei.dao.StorageDao#getInfo()
     */
    @Override
    public void getInfo() {
        System.out.println("\n-- Storage Facility Info --\n");
        System.out.println("- Current vacancy: " + (storage.getNumberOfStorageUnits() - luggageDao.getCount()));
        System.out.println("- First Hour Price: " + getPricing().getFirstHourPrice());
        System.out.println("- Subsequent Hours Price: " + getPricing().getBaseHourPrice());
        System.out.println("\nNote: The time is rounded upwards! (e.g. 61 mins -> 2h)");
    }

    /**
     * @see alexamarandei.dao.StorageDao#getLuggageDao()
     */
    @Override
    public LuggageDao getLuggageDao() {
        return luggageDao;
    }

    /**
     * @see alexamarandei.dao.StorageDao#getPricing()
     */
    @Override
    public Pricing getPricing() {
        return storage.getPricing();
    }

    //// Private Methods ////

    /**
     * @param scanner   Provides input from the user.
     * @param adminCode The code used to prove whether or not the person
     *                  trying to alter the storage data is the admin.
     */
    private void modifyPricing(Scanner scanner, String adminCode) {
        System.out.println("\n-- Modify Pricing --\n");
        int firstHour = -1;
        int baseHour = -1;

        System.out.println("- Please provide a new value for the first hour: ");
        System.out.print("> ");

        try {
            firstHour = Integer.parseInt(scanner.nextLine());
            if (firstHour <= 0)
                throw new InvalidValueException("\nPlease provide a valid value for the price of the first hour!\n");

            System.out.println("- Please provide a new value for the subsequent hours: ");
            System.out.print("> ");

            baseHour = Integer.parseInt(scanner.nextLine());
            if (baseHour <= 0)
                throw new InvalidValueException(
                        "\nPlease provide a valid value for the price of the subsequent hours!\n");

            storage.setPricing(adminCode, new Pricing(firstHour, baseHour));
            System.out.println("\nValue successfully updated!");

        } catch (NumberFormatException | InvalidValueException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @param scanner   Provides input from the user.
     * @param adminCode The code used to prove whether or not the person
     *                  trying to alter the storage data is the admin.
     */
    private void modifyStorageUnits(Scanner scanner, String adminCode) {
        System.out.println("\n-- Modify Storage Units --\n");
        System.out.println("- Please provide a new value: ");
        System.out.print("> ");

        try {
            int units = Integer.parseInt(scanner.nextLine());
            if (units <= 0)
                throw new InvalidValueException("\nPlease provide a valid value for the number of storage units!\n");

            if (units < luggageDao.getCount()) {
                throw new InvalidValueException(
                        "\nThere are too many luggages to store in the space provided (" + luggageDao.getCount()
                                + ")! Try a larger value!\n");
            }

            storage.setNumberOfStorageUnits(adminCode, units);
            System.out.println("\nValue successfully updated!");

        } catch (NumberFormatException | InvalidValueException e) {
            System.err.println(e.getMessage());
        }
    }
}
