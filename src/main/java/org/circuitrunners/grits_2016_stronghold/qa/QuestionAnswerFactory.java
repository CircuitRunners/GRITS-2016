package org.circuitrunners.grits_2016_stronghold.qa;

public class QuestionAnswerFactory {
    public static String[] produceQA() {
        return new String[]{QuestionFactory.produceQuestion(), AnswerFactory.produceAnswer()};
    }
}
