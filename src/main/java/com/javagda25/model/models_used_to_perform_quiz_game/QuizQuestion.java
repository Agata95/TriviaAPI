package com.javagda25.model.models_used_to_perform_quiz_game;

import com.javagda25.model.models_from_api.TriviaQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Pojedyncze pytanie w grze.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion {
    private String question;
    private List<QuizAnswer> answers;
    private QuizAnswer selectedAnswer;

    public QuizQuestion(TriviaQuestion triviaQuestion) {
        this.question = triviaQuestion.getQuestion();
        this.selectedAnswer = null;
        this.answers = new ArrayList<>();

        this.answers.add(new QuizAnswer(triviaQuestion.getCorrect_answer(), true));
        this.answers.addAll(triviaQuestion
                .getIncorrect_answers()
                .stream()
                // zamieniam stringa z listy incorrct_answers na obiekt QuizAnswer
                .map(incorrectAnswer -> new QuizAnswer(incorrectAnswer, false))
                .collect(Collectors.toList()));

        // "tasowanie" elementów (zostaną rozlosowane w innej kolejności niż kolejność dodania)w
        Collections.shuffle(this.answers);
    }

    @Override
    public String toString() {
        final char[] sign = new char[]{'a'};
        StringBuilder answersList = new StringBuilder();

        answers.forEach(quizAnswer -> answersList.append((sign[0]++) + ") " + quizAnswer.getContent() + "\n"));

        return "Question: " + question + " \n\n\n" + answersList.toString();
    }
}
