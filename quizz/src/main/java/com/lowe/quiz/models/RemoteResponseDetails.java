package com.lowe.quiz.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RemoteResponseDetails {

	private String category;
	private String type;
	private String difficulty;
	private String question;
	private String correct_answer;
	private List<String> incorrect_answers;
		


	
	
	
}
