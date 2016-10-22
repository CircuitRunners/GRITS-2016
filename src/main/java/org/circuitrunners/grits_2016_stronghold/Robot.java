package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.*;
import org.circuitrunners.grits_2016_stronghold.qa.AutonomousJoystick;
import org.circuitrunners.grits_2016_stronghold.qa.QuestionAnswerFactory;
import org.circuitrunners.grits_2016_stronghold.system.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Robot extends IterativeRobot {
    private ArrayList<Talon> driveSystem = new ArrayList<>();
    private ArrayList<RobotSystem> systems = new ArrayList<>();

    private RobotDrive drive;

    private Joystick joystick;
    private AutonomousJoystick lsd;
    private Joystick xbox;

    private RobotDriveThread robotDriveThread;

    @Override
    public void robotInit() {
        Arrays.asList(RobotMap.values()).parallelStream().forEach(system -> {
            switch (system.getType()) {
                case SYSTEM_MOTOR:
                    if (system.isOpposite()) {
                        systems.add(new InvertedRobotSystem(system.getButtons(), system.getInverted(), system.isFlop(), system.getJoystickType(), Arrays.stream(system.getPorts()).mapToObj(Talon::new).toArray(Talon[]::new)));
                    } else {
                        systems.add(new BasicRobotSystem(system.getButtons(), system.getInverted(), system.getJoystickType(), Arrays.stream(system.getPorts()).mapToObj(Talon::new).toArray(Talon[]::new)));
                    }
                    break;
                case DRIVE_MOTOR:
                    Talon motor = new Talon(system.getPorts()[0]);
                    if (system.getInverted()) {
                        motor.setInverted(true);
                    }
                    driveSystem.add(motor);
                    break;
                case DOUBLE_SOLENOID:
                    systems.add(new DoubleSolenoidRobotSystem(system.getButtons(), new DoubleSolenoid(1, system.getPorts()[0], system.getPorts()[1])));
                    break;
                case CAN:
                    systems.add(new CANRobotSystem(system.getButtons(), system.getInverted(), system.isOpposite(), new CANTalon(system.getPorts()[0])));
                    break;
                case SWITCH:
                    systems.add(new SwitchSystem(system.getButtons(), system.getJoystickType(), new Relay(system.getPorts()[0])));
                    break;
                default:
                    System.out.println("Did you just assume my MapMotorType?!?!?! I'm " + system.getType() + "!!!!!!! [TRIGGERED]");
                    break;
            }
        });

        driveSystem.sort(Comparator.comparingInt(PWM::getChannel));
        drive = new RobotDrive(driveSystem.get(0), driveSystem.get(1), driveSystem.get(2), driveSystem.get(3));

        joystick = new Joystick(0);
        xbox = new Joystick(1);

        String[] qa = QuestionAnswerFactory.produceQA();
        System.out.println("question: " + qa[0] + "\nanswer: " + qa[1]);
    }

    @Override
    public void autonomousInit() {
        /*lsd = new AutonomousJoystick(5);
        lsd.setRawButton(3, true);
        systems.forEach(s -> s.run(lsd));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            lsd.setRawButton(3, false);
            systems.forEach(s -> s.run(lsd));
        }
        lsd.setRawButton(3, false);
        systems.forEach(s -> s.run(lsd));
        drive.arcadeDrive(1, 0);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            drive.arcadeDrive(0, 0);
        }
        drive.arcadeDrive(0, 0);*/
    }

    @Override
    public void teleopInit() {
        if (robotDriveThread == null) {
            robotDriveThread = new RobotDriveThread();
        }
        robotDriveThread.start();
    }

    @Override
    public void teleopPeriodic() {
        systems.parallelStream().forEach(s -> s.run(s.getJoystickType() == RobotMap.JoystickType.XBOX ? xbox : joystick)); // only works with two joysticks
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
}
