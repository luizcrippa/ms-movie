package br.com.movie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.movie.entity.Movie;
import br.com.movie.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public Map<String, List<Map<String, Object>>> pesquisarFilmesVencedores() {
		List<Movie> movies = movieRepository.pesquisarFilmesComMaisDeUmPremio();

		Map<String, List<Integer>> producerWins = movies.stream()
				.flatMap(movie -> Arrays.stream(movie.getProducers().split(", "))
						.map(producer -> Map.entry(producer, movie.getYear())))
				.collect(Collectors.groupingBy(Map.Entry::getKey,
						Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

		List<Map<String, Object>> minIntervals = new ArrayList<>();
		List<Map<String, Object>> maxIntervals = new ArrayList<>();

		int globalMinInterval = Integer.MAX_VALUE;
		int globalMaxInterval = Integer.MIN_VALUE;

		for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
			String producer = entry.getKey();
			List<Integer> years = entry.getValue();

			if (years.size() > 1) {
				Collections.sort(years);

				for (int i = 1; i < years.size(); i++) {
					int interval = years.get(i) - years.get(i - 1);

					if (interval == globalMinInterval) {
						minIntervals.add(Map.of("producer", producer, "interval", interval, "previousWin",
								years.get(i - 1), "followingWin", years.get(i)));
					} else if (interval < globalMinInterval) {
						globalMinInterval = interval;
						minIntervals.clear();
						minIntervals.add(Map.of("producer", producer, "interval", interval, "previousWin",
								years.get(i - 1), "followingWin", years.get(i)));
					}

					if (interval == globalMaxInterval) {
						maxIntervals.add(Map.of("producer", producer, "interval", interval, "previousWin",
								years.get(i - 1), "followingWin", years.get(i)));
					} else if (interval > globalMaxInterval) {
						globalMaxInterval = interval;
						maxIntervals.clear();
						maxIntervals.add(Map.of("producer", producer, "interval", interval, "previousWin",
								years.get(i - 1), "followingWin", years.get(i)));
					}
				}
			}
		}
		LinkedHashMap<String, List<Map<String, Object>>> retorno = new LinkedHashMap<>();
		retorno.put("min", minIntervals);
		retorno.put("max", maxIntervals);
		
		return retorno;
	}
}
