package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import org.circuitrunners.grits_2016_stronghold.RobotMap.JoystickType;

public interface RobotSystem {
    void run(Joystick joystick);

    JoystickType getJoystickType();
}
