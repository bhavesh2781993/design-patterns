package behavioral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        fillUpVendingMachine(vendingMachine);
        displayInventory(vendingMachine);

        System.out.println("clicking on Insert Coin Button");
        VMState currentState = vendingMachine.getCurrentState();
        currentState.clickOnInsertCoinButton(vendingMachine);

        currentState = vendingMachine.getCurrentState();
        currentState.insertCoin(vendingMachine, Coin.NICKEL);
        currentState.insertCoin(vendingMachine, Coin.QUARTER);

        System.out.println("clicking on Product Selection Button");
        currentState.clickOnStartProductSelectionButton(vendingMachine);

        currentState = vendingMachine.getCurrentState();
        currentState.chooseProduct(vendingMachine, 102);

        displayInventory(vendingMachine);
    }

    private static void fillUpVendingMachine(VendingMachine vendingMachine) {
        final ItemShelf[] shelves = vendingMachine.getInventory().getItemShelves();
        for(int i = 0; i < shelves.length; i++) {
            Item item = new Item();
            if (i >= 0 && i < 3) {
                item.setType(ItemType.COKE);
                item.setPrice(12);
            } else if (i >= 3 && i < 5) {
                item.setType(ItemType.PEPSI);
                item.setPrice(9);
            } else if (i >= 5 && i < 7) {
                item.setType(ItemType.JUICE);
                item.setPrice(13);
            } else if (i >= 7 && i < 10) {
                item.setType(ItemType.SODA);
                item.setPrice(7);
            }
            shelves[i].setItem(item);
            shelves[i].setSoldOut(false);
        }
    }

    private static void displayInventory(VendingMachine vendingMachine) {
        Arrays.stream(vendingMachine.getInventory().getItemShelves()).forEach(shelf -> System.out.println(
            "Code: " + shelf.code + ", Item Type: " + shelf.item.type + ", Item Price: " + shelf.item.price + ", Is Sold Out: " +
                shelf.isSoldOut));
    }
}

/**
 * States
 */
interface VMState {
    default void clickOnInsertCoinButton(VendingMachine vendingMachine) {
        throw new RuntimeException("Click on insert coin button not allowed");
    }

    default void clickOnStartProductSelectionButton(VendingMachine vendingMachine) {
        throw new RuntimeException("Click on start product selection button not allowed");
    }

    default void insertCoin(VendingMachine vendingMachine, Coin coin) {
        throw new RuntimeException("Insert coin button not allowed");
    }

    default void chooseProduct(VendingMachine vendingMachine, int codeNo) {
        throw new RuntimeException("Choose product not allowed");
    }

    default int getChange(int returnChange) {
        throw new RuntimeException("Get change not allowed");
    }

    default Item dispenseProduct(VendingMachine vendingMachine, int codeNo) {
        throw new RuntimeException("Dispense product not allowed");
    }

    default List<Coin> refundFullMoney(VendingMachine vendingMachine) {
        throw new RuntimeException("Refund full money not allowed");
    }

}

class DispenseState implements VMState {
    public DispenseState(VendingMachine vendingMachine, int codeNo) {
        dispenseProduct(vendingMachine, codeNo);
    }

    @Override
    public Item dispenseProduct(VendingMachine vendingMachine, int codeNo) {
        final Item item = vendingMachine.getInventory().getItem(codeNo);
        vendingMachine.getInventory().updateSoldOutItem(codeNo);
        vendingMachine.setCurrentState(new IdleState());
        return item;
    }
}

class HasMoneyState implements VMState {
    public HasMoneyState() {
        System.out.println("Currently in has money state");
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine vendingMachine) {
        vendingMachine.setCurrentState(new SelectionState());
    }

    @Override
    public void insertCoin(VendingMachine vendingMachine, Coin coin) {
        System.out.println("Accepted coin: " + coin);
        vendingMachine.getCoins().add(coin);
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine vendingMachine) {
        final List<Coin> coinsToRefund = vendingMachine.getCoins();
        vendingMachine.setCurrentState(new IdleState(vendingMachine));
        return coinsToRefund;
    }
}

