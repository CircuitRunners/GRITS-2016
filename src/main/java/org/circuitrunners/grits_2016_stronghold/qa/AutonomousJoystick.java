package org.circuitrunners.grits_2016_stronghold.qa;

import edu.wpi.first.wpilibj.Joystick;

import java.util.HashMap;

public class AutonomousJoystick extends Joystick {

    HashMap<Integer, Boolean> fakeButtons = new HashMap<>();

    public AutonomousJoystick(int port) {
        super(port);
    }

    @Override
    public boolean getRawButton(int button) {
        if (fakeButtons.containsKey(button)) {
            return fakeButtons.get(button);
        }
        return super.getRawButton(button);
    }

    public void setRawButton(int button, boolean pressed) {
        fakeButtons.put(button, pressed);
    }
}
