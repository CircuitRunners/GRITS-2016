package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.Joystick;
import org.circuitrunners.grits_2016_stronghold.system.BasicRobotSystem;
import org.circuitrunners.grits_2016_stronghold.system.RobotSystem;

public enum RobotMap {
    LIFT(new BasicRobotSystem(1)
            .setBackwardSpeed(0.5)
            .setForwardSpeed(0.5)
            .setJoystickType(JoystickType.XBOX)
            .setAxis(1)),
    COLLECTOR(new BasicRobotSystem(2)
            .setJoystickType(JoystickType.JOYSTICK)
            .setButtons(new ButtonGroup(1, 2)));

    private RobotSystem system;

    RobotMap(RobotSystem system) {
        this.system = system;
    }

    public RobotSystem getSystem() {
        return system;
    }

    public enum JoystickType {
        JOYSTICK(0),
        XBOX(1);

        private Joystick joystick;

        JoystickType(int joystickPort) {
            joystick = new Joystick(joystickPort);
        }

        public Joystick getJoystick() {
            return joystick;
        }
    }
}
