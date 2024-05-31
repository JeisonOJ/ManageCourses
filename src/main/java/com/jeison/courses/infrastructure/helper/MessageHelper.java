package com.jeison.courses.infrastructure.helper;

import com.jeison.courses.api.dto.request.MessageReq;
import com.jeison.courses.api.dto.response.MessageResp;
import com.jeison.courses.domain.entities.Message;

public class MessageHelper {

    public static MessageResp messageToResp(Message message){
        return MessageResp.builder()
        .id(message.getId())
        .sentDate(message.getSentDate())
        .messageContent(message.getMessageContent())
        .sender(UserHelper.userToResp(message.getSender()))
        .receiver(UserHelper.userToResp(message.getReceiver()))
        .build();
    }

    public static Message reqToMessage(MessageReq messageReq){
        return Message.builder()
        .messageContent(messageReq.getMessageContent())
        .build();
    }

}
