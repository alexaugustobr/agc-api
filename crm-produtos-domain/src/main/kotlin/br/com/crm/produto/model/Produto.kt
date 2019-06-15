package br.com.crm.produto.model

import br.com.crm.db.model.AbstractModel
import java.util.*
import javax.persistence.Entity
import javax.validation.constraints.NotBlank

@Entity
class Produto : AbstractModel {

    @NotBlank
    var nome  : String = ""
    var preco : Double = Double.NaN

    constructor(nome: String, preco: Double) : super() {
        this.nome = nome
        this.preco = preco
    }

    constructor(ativo: Boolean, nome: String, preco: Double) : super(ativo) {
        this.nome = nome
        this.preco = preco
    }

    constructor(id: UUID?, dataCriacao: Date, dataExclusao: Date?, excluido: Boolean, nome: String, preco: Double) :
        super(id, dataCriacao, dataExclusao, excluido) {
        this.nome = nome
        this.preco = preco
    }
}