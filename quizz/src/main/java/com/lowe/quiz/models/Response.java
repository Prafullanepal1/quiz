package com.lowe.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class Response {
	
	List<Quiz> quiz;
}
