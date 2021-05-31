package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UsuarioLogin;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service //annotation para indicar que essa classe é de service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
		
		if(repository.findByUsuario(usuario.getUsuario()).isPresent())  //If para verificar se o usuário já esta cadastrado
			return null;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //Instancia um objeto do tipo BCryptPasswordEncoder 
		
		String senhaEncoder = encoder.encode(usuario.getSenha()); //Encripta a senha 
		usuario.setSenha(senhaEncoder); // Modifica o atributo para a senha encriptada 
		
		return Optional.of(repository.save(usuario)); //Salva o objeto Usuario com a senha encriptada 
	}
	
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //Instancia um objeto do tipo BCryptPasswordEncoder 
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) { //Se a senha do client e a senha encripatada do DB são correspondentes retorna true
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha(); //Concatena atributo de usuario e senha
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth); //Concatena basic e o encodedAuth
				
				user.get().setToken(authHeader); //Insere o token no atributo
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome()); //Pega o nome do Usuario
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());
				
				return user;
			}
		}
		
		return null;
	}
}
