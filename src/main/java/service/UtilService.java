package service;

import model.Question;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Random;

@Component
public class UtilService {
    private Random random;


    public void setRandom(Random random) {
        this.random = random;
    }

    public int getRandomInt(int bound) {
        return random.nextInt(bound);
    }

    public Question getRandomQuestion(Collection<Question> questions) {
        int questionNum = getRandomInt(questions.size());
        int questionCur = 0;
        for (Question question : questions) {
            if (questionCur == questionNum) {
                return question;
            }
            questionCur++;
        }
        return null;
    }
}
