package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.*;
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

    private Joystick xbox;

    @Override
    public void robotInit() {
        Talon motor;
        for (RobotMap system : RobotMap.values()) {
            if (system.getType() == RobotMap.MapMotorType.SYSTEM_MOTOR) {
                if (system.isOpposite()) {
                    systems.add(new InvertedRobotSystem(system.getButtons(), system.getInverted(), system.isFlop(), Arrays.stream(system.getPorts()).mapToObj(Talon::new).toArray(Talon[]::new)));
                } else {
                    systems.add(new BasicRobotSystem(system.getButtons(), system.getInverted(), Arrays.stream(system.getPorts()).mapToObj(Talon::new).toArray(Talon[]::new)));
                }
            } else if (system.getType() == RobotMap.MapMotorType.DRIVE_MOTOR) {
                motor = new Talon(system.getPorts()[0]);
                if (system.getInverted()) {
                    motor.setInverted(true);
                }
                driveSystem.add(motor);
            } else if (system.getType() == RobotMap.MapMotorType.DOUBLE_SOLENOID) {
                systems.add(new DoubleSolenoidRobotSystem(system.getButtons(), new DoubleSolenoid(1, system.getPorts()[0], system.getPorts()[1])));
            } else if (system.getType() == RobotMap.MapMotorType.CAN) {
                systems.add(new CANRobotSystem(system.getButtons(), system.getInverted(), system.isOpposite(), new CANTalon(system.getPorts()[0])));
            } else if (system.getType() == RobotMap.MapMotorType.SWITCH) {
                systems.add(new SwitchSystem(system.getButtons(), new Relay(system.getPorts()[0])));
            } else {
                System.out.println("Did you just assume my MapMotorType?!?!?! I'm " + system.getType() + "!!!!!!! [TRIGGERED]");
            }
        }

        driveSystem.sort(Comparator.comparingInt(PWM::getChannel));

        joystick = new Joystick(0);
        xbox = new Joystick(1);

        drive = new RobotDrive(driveSystem.get(0), driveSystem.get(1), driveSystem.get(2), driveSystem.get(3));

        String[] qa = QuestionAnswerFactory.produceQA();
        System.out.println("question: " + qa[0] + "\nanswer: " + qa[1]);
    }

    @Override
    public void teleopPeriodic() {
        boolean thresholdL = joystick.getMagnitude() >= 0.05;
        drive.arcadeDrive(thresholdL ? joystick.getY() : 0, thresholdL ? joystick.getX() : 0);
        systems.forEach(s -> s.run(joystick)); // only works with two joysticks
    }
}
