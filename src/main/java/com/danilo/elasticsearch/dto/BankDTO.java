package com.danilo.elasticsearch.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankDTO {

    private String id;

    @NotNull(message = "Enter the name.")
    @NotBlank(message = "Enter the name.")
    @Size(max = 100,message = "The name must contain a maximum of 100 characters.")
    private String name;

    @NotNull(message = "Enter the code.")
    @NotBlank(message = "Enter the code.")
    @Size(min = 3,max = 3,message = "The code must contain 3 characters.")
    private String code;

    @NotNull(message = "Enter the agency.")
    @NotBlank(message = "Enter the agency.")
    @Size(min = 4,max = 4,message = "The agency must contain 4 characters.")
    private String agency;

}
