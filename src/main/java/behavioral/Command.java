package behavioral;

import java.util.Stack;

public class Command {
    public static void main(String[] args) {
        RemoteController remoteController = new RemoteController();

        AirConditioner airConditioner = new AirConditioner();

        ICommand turnOnACCommand = new TurnOnACCommand(airConditioner);
        ICommand turnOffACCommand = new TurnOffACCommand(airConditioner);

        remoteController.setCommand(turnOnACCommand);
        remoteController.pressButton();

        remoteController.setCommand(turnOffACCommand);
        remoteController.pressButton();

        remoteController.undo();
        remoteController.undo();
        remoteController.undo();
    }
}

class RemoteController {
    Stack<ICommand> commandHistory = new Stack<>();
    ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void pressButton() {
        this.command.execute();
        commandHistory.add(command);
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            System.out.println("Undoing");
            ICommand lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("Nothing to Undo");
        }
    }

}

interface ICommand {
    void execute();
    void undo();
}

class TurnOnACCommand implements ICommand {
    AirConditioner airConditioner;

    public TurnOnACCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        airConditioner.turnOn();
    }

    @Override
    public void undo() {
        airConditioner.turnOff();
    }
}

class TurnOffACCommand implements ICommand {
    AirConditioner airConditioner;

    public TurnOffACCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        airConditioner.turnOff();
    }

    @Override
    public void undo() {
        airConditioner.turnOn();
    }
}

class AirConditioner {
    boolean isOn;
    int temperature;

    public void turnOn() {
        System.out.println("AC Turned On");
        this.isOn = true;
    }

    public void turnOff() {
        System.out.println("AC Turned Off");
        this.isOn = false;
    }

    public void setTemperature(int temperature) {
        System.out.println("AC Temperature Set to: " + temperature);
        this.temperature = temperature;
    }

}
