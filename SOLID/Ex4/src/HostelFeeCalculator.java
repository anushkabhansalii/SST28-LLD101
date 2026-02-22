import java.util.*;

/**
 * OCP: Closed for modification, open for extension.
 *
 * Monthly fee = sum of all FeeComponent contributions.
 * No switch on room type, no if/else on add-ons.
 * To support a new room type or add-on, update the registries only.
 */
public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        // Build a list of all fee components for this booking
        List<FeeComponent> components = new ArrayList<>();
        components.add(RoomPriceRegistry.forType(req.roomType));
        for (AddOn addOn : req.addOns) {
            components.add(AddOnPriceRegistry.forAddOn(addOn));
        }

        // Sum all contributions — no switch, no branching
        Money total = new Money(0.0);
        for (FeeComponent c : components) {
            total = total.plus(c.monthlyContribution());
        }
        return total;
    }
}
