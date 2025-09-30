package com.zoo.fantastico.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @Min(0)
    private int capacity;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Creature> creatures;
}
