package org.circuitrunners.grits_2016_stronghold;

public class ButtonGroup {

    private final int forward;
    private final int backward;

    public ButtonGroup(int forward, int backward) {
        this.forward = forward;
        this.backward = backward;
    }

    public int getForward() {
        return forward;
    }

    public int getBackward() {
        return backward;
    }
}
