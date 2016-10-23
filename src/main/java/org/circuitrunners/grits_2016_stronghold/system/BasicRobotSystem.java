package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap.JoystickType;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicRobotSystem implements RobotSystem {
    private ButtonGroup buttons;
    private JoystickType joystickType;
    private double forwardSpeed;
    private double backwardSpeed;
    ArrayList<Talon> motors;
    private int axis;

    public BasicRobotSystem(ButtonGroup buttons, JoystickType joystickType, double forwardSpeed, double backwardSpeed, Talon... motors) {
        this.buttons = buttons;
        this.joystickType = joystickType;
        this.forwardSpeed = forwardSpeed;
        this.backwardSpeed = backwardSpeed;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    public BasicRobotSystem(int axis, JoystickType joystickType, double forwardSpeed, double backwardSpeed, Talon... motors) {
        this.axis = axis;
        this.joystickType = joystickType;
        this.forwardSpeed = forwardSpeed;
        this.backwardSpeed = backwardSpeed;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

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
        motors.parallelStream().forEach(motor -> motor.set(getFlipper(joystick)));
    }

    public JoystickType getJoystickType() {
        return joystickType;
    }
}
