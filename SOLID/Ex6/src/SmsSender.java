public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public NotificationResult send(Notification n) {
        // ISP/LSP note: SMS channels don't have subjects.
        // It's acceptable to ignore it if the contract doesn't explicitly guarantee its
        // delivery.
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        String msg = "sms sent";
        audit.add(msg);
        return NotificationResult.success(msg);
    }
}
