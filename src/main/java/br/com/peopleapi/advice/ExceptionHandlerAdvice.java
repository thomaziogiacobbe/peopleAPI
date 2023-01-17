package br.com.peopleapi.advice;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<Object> handleDataIntegrityException(DataIntegrityViolationException ex, WebServer request) {
//        return new ResponseEntity<Object>("Erro ao criar nova pessoa, verifique o nome",
//                                            new HttpHeaders(),
//                                            HttpStatus.CONFLICT);
//    }
}
