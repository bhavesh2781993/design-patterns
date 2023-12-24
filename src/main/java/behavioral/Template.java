package behavioral;

/**
 * When you want all classes to follow the specific steps to process the task
 * but also, need to provide the flexibility that each class can have their own logic in that specific steps.
 */
public class Template {
    public static void main(String[] args) {
        PaymentFlow paymentFlow = new PaymentToFriend();
        paymentFlow.sendMoney();

        System.out.println("======================");

        paymentFlow = new PaymentToMerchant();
        paymentFlow.sendMoney();
    }
}

abstract sealed class PaymentFlow permits PaymentToMerchant, PaymentToFriend {
    protected abstract void validateRequest();

    protected abstract void calculateFees();

    protected abstract void debitAmount();

    protected abstract void creditAmount();

    // This is Template method: which defines the order of steps to execute the task
    // This method is made final intentionally to avoid getting overridden by child classes.
    public final void sendMoney() {
        // Step1
        validateRequest();

        // Step 2
        debitAmount();

        // Step 3
        calculateFees();

        // Step 4
        creditAmount();
    }
}

final class PaymentToFriend extends PaymentFlow {
    @Override
    protected void validateRequest() {
        System.out.println("Validate Friend's Payment Request");
    }

    @Override
    protected void calculateFees() {
        System.out.println("Calculate Platform's Payment Fees");
    }

    @Override
    protected void debitAmount() {
        System.out.println("Debit Your Account");
    }

    @Override
    protected void creditAmount() {
        System.out.println("Credit Friend's Account");
    }

}

final class PaymentToMerchant extends PaymentFlow {
    @Override
    protected void validateRequest() {
        System.out.println("Validate Merchant's Payment Request");
    }

    @Override
    protected void calculateFees() {
        System.out.println("Calculate Platform's Payment Fees");
    }

    @Override
    protected void debitAmount() {
        System.out.println("Debit Your Account");
    }

    @Override
    protected void creditAmount() {
        System.out.println("Credit Merchant's Account");
    }
}