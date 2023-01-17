package br.com.peopleapi.controller.dto;

import lombok.Builder;

@Builder
public record EnderecoDTO(Long id, PessoaDTO pessoa, Boolean isPrincipal, String logradouro, Integer numero, String cep, String cidade) {
}
