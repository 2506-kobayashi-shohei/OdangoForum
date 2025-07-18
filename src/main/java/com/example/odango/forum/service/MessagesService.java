package com.example.odango.forum.service;

import com.example.odango.forum.controller.form.MessagesForm;
import com.example.odango.forum.controller.form.UsersForm;
import com.example.odango.forum.repository.Entity.Messages;
import com.example.odango.forum.repository.MessagesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessagesService {
    @Autowired
    MessagesRepository messagesRepository;
    @Autowired
    HttpSession session;

    /*
     * レコード追加
     */
    public void addMessage(MessagesForm reqMessage) {
//        UsersForm user = (UsersForm) session.getAttribute("loginUser");
//        reqMessage.setUserId(user.getId());
        reqMessage.setUserId(1);
        reqMessage.setCreatedDate(LocalDateTime.now());
        reqMessage.setUpdatedDate(LocalDateTime.now());
        Messages saveMessage = setMessageEntity(reqMessage);
        messagesRepository.insert(saveMessage);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Messages setMessageEntity(MessagesForm reqMessage) {
        Messages message = new Messages();
        message.setId(reqMessage.getId());
        message.setTitle(reqMessage.getTitle());
        message.setText(reqMessage.getText());
        message.setCategory(reqMessage.getCategory());
        message.setUserId(reqMessage.getUserId());
        message.setCreatedDate(reqMessage.getCreatedDate());
        message.setUpdatedDate(reqMessage.getUpdatedDate());
        return message;
    }
}
