package br.com.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movie.service.MovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movie")
public class MovieController {
		
	@Autowired
	private MovieService movieService;

	
	@ApiOperation(value = "Pesquisa filmes", response = Object.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Sucesso", response = Map.class)
	})
	@GetMapping("/pesquisar-filmes")
	public Map<String, List<Map<String, Object>>> listaPrinciparTeste() {

		return this.movieService.pesquisarFilmesVencedores();		
	}	
}
