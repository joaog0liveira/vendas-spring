package br.com.joaogabriel.vendas.rest.controller;

import br.com.joaogabriel.vendas.exception.PedidoNaoEncontradoException;
import br.com.joaogabriel.vendas.exception.RegraNegocioException;
import br.com.joaogabriel.vendas.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleMetodNotValidException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErros(erros);
    }
}
