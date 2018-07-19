package com.example.demo.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.HashSet
import javax.persistence.*

import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity {

    @CreatedDate
    var createdAt: Long? = null

    @LastModifiedDate
    var lastModified: Long? = null

    override fun toString(): String {
        return ("BaseEntity{" + "createdAt=" + createdAt + ", lastModified="
                + lastModified + '}'.toString())
    }

    override fun equals(o: Any?): Boolean {
        if (this === o)
            return true
        if (o == null || javaClass != o.javaClass)
            return false

        val that = o as BaseEntity?

        if (if (createdAt != null)
                    createdAt != that!!.createdAt
                else
                    that!!.createdAt != null)
            return false
        return if (lastModified != null)
            lastModified == that.lastModified
        else
            that.lastModified == null

    }

    override fun hashCode(): Int {
        var result = if (createdAt != null) createdAt!!.hashCode() else 0
        result = 31 * result + if (lastModified != null) lastModified!!.hashCode() else 0
        return result
    }
}
