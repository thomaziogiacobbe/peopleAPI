package br.com.peopleapi.service;

import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> editPessoa(Long id, Pessoa pessoa) {
        return pessoaRepository.findById(id).map(p -> {
            p.setNome(pessoa.getNome());
            p.setDataNascimento(pessoa.getDataNascimento());
            return pessoaRepository.save(p);
        });
    }

    public Pessoa findPessoa(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }
}
