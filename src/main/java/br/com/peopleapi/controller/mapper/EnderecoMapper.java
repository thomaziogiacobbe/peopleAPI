package br.com.peopleapi.controller.mapper;

import br.com.peopleapi.controller.dto.EnderecoDTO;
import br.com.peopleapi.controller.dto.PessoaDTO;
import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.model.Pessoa;

public class EnderecoMapper {
    private EnderecoMapper(){}

    public static Endereco toEntity(EnderecoDTO enderecoDTO) {
        return enderecoDTO == null ? null :
                Endereco
                .builder()
                .id(enderecoDTO.id())
                .cep(enderecoDTO.cep())
                .cidade(enderecoDTO.cidade())
                .numero(enderecoDTO.numero())
                .logradouro(enderecoDTO.logradouro())
                .isPrincipal(enderecoDTO.isPrincipal())
                .pessoa(enderecoDTO.pessoa() != null ?
                        Pessoa.builder().id(enderecoDTO.pessoa().id()).build()
                        : null)
                .build();
    }

    public static EnderecoDTO toDTO(Endereco endereco) {
        return endereco == null ? null :
                EnderecoDTO
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
