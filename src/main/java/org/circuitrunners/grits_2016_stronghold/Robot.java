package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.circuitrunners.grits_2016_stronghold.qa.QuestionAnswerFactory;
import org.circuitrunners.grits_2016_stronghold.system.RobotSystem;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Robot extends IterativeRobot {
    private EnumMap<RobotMap, RobotSystem> systemMap = new EnumMap<>(RobotMap.class);

    private AutonomousJoystick lsd;

    @Override
    public void robotInit() {
        Arrays.stream(RobotMap.values()).parallel().forEach(s -> systemMap.put(s, s.getSystem()));

        String[] qa = QuestionAnswerFactory.produceQA();
        System.out.println("Question: " + qa[0] + " Answer: " + qa[1]);
    }

    @Override
    public void autonomousInit() {
        // TODO: finish enum-based autonomous
        ScheduledExecutorService stopAuto = Executors.newSingleThreadScheduledExecutor();
        lsd = new AutonomousJoystick(2);
        long endTime;
        for (AutonomousSteps autoStep : AutonomousSteps.values()) {
            RobotSystem system = autoStep.getSystem().getSystem();
            Thread thread = new Thread(() -> system.run(lsd));
            thread.start();
            endTime = System.currentTimeMillis() - autoStep.getDuration();
            stopAuto.schedule(thread::interrupt, endTime, TimeUnit.MILLISECONDS);
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
    }
}
