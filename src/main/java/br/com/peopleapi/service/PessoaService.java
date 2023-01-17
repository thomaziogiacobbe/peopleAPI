package br.com.peopleapi.service;

import br.com.peopleapi.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    Pessoa createPessoa(Pessoa pessoa);

    Optional<Pessoa> editPessoa(Pessoa pessoa);

    Pessoa findPessoa(Long id);

    List<Pessoa> getAllPessoas();
}
