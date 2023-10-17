package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.PlayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "nickname", source = "nickname"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "cost", source = "cost"),
            @Mapping(target = "salary", source = "salary"),
            @Mapping(target = "playerRole", source = "playerRole"),
            @Mapping(target = "skill", source = "skill"),
            @Mapping(target = "heroPickSkill", source = "heroPickSkill"),
            @Mapping(target = "captain", source = "captain"),
            @Mapping(target = "greed", source = "greed"),
            @Mapping(target = "laningSkill", source = "laningSkill"),
            @Mapping(target = "macroSkill", source = "macroSkill"),
            @Mapping(target = "farmSkill", source = "farmSkill"),
            @Mapping(target = "teamwork", source = "teamwork"),
            @Mapping(target = "microSkill", source = "microSkill"),
            @Mapping(target = "stressResistance", source = "stressResistance"),
            @Mapping(target = "wardingSkill", source = "wardingSkill"),
            @Mapping(target = "winLossDelta", source = "winLossDelta")
    })
    PlayerDTO playerToPlayerDTO(Player player);

    List<PlayerDTO> playersToPlayerDTOs(List<Player> players);
}
