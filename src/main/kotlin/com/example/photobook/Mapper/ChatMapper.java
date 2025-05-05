package com.example.photobook.Mapper;

import com.example.photobook.Model.ChatMessageModel;
import com.example.photobook.Entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Add componentModel for Spring DI
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    ChatMessage toEntity(ChatMessageModel dto);

    ChatMessageModel toDto(ChatMessage entity);
}