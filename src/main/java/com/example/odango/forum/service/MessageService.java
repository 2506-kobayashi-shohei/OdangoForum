package com.example.odango.forum.service;

import com.example.odango.forum.controller.form.UserMessageForm;
import com.example.odango.forum.mapper.UserMessageMapper;
import com.example.odango.forum.repository.Entity.UserMessage;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    UserMessageMapper userMessageMapper;

    // レコード取得処理
    public List<UserMessageForm> findAllMessage(LocalDateTime start, LocalDateTime end, String category){
        LocalDateTime startDate;
        LocalDateTime endDate;
        Integer limit = 1000;

        if(start == null){
            startDate = LocalDateTime.of(2022,1,1,0,0,0);
        }else{
            startDate = start;
        }

        if(end == null){
            endDate = LocalDateTime.now();

        }else{
            endDate = end.withHour(23).withMinute(59).withSecond(59);
        }

        if(!StringUtils.isEmpty(category)) {
            List<UserMessage> messages = userMessageMapper.selectByCategory(startDate, endDate, category, limit);
            return setUserMessageForm(messages);
        }else {
            List<UserMessage> messages = userMessageMapper.selectAll(startDate, endDate, limit);
            return setUserMessageForm(messages);
        }
    }

    public  List<UserMessageForm> setUserMessageForm(List<UserMessage> results){
        List<UserMessageForm> messages = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            UserMessageForm message = new UserMessageForm();
            UserMessage result = results.get(i);
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
}
