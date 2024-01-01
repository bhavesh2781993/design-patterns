package behavioral;

public class Visitor {
    public static void main(String[] args) {
        // Each separate visitors are separate Operations
        // Each Operation can grow Horizontally with its own class Implementation for all Object Types
        HotelRoomVisitor maintenanceVisitor = new HotelRoomMaintenanceVisitor();
        HotelRoomVisitor pricingVisitor = new HotelRoomPricingVisitor();

        // Each separate Elements are separate Objects
        HotelRoomElement singleRoom = new SingleRoom();
        singleRoom.accept(maintenanceVisitor);
        singleRoom.accept(pricingVisitor);

        HotelRoomElement doubleRoom = new DoubleRoom();
        doubleRoom.accept(maintenanceVisitor);
        doubleRoom.accept(pricingVisitor);

        HotelRoomElement deluxeRoom = new DeluxeRoom();
        deluxeRoom.accept(maintenanceVisitor);
        deluxeRoom.accept(pricingVisitor);
    }
}

interface HotelRoomElement {
    void accept(HotelRoomVisitor visitor);
}

class SingleRoom implements HotelRoomElement {
    public int roomPrice = 0;
    @Override
    public void accept(HotelRoomVisitor visitor) {
        visitor.visit(this);
    }
}

class DoubleRoom implements HotelRoomElement {
    public int roomPrice = 0;
    @Override
    public void accept(HotelRoomVisitor visitor) {
        visitor.visit(this);
    }
}

class DeluxeRoom implements HotelRoomElement {
    public int roomPrice = 0;
    @Override
    public void accept(HotelRoomVisitor visitor) {
        visitor.visit(this);
    }
}

interface HotelRoomVisitor {
    void visit(SingleRoom singleRoom);

    void visit(DoubleRoom doubleRoom);

    void visit(DeluxeRoom deluxeRoom);

}

class HotelRoomMaintenanceVisitor implements HotelRoomVisitor {
    @Override
    public void visit(SingleRoom singleRoom) {
        System.out.println("Maintenance: Single Room");
    }

    @Override
    public void visit(DoubleRoom doubleRoom) {
        System.out.println("Maintenance: Double Room");
    }

    @Override
    public void visit(DeluxeRoom deluxeRoom) {
        System.out.println("Maintenance: Deluxe Room");
    }
}

class HotelRoomPricingVisitor implements HotelRoomVisitor {
    @Override
    public void visit(SingleRoom singleRoom) {
        singleRoom.roomPrice = 100;
        System.out.println("Price: Single Room: " + singleRoom.roomPrice);
    }

    @Override
    public void visit(DoubleRoom doubleRoom) {
        doubleRoom.roomPrice = 200;
        System.out.println("Price: Double Room: " + doubleRoom.roomPrice);
    }

    @Override
    public void visit(DeluxeRoom deluxeRoom) {
        deluxeRoom.roomPrice = 300;
        System.out.println("Price: Deluxe Room: " + deluxeRoom.roomPrice);
    }
}
