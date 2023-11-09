package behavioral;

/**
 * Provides solution to choose and use a strategy dynamically.
 * This uses context class to dynamically set the strategy we want to use.
 */
public class Strategy {
    public static void main(String[] args) {
        NotificationContext context = new NotificationContext();

        EmailNotificationStrategy email = new EmailNotificationStrategy();
        context.setStrategy(email);
        context.sendNotification();

        SMSNotificationStrategy sms = new SMSNotificationStrategy();
        context.setStrategy(sms);
        context.sendNotification();
    }

}

class NotificationContext {
    NotificationStrategy notificationStrategy;
    void setStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    void sendNotification() {
        if (notificationStrategy != null) {
            this.notificationStrategy.send();
        }
    }
}

interface NotificationStrategy {
    void send();
}

class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void send() {
        System.out.println("Sending Email");
    }
}

class SMSNotificationStrategy implements NotificationStrategy {
    @Override
    public void send() {
        System.out.println("Sending SMS");
    }
}
