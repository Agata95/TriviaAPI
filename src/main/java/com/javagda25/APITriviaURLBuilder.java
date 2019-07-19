package com.javagda25;

import com.javagda25.model.models_use_to_ask_user.QuizCategory;
import com.javagda25.model.models_use_to_ask_user.QuizDifficulty;
import com.javagda25.model.models_use_to_ask_user.QuizParameters;
import com.javagda25.model.models_use_to_ask_user.QuizType;

public class APITriviaURLBuilder {
    private static final String BASE_URL = "https://opentdb.com/api.php?amount={amount}";

    private final StringBuilder builderAdresuURL;

    /**
     * Tworzę builderAdresuURL który jako początkowy URL ma link z góry.
     */
    public APITriviaURLBuilder() {
        builderAdresuURL = new StringBuilder(BASE_URL);
    }

    private void appendNumberOfQuestions(int count) {
        if (builderAdresuURL.toString().contains("{amount}")) {
            int pozycjaAmount = builderAdresuURL.lastIndexOf("{amount}");
            builderAdresuURL.replace(pozycjaAmount, pozycjaAmount + 8, String.valueOf(count));
        }
    }

    private void appendCategory(QuizCategory quizCategory) {
        // quizCategory.getId() == -1 to wartość ANY
        if (!builderAdresuURL.toString().contains("&cateogry=") && quizCategory.getId() != -1) {
            builderAdresuURL.append("&category=");
            builderAdresuURL.append(quizCategory.getId());
        } else if (builderAdresuURL.toString().contains("&cateogry=")) {
            System.err.println("Kategoria została już dopisana.");
        }
    }

    private void appendDifficulty(QuizDifficulty quizDifficulty) {
        // quizDifficulty.getId() == -1 to wartość ANY
        if (!builderAdresuURL.toString().contains("&difficulty=") && quizDifficulty != QuizDifficulty.ANY) {
            builderAdresuURL.append("&difficulty=");
            builderAdresuURL.append(quizDifficulty.toString().toLowerCase());
        } else if (builderAdresuURL.toString().contains("&difficulty=")) {
            System.err.println("Poziom trudności został już ustawiony.");
        }
    }

    private void appendType(QuizType quizType) {
        // quizType.getId() == -1 to wartość ANY
        if (!builderAdresuURL.toString().contains("&type=") && quizType != QuizType.ANY) {
            builderAdresuURL.append("&type=");
            builderAdresuURL.append(quizType.toString().toLowerCase());
        } else if (builderAdresuURL.toString().contains("&type=")) {
            System.err.println("Rodzaj pytań został już określony.");
        }
    }

    public String compileURL() {
        return builderAdresuURL.toString();
    }

    public void loadParameters(QuizParameters parameters) {
        appendNumberOfQuestions(parameters.getAmountOfQuestions());
        appendCategory(parameters.getCategory());
        appendDifficulty(parameters.getDifficulty());
        appendType(parameters.getQuizType());
    }

    @Override
    public String toString() {
        return "APITriviaURLBuilder{" +
                "builderAdresuURL=" + builderAdresuURL +
                '}';
    }
}