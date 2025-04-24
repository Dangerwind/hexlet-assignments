package exercise.mapper;

import exercise.repository.CarRepository;
import org.mapstruct.*;

import exercise.dto.CarCreateDTO;
import exercise.dto.CarUpdateDTO;
import exercise.dto.CarDTO;
import exercise.model.Car;
import org.springframework.beans.factory.annotation.Autowired;

// BEGIN


@Mapper(
        // Подключение JsonNullableMapper
        uses = { JsonNullableMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)


public abstract class CarMapper {


    public abstract Car map(CarCreateDTO car);

    public abstract CarDTO map(Car car);

    public abstract void update(CarUpdateDTO dto, @MappingTarget Car car );
}

// END
