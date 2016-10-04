package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Robot extends IterativeRobot {
    private ArrayList<Talon> driveSystem = new ArrayList<>();
    private ArrayList<BasicSystem> systems = new ArrayList<>();

    private RobotDrive drive;

    private Joystick joystick;

    @Override
    public void robotInit() {
        SmartDashboard.putString("question", QuestionAnswerFactory.produceQA()[0]);
        SmartDashboard.putString("answer", QuestionAnswerFactory.produceQA()[1]);

        Talon motor;
        for (RobotMap system : RobotMap.values()) {
            if (system.getType() == RobotMap.MapMotorType.SYSTEM_MOTOR) {
                if (system.getInverted()) {
                    systems.add(new InvertedSystem(system.getButtons(), Arrays.stream(system.getPorts()).mapToObj(Talon::new).toArray(Talon[]::new)));
                } else {
                    systems.add(new BasicSystem(system.getButtons(), Arrays.stream(system.getPorts()).mapToObj(Talon::new).toArray(Talon[]::new)));
                }
            } else if (system.getType() == RobotMap.MapMotorType.DRIVE_MOTOR){
                motor = new Talon(system.getPorts()[0]);
                if (system.getInverted()) {
                    motor.setInverted(true);
                }
                driveSystem.add(motor);
            } else {
                System.out.println("Did you just assume my MapMotorType?!?!?!");
            }
        }

        driveSystem.sort(Comparator.comparingInt(PWM::getChannel));

        joystick = new Joystick(0);

        drive = new RobotDrive(driveSystem.get(0), driveSystem.get(1), driveSystem.get(2), driveSystem.get(3));
    }

    @Override
    public void teleopPeriodic() {
        boolean threshold = joystick.getMagnitude() >= 0.1;
        drive.arcadeDrive(threshold ? joystick.getX() : 0, threshold ? joystick.getY() : 0);
        systems.forEach(b -> b.run(b.getFlipper(joystick)));
    }
}
