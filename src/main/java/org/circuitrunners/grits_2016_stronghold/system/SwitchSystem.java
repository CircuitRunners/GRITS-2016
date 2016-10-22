package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;
import org.circuitrunners.grits_2016_stronghold.RobotMap;

import java.util.ArrayList;
import java.util.Arrays;

public class SwitchSystem implements RobotSystem {

    private final ButtonGroup buttons;
    private RobotMap.JoystickType joystickType;
    private ArrayList<Relay> motors;
    private boolean pressed = false;

    public SwitchSystem(ButtonGroup buttons, RobotMap.JoystickType joystickType, Relay... motors) {
        this.buttons = buttons;
        this.joystickType = joystickType;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    private Relay.Value getFlipper(Joystick joystick) {
        if (joystick.getRawButton(buttons.getForward())) {
            pressed = !pressed;
        }
        return pressed ? Relay.Value.kForward : Relay.Value.kOff;
    }

    @Override
    public void run(Joystick joystick) {
        motors.parallelStream().forEach(m -> m.set(getFlipper(joystick)));
    }

    @Override
    public RobotMap.JoystickType getJoystickType() {
        return joystickType;
    }
}
