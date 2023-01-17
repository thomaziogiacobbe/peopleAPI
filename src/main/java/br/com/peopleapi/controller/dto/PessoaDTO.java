package br.com.peopleapi.controller.dto;

import br.com.peopleapi.model.Endereco;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PessoaDTO(Long id, String nome, String dataNascimento, List<EnderecoDTO> enderecos) {

}
