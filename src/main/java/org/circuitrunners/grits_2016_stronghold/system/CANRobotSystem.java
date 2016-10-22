package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class CANRobotSystem implements RobotSystem {

    private final ButtonGroup buttons;
    private final boolean flip;
    private final boolean opposite;
    private final ArrayList<CANTalon> motors;

    public CANRobotSystem(ButtonGroup buttons, boolean flip, boolean opposite, CANTalon... motors) {
        this.buttons = buttons;
        this.flip = flip;
        this.opposite = opposite;
        this.motors = new ArrayList<>(Arrays.asList(motors));
        for (CANTalon motor : motors) {
            // black magic... don't ask
            PIDController pidController = new PIDController(-0.001, 0, 0, motor, motor);
            pidController.disable();
        }
    }

    protected double getFlipper(Joystick joystick) {
        double thing = opposite ? 0.5 : 1;
        double flop = flip ? -thing : thing;
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
        motors.forEach(motor -> motor.set(getFlipper(joystick)));
    }
}
