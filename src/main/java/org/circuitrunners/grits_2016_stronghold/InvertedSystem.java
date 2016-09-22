package org.circuitrunners.grits_2016_stronghold;

import edu.wpi.first.wpilibj.Talon;

public class InvertedSystem extends BasicSystem {
    public InvertedSystem(ButtonGroup buttons, Talon... motors) {
        super(buttons, motors);
    }

    @Override
    public void run(int flipper) {
        for (int i = 0; i < motors.size() - 1; i += 2) {
            motors.get(i).set(flipper);
            motors.get(i + 1).set(-flipper);
        }
    }
}
