package br.com.crm.system.db.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class AbstractModel(
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Id
        @Column(columnDefinition = "uuid")
        var id: UUID? = null,

        @Temporal(TemporalType.TIMESTAMP)
        @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        var dataCriacao : Date = Date(),

        var dataExclusao : Date? = null,

        @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
        var excluido : Boolean = false
) : Serializable {

    @JsonProperty
    fun getType()  = this.javaClass.simpleName

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}