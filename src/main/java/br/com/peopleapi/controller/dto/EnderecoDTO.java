package br.com.peopleapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record EnderecoDTO(Long id, PessoaDTO pessoa, Boolean isPrincipal, String logradouro, Integer numero, String cep, String cidade) {
}
