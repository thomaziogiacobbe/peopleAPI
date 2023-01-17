package br.com.peopleapi.service;

import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.model.repository.EnderecoRepository;
import br.com.peopleapi.model.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    private final EnderecoRepository enderecoRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> editPessoa(Pessoa pessoa) {
        var enderecos = enderecoRepository.findAllByPessoa(pessoa);
        pessoa.setEnderecos(enderecos);

        return Optional.of(this.createPessoa(pessoa));
    }

    public Pessoa findPessoa(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }
}
