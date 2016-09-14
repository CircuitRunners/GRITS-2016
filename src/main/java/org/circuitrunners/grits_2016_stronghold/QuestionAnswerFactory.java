package org.circuitrunners.grits_2016_stronghold;

/**
 * Created by runner on 9/14/16.
 */
public class QuestionAnswerFactory {
    public static String[] produceQA() {
        return new String[]{AnswerFactory.produceAnswer(), QuestionFactory.produceQuestion()};
    }
}
