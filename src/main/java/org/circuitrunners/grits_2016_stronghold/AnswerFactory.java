package org.circuitrunners.grits_2016_stronghold;

import java.util.concurrent.ThreadLocalRandom;

public class AnswerFactory {

    private static int lastAnswerIndex = -1;

    public static String produceAnswer() {
        String[] answers = {"nipple salads! nipple salads! nipple salads!", "I see feel good needles", "why aren't my fingers in someone's eye sockets right now?", "IM THE CONDUCTOR OF THE POOP TRAIN!!!", "I HAVE THE SHINIEST MEAT BICYCLE!!!", "im going to put my pain into your soul"};
        int randomIndex = ThreadLocalRandom.current().nextInt(0, 6);
        if (randomIndex == lastAnswerIndex) {
            return produceAnswer();
        } else {
            lastAnswerIndex = randomIndex;
            return answers[randomIndex];
        }
    }
}
