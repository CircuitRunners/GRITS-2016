package org.circuitrunners.grits_2016_stronghold;

public enum AutonomousSteps {
    INTAKE(RobotMap.LIFT, true, 5000);

    private RobotMap system;
    private boolean direction;
    private int duration;
    private AutonomousSteps next;
    private AutonomousSteps previous;

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
