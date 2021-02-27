package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class RegistrationData {
    private final String city;
    private final String deliveryDate;
    private final String name;
    private final String phone;

}
