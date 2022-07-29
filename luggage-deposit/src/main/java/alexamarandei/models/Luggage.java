package alexamarandei.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Luggage {
    private int luggageId;
    private String ownerLastName;
    private LocalDateTime timeOfDeposit;

    //// Constructor ////

    public Luggage(int luggageId, String ownerLastName) {
        this.luggageId = luggageId;
        this.ownerLastName = ownerLastName;
        this.timeOfDeposit = LocalDateTime.now();
    }

    //// Getters ////

    public int getLuggageId() {
        return luggageId;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public LocalDateTime getTimeOfDeposit() {
        return timeOfDeposit;
    }

    //// Overridden Methods ////

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n-- Luggage #")
                .append(luggageId)
                .append(" --\n\n")
                .append("- Owner: ")
                .append(ownerLastName)
                .append("\n")
                .append("- Deposited at: ")
                .append(timeOfDeposit.format(DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm")))
                .append("\n");

        return stringBuilder.toString();
    }
}
