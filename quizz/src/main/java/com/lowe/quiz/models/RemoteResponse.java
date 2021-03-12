package com.lowe.quiz.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RemoteResponse {
	
	private Long response_code;
	private List<RemoteResponseDetails> results;

}
