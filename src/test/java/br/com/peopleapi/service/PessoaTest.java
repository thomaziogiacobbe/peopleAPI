package br.com.peopleapi.service;

import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.model.repository.PessoaRepository;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PessoaTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    private static LocalDate dateOfBirth;

    @Before
    public void setDateOfBirth() {
        dateOfBirth = dateOfBirth
                .withYear(1998)
                .withMonth(9)
                .withDayOfMonth(3);
    }

    @Test
    @Order(1)
    void shouldNotAllowNullName() {
        var pessoa = new Pessoa(null, null, dateOfBirth, null);

        assertThatThrownBy(() -> pessoaRepository.save(pessoa))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @Order(2)
    void savePessoa_Then_findAllPessoasWithEqualNameIgnoringCase() {
        var pessoa = new Pessoa(null, "Thomazio", dateOfBirth, null);

        pessoaRepository.save(pessoa);

        assertThat(pessoaRepository.findAllByNomeContainsIgnoreCase("tHoMaZiO")).contains(pessoa);
    }

    @Test
    @Order(3)
    void should_GetAllPessoas() {
        var pessoas = pessoaRepository.findAll();

        assertThat(pessoas.size()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void should_APessoas() {
        var pessoa = pessoaRepository.findById(1L).orElse(null);

        assertThat(pessoa).isNotEqualTo(null);
        assertThat(pessoa.getNome()).isEqualTo("Thomazio");
    }
}
