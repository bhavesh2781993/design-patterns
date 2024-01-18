package structural;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Provides solution to combine multiple operations in different order and different combinations
 * Here, Email Sender Decorates any object of type NotificationSender, similarly for SMS Sender as well.
 * Similar to Adapter Pattern.
 *  Adapter pattern mainly adapting between 2 interfaces,
 *  While Decorator is mainly decorating 1 interface with adding the features
 * Example: BufferedReader, InputStreamReader, FileInputStream
 */
public class Decorator {
    public static void main(String[] args) throws FileNotFoundException {
        NotificationSender notificationSender1 = new EmailSender(new SMSSender(null));
        notificationSender1.send();

        System.out.println("==========");

        NotificationSender notificationSender2 = new SMSSender(new EmailSender(null));
        notificationSender2.send();

        System.out.println("==========");

        NotificationSender notificationSender3 = new SMSSender(new SMSSender(new SMSSender(null)));
        notificationSender3.send();

        System.out.println("==========");

        // new BufferedReader(new InputStreamReader(new FileInputStream("")));
    }
}

interface NotificationSender {
    void send();
}

class EmailSender implements NotificationSender {

    private final NotificationSender notificationSender;

    public EmailSender(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    @Override
    public void send() {
        if (notificationSender != null) {
            notificationSender.send();
        }
        System.out.println("Sending Email Notification");
    }
}

class SMSSender implements NotificationSender {

    private final NotificationSender notificationSender;

    public SMSSender(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    @Override
    public void send() {
        if (this.notificationSender != null) {
            this.notificationSender.send();
        }
        System.out.println("Sending SMS Notification");
    }
}
