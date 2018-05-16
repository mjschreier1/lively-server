package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.Payment;
import com.lively.LiveLy.repo.PaymentRepository;
import com.lively.LiveLy.repo.UserRepository;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class PaymentController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/payment")
    public Payment submitPayment(@RequestBody Map<String, String> body) {
        Payment payment = new Payment(
                userRepository.findById(Integer.parseInt(body.get("id"))),
                Float.parseFloat(body.get("amount")),
                LocalDateTime.now());
        paymentRepository.save(payment);

        Stripe.apiKey = System.getenv("STRIPE_KEY");

        String token = body.get("stripeToken");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", 999);
        params.put("currency", "usd");
        params.put("description", "Example charge");
        params.put("source", token);
        try {
            Charge charge = Charge.create(params);
        } catch (Exception err) {
            System.out.println(err);
        }
        return payment;
    }
}
