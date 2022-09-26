package ru.ikv.qa.auto.spring_web.restControllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String fullName;
    private boolean isResident;
    private String inn;
    private ClientTypeCode clientTypeCode;

}
