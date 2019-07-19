package com.javagda25;

import com.javagda25.model.models_from_api.TriviaResponse;
import com.javagda25.model.models_use_to_ask_user.QuizParameters;
import com.javagda25.model.models_used_to_perform_quiz_game.QuizGame;
import com.javagda25.model.models_used_to_perform_quiz_game.QuizQuestion;

public class Main {

    public static void main(String[] args) {
        // models_from_api trivia
        APITriviaURLBuilder builder = new APITriviaURLBuilder();
        QuizParameters quizParameters = new QuizParameters();
        ScannerContentLoader scannerContentLoader = new ScannerContentLoader();

        scannerContentLoader.loadAmount(quizParameters);
        scannerContentLoader.loadCategory(quizParameters);
        scannerContentLoader.loadDifficulty(quizParameters);
        scannerContentLoader.loadType(quizParameters);

        builder.loadParameters(quizParameters);

        String requestURL = builder.compileURL();

        TriviaAPI api = new TriviaAPI();

        TriviaResponse response = api.loadURLbyInputStream(requestURL);
        if (response.getResponse_code() == 0) {
            QuizGame game = new QuizGame(response);

            while (!game.quizEnded()) {
                QuizQuestion quizQuestion = game.getCurrentQuestion();

                System.out.println(quizQuestion); // todo : zadaj pytanie

                String answer = scannerContentLoader.loadAnswer();
                game.submitAnswer(answer);
            }

            long score = game.getQuizQuestions()
                    .stream()
                    .filter(quizQuestion -> quizQuestion.getSelectedAnswer().isCorrect())
                    .count();

            System.out.println("Wynik dobrych odpowiedzi: " + score);
        }

    }
}
