import java.util.Optional;

/**
 * OCP: Rule — fails if the student's CGR is below the configured minimum.
 */
public class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public Optional<String> check(StudentProfile student) {
        if (student.cgr < minCgr) {
            return Optional.of("CGR below " + minCgr);
        }
        return Optional.empty();
    }
}