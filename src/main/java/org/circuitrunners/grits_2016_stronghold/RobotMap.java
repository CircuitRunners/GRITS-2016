package org.circuitrunners.grits_2016_stronghold;

public enum RobotMap {
    FLASHLIGHT(new ButtonGroup(1, 1), MapMotorType.SWITCH, JoystickType.XBOX, 0), // HACK! we use the same value for both buttons to avoid writing a new class for systems using only one button
    FRONT_LEFT(MapMotorType.DRIVE_MOTOR, true, 2),
    FRONT_RIGHT(MapMotorType.DRIVE_MOTOR, 8),
    REAR_LEFT(MapMotorType.DRIVE_MOTOR, true, 3),
    REAR_RIGHT(MapMotorType.DRIVE_MOTOR, 9),
    INTAKE_ARM(new ButtonGroup(3, 2), MapMotorType.SYSTEM_MOTOR, JoystickType.XBOX, 4, 5),
    SHOOTER(new ButtonGroup(2, 3), MapMotorType.SYSTEM_MOTOR, true, true, true, JoystickType.JOYSTICK, 6, 7),
    LIFTER(new ButtonGroup(1, 9), MapMotorType.DOUBLE_SOLENOID, 0, 1),
    ROLLERS(new ButtonGroup(5, 4), MapMotorType.CAN, 12),
    AIMER(new ButtonGroup(6, 7), MapMotorType.CAN, false, true, JoystickType.JOYSTICK, 13);

    private boolean inverted = false;
    private MapMotorType type;
    private JoystickType joystickType = JoystickType.JOYSTICK;
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

    RobotMap(ButtonGroup buttons, MapMotorType type, JoystickType joystickType, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.joystickType = joystickType;
        this.ports = ports;
    }

    RobotMap(MapMotorType type, boolean inverted, int... ports) {
        this.type = type;
        this.inverted = inverted;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, MapMotorType type, boolean inverted, JoystickType joystickType, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.inverted = inverted;
        this.joystickType = joystickType;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, MapMotorType type, boolean inverted, boolean opposite, JoystickType joystickType, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.inverted = inverted;
        this.opposite = opposite;
        this.joystickType = joystickType;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, MapMotorType type, boolean inverted, boolean opposite, boolean flop, JoystickType joystickType, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.inverted = inverted;
        this.opposite = opposite;
        this.flop = flop;
        this.joystickType = joystickType;
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

    public JoystickType getJoystickType() {
        return joystickType;
    }

    public enum MapMotorType {
        DRIVE_MOTOR,
        SYSTEM_MOTOR,
        DOUBLE_SOLENOID,
        SWITCH,
        CAN
    }

    public enum JoystickType {
        JOYSTICK,
        XBOX
    }
}
