package io.danielhartman.tasks.repository.storage

import io.paperdb.Book
import me.danielhartman.common.core.CoreResponse

class PaperHelper<T>(val book:Book){
    fun getItem(identifier:((T)-> Boolean)):T?{
        return book.allKeys.asSequence().map { book.read<T>(it) }.filter { identifier(it) }.firstOrNull()
    }

}