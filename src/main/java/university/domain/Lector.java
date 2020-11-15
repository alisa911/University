package university.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lectors")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lector {

    @Id
    @SequenceGenerator(name="my_seq", sequenceName="lectors_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_seq")
    private Long id;

    private String name;

    @Column(name ="degree_id")
    private Long degree;

    private Long salary;

    @ManyToMany(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Department> departments;
}
