package br.com.peopleapi.controller;

import br.com.peopleapi.controller.dto.EnderecoDTO;
import br.com.peopleapi.controller.mapper.EnderecoMapper;
import br.com.peopleapi.exception.EnderecoNotFoundException;
import br.com.peopleapi.exception.PessoaNotFoundException;
import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.service.EnderecoService;
import jakarta.websocket.server.PathParam;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/newEndereco/")
    public ResponseEntity<EnderecoDTO> createNewAdrress(@RequestBody EnderecoDTO endereco) {
        try {
            var enderecoDTO = EnderecoMapper.toDTO(
                    enderecoService.createEndereco(EnderecoMapper.toEntity(endereco)));
            return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (PessoaNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{pessoaId}")
    public ResponseEntity<List<Endereco>> findAllEnderecoByPessoaId(@PathParam("pessoaId") Long id) {
        var enderecos = enderecoService.findAllByPessoaId(id);
        return new ResponseEntity<>(enderecos, enderecos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/get/{pessoaNome}")
    public ResponseEntity<List<Endereco>> findAllEnderecoByPessoaNome(@PathParam("pessoaNome") String nome) {
        var enderecos = enderecoService.findAllByPessoaNome(nome);
        return new ResponseEntity<>(enderecos, enderecos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @PatchMapping("/setPrincipal/{pessoaId}/{enderecoId}/")
    public ResponseEntity<Endereco> setEnderecoPrincipal(@PathVariable("pessoaId") Long pessoaId,
                                                         @PathVariable("enderecoId") Long enderecoId) {
        try {
            return new ResponseEntity<>(enderecoService.setPrincipal(pessoaId, enderecoId), HttpStatus.OK);
        } catch (PessoaNotFoundException | EnderecoNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
