import java.util.Optional;

/**
 * OCP: Rule — fails if the student's attendance percentage is below the
 * configured minimum.
 */
public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public Optional<String> check(StudentProfile student) {
        if (student.attendancePct < minAttendance) {
            return Optional.of("attendance below " + minAttendance);
        }
        return Optional.empty();
    }
}