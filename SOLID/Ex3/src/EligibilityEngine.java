import java.util.*;

/**
 * OCP: Closed for modification, open for extension.
 *
 * New rules are added by implementing EligibilityRule and passing them in —
 * this class never needs to change when new rules are introduced.
 *
 * The original if/else chain used short-circuit evaluation (stop at first
 * failure).
 * That behaviour is preserved here: we stop at the first failing rule so the
 * output remains identical.
 */
public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store, List<EligibilityRule> rules) {
        this.store = store;
        this.rules = rules;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();

        // Iterate over rules; stop at the first failure (preserves original behaviour)
        for (EligibilityRule rule : rules) {
            Optional<String> failure = rule.check(s);
            if (failure.isPresent()) {
                reasons.add(failure.get());
                return new EligibilityEngineResult("NOT_ELIGIBLE", reasons);
            }
        }

        return new EligibilityEngineResult("ELIGIBLE", reasons);
    }
}
