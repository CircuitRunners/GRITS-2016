package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

public class DoubleSolenoidRobotSystem implements RobotSystem {

    private ButtonGroup buttons;
    private DoubleSolenoid solenoid;

    public DoubleSolenoidRobotSystem(ButtonGroup buttons, DoubleSolenoid solenoid) {
        this.buttons = buttons;
        this.solenoid = solenoid;
    }

    private int getFlipper(Joystick joystick) {
        if (joystick.getRawButton(buttons.getForward())) {
            return 1;
        } else if (joystick.getRawButton(buttons.getBackward())) {
            return -1;
        } else {
            return 0;
        }
    }

    public void run(Joystick joystick) {
        DoubleSolenoid.Value value;
        switch (getFlipper(joystick)) {
            case 1:
                value = kForward;
                break;
            case -1:
                value = kReverse;
                break;
            default:
                value = kOff;
                break;
        }
        solenoid.set(value);
    }
}
