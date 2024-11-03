package ru.trushkov.library.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import jakarta.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorDto {

    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

}
