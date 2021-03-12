package com.lowe.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Quiz {
	
	private String category;
	private List<Results> results;
}
