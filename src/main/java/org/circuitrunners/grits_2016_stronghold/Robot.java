package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

import java.util.ArrayList;
import java.util.Arrays;

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
        frontLeft = new Talon(RobotMap.FRONT_LEFT.getPorts()[0]);
        frontRight = new Talon(RobotMap.FRONT_RIGHT.getPorts()[0]);
        backLeft = new Talon(RobotMap.BACK_LEFT.getPorts()[0]);
        backRight = new Talon(RobotMap.BACK_RIGHT.getPorts()[0]);

        frontRight.setInverted(true);
        backRight.setInverted(true);

        for (RobotMap system : RobotMap.values()) {
            if (system.getButtons() != null) {
                if (system.getInverted()) {
                    systems.add(new InvertedSystem(system.getButtons(), Arrays.stream(system.getPorts()).mapToObj(Talon::new).toArray(Talon[]::new)));
                } else {
                    systems.add(new BasicSystem(system.getButtons(), Arrays.stream(system.getPorts()).mapToObj(Talon::new).toArray(Talon[]::new)));
                }
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
