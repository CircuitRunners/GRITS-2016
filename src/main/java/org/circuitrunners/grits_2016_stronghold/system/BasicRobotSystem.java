package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicRobotSystem implements RobotSystem {
    ButtonGroup buttons;
    ArrayList<Talon> motors;
    private boolean flip;

    public BasicRobotSystem(ButtonGroup buttons, boolean flip, Talon... motors) {
        this.buttons = buttons;
        this.flip = flip;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    protected int getFlipper(Joystick joystick) {
        int flop = flip ? -1 : 1;
        if (joystick.getRawButton(buttons.getForward())) {
            return flop;
        } else if (joystick.getRawButton(buttons.getBackward())) {
            return -flop;
        } else {
            return 0;
        }
    }

    public void run(Joystick joystick) {
        motors.forEach(motor -> motor.set(getFlipper(joystick)));
    }
}
