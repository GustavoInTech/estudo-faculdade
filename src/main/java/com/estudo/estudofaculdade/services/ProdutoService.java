package com.estudo.estudofaculdade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.estudofaculdade.model.Produto;
import com.estudo.estudofaculdade.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * 
     * @return lista de produtos.
     */
    public List<Produto> obterTodos() {
        return produtoRepository.obterTodos();
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu id.
     * 
     * @param id do produto que será localizado
     * @return Retona um produto caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtoRepository.obterPorId(id);
    }

    /**
     * Metodo para adicionar produto na lista.
     * 
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto) {
        // Poderia ter alguma regra de negocio aqui para validar o produto.
        return produtoRepository.adicionar(produto);

    }

    /**
     * Metodo para deletar o produto por id.
     * 
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id) {
        produtoRepository.deletar(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * 
     * @param produto que será atualizado.
     * @param id      do produto.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Integer id, Produto produto) {

        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }
}
