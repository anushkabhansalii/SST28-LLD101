import java.util.*;

/**
 * SRP: Single responsibility — validate student field values.
 * Returns a list of error strings; does NOT print anything.
 * Testable without any console IO side-effects.
 */
public class StudentValidator {
    private static final List<String> ALLOWED_PROGRAMS = Arrays.asList("CSE", "AI", "SWE");

    /**
     * Validates the four required student fields.
     *
     * @return an empty list when all fields are valid; otherwise a list of error
     *         messages.
     */
    public List<String> validate(String name, String email, String phone, String program) {
        List<String> errors = new ArrayList<>();
        if (name.isBlank())
            errors.add("name is required");
        if (email.isBlank() || !email.contains("@"))
            errors.add("email is invalid");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");
        if (!ALLOWED_PROGRAMS.contains(program))
            errors.add("program is invalid");
        return errors;
    }
}