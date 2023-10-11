package com.company.training_center.assistant;

import com.company.training_center.modul.Student;
import com.company.training_center.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class StudentAssistant {

    private final StudentRepository studentRepository;

    public Page<Student> assistantMethod(Map<String, String> params) {
        int page = 0, size = 10;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        return this.studentRepository.searchByStudent(
                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                params.get("firstname"),
                params.get("lastname"),
                params.get("email"),
                params.get("username"),
                params.get("school"),
                PageRequest.of(page,size)
        );

    }

}
