package ru.trushkov.library.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authors")
@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
