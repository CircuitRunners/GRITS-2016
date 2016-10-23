package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap.JoystickType;

import java.util.Arrays;

public class SwitchSystem implements RobotSystem {

    private final ButtonGroup buttons;
    private JoystickType joystickType;
    private Relay[] motors;
    private boolean pressed;

    public SwitchSystem(ButtonGroup buttons, JoystickType joystickType, Relay... motors) {
        this.buttons = buttons;
        this.joystickType = joystickType;
        this.motors = motors;
    }

    private Value getFlipper(Joystick joystick) {
        if (joystick.getRawButton(buttons.getForward())) {
            pressed = !pressed;
        }
        return pressed ? Value.kForward : Value.kOff;
    }

    @Override
    public void run(Joystick joystick) {
        Arrays.stream(motors).parallel().forEach(m -> m.set(getFlipper(joystick)));
    }

    @Override
    public JoystickType getJoystickType() {
        return joystickType;
    }
}
