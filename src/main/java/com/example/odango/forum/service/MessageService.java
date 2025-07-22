package com.example.odango.forum.service;

import com.example.odango.forum.controller.form.MessageForm;
import com.example.odango.forum.controller.form.UserMessageForm;
import com.example.odango.forum.repository.Entity.Message;
import com.example.odango.forum.repository.MessageRepository;
import com.example.odango.forum.repository.UserMessageRepository;
import com.example.odango.forum.repository.Entity.UserMessage;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserMessageRepository userMessageRepository;

    // レコード取得処理
    public List<UserMessageForm> findAllMessage(LocalDate start, LocalDate end, String category){
        LocalDateTime startDate;
        LocalDateTime endDate;
        Integer limit = 1000;

        if(start == null){
            startDate = LocalDateTime.of(2022,1,1,0,0,0);
        }else{
            startDate = start.atStartOfDay();
        }

        if(end == null){
            endDate = LocalDateTime.now();

        }else{
            endDate = end.atTime(23, 59, 59);
        }

        if(!StringUtils.isEmpty(category)) {
            List<UserMessage> messages = userMessageRepository.selectByCategory(startDate, endDate, category, limit);
            return setUserMessageForm(messages);
        }else {
            List<UserMessage> messages = userMessageRepository.selectAll(startDate, endDate, limit);
            return setUserMessageForm(messages);
        }
    }

    public  List<UserMessageForm> setUserMessageForm(List<UserMessage> results){
        List<UserMessageForm> messages = new ArrayList<>();

        for (UserMessage result : results) {
            UserMessageForm message = new UserMessageForm();
            message.setId(result.getId());
            message.setAccount(result.getAccount());
            message.setName(result.getName());
            message.setBranchId(result.getBranchId());
            message.setDepartmentId(result.getDepartmentId());
            message.setUserId(result.getUserId());
            message.setTitle(result.getTitle());
            message.setText(result.getText());
            message.setCategory(result.getCategory());
            message.setCreatedDate(result.getCreatedDate());
            message.setUpdatedDate(result.getUpdatedDate());
            messages.add(message);
        }
        return  messages;
    }

    /*
     * レコード追加
     */
    public void addMessage(MessageForm reqMessage, Integer userId) {
        reqMessage.setUserId(userId);
        reqMessage.setCreatedDate(LocalDateTime.now());
        reqMessage.setUpdatedDate(LocalDateTime.now());
        Message saveMessage = setMessageEntity(reqMessage);
        messageRepository.insert(saveMessage);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Message setMessageEntity(MessageForm reqMessage) {
        Message message = new Message();
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
