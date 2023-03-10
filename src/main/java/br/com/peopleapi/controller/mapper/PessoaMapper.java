package br.com.peopleapi.controller.mapper;

import br.com.peopleapi.controller.dto.PessoaDTO;
import br.com.peopleapi.model.Pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PessoaMapper {

    private PessoaMapper() {}

    public static Pessoa toEntity(PessoaDTO pessoaDTO) {

        return pessoaDTO == null ? null :
                Pessoa
                .builder()
                .id(pessoaDTO.id())
                .dataNascimento(pessoaDTO.dataNascimento() != null ?
                        LocalDate.parse(pessoaDTO.dataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : null)
                .nome(pessoaDTO.nome())
                .enderecos(pessoaDTO.enderecos() != null ?
                            pessoaDTO.enderecos().stream().map(EnderecoMapper::toEntity).toList()
                        : null)
                .build();
    }

    public static PessoaDTO toDTO(Pessoa pessoa) {
        return pessoa == null ? null :
                PessoaDTO
                .builder()
                .id(pessoa.getId())
                .dataNascimento(pessoa.getDataNascimento() != null ?
                        pessoa.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : null)
                .nome(pessoa.getNome())
                .enderecos(pessoa.getEnderecos() != null ?
                        pessoa.getEnderecos().stream().map(EnderecoMapper::toDTO).toList()
                        : null)
                .build();
    }
}
