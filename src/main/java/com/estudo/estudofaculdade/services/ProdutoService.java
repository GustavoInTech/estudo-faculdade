package com.estudo.estudofaculdade.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.estudofaculdade.model.Produto;
import com.estudo.estudofaculdade.model.exception.ResourceNotFoundException;
import com.estudo.estudofaculdade.repository.ProdutoRepository;
import com.estudo.estudofaculdade.shared.ProdutoDTO;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * 
     * @return lista de produtos.
     */
    public List<ProdutoDTO> obterTodos() {

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu id.
     * 
     * @param id do produto que será localizado
     * @return Retona um produto caso seja encontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: " + id + "não encontrado");
        }

        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produto na lista.
     * 
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */

    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {

        // Remover o id pata conseguir fazer o cadastro
        produtoDto.setId(null);

        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter o nosso produtoDTO em produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Salvar o produto do banco
        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());

        // Retornar o produtoDtO atualizado
        return produtoDto;

    }

    /**
     * Metodo para deletar o produto por id.
     * 
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Não foi possivel deletar o produto com id: " + id + " - Produto não existe");
        }
        produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * 
     * @param produto que será atualizado.
     * @param id      do produto.
     * @return Retorna o produto após atualizar a lista.
     */

    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {

        // Passar o id para o produtoDTO
        produtoDto.setId(id);
        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        // Converte o produtoDTO em um produto.
        Produto produto = mapper.map(produtoDto, Produto.class);
        // Atualizar o produto no Banco de Dados
        produtoRepository.save(produto);
        // Retornar o produtoDTO atualizado...
        return produtoDto;
    }

}
