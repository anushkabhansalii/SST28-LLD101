import java.util.*;

/**
 * SRP: In-memory persistence implementation.
 * Implements StudentRepository so callers depend on the interface, not this
 * class.
 */
public class FakeDb implements StudentRepository {
    private final List<StudentRecord> rows = new ArrayList<>();

    @Override
    public void save(StudentRecord r) {
        rows.add(r);
    }

    @Override
    public int count() {
        return rows.size();
    }

    public List<StudentRecord> all() {
        return Collections.unmodifiableList(rows);
    }
}
