package org.circuitrunners.grits_2016_stronghold;

public enum RobotMap {
    FRONT_LEFT(MapMotorType.DRIVE_MOTOR, true, 0),
    FRONT_RIGHT(MapMotorType.DRIVE_MOTOR, 1),
    REAR_LEFT(MapMotorType.DRIVE_MOTOR, true, 2),
    REAR_RIGHT(MapMotorType.DRIVE_MOTOR, 3),
    INTAKE_ARM(new ButtonGroup(5, 6), MapMotorType.SYSTEM_MOTOR, 4),
    INTAKE_HEAD(new ButtonGroup(7, 8), MapMotorType.SYSTEM_MOTOR, 5),
    SHOOTER(new ButtonGroup(1, 2), MapMotorType.SYSTEM_MOTOR, true, 6),
    AIMER(new ButtonGroup(3, 4), MapMotorType.SYSTEM_MOTOR, 7);

    private boolean inverted = false;
    private MapMotorType type;
    private int[] ports;
    private ButtonGroup buttons = null;

    RobotMap(MapMotorType type, int... ports) {
        this.type = type;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, MapMotorType type, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.ports = ports;
    }

    RobotMap(MapMotorType type, boolean inverted, int... ports) {
        this.type = type;
        this.inverted = inverted;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, MapMotorType type, boolean inverted, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.inverted = inverted;
        this.ports = ports;
    }

    public int[] getPorts() {
        return ports;
    }

    public ButtonGroup getButtons() {
        return buttons;
    }

    public boolean getInverted() {
        return inverted;
    }

    public MapMotorType getType() {
        return type;
    }

    public enum MapMotorType {
        DRIVE_MOTOR,
        SYSTEM_MOTOR
    }
}
