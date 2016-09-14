package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class BasicMotor {
    Talon motor;
    ButtonGroup buttons;

    public BasicMotor(Talon motor, ButtonGroup buttons) {
        this.motor = motor;
        this.buttons = buttons;
    }

    public void run(Joystick joystick) {
        if (joystick.getRawButton(buttons.getForward())) {
            motor.set(1);
        } else if (joystick.getRawButton(buttons.getBackward())) {
            motor.set(-1);
        } else {
            motor.set(0);
        }
    }
}
