package com.lowe.quiz.resource;

import com.lowe.quiz.models.Quiz;
import com.lowe.quiz.models.Response;
import com.lowe.quiz.models.Results;
import com.lowe.quiz.remoteapihandler.RemoteServiceHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

    @InjectMocks
    QuizController controller;

    @Mock
    RemoteServiceHandler handler;

    Response resp;

    @BeforeEach
    void setUp() {
        List<String> allAnswers = Arrays.asList("R.E.M.","U2", "Coldplay", "The Rolling Stones");

        Results results = new Results(
                "multiple",
                "medium",
                "Who released the 1991 album &quot;Out of Time&quot;?",
                "R.E.M.",
                allAnswers
        );

        List<Results> resultsList = Arrays.asList(results);
        Quiz q = new Quiz("11,12", resultsList);
        List<Quiz> quizList = Arrays.asList(q);
        resp = new Response(quizList);
    }

    @Test
    void getQuiz() {

        Mockito.when(
                handler.getQuiz(5, "11,12")).thenReturn(resp);

        assertEquals("multiple", controller.getQuiz(
                5, "11,12").getQuiz().get(0).getResults().get(0).getType());

    }
}