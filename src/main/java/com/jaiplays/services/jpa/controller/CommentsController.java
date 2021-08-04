package com.jaiplays.services.jpa.controller;

import com.jaiplays.services.jpa.dto.CommentsDTO;
import com.jaiplays.services.jpa.dto.CommentsForm;
import com.jaiplays.services.jpa.exception.CommentNotFoundException;
import com.jaiplays.services.jpa.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Slf4j
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    public ResponseEntity<CommentsDTO> addComment(@RequestBody CommentsForm commentsForm) {
        log.info("Add Request received");
        CommentsDTO commentsDTO =   commentsService.addComment(commentsForm);
        return new ResponseEntity<>(commentsDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentsDTO> getComment(@PathVariable(value = "id") Long id) {
        log.info("Get Request received : {}",id);
        CommentsDTO commentsDTO =   commentsService.getComment(id);
        return new ResponseEntity<>(commentsDTO,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentsDTO> updateComment(@RequestBody CommentsForm commentsForm, @PathVariable(value = "id") Long id) {
        log.info("Update Request received : {}",id);
        CommentsDTO commentsDTO =   commentsService.updateComment(commentsForm, id);
        return new ResponseEntity<>(commentsDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentsDTO> deleteComment(@PathVariable(value = "id") Long id) {
        log.info("Delete Request received : {}",id);
        CommentsDTO commentsDTO = commentsService.deleteComment(id);
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Void> handleCommentNotFoundException(CommentNotFoundException ex){
        log.error("Comments not found exception handled : {}",ex);
        return ResponseEntity.notFound().build();
    }

}
