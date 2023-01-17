package br.com.peopleapi.service;

import br.com.peopleapi.exception.EnderecoNotFoundException;
import br.com.peopleapi.exception.PessoaNotFoundException;
import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.model.repository.EnderecoRepository;
import br.com.peopleapi.model.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final PessoaRepository pessoaRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Endereco createEndereco(Endereco endereco) {
        return pessoaRepository.findById(endereco.getPessoa().getId())
                .map(pessoa -> {
                    pessoa.addEndereco(endereco);
                    pessoaRepository.save(pessoa);
                    return enderecoRepository.findByCepAndLogradouroAndNumero(
                            endereco.getCep(), endereco.getLogradouro(), endereco.getNumero());
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
        var pessoa = pessoaRepository.findById(pessoaId).orElse(null);

        if (pessoa != null) {
            var endereco = enderecoRepository.findById(enderecoId).orElse(null);

            if (endereco != null) {
                var enderecoPrincipal = pessoa.getEnderecos().stream()
                        .filter(Endereco::getIsPrincipal)
                        .findFirst().orElse(null);

                if (enderecoPrincipal != null) {
                    enderecoRepository.save(configureIsPrincipal(enderecoPrincipal, false));
                }

                return enderecoRepository.save(configureIsPrincipal(endereco, true));
            } else {
                throw new EnderecoNotFoundException();
            }
        } else {
          throw new PessoaNotFoundException();
        }
    }

    private Endereco configureIsPrincipal(Endereco endereco, Boolean isPrincipal) {
        endereco.setIsPrincipal(isPrincipal);
        endereco.setPessoa(Pessoa
                        .builder()
                        .id(endereco.getPessoa() != null ?
                                endereco.getPessoa().getId()
                                : null)
                        .build());
        return endereco;
    }
}
