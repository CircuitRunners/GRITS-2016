package org.circuitrunners.grits_2016_stronghold.qa;

import java.util.concurrent.ThreadLocalRandom;

public class QuestionFactory {

    private static int lastQuestionIndex = -1;

    public static String produceQuestion() {
        String[] questions = {"How was school?", "What is the meaning of life?", "What are you most proud of?", "Who is your favorite US president? Mine is George W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W W Bush."};
        int randomIndex = ThreadLocalRandom.current().nextInt(0, 5);
        if (randomIndex == lastQuestionIndex) {
            return produceQuestion();
        } else {
            lastQuestionIndex = randomIndex;
            return questions[randomIndex];
        }
    }
}
