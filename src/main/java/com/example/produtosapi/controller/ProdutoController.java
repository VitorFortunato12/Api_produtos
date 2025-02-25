package com.example.produtosapi.controller;

import com.example.produtosapi.model.Produto;
import com.example.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController//Diz que essa classe vai receber requisiçoes Rest
@RequestMapping("produtos")// Diz qual e a url base do controller
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping//Refere-se que esse metodo e um post entao ou seja salvar
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto recebido "+ produto);
        produto.setId(UUID.randomUUID().toString());//Gera um id na
        produtoRepository.save(produto);//Inseri o produto no banco de dados
        return produto;
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id){
        //Optional<Produto> produto = produtoRepository.findById(id);
        //if (produto.isPresent()){ //Verifica se a algum produto com esse id
           // return produto.get();//Se sim retorna o produto
        //}
        //return null;
        return produtoRepository.findById(id).orElse(null); //faz a mesma coisa do que o de cima
    }

    @DeleteMapping("/{id}")
    public void deletarPorId( @PathVariable("id") String id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable("id") String id, @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
    }
}
