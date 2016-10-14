package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class CANRobotSystem implements RobotSystem {

    ButtonGroup buttons;
    ArrayList<CANTalon> motors;
    private boolean flip;

    public CANRobotSystem(ButtonGroup buttons, boolean flip, CANTalon... motors) {
        this.buttons = buttons;
        this.flip = flip;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    protected int getFlipper(Joystick joystick) {
        int flop = flip ? -1 : 1;
        if (joystick.getRawButton(buttons.getForward())) {
            return flop;
        } else if (joystick.getRawButton(buttons.getBackward())) {
            return flop;
        } else {
            return 0;
        }
    }

    @Override
    public void run(Joystick joystick) {
        motors.forEach(motor -> motor.set(getFlipper(joystick)));
    }
}
