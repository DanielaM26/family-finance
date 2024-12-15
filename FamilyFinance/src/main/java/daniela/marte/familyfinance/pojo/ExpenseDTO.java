package daniela.marte.familyfinance.pojo;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

    @NotBlank(message = "Description is required.")
    @Size(max = 50, message = "Description must not exceed 50 characters.")
    private String description;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0.")
    private Double price;

    @NotNull(message = "Quantity is required.")
    @Min(value = 1, message = "Quantity must be at least 1.")
    private Integer quantity;

    @NotNull(message = "Family member ID is required.")
    private Long familyMemberId; // Referință la membrul familiei
}
