package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import org.circuitrunners.grits_2016_stronghold.RobotMap;

public interface RobotSystem {
    void run(Joystick joystick);

    RobotMap.JoystickType getJoystickType();
}
