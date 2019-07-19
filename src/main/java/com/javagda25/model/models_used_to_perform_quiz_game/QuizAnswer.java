package com.javagda25.model.models_used_to_perform_quiz_game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAnswer {
    private String content;
    private boolean isCorrect;
}
