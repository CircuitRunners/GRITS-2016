package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;

public class CANEncoderRobotSystem extends CANRobotSystem {

    private Encoder encoder;

    public CANEncoderRobotSystem(ButtonGroup buttons, boolean flip, CANTalon... motors) {
        super(buttons, flip, motors);
    }

    public CANEncoderRobotSystem(ButtonGroup buttons, boolean flip, Encoder encoder, CANTalon... motors) {
        this(buttons, flip, motors);
        this.encoder = encoder;
        this.motors.forEach(motor -> {
            motor.setP(0.1);
            motor.setI(0.001);
            motor.setD(0.0011);
        });
    }

    @Override
    public void run(Joystick joystick) {
        motors.forEach(motor -> motor.set(getFlipper(joystick)));
    }
}
