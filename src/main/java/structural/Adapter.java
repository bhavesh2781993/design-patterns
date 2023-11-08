package structural;

/**
 * Provides with a solution to adapt dependency features with custom features.
 * Example: Spring Security
 *  UserDetails => Spring Security specific Dependency
 *  Here, CustomUserDetails class Adapts User
 *  <pre>
 *  class CustomUserDetails implements UserDetails {
 *      private final User user;
 *      public CustomUserDetails(User user) {
 *         this.user = user;
 *      }
 *  }
 *  </pre>
 */
public class Adapter {
    public static void main(String[] args) {
        DependencyNotificationSender dependencyNotificationSender = new DependencyNotificationSender();
        CustomNotificationSender customNotificationSender = new CustomNotificationSender(dependencyNotificationSender);
        customNotificationSender.send();
    }
}

/**
 * This is a dependency
 */
class DependencyNotificationSender {
    public void send() {
        System.out.println("Sending Notification ...");
    }
}

interface NotificationSenderI {
    void send();
}

/**
 * Adapts to DependencyNotificationSender
 */
class CustomNotificationSender implements NotificationSenderI {
    private final DependencyNotificationSender dependencyNotificationSender;

    public CustomNotificationSender(DependencyNotificationSender dependencyNotificationSender) {
        this.dependencyNotificationSender = dependencyNotificationSender;
    }

    @Override
    public void send() {
        dependencyNotificationSender.send();
    }

}
