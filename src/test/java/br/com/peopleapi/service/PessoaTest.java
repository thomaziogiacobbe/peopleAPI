package br.com.peopleapi.service;

import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.model.repository.PessoaRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PessoaTest {

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
    void shouldNotAllowNullName() {

        Pessoa p = new Pessoa(1L, null, dateOfBirth, null);

        assertThatThrownBy(() -> pessoaRepository.save(p))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void savePessoa_Then_findAllPessoasWithEqualNameIgnoringCase() {
        var pessoa = new Pessoa(null, "Thomazio", dateOfBirth, null);

        pessoaRepository.save(p);

        assertThat(pessoaRepository.findAllByNomeContainsIgnoreCase("tHoMaZiO")).contains(p);
    }
}
