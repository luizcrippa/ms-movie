package br.com.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinnerTrue();
    
    @Query("SELECT m FROM Movie m WHERE m.winner = true AND m.producers IN (" +
    	       "SELECT m2.producers FROM Movie m2 WHERE m2.winner = true GROUP BY m2.producers HAVING COUNT(m2) > 1) " +
    	       "ORDER BY m.producers")
    List<Movie> pesquisarFilmesComMaisDeUmPremio();
}