package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class CANRobotSystem implements RobotSystem {

    private final ButtonGroup buttons;
    private final boolean flip;
    private final ArrayList<CANTalon> motors;

    public CANRobotSystem(ButtonGroup buttons, boolean flip, CANTalon... motors) {
        this.buttons = buttons;
        this.flip = flip;
        this.motors = new ArrayList<>(Arrays.asList(motors));
        for (CANTalon motor : motors) {
            motor.setControlMode(2);
            motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
            motor.setPID(1, 1, 1);
        }
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

    @Override
    public void run(Joystick joystick) {
        final int flipper = getFlipper(joystick);
        motors.forEach(motor -> motor.set(flipper));
    }
}
