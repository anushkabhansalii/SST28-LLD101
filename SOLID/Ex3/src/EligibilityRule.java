import java.util.Optional;

/**
 * OCP: The shared abstraction for every eligibility rule.
 *
 * A rule inspects a StudentProfile and returns:
 * - Optional.empty() → this rule passes
 * - Optional.of("reason text") → this rule fails, with the failure reason
 *
 * Adding a new rule = creating a new class that implements this interface.
 * EligibilityEngine does not need to be edited.
 */
public interface EligibilityRule {
    Optional<String> check(StudentProfile student);
}