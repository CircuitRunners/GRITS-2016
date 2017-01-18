package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.circuitrunners.grits_2016_stronghold.qa.QuestionAnswerFactory;
import org.circuitrunners.grits_2016_stronghold.system.RobotSystem;

import java.util.Arrays;
import java.util.HashMap;

public class Robot extends IterativeRobot {
    // private ArrayList<Talon> driveSystem = new ArrayList<>(4); TODO: make new drive system
    private HashMap<RobotMap, RobotSystem> systemMap = new HashMap<>(RobotMap.values().length, 1);

    //RobotDrive drive;

    private AutonomousJoystick lsd;

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
        // TODO: update to new robot system system
        // TODO: finish enum-based autonomous
        lsd = new AutonomousJoystick(2);
        long endTime;
        for (AutonomousSteps autoStep : AutonomousSteps.values()) {
            RobotMap system = autoStep.getSystem();
            endTime = System.currentTimeMillis() - autoStep.getDuration();
            while (System.currentTimeMillis() < endTime) {
                system.getSystem().run(lsd); // TODO: make this actually work
            }
        }
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        systemMap.values().parallelStream().forEach(s -> s.run(s.getJoystickType().getJoystick()));
    }

    @Override
    public void disabledInit() {
        /*drive.arcadeDrive(0, 0);*/
    }
}
