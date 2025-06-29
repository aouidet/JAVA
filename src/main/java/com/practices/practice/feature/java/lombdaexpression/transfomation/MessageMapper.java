package com.practices.practice.feature.java.lombdaexpression.transfomation;

import java.util.function.Function;

public class MessageMapper implements Function<MessageDto, MessageEntity> {
    @Override
    public MessageEntity apply(MessageDto messageDto) {
        return new MessageEntity(messageDto.content());
    }
}
