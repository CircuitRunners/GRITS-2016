package org.circuitrunners.grits_2016_stronghold;

public enum RobotMap {
    FLASHLIGHT(new ButtonGroup(10, 10), MapMotorType.SWITCH, 1), // HACK! we use the same value for both buttons to avoid writing a new class for systems using only one button
    FRONT_LEFT(MapMotorType.DRIVE_MOTOR, true, 2),
    FRONT_RIGHT(MapMotorType.DRIVE_MOTOR, 8),
    REAR_LEFT(MapMotorType.DRIVE_MOTOR, true, 3),
    REAR_RIGHT(MapMotorType.DRIVE_MOTOR, 9),
    INTAKE_ARM(new ButtonGroup(4, 5), MapMotorType.SYSTEM_MOTOR, 4, 5),
    SHOOTER(new ButtonGroup(1, 2), MapMotorType.SYSTEM_MOTOR, true, true, true, 6, 7),
    LIFTER(new ButtonGroup(1, 2), MapMotorType.DOUBLE_SOLENOID, 0, 1),
    SCALER(new ButtonGroup(11, 11), MapMotorType.CAN, 11), //
    ROLLERS(new ButtonGroup(8, 9), MapMotorType.CAN, 13), //
    AIMER(new ButtonGroup(6, 7), MapMotorType.CAN, 12); //

    private boolean inverted = false;
    private MapMotorType type;
    private boolean opposite = false;
    private boolean flop = false;
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

    RobotMap(ButtonGroup buttons, MapMotorType type, boolean inverted, boolean opposite, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.inverted = inverted;
        this.opposite = opposite;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, MapMotorType type, boolean inverted, boolean opposite, boolean flop, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.inverted = inverted;
        this.opposite = opposite;
        this.flop = flop;
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

    public boolean isOpposite() {
        return opposite;
    }

    public boolean isFlop() {
        return flop;
    }

    public enum MapMotorType {
        DRIVE_MOTOR,
        SYSTEM_MOTOR,
        DOUBLE_SOLENOID,
        SWITCH,
        CAN
    }
}
