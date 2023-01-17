package br.com.peopleapi.service;

import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.model.repository.EnderecoRepository;
import br.com.peopleapi.model.repository.PessoaRepository;
import br.com.peopleapi.utils.EnderecoFactory;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class EnderecoTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    @Order(1)
    void shouldNotAllowNullLCep() {
        Endereco endereco = new Endereco(null, null, false, "Rua Um", 312, null, "Pelotas");

        assertThatThrownBy(() -> enderecoRepository.save(endereco))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @Order(2)
    void givenCidade_shouldReturnListOfPessoaFromCidade() {

        var enderecos = EnderecoFactory.createEnderecos();

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Thomazio Giacobbe");

        pessoaRepository.save(pessoa);

        assertThat(enderecoRepository.findAllByPessoa_NomeContainsIgnoreCase("Thomazio Giacobbe"))
                .contains(enderecos.get(0), enderecos.get(1));
    }

    @Test
    @Order(3)
    void givenTwoEndereco_isUniqueEnderecoShouldReturnFalse() {
        var enderecos = EnderecoFactory.createEnderecos();

        assertThat(enderecos.get(0)).isNotEqualTo(enderecos.get(1));
    }
}
