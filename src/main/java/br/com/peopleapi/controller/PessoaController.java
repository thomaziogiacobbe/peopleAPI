package br.com.peopleapi.controller;

import br.com.peopleapi.controller.dto.PessoaDTO;
import br.com.peopleapi.controller.mapper.PessoaMapper;
import br.com.peopleapi.service.PessoaServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaServiceImpl pessoaService;

    public PessoaController(PessoaServiceImpl pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/new/")
    public ResponseEntity<PessoaDTO> newPessoa(@RequestBody PessoaDTO pessoa) {
        try {
            var pessoaDTO = PessoaMapper.toDTO(pessoaService.createPessoa(PessoaMapper.toEntity(pessoa)));
            return new ResponseEntity<>(pessoaDTO, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (DateTimeParseException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/")
    public ResponseEntity<PessoaDTO> editPessoa(@RequestBody PessoaDTO pessoa) {
        return pessoaService.editPessoa(PessoaMapper.toEntity(pessoa))
                .map(p -> new ResponseEntity<>(PessoaMapper.toDTO(p), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/get/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public PessoaDTO getPessoa(@PathVariable("id") Long id) {
        return PessoaMapper.toDTO(pessoaService.findPessoa(id));
    }

    @GetMapping("/getAll/")
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaDTO> getAllPessoas() {
        return pessoaService.getAllPessoas().stream()
                .map(PessoaMapper::toDTO)
                .toList();
    }
}
