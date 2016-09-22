package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

import java.util.ArrayList;

public class Robot extends IterativeRobot {

    Talon frontLeft;
    Talon frontRight;
    Talon backLeft;
    Talon backRight;

    ArrayList<BasicSystem> systems = new ArrayList<>();

    RobotDrive drive;

    Joystick joystick;

    @Override
    public void robotInit() {
        //SmartDashboard.putString("question", QuestionAnswerFactory.produceQA()[0]);
        //SmartDashboard.putString("answer", QuestionAnswerFactory.produceQA()[1]);
        frontLeft = new Talon(RobotMap.FRONT_LEFT.getPort());
        frontRight = new Talon(RobotMap.FRONT_RIGHT.getPort());
        backLeft = new Talon(RobotMap.BACK_LEFT.getPort());
        backRight = new Talon(RobotMap.BACK_RIGHT.getPort());

        frontRight.setInverted(true);
        backRight.setInverted(true);

        for (RobotMap motor : RobotMap.values()) {
            if (motor.getButtons() != null) {
                systems.add(new BasicSystem(motor.getButtons(), new Talon(motor.getPort())));
            }
        }

        joystick = new Joystick(0);

        drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
    }

    @Override
    public void teleopPeriodic() {
        boolean threshold = joystick.getMagnitude() > 0.1;
        drive.arcadeDrive(threshold ? joystick.getMagnitude() : 0, threshold ? -Math.cos(joystick.getDirectionRadians()) : 0);
        systems.forEach(b -> b.run(b.getFlipper(joystick)));
    }
}
