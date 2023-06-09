package jpabook.jpashop.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {
    @NotNull
    private Long itemId;
    @Min(value = 1, message = "The count must bigger than 1.")
    private int count;
}
