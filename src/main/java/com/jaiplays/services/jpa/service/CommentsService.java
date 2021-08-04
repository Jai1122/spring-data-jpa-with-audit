package com.jaiplays.services.jpa.service;

import com.jaiplays.services.jpa.dto.CommentsDTO;
import com.jaiplays.services.jpa.dto.CommentsForm;
import com.jaiplays.services.jpa.entity.Comments;
import com.jaiplays.services.jpa.exception.CommentNotFoundException;
import com.jaiplays.services.jpa.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsDTO addComment(CommentsForm commentsForm) {
        Comments comments = commentsRepository.save(Comments.builder()
                .postName(commentsForm.getPostName())
                .comment(commentsForm.getComment())
                .build());

        return CommentsDTO.builder()
                .id(comments.getId())
                .postName(comments.getPostName())
                .comment(comments.getComment())
                .createdAt(comments.getCreatedAt())
                .lastModifiedAt(comments.getLastModifiedAt())
                .lastModifiedBy(comments.getLastModifiedBy())
                .build();
    }

    public CommentsDTO getComment(Long id) {
        Comments comment = commentsRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("No comment found for given ID"));

        return CommentsDTO.builder()
                .id(comment.getId())
                .postName(comment.getPostName())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .lastModifiedBy(comment.getLastModifiedBy())
                .build();
    }

    public CommentsDTO updateComment(CommentsForm commentsForm, Long id) {
        Comments comment = commentsRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("No comment found for given ID"));
        comment.setComment(commentsForm.getComment());
        comment.setPostName(comment.getPostName());
        Comments savedComment = commentsRepository.save(comment);

        return CommentsDTO.builder()
                .id(savedComment.getId())
                .postName(savedComment.getPostName())
                .comment(savedComment.getComment())
                .createdAt(savedComment.getCreatedAt())
                .lastModifiedAt(savedComment.getLastModifiedAt())
                .lastModifiedBy(savedComment.getLastModifiedBy())
                .build();
    }

    public CommentsDTO deleteComment(Long id) {
        Comments comment = commentsRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("No comment found for given ID"));
        commentsRepository.delete(comment);
        return CommentsDTO.builder()
                .id(comment.getId())
                .postName(comment.getPostName())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .lastModifiedBy(comment.getLastModifiedBy())
                .build();
    }

    public List<CommentsDTO> listAllComments(Pageable pageable) {
        return commentsRepository.findAll(pageable).map(comment -> CommentsDTO.builder()
                .id(comment.getId())
                .postName(comment.getPostName())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .lastModifiedBy(comment.getLastModifiedBy())
                .build())
                .stream().collect(Collectors.toList());
    }
}
