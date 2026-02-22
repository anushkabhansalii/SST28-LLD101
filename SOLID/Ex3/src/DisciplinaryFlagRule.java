import java.util.Optional;

/**
 * OCP: Rule — fails if the student has any disciplinary flag.
 */
public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile student) {
        if (student.disciplinaryFlag != LegacyFlags.NONE) {
            return Optional.of("disciplinary flag present");
        }
        return Optional.empty();
    }
}