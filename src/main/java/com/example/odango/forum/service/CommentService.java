package com.example.odango.forum.service;

import com.example.odango.forum.controller.form.UserCommentForm;
import com.example.odango.forum.repository.Entity.UserComment;
import com.example.odango.forum.controller.form.CommentForm;
import com.example.odango.forum.repository.CommentRepository;
import com.example.odango.forum.repository.Entity.Comment;
import com.example.odango.forum.repository.UserCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    UserCommentRepository userCommentRepository;

    @Autowired
    CommentRepository commentRepository;

    /*コメント表示*/
    public List<UserCommentForm> findAllComment(){
        List<UserComment> reqComments = userCommentRepository.findAll();
        return setUserComments(reqComments);
    }

    private List<UserCommentForm> setUserComments(List<UserComment> reqComments){
        List<UserCommentForm> comments = new ArrayList<>();
        for(UserComment reqComment : reqComments){
            UserCommentForm comment = new UserCommentForm();
            comment.setId(reqComment.getId());
            comment.setAccount(reqComment.getAccount());
            comment.setName(reqComment.getName());
            comment.setBranchId(reqComment.getBranchId());
            comment.setDepartmentId(reqComment.getDepartmentId());
            comment.setUserId(reqComment.getUserId());
            comment.setMessageId(reqComment.getMessageId());
            comment.setText(reqComment.getText());
            comment.setCreatedDate(reqComment.getCreatedDate());
            comment.setUpdatedDate(reqComment.getUpdatedDate());
            comments.add(comment);
        }
        return comments;
    }

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
