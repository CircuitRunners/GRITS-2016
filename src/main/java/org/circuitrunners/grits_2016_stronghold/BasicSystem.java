package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicSystem {
    ButtonGroup buttons;
    ArrayList<Talon> motors;

    public BasicSystem(ButtonGroup buttons, Talon... motors) {
        this.buttons = buttons;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    public int getFlipper(Joystick joystick) {
        if (joystick.getRawButton(buttons.getForward())) {
            return 1;
        } else if (joystick.getRawButton(buttons.getBackward())) {
            return -1;
        } else {
            return 0;
        }
    }

    public void run(int flipper) {
        motors.forEach(motor -> motor.set(flipper));
    }
}
