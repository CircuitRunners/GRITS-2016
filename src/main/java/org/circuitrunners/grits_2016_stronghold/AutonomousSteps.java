package org.circuitrunners.grits_2016_stronghold;

public enum AutonomousSteps {
    INTAKE(RobotMap.INTAKE_ARM, true, 5000, AutonomousSteps.DRIVE, null),
    DRIVE(RobotMap.FRONT_LEFT, true, 5000, null, AutonomousSteps.INTAKE),
    SEX(),
    NEUROTOXIN();

    private RobotMap system = null;
    private boolean direction = false;
    private int duration = 0;
    private AutonomousSteps next = null;
    private AutonomousSteps previous = null;

    AutonomousSteps(RobotMap system, boolean direction, int duration) {
        this.system = system;
        this.direction = direction;
        this.duration = duration;
    }

    AutonomousSteps(RobotMap system, boolean direction, int duration, AutonomousSteps next, AutonomousSteps previous) {
        this.system = system;
        this.direction = direction;
        this.duration = duration;
        this.next = next;
        this.previous = previous;
    }

    AutonomousSteps() {
        System.out.println("what a memer... you didn't define the autonomous step you declared");
    }

    public RobotMap getSystem() {
        return system;
    }

    public boolean getDirection() {
        return direction;
    }

    public int getDuration() {
        return duration;
    }

    public AutonomousSteps getPrevious() {
        return previous;
    }

    public AutonomousSteps getNext() {
        return next;
    }
}
