package org.circuitrunners.grits_2016_stronghold;

public enum RobotMap {
    FRONT_LEFT(null, 0),
    FRONT_RIGHT(null, 1),
    BACK_LEFT(null, 2),
    BACK_RIGHT(null, 3),
    INTAKE_ARM(new ButtonGroup(5, 6), 4),
    INTAKE_HEAD(new ButtonGroup(7, 8), 5),
    SHOOTER(new ButtonGroup(1, 2), 6),
    AIMER(new ButtonGroup(3, 4), 7);

    int ports;
    ButtonGroup buttons;

    RobotMap(ButtonGroup buttons, int ports) {
        this.ports = ports;
        this.buttons = buttons;
    }

    public int getPort() {
        return ports;
    }

    public ButtonGroup getButtons() {
        return buttons;
    }
}
