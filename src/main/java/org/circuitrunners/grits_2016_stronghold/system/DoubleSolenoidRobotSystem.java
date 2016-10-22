package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class DoubleSolenoidRobotSystem implements RobotSystem {   //Works best when the system is plugged in right side up.

    private ButtonGroup buttons;
    private DoubleSolenoid solenoid;

    public DoubleSolenoidRobotSystem(ButtonGroup buttons, DoubleSolenoid solenoid) {
        this.buttons = buttons;
        this.solenoid = solenoid;
    }

    private DoubleSolenoid.Value getFlipper(Joystick joystick) {
        if (joystick.getRawButton(buttons.getForward())) {
            return kForward;
        } else if (joystick.getRawButton(buttons.getBackward())) {
            return kReverse;
        } else {
            return kOff;
        }
    }

    public void run(Joystick joystick) {
        solenoid.set(getFlipper(joystick));
    }
}
