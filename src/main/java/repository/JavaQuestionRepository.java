package repository;

import excepcion.BadRequestException;
import
        jakarta.annotation.PostConstruct;
import model.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @PostConstruct
    private void init() {
        questions.add(new Question("question", "answer"));
        questions.add(new Question("question1", "answer1"));
        questions.add(new Question("question2", "answer2"));
        questions.add(new Question("question3", "answer3"));
        questions.add(new Question("question4", "answer4"));
        questions.add(new Question("question5", "answer5"));
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new BadRequestException("Введите вопрос");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new BadRequestException("Такого вопроса нет");
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }
}
