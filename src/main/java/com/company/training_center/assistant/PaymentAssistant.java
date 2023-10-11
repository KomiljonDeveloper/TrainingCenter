package com.company.training_center.assistant;

import com.company.training_center.modul.Payment;
import com.company.training_center.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class PaymentAssistant {

    private final PaymentRepository paymentRepository;

    public Page<Payment> assistantMethod(Map<String, String> params){
           int page = 0,size = 10;
           if (params.containsKey("page")){
               page = Integer.parseInt(params.get("page"));
           }
           if (params.containsKey("size")){
               size = Integer.parseInt(params.get("size"));
           }

          return this.paymentRepository.searchByPayment(
                   params.get("id") == null?null:Integer.parseInt(params.get("id")),
                   params.get("month"),
                   params.get("amount") == null?null:Double.valueOf(params.get("amount")),
                   PageRequest.of(page,size)
           );

    }
}
