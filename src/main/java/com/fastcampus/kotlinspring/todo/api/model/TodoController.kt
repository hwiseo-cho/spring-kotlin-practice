package com.fastcampus.kotlinspring.todo.api.model

import com.fastcampus.kotlinspring.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class TodoController(
    private val todoService: TodoService,
) {

    @GetMapping
    fun getAll() =
        ResponseEntity.ok(TodoListResponse.of(todoService.findAll()))

    @GetMapping("/{id}")
    fun get(@PathVariable id : Long) =
        ResponseEntity.ok(TodoResponse.of(todoService.findById(id)))

    @PostMapping
    fun create(@RequestBody request: TodoRequest) =
        ResponseEntity.ok(TodoResponse.of(todoService.create(request)))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: TodoRequest
    ) = ResponseEntity.ok(TodoResponse.of(todoService.update(id, request)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Unit> {
        todoService.delete(id)
        return ResponseEntity.noContent().build()
    }

}