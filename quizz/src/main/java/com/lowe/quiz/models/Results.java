package com.lowe.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Results {

	private String type;
	private String difficulty;
	private String question;
	private String correct_answer;
	private List<String> all_answers;
}
