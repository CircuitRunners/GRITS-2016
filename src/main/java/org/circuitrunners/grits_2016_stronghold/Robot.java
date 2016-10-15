package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.*;
import org.circuitrunners.grits_2016_stronghold.qa.QuestionAnswerFactory;
import org.circuitrunners.grits_2016_stronghold.system.BasicRobotSystem;
import org.circuitrunners.grits_2016_stronghold.system.DoubleSolenoidRobotSystem;
import org.circuitrunners.grits_2016_stronghold.system.InvertedRobotSystem;
import org.circuitrunners.grits_2016_stronghold.system.RobotSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Robot extends IterativeRobot {
    private ArrayList<Talon> driveSystem = new ArrayList<>();
    private ArrayList<RobotSystem> systems = new ArrayList<>();

    private RobotDrive drive;

    private Joystick joystick;

    @Override
    public void robotInit() {
        System.out.println("question: " + QuestionAnswerFactory.produceQA()[0]);
        System.out.println("answer" + QuestionAnswerFactory.produceQA()[1]);

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
                systems.add(new DoubleSolenoidRobotSystem(system.getButtons(), new DoubleSolenoid(system.getPorts()[0], system.getPorts()[1])));
            } else {
                System.out.println("Did you just assume my MapMotorType?!?!?!");
            }
        }

        driveSystem.sort(Comparator.comparingInt(PWM::getChannel));

        joystick = new Joystick(0);

        drive = new RobotDrive(driveSystem.get(0), driveSystem.get(2), driveSystem.get(1), driveSystem.get(3));
    }

    @Override
    public void teleopPeriodic() {
        boolean threshold = joystick.getMagnitude() >= 0.05;
        drive.arcadeDrive(threshold ? joystick.getY() : 0, threshold ? joystick.getX() : 0);
        systems.forEach(s -> s.run(joystick));
    }
}
