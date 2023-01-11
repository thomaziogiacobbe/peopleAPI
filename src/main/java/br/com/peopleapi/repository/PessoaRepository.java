package br.com.peopleapi.repository;

import br.com.peopleapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findById(Long id);

    List<Pessoa> findAllByNomeContainsIgnoreCase(String nome);

    List<Pessoa> findAll();
}