package com.lowe.quiz.resource;

import com.lowe.quiz.remoteapihandler.RemoteServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowe.quiz.models.Response;

@RestController
public class QuizController {

	@Autowired
	RemoteServiceHandler handler;
	
	@GetMapping("/coding/quiz")
	public Response getQuiz(@RequestParam(value = "questions", defaultValue = "5") int numQuestions,
							@RequestParam(value = "categories", defaultValue = "11,12") String separatedCategories) {
		return handler.getQuiz(numQuestions, separatedCategories);
	}
	
}
