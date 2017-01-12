package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.circuitrunners.grits_2016_stronghold.qa.QuestionAnswerFactory;
import org.circuitrunners.grits_2016_stronghold.system.BasicRobotSystem;
import org.circuitrunners.grits_2016_stronghold.system.DoubleSolenoidRobotSystem;
import org.circuitrunners.grits_2016_stronghold.system.RobotSystem;

import java.util.Arrays;
import java.util.HashMap;

public class Robot extends IterativeRobot {
    // private ArrayList<Talon> driveSystem = new ArrayList<>(4); TODO: make new drive system
    private HashMap<RobotMap, RobotSystem> systemMap = new HashMap<>(RobotMap.values().length, 1);

    //RobotDrive drive;

    private AutonomousJoystick lsd;

    private final RobotDriveThread robotDriveThread = new RobotDriveThread();

    // private DigitalInput intakeArmLimit; TODO: use intake arm limit switch

    @Override
    public void robotInit() {
        Arrays.stream(RobotMap.values()).parallel().forEach(s -> systemMap.put(s, s.getSystem()));

        // intakeArmLimit = new DigitalInput(1);

        String[] qa = QuestionAnswerFactory.produceQA();
        System.out.println("Question: " + qa[0] + " Answer: " + qa[1]);
    }

    @Override
    public void autonomousInit() {
        /* TODO: update to new robot system system
        // TODO: finish enum-based autonomous
        lsd = new AutonomousJoystick(2);
        long endTime;
        for (AutonomousSteps autoStep : AutonomousSteps.values()) {
            RobotMap system = autoStep.getSystem();
            if (system.getType() == SystemType.DRIVE_MOTOR) {
                endTime = System.currentTimeMillis() - autoStep.getDuration();
                while (System.currentTimeMillis() < endTime) {
                    drive.arcadeDrive(autoStep.getDirection() ? 1 : -1, 0);
                }
                drive.arcadeDrive(0, 0);
            } else {
                ButtonGroup buttons = system.getButtons();
                int button;
                if (autoStep.getDirection()) {
                    lsd.setRawButton(button = buttons.getForward(), true);
                } else {
                    lsd.setRawButton(button = buttons.getBackward(), true);
                }
                endTime = System.currentTimeMillis() - autoStep.getDuration();
                while (System.currentTimeMillis() < endTime) {
                    systems.parallelStream().forEach(s -> {
                        if (s.getJoystickType() == system.getJoystickType()) {
                            s.run(lsd);
                        }
                    });
                }
                lsd.setRawButton(button, false);
                systems.parallelStream().forEach(s -> s.run(lsd));
            }
        }
        */

        /*
        *** GRITS autonomous ***

        lsd.setRawButton(2, true);

        long stop = System.currentTimeMillis() + 1000;
        while (System.currentTimeMillis() < stop && limitSwitch.get()) {
            systems.parallelStream().forEach(s -> {
                if (s.getJoystickType() == RobotMap.JoystickType.XBOX) {
                    s.run(lsd);
                }
            });
        }
        lsd.setRawButton(2, false);
        systems.parallelStream().forEach(s -> s.run(lsd));

        stop = System.currentTimeMillis() + 4000;
        while (System.currentTimeMillis() < stop) {
            drive.arcadeDrive(-0.5, 0);
        }
        drive.arcadeDrive(0, 0);
        */
    }

    @Override
    public void teleopInit() {
        robotDriveThread.start();
    }

    @Override
    public void teleopPeriodic() {
        systemMap.values().parallelStream().forEach(s -> s.run(s.getJoystickType().getJoystick()));
    }

    private class RobotDriveThread extends Thread {

            @Override
            public void run() {
                while (isOperatorControl()) {
                    boolean thresholdL = Math.abs(xbox.getRawAxis(1)) >= 0.05;
                    boolean thresholdR = Math.abs(xbox.getRawAxis(4)) >= 0.05;
                    drive.arcadeDrive(thresholdL ? xbox.getRawAxis(1) : 0, thresholdR ? xbox.getRawAxis(4) : 0);
                }
                interrupt();
            }

            @Override
            public void interrupt() {
                drive.arcadeDrive(0, 0);
            }
    }


    @Override
    public void disabledInit() {
        drive.arcadeDrive(0, 0);
    }
}
