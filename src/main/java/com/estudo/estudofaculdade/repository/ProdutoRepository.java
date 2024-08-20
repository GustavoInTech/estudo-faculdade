package com.estudo.estudofaculdade.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.estudo.estudofaculdade.model.Produto;

@Repository
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos
     * 
     * @return lista de produtos.
     */
    public List<Produto> obteProdutos() {
        return produtos;
    }
}
