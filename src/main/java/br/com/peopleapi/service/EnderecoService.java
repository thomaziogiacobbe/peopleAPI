package br.com.peopleapi.service;

import br.com.peopleapi.exception.EnderecoNotFoundException;
import br.com.peopleapi.exception.PessoaNotFoundException;
import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.model.repository.EnderecoRepository;
import br.com.peopleapi.model.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Endereco createEndereco(Long pessoaId, Endereco endereco) {
        return pessoaRepository.findById(pessoaId)
                .map(pessoa -> {
                    pessoa.addEndereco(endereco);
                    pessoaRepository.save(pessoa);
                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(PessoaNotFoundException::new);
    }

    public List<Endereco> findAllByPessoaId(Long idPessoa) {
        return enderecoRepository.findAllByPessoa_Id(idPessoa);
    }

    public List<Endereco> findAllByPessoaNome(String nome) {
        return enderecoRepository.findAllByPessoa_NomeContainsIgnoreCase(nome);
    }

    public Endereco setPrincipal(Long pessoaId, Long enderecoId) {
        var pessoa = pessoaRepository.findById(pessoaId);

        return findAndSwitchOldEndereco(enderecoId, pessoa).orElseThrow(PessoaNotFoundException::new);
    }

    private Optional<Endereco> findAndSwitchOldEndereco(Long enderecoId, Optional<Pessoa> pessoa){
        return pessoa.map(p -> {
            var endereco = enderecoRepository.findById(enderecoId);

            return switchEnderecoPrincipal(endereco.orElseThrow(EnderecoNotFoundException::new), p);
        });
    }

    private Endereco switchEnderecoPrincipal(Endereco enderecoNew, Pessoa pessoa) {
        var enderecoPrincipal = pessoa.getEnderecos().stream()
                .filter(Endereco::getIsPrincipal)
                .findFirst();

        if (enderecoPrincipal.isPresent()) {
            var endereco = enderecoPrincipal.get();

            endereco.setIsPrincipal(false);
            enderecoRepository.save(endereco);
        }
        enderecoNew.setIsPrincipal(true);
        return enderecoRepository.save(enderecoNew);
    }
}
