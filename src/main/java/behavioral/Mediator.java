package behavioral;

import java.util.ArrayList;
import java.util.List;

public class Mediator {
    public static void main(String[] args) {
        AuctionMediator auctionMediator = new Auction();
        Colleague bidder1 = new Bidder("Bhavesh", auctionMediator);
        Colleague bidder2 = new Bidder("Zeel", auctionMediator);
        Colleague bidder3 = new Bidder("Dhwit", auctionMediator);

        bidder1.placeBid(1000);
        bidder2.placeBid(2000);
        bidder3.placeBid(3000);
        bidder1.placeBid(4000);
    }
}

interface AuctionMediator {

    void addColleague(Colleague colleague);
    void placeBid(Colleague bidder, Integer amount);
}

class Auction implements AuctionMediator {
    List<Colleague> colleagues;

    @Override
    public void addColleague(Colleague colleague) {
        if (colleagues == null || colleagues.isEmpty()) {
            colleagues = new ArrayList<>();
        }
        colleagues.add(colleague);
    }

    @Override
    public void placeBid(Colleague bidder, Integer amount) {
        System.out.println("Placed bid of amount: " + amount + " by: " + bidder.getName());
        colleagues.stream()
            .filter(colleague -> !colleague.getName().equals(bidder.getName()))
            .forEach(colleague -> colleague.receiveNotification(bidder, amount));
    }
}

interface Colleague {
    void placeBid(Integer amount);
    void receiveNotification(Colleague bidder, Integer amount);
    String getName();
}

class Bidder implements Colleague {
    private String name;
    private AuctionMediator auctionMediator;

    public Bidder(String name, AuctionMediator auctionMediator) {
        this.name = name;
        this.auctionMediator = auctionMediator;
        this.auctionMediator.addColleague(this);
    }

    @Override
    public void placeBid(Integer amount) {
        this.auctionMediator.placeBid(this, amount);
    }

    @Override
    public void receiveNotification(Colleague bidder, Integer amount) {
        System.out.println("Received Notification to: " + name + " from: " + bidder.getName() + " for amount: " + amount);
    }
    @Override
    public String getName() {
        return name;
    }
}
