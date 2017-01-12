package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import org.circuitrunners.grits_2016_stronghold.system.BasicRobotSystem;
import org.circuitrunners.grits_2016_stronghold.system.RobotSystem;

public enum RobotMap {
    LIFT(new BasicRobotSystem()
            .setMotors(new Talon(1))
            .setBackwardSpeed(0.5)
            .setForwardSpeed(0.5)
            .setJoystickType(JoystickType.XBOX));

    private RobotSystem system;

    RobotMap(RobotSystem system) {
        this.system = system;
    }

    public RobotSystem getSystem() {
        return system;
    }

    public enum JoystickType {
        JOYSTICK(new Joystick(0)),
        XBOX(new Joystick(1));

        private Joystick joystick;

        JoystickType(Joystick joystick) {
            this.joystick = joystick;
        }

        public Joystick getJoystick() {
            return joystick;
        }
    }
}
