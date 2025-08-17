package com.ilyaproject.order.controller.rest;

import com.ilyaproject.order.constants.OrderConstants;
import com.ilyaproject.order.controller.grpc.client.OrderClient;
import com.ilyaproject.order.dto.general.ResponseDTO;
import com.ilyaproject.order.dto.write.CreateOrderDTO;
import com.ilyaproject.order.entity.Order;
import com.ilyaproject.order.repository.OrderRepository;
import com.ilyaproject.order.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/rest", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class OrderExternalController {

    private final OrderServiceImpl orderService;
    @PostMapping("/createorder")
    public ResponseEntity<ResponseDTO> createOrder(@Valid @RequestBody CreateOrderDTO orderDTO){
        orderService.createService(orderDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(OrderConstants.STATUS_201, OrderConstants.MESSAGE_201));
    }
}
