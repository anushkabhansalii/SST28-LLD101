import java.util.*;

/**
 * SRP: Single responsibility — orchestrate the student onboarding workflow.
 *
 * This class no longer parses, validates, formats, or persists directly.
 * Each concern is delegated to a dedicated collaborator:
 * - StudentParser → parse raw input
 * - StudentValidator → validate field values
 * - StudentRepository → persist records (interface; does NOT know FakeDb)
 * - StudentPrinter → print confirmation / error messages
 * - IdUtil → generate student IDs
 */
public class OnboardingService {
    private final StudentRepository repository;
    private final StudentParser parser;
    private final StudentValidator validator;
    private final StudentPrinter printer;

    public OnboardingService(StudentRepository repository) {
        this.repository = repository;
        this.parser = new StudentParser();
        this.validator = new StudentValidator();
        this.printer = new StudentPrinter();
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        // Parse
        Map<String, String> kv = parser.parse(raw);
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        // Validate
        List<String> errors = validator.validate(name, email, phone, program);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        // Persist
        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);
        repository.save(rec);

        // Confirm
        printer.printSuccess(id, repository.count(), rec);
    }
}
