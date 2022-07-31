package alexamarandei.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class LuggageTest {
    protected int luggageId;
    protected String ownerLastName;

    protected void setUp() {
        SecureRandom secureRandom = new SecureRandom();
        luggageId = secureRandom.nextInt();

        int length = secureRandom.nextInt(21) + 1;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            sb.append((char) (secureRandom.nextInt(27) + 97));
        }
    }

    @Test
    public void testConstructor() {
        Luggage luggage = new Luggage(luggageId, ownerLastName);

        assertEquals(luggageId, luggage.getLuggageId());
        assertEquals(ownerLastName, luggage.getOwnerLastName());

        Duration duration = Duration.between(luggage.getTimeOfDeposit(), LocalDateTime.now());
        assertTrue(duration.toSeconds() <= 1);
    }

    @Test
    public void testToString() {
        Luggage luggage = new Luggage(luggageId, ownerLastName);

        StringBuilder sb = new StringBuilder("\n-- Luggage #")
                .append(luggageId)
                .append(" --\n\n")
                .append("- Owner: ")
                .append(ownerLastName)
                .append("\n")
                .append("- Deposited at: ")
                .append(luggage.getTimeOfDeposit()
                        .format(DateTimeFormatter
                                .ofPattern(
                                        "dd-MM-yyyy HH:mm")))
                .append("\n");

        assertEquals(sb.toString(), luggage.toString());
    }
}
