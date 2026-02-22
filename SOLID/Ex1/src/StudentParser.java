import java.util.*;

/**
 * SRP: Single responsibility — parse a raw key=value;... string into a Map.
 * No validation, no printing, no persistence.
 */
public class StudentParser {
    /**
     * Parses a raw input string like
     * "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE"
     * into a map of field names to values.
     */
    public Map<String, String> parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2)
                kv.put(t[0].trim(), t[1].trim());
        }
        return kv;
    }
}