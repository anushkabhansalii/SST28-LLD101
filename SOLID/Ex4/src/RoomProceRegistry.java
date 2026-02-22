import java.util.*;

/**
 * OCP: Maps room-type codes to their RoomPrice components.
 * Replaces the switch-case in HostelFeeCalculator.
 * To add a new room type: add one entry here; no other class changes.
 */
public class RoomPriceRegistry {
    private static final Map<Integer, RoomPrice> REGISTRY = new LinkedHashMap<>();

    static {
        REGISTRY.put(LegacyRoomTypes.SINGLE, new RoomPrice(14000.0));
        REGISTRY.put(LegacyRoomTypes.DOUBLE, new RoomPrice(15000.0));
        REGISTRY.put(LegacyRoomTypes.TRIPLE, new RoomPrice(12000.0));
        REGISTRY.put(LegacyRoomTypes.DELUXE, new RoomPrice(16000.0));
    }

    /**
     * Returns the RoomPrice for the given room type code, or DELUXE price as
     * default.
     */
    public static RoomPrice forType(int roomType) {
        return REGISTRY.getOrDefault(roomType, REGISTRY.get(LegacyRoomTypes.DELUXE));
    }
}