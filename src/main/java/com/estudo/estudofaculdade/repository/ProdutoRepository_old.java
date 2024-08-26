package com.estudo.estudofaculdade.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.estudo.estudofaculdade.model.Produto;
import com.estudo.estudofaculdade.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository_old {

    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos
     * 
     * @return lista de produtos.
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu id.
     * 
     * @param id do produto que será localizado
     * @return Retona um produto caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    /**
     * Metodo para adicionar produto na lista.
     * 
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto) {

        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Metodo para deletar o produto por id.
     * 
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * 
     * @param produto que será atualizado
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Produto produto) {

        // encontrar o produto na lista
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não pode ser atualizado pois não existe.");
        }

        // Eu tenho que remover o produto antigo da lista.
        deletar(produto.getId());

        // Depois adicionar o produto atualizado na lista.
        produtos.add(produto);

        return produto;
    }
}
