package br.com.plusoft.jadv.challenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import br.com.plusoft.jadv.challenge.entidades.Usuario;
import br.com.plusoft.jadv.challenge.repositorios.AnuncioRepository;
import br.com.plusoft.jadv.challenge.repositorios.CampanhaRepository;
import br.com.plusoft.jadv.challenge.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
	
	@Bean
	@Order(value = 2)
	@Transactional
	public CommandLineRunner teste(
			UsuarioRepository usuarioRepository,
			AnuncioRepository anuncioRepository,
			CampanhaRepository campanhaRepository
			) {
		
		return (args) -> {
			Usuario usuario = new Usuario();
			usuario.setNome("Rennan");
			usuario.setEmail("rennan@gmail.com");
			usuario.setSenha("123456");
			usuario.setChaveGoogleAds("def123");
			usuarioRepository.save(usuario);
			
			
			System.out.println(usuario);

			};
	}
	
	
	//*@Bean
	//@Order(value = 1)
	//@Transactional
	//public CommandLineRunner testeBuscaCliente(TesteService testeService) {
		
		//return (args) -> {
			//testeService.testarBuscaCliente();
		//};
	//}
	
}
