package com.practices.practice.feature.java.lombdaexpression.transfomation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Dans une application de messagerie, tu reçois une liste de messages sous forme d’objets MessageDTO,
 * et tu dois les convertir en entités MessageEntity pour les sauvegarder en base de données.
 */
public class Transforme {

    static <T, R> List<R> convert(List<T> input, Function<T, R> mapper) {
        List<R> output = new ArrayList<>();
        for(T item : input) {
            output.add(mapper.apply(item));
        }
        return output;
    }

    public static void main(String[] args) {

        List<MessageDto> dtos = List.of(new MessageDto("content 1"), new MessageDto("content 2"));
        //var messageEntities = convert(dtos, new MessageMapper());
        var messageEntities = convert(dtos, messageDto -> messageDto);
        messageEntities.forEach(System.out::println);
    }
}
