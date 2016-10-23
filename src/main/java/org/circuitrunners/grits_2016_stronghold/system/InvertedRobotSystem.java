package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap.JoystickType;

public class InvertedRobotSystem extends BasicRobotSystem {

    public InvertedRobotSystem(ButtonGroup buttons, JoystickType joystickType, double forwardSpeed, double backwardSpeed, Talon... motors) {
        super(buttons, joystickType, forwardSpeed, backwardSpeed, motors);
    }

    @Override
    public void run(Joystick joystick) {
        int size = motors.size();
        for (int i = 0; i < size - 1; i += 2) {
            motors.get(i).set(getFlipper(joystick));
            motors.get(i + 1).set(-getFlipper(joystick));
        }
    }
}
