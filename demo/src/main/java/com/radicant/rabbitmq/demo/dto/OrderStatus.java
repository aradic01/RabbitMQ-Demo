package com.radicant.rabbitmq.demo.dto;

import com.radicant.rabbitmq.demo.dto.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStatus {

    private Order order;
    private StatusType status;
    private String message;
}
