package br.com.peopleapi.controller.mapper;

import br.com.peopleapi.controller.dto.EnderecoDTO;
import br.com.peopleapi.controller.dto.PessoaDTO;
import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PessoaMapper {

    private PessoaMapper() {}

    public static Pessoa toEntity(PessoaDTO pessoaDTO) {
        return Pessoa
                .builder()
                .id(pessoaDTO.id())
                .dataNascimento(LocalDate.parse(pessoaDTO.dataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nome(pessoaDTO.nome())
                .enderecos( pessoaDTO.enderecos() != null ?
                            pessoaDTO.enderecos().stream().map(PessoaMapper::toEnderecoEntity).toList()
                        : null)
                .build();
    }

    public static PessoaDTO toDTO(Pessoa pessoa) {
        return PessoaDTO
                .builder()
                .id(pessoa.getId())
                .dataNascimento(pessoa.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nome(pessoa.getNome())
                .enderecos(pessoa.getEnderecos() != null ?
                        pessoa.getEnderecos().stream().map(PessoaMapper::toEnderecoDTO).toList()
                        : null)
                .build();
    }

    private static Endereco toEnderecoEntity(EnderecoDTO endereco) {
        return Endereco
                .builder()
                .id(endereco.id())
                .cep(endereco.cep())
                .cidade(endereco.cidade())
                .logradouro(endereco.logradouro())
                .isPrincipal(endereco.isPrincipal())
                .pessoa(endereco.pessoa() != null ?
                        Pessoa.builder().id(endereco.pessoa().id()).build()
                        : null)
                .build();
    }

    private static EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .logradouro(endereco.getLogradouro())
                .isPrincipal(endereco.getIsPrincipal())
                .pessoa(endereco.getPessoa() != null ?
                        PessoaDTO.builder().id(endereco.getPessoa().getId()).build()
                        : null)
                .build();
    }
}
