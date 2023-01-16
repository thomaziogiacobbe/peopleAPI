package br.com.peopleapi;

import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.repository.PessoaRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

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
    void persistingAPessoaWithAGivenName_findAllByNameShouldContainThatPessoaIgnoringCase() {

        Calendar cal = Calendar.getInstance();
        cal.set(1998, Calendar.OCTOBER, 3);

        Pessoa p = new Pessoa(1L, "Thomazio Giacobbe", dateOfBirth, null);

        pessoaRepository.save(p);

        assertThat(pessoaRepository.findAllByNomeContainsIgnoreCase("tHoMaZiO")).contains(p);
    }
}
