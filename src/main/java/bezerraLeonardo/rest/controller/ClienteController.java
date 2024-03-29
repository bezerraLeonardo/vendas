package bezerraLeonardo.rest.controller;

import bezerraLeonardo.domain.entity.Cliente;
import bezerraLeonardo.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController( Clientes clientes ) {
        this.clientes = clientes;
    }

    @GetMapping("{id}")
    public Cliente getClienteById( @PathVariable Integer id ){ //annotation @PathVariable é usada para referenciar o parametro com a variavel da URL no GetMapping
        return clientes
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save ( @RequestBody Cliente cliente ) {
        return clientes.save(cliente);
//metodo p salvar cliente
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id ){
        clientes.findById(id)
                .map( cliente -> {clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
//metodo p deletar cliente
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Cliente cliente){
        clientes.findById(id).map( clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente);
            return clienteExistente;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado"));
//metodo p atualizar e obter os dados de cliente
    }

    @GetMapping
    public List<Cliente> find( Cliente filtro ){ //esse método será p fazer a listagem dos clientes
        ExampleMatcher matcher = ExampleMatcher // examplematcher é um obj que permite ser config para encontrar o cliente atraves das propriedades
                        .matching()
                        .withIgnoreCase() //.withmatcher é usado na busca para ignorar se a escrita esta com case sensitive
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);// é a forma que vc pode definir para encontrar os valores String
        Example example = Example.of(filtro, matcher); // example usa o obj passado no arg e cria um exemplo de p filtrar
        return clientes.findAll(example);
// metodo p filtrar clientes
    }
}
