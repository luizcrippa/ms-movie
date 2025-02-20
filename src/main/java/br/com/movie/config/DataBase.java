package br.com.movie.config;

import java.io.FileReader;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.opencsv.CSVReader;

import br.com.movie.entity.Movie;
import br.com.movie.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataBase {

	private final MovieRepository movieRepository;

    public DataBase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() {    	
        if (movieRepository.count() == 0) {
            try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/movielist.csv"), ';')) {
                String[] nextLine;
                reader.readNext();

                while ((nextLine = reader.readNext()) != null) {
                    if (nextLine.length < 5) {
                        System.err.println("Linha inválida: " + Arrays.toString(nextLine));
                        continue;
                    }
                    
                    String producer = nextLine[3].trim().replaceAll("and", ",");
                    String[] producers = producer.trim().split(",");                   
                   
                    for (String p : producers) {
	                    Movie movie = new Movie();
	                    movie.setYear(Integer.parseInt(nextLine[0].trim()));
	                    movie.setTitle(nextLine[1].trim());
	                    movie.setStudios(nextLine[2].trim());
	                    movie.setProducers(p.trim());
	                    movie.setWinner("yes".equalsIgnoreCase(nextLine[4].trim()));
	                    movieRepository.save(movie);
                    }
                }
            } catch (Exception e) {
            	log.error("Erro ao inserrir no banco de dados: {}", e);                
            }
        }
    }
}
