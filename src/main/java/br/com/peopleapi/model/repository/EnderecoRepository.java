package br.com.peopleapi.model.repository;

import br.com.peopleapi.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findById(Long id);

    List<Endereco> findAllByPessoa_NomeContainsIgnoreCase(String nome);

    List<Endereco> findAllByPessoa_Id(Long id);
}