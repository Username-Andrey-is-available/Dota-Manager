package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.model.TeamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "budget", source = "budget"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "fans", source = "fans")
    })
    TeamDTO teamToTeamDTO(Team team);

    List<TeamDTO> teamsToTeamsDTOs(List<Team> teams);
}
