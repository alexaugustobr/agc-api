package br.com.crm.produto.infra

import br.com.crm.produto.model.Produto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProdutoRepository : JpaRepository<Produto, UUID>