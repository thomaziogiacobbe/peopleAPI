package br.com.peopleapi.controller.mapper;

import br.com.peopleapi.controller.dto.EnderecoDTO;
import br.com.peopleapi.controller.dto.PessoaDTO;
import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;

public class EnderecoMapper {
    private EnderecoMapper(){}

    public static Endereco toEntity(EnderecoDTO endereco) {
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

    public static EnderecoDTO toDTO(Endereco endereco) {
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
