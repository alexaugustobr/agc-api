package br.com.crm.commons.produto.model

import br.com.crm.system.db.model.AbstractModel
import java.util.*
import javax.persistence.Entity
import javax.validation.constraints.NotBlank

@Entity
class Produto(@NotBlank
              open var nome  : String = "",
              open var preco : Double = Double.NaN, id: UUID?, dataCriacao: Date, dataExclusao: Date?, excluido: Boolean
) : AbstractModel(id, dataCriacao, dataExclusao, excluido) {


}