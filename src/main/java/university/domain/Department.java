package university.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @SequenceGenerator(name="my_seq", sequenceName="account_user_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_seq")
    private Long id;

    private String name;

    private Long head;

    @ManyToMany(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Lector> lectors;
}
