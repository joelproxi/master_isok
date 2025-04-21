package com.uds.master_isok.teacher;

import com.uds.master_isok.utils.PersonRequest;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherRequest extends PersonRequest {
    private String biography;
    private String publications;
    private String photoUrl;
}
