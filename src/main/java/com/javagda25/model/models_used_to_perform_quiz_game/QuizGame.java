package com.javagda25.model.models_used_to_perform_quiz_game;

import com.javagda25.model.models_from_api.TriviaResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa odpowiedzialna za przechowywanie stanu gry (numer pytania, jakie są pytania, jakie są odpowiedzi itd.
 */
@Data
public class QuizGame {
    private List<QuizQuestion> quizQuestions;
    private int currentQuestionNumber = 0;

    public QuizGame(TriviaResponse response) {
        quizQuestions = response.getResults()
                .stream()
                .map(triviaQuestion -> new QuizQuestion(triviaQuestion))
                .collect(Collectors.toList());
    }

    public boolean quizEnded() {
        return currentQuestionNumber >= quizQuestions.size();
    }

    public QuizQuestion getCurrentQuestion() {
        return quizQuestions.get(currentQuestionNumber);
    }

    public void submitAnswer(String answer) {
        // użytkownik podaje: "A" "B" "c" "d" (dowolna opcja pierwszych czterech liter alfabetu)
        // zamieniamy litery na małe, wyciągamy pierwszą literę i zamieniamy ją na wartość ASCII
        // następnie od tej wartości odejmujemy 'a' (97)
        //
        // 'a' =97
        // 'b' =98
        // 'c' =99
        // 'd' =100
        //  c
        //  99 - 97 = 2 (odpowiedź na pozycji 3 (indeks pozycji to 2)) (z listy answers w klasie QuizQuestion)
        int answerNumber = answer.toLowerCase().charAt(0) - 'a';
        if (quizQuestions.get(currentQuestionNumber).getAnswers().size() > answerNumber) {
            // ok (wybraliśmy istniejącą opcję)
            QuizQuestion pytanieNaKtoreOdpowiedziUdzielamy = quizQuestions.get(currentQuestionNumber);
            QuizAnswer odpowiedzUzytkownika = pytanieNaKtoreOdpowiedziUdzielamy.getAnswers().get(answerNumber);

            pytanieNaKtoreOdpowiedziUdzielamy.setSelectedAnswer(odpowiedzUzytkownika);
            if (odpowiedzUzytkownika.isCorrect()) {
                System.out.println("Good");
            } else {
                System.out.println("Bad, proper answer is:");
                pytanieNaKtoreOdpowiedziUdzielamy.getAnswers().stream().filter(quizAnswer -> quizAnswer.isCorrect())
                        .findFirst().ifPresent(System.out::println);
            }
            currentQuestionNumber++;
        } else {
            System.err.println("Nie ma takiej odpowiedzi.");
        }
    }

    public void printScore() {

    }
}

