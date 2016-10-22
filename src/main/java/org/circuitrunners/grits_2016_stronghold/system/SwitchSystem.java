package org.circuitrunners.grits_2016_stronghold.system;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import org.circuitrunners.grits_2016_stronghold.ButtonGroup;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cr7 on 10/20/16.
 */
public class SwitchSystem implements RobotSystem {

    private final ButtonGroup buttons;
    ArrayList<Relay> motors;
    private boolean pressed = true;

    public SwitchSystem(ButtonGroup buttons, Relay... motors) {
        this.buttons = buttons;
        this.motors = new ArrayList<>(Arrays.asList(motors));
    }

    protected Relay.Value getFlipper(Joystick joystick) {
        /*if (joystick.getRawButton(buttons.getForward())) {
            pressed = !pressed;
        }*/
        return pressed ? Relay.Value.kForward : Relay.Value.kOff;
    }

    @Override
    public void run(Joystick joystick) {
        motors.forEach(m -> m.set(getFlipper(joystick)));
    }
}
