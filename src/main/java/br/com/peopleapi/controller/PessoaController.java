package br.com.peopleapi.controller;

import br.com.peopleapi.model.Pessoa;
import br.com.peopleapi.service.PessoaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/new")
    public ResponseEntity<Pessoa> newPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa p = pessoaService.createPessoa(pessoa);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Pessoa> editPessoa(@PathVariable("id") Long id,
                                             @RequestBody Pessoa pessoa) {
        return pessoaService.editPessoa(id, pessoa)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/get/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa getPessoa(@PathVariable("id") Long id) {
        return pessoaService.findPessoa(id);
    }

    @GetMapping("/getAll/")
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> getAllPessoas() {
        return pessoaService.getAllPessoas();
    }
}
