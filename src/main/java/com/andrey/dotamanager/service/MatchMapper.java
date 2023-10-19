package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Match;
import com.andrey.dotamanager.model.MatchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "team1", source = "team1.name"),
            @Mapping(target = "team2", source = "team2.name"),
            @Mapping(target = "winner", source = "winner.name"),
    })
    MatchDTO matchToMatchDTO(Match match);

    List<MatchDTO> matchesToMatchesDTO(List<Match> matches);
}

