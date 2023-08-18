package com.strmanager.api.dto;

import lombok.*;

import java.util.ArrayList;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineDeleteDto {

    private long id;
    private long memberId;
    private String planDate;

}
