package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class Robot extends IterativeRobot {

    Talon frontLeft;
    Talon frontRight;
    Talon backLeft;
    Talon backRight;

    RobotDrive drive;

    Joystick joystick;

    @Override
    public void robotInit() {
        frontLeft = new Talon(0);
        frontRight = new Talon(1);
        backLeft = new Talon(2);
        backRight = new Talon(3);

        joystick = new Joystick(0);

        drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
    }

    @Override
    public void teleopPeriodic() {
        if (joystick.getMagnitude() > 0.1) {
            drive.arcadeDrive(joystick, true);
        }
    }
}
