package br.com.peopleapi.service;

import br.com.peopleapi.model.Endereco;

import java.util.List;

public interface EnderecoService {

    Endereco createEndereco(Endereco endereco);

    List<Endereco> findAllByPessoaId(Long idPessoa);

    List<Endereco> findAllByPessoaNome(String nome);

    Endereco setPrincipal(Long pessoaId, Long enderecoId);
}
