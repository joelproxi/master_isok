package com.uds.master_isok.teacher;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class TeacherResponse {
    private String biography;
    private String publications;
    private String photoUrl;
    private String firstName;
    private String lastName;
    private String email;
}
