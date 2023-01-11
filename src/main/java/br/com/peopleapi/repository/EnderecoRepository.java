package br.com.peopleapi.repository;

import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findById(Long id);

    List<Endereco> findAllByPessoa_Nome(String nome);
}