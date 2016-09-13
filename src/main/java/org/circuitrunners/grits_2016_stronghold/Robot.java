package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

import java.util.HashMap;

public class Robot extends IterativeRobot {

    Talon frontLeft;
    Talon frontRight;
    Talon backLeft;
    Talon backRight;

    HashMap<Talon, ButtonGroup> basicMotors = new HashMap<>();

    RobotDrive drive;

    Joystick joystick;

    @Override
    public void robotInit() {
        frontLeft = new Talon(0);
        frontRight = new Talon(1);
        backLeft = new Talon(2);
        backRight = new Talon(3);

        int[][] basicMotorPorts = {{4, 4, 5}, {5, 6, 7}, {6, 0, 1}, {7, 2, 3}};

        for (int[] ids : basicMotorPorts) {
            basicMotors.put(new Talon(ids[0]), new ButtonGroup(ids[1], ids[2]));
        }

        joystick = new Joystick(0);

        drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
    }

    @Override
    public void teleopPeriodic() {
        boolean threshold = joystick.getMagnitude() > 0.1;
        drive.arcadeDrive(threshold ? joystick.getMagnitude() : 0, threshold ? -Math.cos(joystick.getDirectionRadians()) : 0);
        basicMotors.forEach(this::basicMotor);
    }

    private void basicMotor(Talon motor, ButtonGroup buttons) {
        if (joystick.getRawButton(buttons.getForward())) {
            motor.set(1);
        } else if (joystick.getRawButton(buttons.getBackward())) {
            motor.set(-1);
        } else {
            motor.set(0);
        }
    }
}
