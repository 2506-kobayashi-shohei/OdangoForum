package com.example.odango.forum.service;

import com.example.odango.forum.controller.form.CommentForm;
import com.example.odango.forum.repository.CommentRepository;
import com.example.odango.forum.repository.Entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    // コメント登録処理
    public void saveComment(CommentForm commentForm) {
        Comment comment = setCommentEntity(commentForm);
        commentRepository.insert(comment);
    }

    private Comment setCommentEntity(CommentForm commentForm) {
        Comment comment = new Comment();
        comment.setId(commentForm.getId());
        comment.setText(commentForm.getText());
        comment.setUserId(commentForm.getUserId());
        comment.setMessageId(commentForm.getMessageId());
        comment.setUpdatedDate(comment.getUpdatedDate());
        return comment;
    }
}
