package br.com.peopleapi;

import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PessoaTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void shouldNotAllowNullName() {

        Calendar cal = Calendar.getInstance();
        cal.set(1998, Calendar.OCTOBER, 3);

        Pessoa p = new Pessoa(1L, null, cal.getTime(), null);

        assertThatThrownBy(() -> pessoaRepository.save(p))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void persistingAPessoaWithAGivenName_findAllByNameShouldContainThatPessoaIgnoringCase() {

        Calendar cal = Calendar.getInstance();
        cal.set(1998, Calendar.OCTOBER, 3);

        Pessoa p = new Pessoa(1L, "Thomazio Giacobbe", cal.getTime(), null);

        pessoaRepository.save(p);

        assertThat(pessoaRepository.findAllByNomeContainsIgnoreCase("tHoMaZiO")).contains(p);
    }
}
