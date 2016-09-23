package org.circuitrunners.grits_2016_stronghold;

public enum RobotMap {
    FRONT_LEFT(0),
    FRONT_RIGHT(1),
    BACK_LEFT(2),
    BACK_RIGHT(3),
    INTAKE_ARM(new ButtonGroup(5, 6), 4),
    INTAKE_HEAD(new ButtonGroup(7, 8), 5),
    SHOOTER(new ButtonGroup(1, 2), true, 6),
    AIMER(new ButtonGroup(3, 4), 7);

    private boolean inverted = false;
    private int[] ports;
    private ButtonGroup buttons = null;

    RobotMap(int... ports) {
        this.ports = ports;
    }

    RobotMap(ButtonGroup buttons, int... ports) {
        this.ports = ports;
        this.buttons = buttons;
    }

    RobotMap(ButtonGroup buttons, boolean inverted, int... ports) {
        this.inverted = inverted;
        this.ports = ports;
        this.buttons = buttons;
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
}
