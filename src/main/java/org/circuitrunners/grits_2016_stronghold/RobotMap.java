package org.circuitrunners.grits_2016_stronghold;

public enum RobotMap {
    FRONT_LEFT(0),
    FRONT_RIGHT(1),
    BACK_LEFT(2),
    BACK_RIGHT(3),
    INTAKE_ARM(4, new ButtonGroup(4, 5)),
    INTAKE_HEAD(5, new ButtonGroup(6, 7)),
    SHOOTER(6, new ButtonGroup(0, 1)),
    AIMER(7, new ButtonGroup(2, 3));

    int port;
    ButtonGroup buttons = null;

    RobotMap(int port) {
        this.port = port;
    }

    RobotMap(int port, ButtonGroup buttons) {
        this.port = port;
        this.buttons = buttons;
    }

    public int getPort() {
        return port;
    }

    public ButtonGroup getButtons() {
        return buttons;
    }
}


