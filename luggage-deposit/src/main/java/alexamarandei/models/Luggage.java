package alexamarandei.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Model for a Luggage, encapsulating:
 * - the Id at the time of depositing
 * - the last name of its owner
 * - the time of depositing
 */
public class Luggage {
    private int luggageId;
    private String ownerLastName;
    private LocalDateTime timeOfDeposit;

    //// Constructor ////

    /**
     * @param luggageId The luggage id (it is the current luggage counter value)
     * @see alexamarandei.App#luggageCounter
     * 
     * @param ownerLastName The last name of the luggage's owner
     */
    public Luggage(int luggageId, String ownerLastName) {
        this.luggageId = luggageId;
        this.ownerLastName = ownerLastName;
        this.timeOfDeposit = LocalDateTime.now();
    }

    //// Getters ////

    /**
     * @return The luggage id (it is the luggage counter value at the time of
     *         depositing)
     * @see alexamarandei.App#luggageCounter
     * 
     */
    public int getLuggageId() {
        return luggageId;
    }

    /**
     * @return The last name of the luggage's owner
     */
    public String getOwnerLastName() {
        return ownerLastName;
    }

    /**
     * @return The time when the luggage was deposited
     */
    public LocalDateTime getTimeOfDeposit() {
        return timeOfDeposit;
    }

    //// Overridden Methods ////

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n-- Luggage #")
                .append(luggageId)
                .append(" --\n\n")
                .append("- Owner: ")
                .append(ownerLastName)
                .append("\n")
                .append("- Deposited at: ")
                .append(timeOfDeposit.format(DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm")))
                .append("\n");

        return sb.toString();
    }
}
