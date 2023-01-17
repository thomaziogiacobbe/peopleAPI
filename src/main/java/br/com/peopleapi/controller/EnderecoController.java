package br.com.peopleapi.controller;

import br.com.peopleapi.controller.dto.EnderecoDTO;
import br.com.peopleapi.controller.mapper.EnderecoMapper;
import br.com.peopleapi.exception.EnderecoNotFoundException;
import br.com.peopleapi.exception.PessoaNotFoundException;
import br.com.peopleapi.model.Endereco;
import br.com.peopleapi.service.EnderecoServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoServiceImpl enderecoService;

    public EnderecoController(EnderecoServiceImpl enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/newEndereco/")
    public ResponseEntity<EnderecoDTO> createNewAdrress(@RequestBody EnderecoDTO endereco) {
        try {
            var enderecoDTO = EnderecoMapper.toDTO(
                    enderecoService.createEndereco(EnderecoMapper.toEntity(endereco)));
            return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (PessoaNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{pessoaId}/")
    public ResponseEntity<List<EnderecoDTO>> findAllEnderecoByPessoaId(@PathVariable("pessoaId") Long id) {
        var enderecoDTOs = enderecoService.findAllByPessoaId(id).stream().map(EnderecoMapper::toDTO).toList();
        return new ResponseEntity<>(enderecoDTOs, enderecoDTOs.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/getByNome/{pessoaNome}/")
    public ResponseEntity<List<EnderecoDTO>> findAllEnderecoByPessoaNome(@PathVariable("pessoaNome") String nome) {
        var enderecoDTOs = enderecoService.findAllByPessoaNome(nome).stream().map(EnderecoMapper::toDTO).toList();
        return new ResponseEntity<>(enderecoDTOs, enderecoDTOs.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @PatchMapping("/setPrincipal/{pessoaId}/{enderecoId}/")
    public ResponseEntity<Endereco> setEnderecoPrincipal(@PathVariable("pessoaId") Long pessoaId,
                                                         @PathVariable("enderecoId") Long enderecoId) {
        try {
            return new ResponseEntity<>(enderecoService.setPrincipal(pessoaId, enderecoId), HttpStatus.OK);
        } catch (PessoaNotFoundException | EnderecoNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
