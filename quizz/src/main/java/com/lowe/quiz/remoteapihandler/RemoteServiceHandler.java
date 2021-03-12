package com.lowe.quiz.remoteapihandler;

import com.lowe.quiz.models.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class RemoteServiceHandler {

	private static ExecutorService executorService =
			new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<>());
		
	private final String REMOTE_BASE_URL = "https://opentdb.com/api.php";
	public RemoteResponse getQuestions(int numOfQuestions, String category) {
		ResponseEntity<RemoteResponse> response = new RestTemplateBuilder().build()
				 .exchange(
						 REMOTE_BASE_URL+"?amount="+numOfQuestions+"&category="+category,
						 HttpMethod.GET,
						 null,
						 RemoteResponse.class
						 );
		 return response.getBody();
	}

	private static Results getResults(RemoteResponseDetails remote){
		List<String> all_answers = new ArrayList<>();
		all_answers.add(remote.getCorrect_answer());
		all_answers.addAll(remote.getIncorrect_answers());
		return new Results(
				remote.getType(),
				remote.getDifficulty(),
				remote.getQuestion(),
				remote.getCorrect_answer(),
				all_answers);
	}

	private List<RemoteResponseDetails> getRemoteDetails( int size, List<String> categories) {

		List<RemoteResponseDetails> responseDetailsList = new ArrayList<>();

		try {
			List<Callable<RemoteResponse>> callables = categories.stream().map(c ->
					(Callable<RemoteResponse>) () -> getQuestions(size, c)).collect(Collectors.toList());

			List<Future<RemoteResponse>> futures = executorService.invokeAll(callables);
			for (Future<RemoteResponse> fr : futures) {
				RemoteResponse temp = fr.get();
				if (temp != null && temp.getResults() != null) {
					responseDetailsList.addAll(temp.getResults());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDetailsList;
	}

	public Response getQuiz(int size, String commaSeparatedCategories) {
		RemoteResponse response = new RemoteResponse();
		response.setResults(new ArrayList<>());
		List<Quiz> quizList = new ArrayList<>();
		List<RemoteResponseDetails> remoteList = getRemoteDetails(size, Arrays.asList(commaSeparatedCategories.split(",")));
		Map<String, List<Results>> map = remoteList.stream()
				.collect(Collectors.groupingBy(RemoteResponseDetails::getCategory,
						Collectors.mapping(RemoteServiceHandler::getResults, Collectors.toList())));
		map.keySet().forEach(key -> quizList.add(new Quiz(key, map.get(key))));
		return new Response(quizList);

	}
}
