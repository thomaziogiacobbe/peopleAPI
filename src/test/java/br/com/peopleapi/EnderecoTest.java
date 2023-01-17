package br.com.peopleapi;

import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.model.repository.EnderecoRepository;
import br.com.peopleapi.model.repository.PessoaRepository;
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
    void shouldNotAllowNullLCep() {

        Endereco endereco = new Endereco(1L, null, false, "Rua Um", 312, null, "Pelotas");

        assertThatThrownBy(() -> enderecoRepository.save(endereco))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void givenCidade_shouldReturnListOfPessoaFromCidade() {

        Endereco endereco1 = new Endereco();
        endereco1.setCidade("Pelotas");
        endereco1.setCep("96010470");

        Endereco endereco2 = new Endereco();
        endereco2.setCidade("Pelotas");
        endereco2.setCep("96010470");

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Thomazio Giacobbe");
        pessoa.addEndereco(endereco1);
        pessoa.addEndereco(endereco2);

        pessoaRepository.save(pessoa);

        assertThat(enderecoRepository.findAllByPessoa_NomeContainsIgnoreCase("Thomazio Giacobbe"))
                .contains(endereco1, endereco2);
    }
}
