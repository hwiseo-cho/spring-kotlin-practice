package com.fastcampus.kotlinspring.todo.service

import com.fastcampus.kotlinspring.todo.api.model.TodoRequest
import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fastcampus.kotlinspring.todo.domain.TodoRepository
import org.springframework.data.domain.Sort
import org.springframework.data.querydsl.QSort.by
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    @Transactional
    fun findAll() : List<Todo> =
        todoRepository.findAll(by(Sort.Direction.DESC, "id"))

    @Transactional
    fun findById(id: Long) : Todo =
        todoRepository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @Transactional
    fun create(request: TodoRequest) : Todo {
        checkNotNull(request) { "TodoRequest is null"}

        val todo = Todo {
            title = request.title,
            description = request.description,
            done = request.done,
            createAt = LocalDateTime.now()
        }

        return todoRepository.save(todo)
    }

    @Transactional
    fun update(id: Long, request: TodoRequest?) : Todo {
        checkNotNull(request) { "TodoRequest is null"}

        return findById(id).let {
            it.update(request.title, request.description, request.done)
            todoRepository.save(it)
        }
    }

    @Transactional
    fun delete(id: Long) = todoRepository.deleteById(id)
}