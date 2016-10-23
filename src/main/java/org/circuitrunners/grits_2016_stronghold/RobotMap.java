package org.circuitrunners.grits_2016_stronghold;

public enum RobotMap {
    FLASHLIGHT(new ButtonGroup(1, 1), SystemType.SWITCH, JoystickType.XBOX, 0), // HACK! we use the same value for both buttons to avoid writing a new class for systems using only one button
    FRONT_LEFT(SystemType.DRIVE_MOTOR, true, 2),
    FRONT_RIGHT(SystemType.DRIVE_MOTOR, 8),
    REAR_LEFT(SystemType.DRIVE_MOTOR, true, 3),
    REAR_RIGHT(SystemType.DRIVE_MOTOR, 9),
    INTAKE_ARM(new ButtonGroup(3, 2), JoystickType.XBOX, 4, 5),
    SHOOTER(new ButtonGroup(3, 2), 1, 0.5, 6, 7),
    LIFTER(new ButtonGroup(1, 9), SystemType.DOUBLE_SOLENOID, 0, 1),
    ROLLERS(new ButtonGroup(5, 4), SystemType.CAN, 12),
    AIMER(1, SystemType.CAN, true, 13);

    private SystemType type = SystemType.SYSTEM_MOTOR;
    private JoystickType joystickType = JoystickType.JOYSTICK;
    /*
     * Alternative mode - a convenient switch for a special setting according to the motor type
     * System motor: every other motor moves in the opposite direction
     * Drive motor: flips direction of the drive motor
     * CAN motor: sets it to half speed
     */
    private boolean alternative;
    private double forwardSpeed = 1;
    private double backwardSpeed = 1;
    private int[] ports;
    private ButtonGroup buttons;
    private int axis;

    RobotMap(SystemType type, int... ports) {
        this.type = type;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, int... ports) {
        this.buttons = buttons;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, double forwardSpeed, double backwardSpeed, int... ports) {
        this.buttons = buttons;
        this.forwardSpeed = forwardSpeed;
        this.backwardSpeed = backwardSpeed;
        this.ports = ports;
    }

    RobotMap(int axis, int... ports) {
        this.axis = axis;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, SystemType type, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, JoystickType joystickType, int... ports) {
        this.buttons = buttons;
        this.joystickType = joystickType;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, SystemType type, JoystickType joystickType, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.joystickType = joystickType;
        this.ports = ports;
    }

    RobotMap(SystemType type, boolean alternative, int... ports) {
        this.type = type;
        this.alternative = alternative;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, SystemType type, boolean alternative, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.alternative = alternative;
        this.ports = ports;
    }

    RobotMap(int axis, SystemType type, boolean alternative, int... ports) {
        this.axis = axis;
        this.type = type;
        this.alternative = alternative;
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, SystemType type, boolean alternative, JoystickType joystickType, int... ports) {
        this.buttons = buttons;
        this.type = type;
        this.alternative = alternative;
        this.joystickType = joystickType;
        this.ports = ports;
    }

    public int[] getPorts() {
        return ports;
    }

    public ButtonGroup getButtons() {
        return buttons;
    }

    public boolean isAlternative() {
        return alternative;
    }

    public SystemType getType() {
        return type;
    }

    public JoystickType getJoystickType() {
        return joystickType;
    }

    public int getAxis() {
        return axis;
    }

    public double getForwardSpeed() {
        return forwardSpeed;
    }

    public double getBackwardSpeed() {
        return backwardSpeed;
    }

    public enum SystemType {
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
