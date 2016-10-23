package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.Joystick;

import java.util.HashMap;

public class AutonomousJoystick extends Joystick {

    private HashMap<Integer, Boolean> fakeButtons = new HashMap<>(10, 1);

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
