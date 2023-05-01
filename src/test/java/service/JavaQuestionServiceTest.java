package service;

import excepcion.BadRequestException;
import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.QuestionRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService out;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private UtilService utilService;


    @BeforeEach
    void setUp() {
        out = new JavaQuestionService(questionRepository, utilService);
    }

    @Test
    void add() {
        Question expected = new Question("Question", "Answer");
        Mockito.when(questionRepository.add(any())).thenReturn(expected);
        Question actual = out.add(expected);
        org.assertj.core.api.Assertions.assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only()).add(expected);
    }

    @Test
    void testAdd() {
        Question expected = new Question("Question", "Answer");
        Mockito.when(questionRepository.add(any())).thenReturn(expected);
        Question actual = out.add(expected);
        org.assertj.core.api.Assertions.assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only()).add(expected);
    }


    @Test
    void addWithNull() {
        org.assertj.core.api.Assertions.assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> {
            out.add(null, null);
        });
        Mockito.verify(questionRepository, Mockito.never()).add(any());
    }

    @Test
    void addWithBlank() {
        org.assertj.core.api.Assertions.assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> {
            out.add("", " ");
        });
        Mockito.verify(questionRepository, Mockito.never()).add(any());
    }

    @Test
    void remove() {
        Question expected = new Question("question", "answer");
        Mockito.when(questionRepository.remove(any())).thenReturn(expected);
        Question actual = out.remove(expected);
        org.assertj.core.api.Assertions.assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only()).remove(expected);
    }

    @Test
    void getAll() {
        Collection<Question> expected = List.of(new Question("question", "answer"));
        Mockito.when(questionRepository.getAll()).thenReturn(expected);
        Collection<Question> actual = out.getAll();
        org.assertj.core.api.Assertions.assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only()).getAll();
    }

    @Test
    void getRandomQuestion() {
        Question expected = new Question("question", "answer");
        Collection<Question> expectedList = List.of(expected);
        Mockito.when(questionRepository.getAll()).thenReturn(expectedList);
        Mockito.when(utilService.getRandomQuestion(expectedList)).thenReturn(expected);
        Question actual = out.getRandomQuestion();
        org.assertj.core.api.Assertions.assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only()).getAll();
        Mockito.verify(utilService, Mockito.only()).getRandomQuestion(expectedList);
    }
}