package com.crud.movies.mapper;

import com.crud.movies.domain.Order;
import com.crud.movies.domain.OrderDto;
import com.crud.movies.domain.Rent;
import com.crud.movies.domain.RentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {

    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(
                rentDto.getRentId()
        );
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getRentId()
        );
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rents) {
        return rents.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
