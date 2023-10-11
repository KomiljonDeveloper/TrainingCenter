package com.company.training_center.assistant;

import com.company.training_center.modul.Science;
import com.company.training_center.repository.ScienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ScienceAssistant {

    private final ScienceRepository scienceRepository;

    public Page<Science> assistantMethod(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        return this.scienceRepository.searchScience(
                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                params.get("name"),
                params.get("room"),
                PageRequest.of(page, size)
        );


    }

}
