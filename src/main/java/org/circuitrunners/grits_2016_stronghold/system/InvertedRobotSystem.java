package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap;

public class InvertedRobotSystem extends BasicRobotSystem {
    private boolean flap;

    public InvertedRobotSystem(ButtonGroup buttons, boolean flip, boolean flap, RobotMap.JoystickType joystickType, Talon... motors) {
        super(buttons, flip, joystickType, motors);
        this.flap = flap;
    }

    @Override
    public void run(Joystick joystick) {
        int size = motors.size();
        for (int i = 0; i < size - 1; i += 2) {
            int flop = flap ? 1 : -1;
            motors.get(i).set(getFlipper(joystick));
            motors.get(i + 1).set(flop * getFlipper(joystick));
        }
    }
}
