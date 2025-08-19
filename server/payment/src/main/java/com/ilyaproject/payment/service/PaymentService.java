package com.ilyaproject.payment.service;

import com.ilyaproject.payment.dto.write.PaymentInformationDTO;

public interface PaymentService {
    void pay(PaymentInformationDTO paymentInformationDTO);
}
