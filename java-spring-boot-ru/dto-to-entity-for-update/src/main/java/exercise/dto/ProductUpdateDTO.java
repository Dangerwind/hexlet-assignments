package exercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

// BEGIN

@Getter
@Setter
public class ProductUpdateDTO {
    private String title;
    private int price;
}
// END