class IdleState implements VMState {
    public IdleState() {
        System.out.println("Currently in idle state");
    }
    public IdleState(VendingMachine vendingMachine) {
        System.out.println("Curerntly in idle state");
        vendingMachine.setCoins(new ArrayList<>());
    }

    @Override
    public void clickOnInsertCoinButton(VendingMachine vendingMachine) {
        vendingMachine.setCurrentState(new HasMoneyState());
    }
}

class SelectionState implements VMState {
    public SelectionState() {
        System.out.println("Currently in selection state");
    }

    @Override
    public void chooseProduct(VendingMachine vendingMachine, int codeNo) {
        final Item item = vendingMachine.getInventory().getItem(codeNo);
        final int totalPaid = vendingMachine.getCoins()
            .stream()
            .mapToInt(Coin::getValue)
            .sum();
        if (totalPaid < item.getPrice()) {
            refundFullMoney(vendingMachine);
            throw new RuntimeException("Insufficient amount");
        } else {
            if (totalPaid > item.getPrice()) {
                getChange(totalPaid - item.getPrice());
            }
            vendingMachine.setCurrentState(new DispenseState(vendingMachine, codeNo));
        }

    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine vendingMachine) {
        vendingMachine.setCurrentState(new IdleState(vendingMachine));
        return vendingMachine.getCoins();
    }

    @Override
    public int getChange(int returnChange) {
        System.out.println("returning change of: " + returnChange);
        return returnChange;
    }
}

/**
 * Objects
 */
class VendingMachine {
    private VMState currentState;
    private Inventory inventory;
    private List<Coin> coins;

    public VendingMachine() {
        currentState = new IdleState();
        inventory = new Inventory(10);
        coins = new ArrayList<>();
    }

    public VMState getCurrentState() {
        return currentState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCurrentState(VMState currentState) {
        this.currentState = currentState;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }
}

enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    public final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class Inventory {
    ItemShelf[] itemShelves;

    public Inventory(int itemCount) {
        this.itemShelves = new ItemShelf[itemCount];
        fillUpInventoryShelves();
    }

    private void fillUpInventoryShelves() {
        int startCode = 101;
        for(int i = 0; i < itemShelves.length; i++) {
            ItemShelf shelf = new ItemShelf();
            shelf.setCode(startCode);
            shelf.setSoldOut(false);
            itemShelves[i] = shelf;
            startCode++;
        }
    }

    public void addItem(Item item, int codeNumber) {
        for(ItemShelf shelf: itemShelves) {
            if (shelf.code == codeNumber && shelf.isSoldOut()) {
                shelf.item = item;
                shelf.setSoldOut(false);
            } else {
                throw new RuntimeException("Item already present for code: " + codeNumber);
            }
        }
    }

    public ItemShelf[] getItemShelves() {
        return itemShelves;
    }

    public Item getItem(int codeNumber) {
        return Arrays.stream(itemShelves)
            .filter(itemShelf -> itemShelf.code == codeNumber)
            .map(ItemShelf::getItem)
            .findAny()
            .orElseThrow(() -> new RuntimeException("No matching item with code: " + codeNumber));
    }

    public void updateSoldOutItem(int codeNo) {
        final ItemShelf shelf = Arrays.stream(itemShelves)
            .filter(itemShelf -> itemShelf.code == codeNo)
            .findAny()
            .orElseThrow(() -> new RuntimeException("No matching item shelf with code: " + codeNo));
        shelf.setSoldOut(true);
    }
}

class Item {
    ItemType type;
    int price;

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class ItemShelf {
    int code;
    Item item;
    boolean isSoldOut;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}

enum ItemType {
    COKE,
    PEPSI,
    JUICE,
    SODA;
}
