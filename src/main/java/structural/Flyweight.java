package structural;

import java.util.EnumMap;
import java.util.Map;

public class Flyweight {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            IRobot humanoid = RobotFactory.getInstance(RobotType.HUMANOID);
            humanoid.display(i, i);
        }

        System.out.println("===============");

        for (int i = 0; i < 5; i++) {
            IRobot humanoid = RobotFactory.getInstance(RobotType.ROBOTIC_DOG);
            humanoid.display(i, i);
        }
    }
}

class RobotFactory {
    private static final Map<RobotType, IRobot> flyweightRobotCache = new EnumMap<>(RobotType.class);

    private RobotFactory() {}

    public static IRobot getInstance(RobotType robotType) {
        return switch (robotType) {
            case HUMANOID -> {
                flyweightRobotCache.computeIfAbsent(robotType, type -> new FlyWeightHumanoidRobot(type, new Sprites()));
                yield flyweightRobotCache.get(robotType);
            }
            case ROBOTIC_DOG -> {
                flyweightRobotCache.computeIfAbsent(robotType, type -> new FlyWeightRoboticDobRobot(type, new Sprites()));
                yield flyweightRobotCache.get(robotType);
            }
        };
    }
}

interface IRobot {
    void display(int x, int y);
}

record FlyWeightHumanoidRobot(RobotType robotType, Sprites body) implements IRobot {
    @Override
    public void display(int x, int y) {
        System.out.println("Humanoid Robot: " + "x: " + x + ", y: " + y + ", body: " + body);
    }
}

record FlyWeightRoboticDobRobot(RobotType robotType, Sprites body) implements IRobot {
    @Override
    public void display(int x, int y) {
        System.out.println("Robotic Dog Robot: " + "x: " + x + ", y: " + y + ", body: " + body);
    }
}

class Sprites {}

enum RobotType {
    HUMANOID,
    ROBOTIC_DOG
}
