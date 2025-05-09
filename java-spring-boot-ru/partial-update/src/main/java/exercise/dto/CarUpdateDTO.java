package exercise.dto;

import jakarta.validation.constraints.Email;
import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class CarUpdateDTO {

        private JsonNullable<String> model;

        private JsonNullable<String> manufacturer;

        private JsonNullable<Integer> enginePower;
}


// model — модель автомобиля
//manufacturer — производитель
//enginePower — мощность двигателя

// END
