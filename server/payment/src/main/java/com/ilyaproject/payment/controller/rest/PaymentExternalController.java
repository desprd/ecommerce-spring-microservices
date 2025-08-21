package com.ilyaproject.payment.controller.rest;

import com.google.api.Http;
import com.ilyaproject.payment.constants.PaymentConstants;
import com.ilyaproject.payment.dto.general.ResponseDTO;
import com.ilyaproject.payment.dto.write.PaymentInformationDTO;
import com.ilyaproject.payment.dto.write.PaymentResultDTO;
import com.ilyaproject.payment.service.impl.PaymentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/rest", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class PaymentExternalController {

    private final PaymentServiceImpl paymentService;
    private final KafkaTemplate<String, PaymentResultDTO> kafkaTemplate;

    @PostMapping("/payment")
    public ResponseEntity<ResponseDTO> pay(@Valid @RequestBody PaymentInformationDTO paymentInformationDTO){
        paymentService.pay(paymentInformationDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(PaymentConstants.STATUS_200, PaymentConstants.MESSAGE_200));
    }

}
