package daniela.marte.familyfinance.pojo;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "FamilyMember")
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FamilyRole role; // Enum pentru rol
}
