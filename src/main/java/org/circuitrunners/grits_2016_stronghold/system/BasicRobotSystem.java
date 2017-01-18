package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap.JoystickType;

import java.util.Arrays;

public class BasicRobotSystem implements RobotSystem {
    private JoystickType joystickType;
    private double forwardSpeed;
    private double backwardSpeed;
    Talon[] motors;
    private int axis;
    private double lastFlipper;
    private JoystickButton forwardButton;
    private JoystickButton backwardButton;
    private byte lastButtOn;
    private boolean usesButtons;

    public BasicRobotSystem(int... ports) {
        int length = ports.length;
        for (int i = 0; i < length; i++) {
            motors[i] = new Talon(ports[i]);
        }
    }

    double getFlipper(Joystick joystick) {
        double raw = joystick.getRawAxis(axis);
        return raw >= 0.05 ? raw * forwardSpeed : raw <= -0.05 ? raw * backwardSpeed : 0;
    }

    double getFlipper() {
        return forwardButton.get() ? forwardSpeed : backwardButton.get() ? backwardSpeed : 0;
    }

    public void run(Joystick joystick) {
        double flipper = usesButtons ? getFlipper() : getFlipper(joystick);
        boolean setMotors;
        if (usesButtons) {
            setMotors = Byte.compare(lastButtOn, (byte) (forwardButton.get() ? 0 : backwardButton.get() ? 1 : 2)) != 0;
        } else {
            setMotors = flipper - lastFlipper > 0.01 || flipper - lastFlipper < -0.01;
        }
        if (setMotors) {
            Arrays.stream(motors).parallel().forEach(motor -> motor.set(flipper));
        }
        if (usesButtons) {
            lastButtOn = (byte) (forwardButton.get() ? 0 : backwardButton.get() ? 1 : 2);
        } else {
            lastFlipper = flipper;
        }
    }

    public JoystickType getJoystickType() {
        return joystickType;
    }

    public BasicRobotSystem setButtons(ButtonGroup buttons) {
        forwardButton = new JoystickButton(joystickType.getJoystick(), buttons.getForward());
        backwardButton = new JoystickButton(joystickType.getJoystick(), buttons.getBackward());
        usesButtons = true;
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
