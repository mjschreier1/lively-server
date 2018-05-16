package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.DeleteAllPaymentsResponse;
import com.lively.LiveLy.model.Payment;
import com.lively.LiveLy.model.User;
import com.lively.LiveLy.repo.PaymentRepository;
import com.lively.LiveLy.repo.UserRepository;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/payment")
    public Iterable<Payment> getPaymentsByDates(
            @RequestParam("minYear") int minYear,
            @RequestParam("minMonth") int minMonth,
            @RequestParam("minDate") int minDate,
            @RequestParam("maxYear") int maxYear,
            @RequestParam("maxMonth") int maxMonth,
            @RequestParam("maxDate") int maxDate) {
        LocalDateTime min = LocalDateTime.of(minYear, minMonth, minDate, 0, 0).minusHours(6);
        LocalDateTime max = LocalDateTime.of(maxYear, maxMonth, maxDate, 0, 0).minusHours(6);

        Iterable<Payment> matchingPayments = paymentRepository.findBySubmittedOnBetween(min, max);

        System.out.println(min);
        System.out.println(max);

        // removes user PIN from response
        for (Payment payment:matchingPayments) {
            payment.setUser(new User(
                    payment.getUser().getFirst(),
                    payment.getUser().getLast(),
                    0,
                    payment.getUser().isAdmin(),
                    payment.getUser().getEmail()
            ));
        }

        return matchingPayments;
    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> submitPayment(@RequestBody Map<String, String> body) {
        Payment payment = new Payment(
                userRepository.findById(Integer.parseInt(body.get("id"))),
                Long.parseLong(body.get("amount")) * 100,
                LocalDateTime.now().minusHours(6));
        paymentRepository.save(payment);

        Stripe.apiKey = System.getenv("STRIPE_KEY");
        String token = body.get("stripeToken");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", payment.getAmount());
        params.put("currency", "usd");
        params.put("description", "Rent Payment");
        params.put("source", token);
        try {
            Charge charge = Charge.create(params);
            payment.setSuccessful(true);
            paymentRepository.save(payment);
        } catch (Exception err) {
            System.out.println(err);
            payment.setSuccessful(false);
            paymentRepository.save(payment);
            return new ResponseEntity<Payment>(payment, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    @DeleteMapping("/payment/all")
    public DeleteAllPaymentsResponse deleteAllPayments() {
        paymentRepository.deleteAll();
        return new DeleteAllPaymentsResponse(200, "OK");
    }
}
