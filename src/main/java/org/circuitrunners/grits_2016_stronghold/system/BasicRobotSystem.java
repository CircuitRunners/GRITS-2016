package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicRobotSystem implements RobotSystem {
    private ButtonGroup buttons = null;
    private RobotMap.JoystickType joystickType;
    ArrayList<Talon> motors;
    private int axis;

    public BasicRobotSystem(ButtonGroup buttons, RobotMap.JoystickType joystickType, Talon... motors) {
        this.buttons = buttons;
        this.joystickType = joystickType;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    public BasicRobotSystem(int axis, RobotMap.JoystickType joystickType, Talon... motors) {
        this.axis = axis;
        this.joystickType = joystickType;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    double getFlipper(Joystick joystick) {
        if (buttons != null) {
            if (joystick.getRawButton(buttons.getForward())) {
                return 1;
            } else if (joystick.getRawButton(buttons.getBackward())) {
                return -1;
            } else {
                return 0;
            }
        } else {
            double raw = joystick.getRawAxis(axis);
            return Math.abs(raw) >= 0.05 ? raw : 0;
        }
    }

    public void run(Joystick joystick) {
        motors.parallelStream().forEach(motor -> motor.set(getFlipper(joystick)));
    }

    public RobotMap.JoystickType getJoystickType() {
        return joystickType;
    }
}
