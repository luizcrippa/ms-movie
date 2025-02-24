package br.com.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class MovieControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("rawtypes")
	@Test
    public void testPesquisarFilmes() {
        ResponseEntity<Map> response = restTemplate.getForEntity("/movie/pesquisar-filmes", Map.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("min"));
        assertTrue(response.getBody().containsKey("max"));
    }
    
    @Test
    public void testPesquisarFilmesVencedoresComArquivoOriginal() throws Exception {
    	
    	String expectedJson = "{"
    	        + "\"min\": ["
    	        + "  {"
    	        + "    \"producer\": \"Joel Silver\","
    	        + "    \"followingWin\": 1991,"
    	        + "    \"interval\": 1,"
    	        + "    \"previousWin\": 1990"
    	        + "  }"
    	        + "],"
    	        + "\"max\": ["
    	        + "  {"
    	        + "    \"producer\": \"Matthew Vaughn\","
    	        + "    \"followingWin\": 2015,"
    	        + "    \"interval\": 13,"
    	        + "    \"previousWin\": 2002"
    	        + "  }"
    	        + "]"
    	        + "}";
        

        mockMvc.perform(get("/movie/pesquisar-filmes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }
}

