package com.udemy.projeto.services;

import com.udemy.projeto.model.PaymentSlip;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class SlipService {
    public void fillPaymentSlip(PaymentSlip paymentSlip, Date orderInstant){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderInstant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        paymentSlip.setDueDate(calendar.getTime());
    }
}
