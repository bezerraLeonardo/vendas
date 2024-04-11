package bezerraLeonardo.rest.controller;

import bezerraLeonardo.exception.PedidoNaoEncontradoException;
import bezerraLeonardo.exception.RegraNegocioException;
import bezerraLeonardo.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mesagemErro = ex.getMessage();
        return new ApiErrors(mesagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex ){
        return new ApiErrors(ex.getMessage());
    }
}
