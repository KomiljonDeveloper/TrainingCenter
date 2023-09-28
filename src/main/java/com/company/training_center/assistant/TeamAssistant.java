package com.company.training_center.assistant;

import com.company.training_center.modul.Team;
import com.company.training_center.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TeamAssistant {
    private final TeamRepository teamRepository;
    public  Page<Team> assistantMethod(Map<String,String> params){
          int size = 10,page = 0;
          if (params.containsKey("size")){
              size = Integer.parseInt(params.get("size"));
          }
          if (params.containsKey("page")){
              page = Integer.parseInt(params.get("page"));
          }
          return this.teamRepository.searchByBasic(
                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                params.get("name"),
                params.get("level"),
                PageRequest.of(page, size)
        );
    }
}
