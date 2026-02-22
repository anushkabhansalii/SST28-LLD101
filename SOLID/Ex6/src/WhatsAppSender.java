public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public NotificationResult send(Notification n) {
        // LSP fix: Return failure result instead of throwing a RuntimeException.
        if (n.phone == null || !n.phone.startsWith("+")) {
            String err = "phone must start with + and country code";
            audit.add("WA failed"); // preserve audit behavior
            return NotificationResult.failure(err, "WA failed");
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        String msg = "wa sent";
        audit.add(msg);
        return NotificationResult.success(msg);
    }
}
