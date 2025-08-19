package com.ilyaproject.payment.service.impl;

import com.ilyaproject.payment.dto.write.PaymentInformationDTO;
import com.ilyaproject.payment.exception.WrongPaymentAmountException;
import com.ilyaproject.payment.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public void pay(PaymentInformationDTO paymentInformationDTO) {
        if (!paymentInformationDTO.getPrice().equals(paymentInformationDTO.getPaidAmount())){
            throw new WrongPaymentAmountException("Wrong amount! User paid " + paymentInformationDTO.getPaidAmount() + " when the course is " + paymentInformationDTO.getPrice());
        }

    }
}
