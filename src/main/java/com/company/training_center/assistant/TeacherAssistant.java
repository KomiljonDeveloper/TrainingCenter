package com.company.training_center.assistant;

import com.company.training_center.modul.Teacher;
import com.company.training_center.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class  TeacherAssistant {

    private final TeacherRepository teacherRepository;

    public Page<Teacher> assistantMethod(Map<String, String> params) {
        int page = 0, size = 10;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        return this.teacherRepository.searchTeacherBasic(
                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                params.get("firstname"),
                params.get("lastname"),
                params.get("father"),
                params.get("username"),
                params.get("phone"),
                PageRequest.of(page,size)
        );
    }
}
