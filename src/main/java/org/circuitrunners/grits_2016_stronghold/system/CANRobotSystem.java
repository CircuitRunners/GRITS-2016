package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap.JoystickType;

import java.util.Arrays;

public class CANRobotSystem implements RobotSystem {

    private ButtonGroup buttons;
    private int axis;
    private boolean alternative;
    private CANTalon[] motors;

    public CANRobotSystem(ButtonGroup buttons, boolean alternative, CANTalon... motors) {
        this.buttons = buttons;
        this.alternative = alternative;
        this.motors = motors;
        Arrays.stream(motors).parallel().forEach(motor -> {
            PIDController pidController = new PIDController(-0.001, 0, 0, motor, motor);
            pidController.disable();
        });
    }

    public CANRobotSystem(int axis, boolean alternative, CANTalon... motors) {
        this.axis = axis;
        this.alternative = alternative;
        this.motors = motors;
        Arrays.stream(motors).parallel().forEach(motor -> {
            PIDController pidController = new PIDController(-0.001, 0, 0, motor, motor);
            pidController.disable();
        });
    }

    private double getFlipper(Joystick joystick) {
        if (buttons != null) {
            double mode = alternative ? 0.5 : 1;
            if (joystick.getRawButton(buttons.getForward())) {
                return mode;
            } else if (joystick.getRawButton(buttons.getBackward())) {
                return -mode;
            } else {
                return 0;
            }
        } else {
            double raw = joystick.getRawAxis(axis);
            return Math.abs(raw) >= 0.05 ? raw : 0;
        }
    }

    @Override
    public void run(Joystick joystick) {
        Arrays.stream(motors).parallel().forEach(motor -> motor.set(getFlipper(joystick)));
    }

    @Override
    public JoystickType getJoystickType() {
        return null; // meh, it doesn't use xbox so we don't need to implement this
    }
}
