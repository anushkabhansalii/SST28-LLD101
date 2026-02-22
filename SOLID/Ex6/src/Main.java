public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms = new SmsSender(audit);
        NotificationSender wa = new WhatsAppSender(audit);

        email.send(n);
        sms.send(n);

        NotificationResult resWa = wa.send(n);
        if (!resWa.isSuccess) {
            System.out.print("WA ERROR: ");
            System.out.println(resWa.errorMessage);
        }

        System.out.print("AUDIT entries=");
        System.out.println(audit.size());
    }
}
