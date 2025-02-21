package com.fastcampus.kotlinspring.todo.api.model

import com.fastcampus.kotlinspring.todo.domain.Todo
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val description: String,
    val done: Boolean,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime?
) {

    companion object {
        fun of (todo: Todo?): TodoResponse {
            checkNotNull(todo) {"Todo is null"}

            return TodoResponse(
                id = todo.id!!,
                title = todo.title,
                description = todo.description,
                done = todo.done,
                createdAt = todo.createdAt,
                updateAt = todo.updatedAt
            )
        }
    }
}
