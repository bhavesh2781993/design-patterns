package behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 2 Parts: Subject, Listeners / Observers.
 * Upon completion of a subject task, All the listeners / observers registered with a subject gets notified
 * Listeners can register and de-register themselves from a subject
 * Example: Swing, JavaFX uses observer pattern
 */
public class Observer {
    public static void main(String[] args) {
        PaymentNotification notification = new PaymentNotification();
        PaymentEventLogger eventLogger = new PaymentEventLogger();

        PaymentManager paymentManager = new PaymentManager();
        paymentManager.registerPaymentListener(notification);
        paymentManager.registerPaymentListener(eventLogger);

        paymentManager.pay();

        System.out.println("========");

        paymentManager.pay();

        paymentManager.deRegisterPaymentListener(notification);

        System.out.println("========");

        paymentManager.pay();
    }
}

// Subject
class PaymentManager {

    List<PaymentListener> listeners = new ArrayList<>();

    public void pay() {
        System.out.println("Payment Completed ...");
        listeners.forEach(PaymentListener::paymentCompleted);
    }

    public void registerPaymentListener(PaymentListener paymentListener) {
        listeners.add(paymentListener);
    }

    public void deRegisterPaymentListener(PaymentListener paymentListener) {
        listeners.remove(paymentListener);
    }

}

// Listener Interface
interface PaymentListener {
    void paymentCompleted();
}

// Listener Impl
class PaymentEventLogger implements PaymentListener {
    @Override
    public void paymentCompleted() {
        logEvent();
    }

    private void logEvent() {
        System.out.println("Payment Logs Completed ...");
    }
}

// Listener Impl
class PaymentNotification implements PaymentListener {
    @Override
    public void paymentCompleted() {
        sendNotification();
    }

    private void sendNotification() {
        System.out.println("Payment Notification Completed ...");
    }
}
