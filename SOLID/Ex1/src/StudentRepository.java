/**
 * SRP: Abstracts the persistence concern.
 * OnboardingService depends on this interface, not on FakeDb directly.
 */
public interface StudentRepository {
    void save(StudentRecord record);

    int count();
}