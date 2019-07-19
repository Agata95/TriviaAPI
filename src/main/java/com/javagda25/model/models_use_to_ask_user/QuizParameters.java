package com.javagda25.model.models_use_to_ask_user;

import lombok.Data;

@Data
public class QuizParameters {
    private QuizCategory category;
    private QuizDifficulty difficulty;
    private QuizType quizType;
    private Integer amountOfQuestions;
}
