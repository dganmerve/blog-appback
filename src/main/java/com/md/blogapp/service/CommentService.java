package com.md.blogapp.service;

import com.md.blogapp.entity.Comment;
import com.md.blogapp.exception.CommentNotFoundException;
import com.md.blogapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Tüm yorumları listeleme
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // ID ile bir yorum getirme
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Yeni bir yorum oluşturma
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Bir yorumu güncelleme
    public Comment updateComment(Long id, Comment updatedComment) {
        Optional<Comment> existingComment = commentRepository.findById(id);

        if (existingComment.isPresent()) {
            Comment comment = existingComment.get();
            comment.setText(updatedComment.getText());
            return commentRepository.save(comment);
        } else {
            throw new CommentNotFoundException("Yorum bulunamadı: " + id);
        }
    }

    // Bir yorumu silme
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
