import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");

        RuleInput cfg = new RuleInput();

        // Composition root: wire rules in order (same order as original if/else chain)
        List<EligibilityRule> rules = List.of(
                new DisciplinaryFlagRule(),
                new CgrRule(cfg.minCgr),
                new AttendanceRule(cfg.minAttendance),
                new CreditsRule(cfg.minCredits));

        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        EligibilityEngine engine = new EligibilityEngine(new FakeEligibilityStore(), rules);
        engine.runAndPrint(s);
    }
}
