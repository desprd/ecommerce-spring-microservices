package com.ilyaproject.order.controller.rest;

import com.ilyaproject.order.constants.OrderConstants;
import com.ilyaproject.order.dto.general.ResponseDTO;
import com.ilyaproject.order.dto.write.CreateOrderDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/rest", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class OrderExternalController {
    @GetMapping("/createorder")
    public ResponseEntity<ResponseDTO> createOrder(@Valid @RequestBody CreateOrderDTO orderDTO){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(OrderConstants.STATUS_201, OrderConstants.MESSAGE_201));
    }
}
