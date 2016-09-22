package org.circuitrunners.grits_2016_stronghold;

public class QuestionAnswerFactory {
    public static String[] produceQA() {
        return new String[]{QuestionFactory.produceQuestion(), AnswerFactory.produceAnswer()};
    }
}
