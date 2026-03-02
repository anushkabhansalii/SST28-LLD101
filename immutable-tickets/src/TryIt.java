import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo that shows building and updating immutable tickets.
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Demonstrate service updating by returning a new object
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);

        System.out.println("\nOriginal is untouched: " + t);
        System.out.println("After service updates (new ticket): " + escalated);

        // Demonstrate immutability via unmodifiable tags list
        System.out.println("\nAttempting to mutate tags list externally...");
        try {
            List<String> tags = escalated.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println(
                    "Caught Expected Exception: tags list is immutable (" + e.getClass().getSimpleName() + ")");
        }
    }
}