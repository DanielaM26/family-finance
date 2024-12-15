package daniela.marte.familyfinance.pojo;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FamilyMemberDTO {

    @NotBlank(message = "First name is required.")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters.")
    private String lastName;

    @NotNull(message = "Age is required.")
    @Min(value = 1, message = "Age must be at least 1.")
    private Integer age;

    @NotNull(message = "Role is required.")
    private FamilyRole role;
}
