package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {

	/* Método Query - Criação de uma consulta personalizada 
	 * findAllByDescricaoContainingIgnoreCase - Consulta através da descrição 
	*/
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}
