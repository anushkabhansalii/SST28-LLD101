import java.util.*;

/**
 * OCP: Maps AddOn enum values to their AddOnPrice components.
 * Replaces the if/else chain over add-ons in HostelFeeCalculator.
 * To add a new add-on: add it to the AddOn enum and one entry here.
 */
public class AddOnPriceRegistry {
    private static final Map<AddOn, AddOnPrice> REGISTRY = new EnumMap<>(AddOn.class);

    static {
        REGISTRY.put(AddOn.MESS, new AddOnPrice(1000.0));
        REGISTRY.put(AddOn.LAUNDRY, new AddOnPrice(500.0));
        REGISTRY.put(AddOn.GYM, new AddOnPrice(300.0));
    }

    /** Returns the AddOnPrice for the given add-on, or zero-cost if unknown. */
    public static AddOnPrice forAddOn(AddOn addOn) {
        return REGISTRY.getOrDefault(addOn, new AddOnPrice(0.0));
    }
}