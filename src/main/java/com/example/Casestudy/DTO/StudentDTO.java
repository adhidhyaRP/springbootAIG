package com.example.Casestudy.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long stdId;
    private String stdName;

    public StudentDTO(Long stdId, String stdName) {
        this.stdId = stdId;
        this.stdName = stdName;
    }
}
