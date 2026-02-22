import java.util.Optional;

/**
 * OCP: Rule — fails if the student's earned credits are below the configured
 * minimum.
 */
public class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public Optional<String> check(StudentProfile student) {
        if (student.earnedCredits < minCredits) {
            return Optional.of("credits below " + minCredits);
        }
        return Optional.empty();
    }
}