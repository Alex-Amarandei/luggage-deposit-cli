package alexamarandei.dao.impl;

import java.util.Scanner;

import alexamarandei.dao.LuggageDao;
import alexamarandei.dao.StorageDao;
import alexamarandei.models.Pricing;
import alexamarandei.models.Storage;

public class StorageDaoImpl implements StorageDao {
    private Storage storage;
    private String adminCode;
    private LuggageDao luggageDao;

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

    @Override
    public LuggageDao getLuggageDao() {
        return luggageDao;
    }

    @Override
    public void getInfo() {
        System.out.println("\n-- Storage Facility Info --\n");
        System.out.println("- Current vacancy: " + (storage.getNumberOfStorageUnits() - luggageDao.getCount()));
        System.out.println("- First Hour Price: " + getPricing().firstHourPrice);
        System.out.println("- Subsequent Hours Price: " + getPricing().baseHourPrice);
        System.out.println("\nNote: The time is rounded upwards! (e.g. 61 mins -> 2h)");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }

    @Override
    public Pricing getPricing() {
        return storage.getPricing();
    }

    @Override
    public void accessAdminMode(Scanner scanner) {
    }
}
