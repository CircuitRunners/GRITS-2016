package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap.JoystickType;

import java.util.Arrays;

public class BasicRobotSystem implements RobotSystem {
    private ButtonGroup buttons;
    private JoystickType joystickType;
    private double forwardSpeed;
    private double backwardSpeed;
    Talon[] motors;
    private int axis;

    double getFlipper(Joystick joystick) {
        if (buttons != null) {
            if (joystick.getRawButton(buttons.getForward())) {
                return forwardSpeed;
            } else if (joystick.getRawButton(buttons.getBackward())) {
                return -backwardSpeed;
            } else {
                return 0;
            }
        } else {
            double raw = joystick.getRawAxis(axis);
            return raw >= 0.05 ? raw * forwardSpeed : raw <= -0.05 ? raw * backwardSpeed : 0;
        }
    }

    public void run(Joystick joystick) {
        Arrays.stream(motors).parallel().forEach(motor -> motor.set(getFlipper(joystick)));
    }

    public ButtonGroup getButtons() {
        return buttons;
    }

    public JoystickType getJoystickType() {
        return joystickType;
    }

    public BasicRobotSystem setButtons(ButtonGroup buttons) {
        this.buttons = buttons;
        return this;
    }

    public BasicRobotSystem setJoystickType(JoystickType joystickType) {
        this.joystickType = joystickType;
        return this;
    }

    public BasicRobotSystem setForwardSpeed(double forwardSpeed) {
        this.forwardSpeed = forwardSpeed;
        return this;
    }

    public BasicRobotSystem setBackwardSpeed(double backwardSpeed) {
        this.backwardSpeed = backwardSpeed;
        return this;
    }

    public BasicRobotSystem setMotors(Talon... motors) {
        this.motors = motors;
        return this;
    }

    public BasicRobotSystem setAxis(int axis) {
        this.axis = axis;
        return this;
    }
}
