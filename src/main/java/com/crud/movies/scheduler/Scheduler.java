package com.crud.movies.scheduler;

import com.crud.movies.config.AdminConfig;
import com.crud.movies.domain.Mail;
import com.crud.movies.domain.Order;
import com.crud.movies.repository.OrderRepository;
import com.crud.movies.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private static final String SUBJECT = "MOVIE RENTAL INFO";

    private final EmailService emailService;

    private final OrderRepository orderRepository;

    private final AdminConfig adminConfig;

    @Scheduled(cron = "${profile.cron}")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        List<Order> allOrders = orderRepository.findAll();
        for(Order order : allOrders ) {
            if (order.getOrderDateEnd().equals(LocalDate.now())) {
                emailService.send(
                        Mail.builder()
                                .mailTo(order.getRent().getUser().getUserEmail())
                                .subject(SUBJECT)
                                .message("Your order deadline for: " + order.getMovies().stream()
                                        .map(Object::toString)
                                        .collect(Collectors.joining(",")) + " is today.")
                                .toCc(adminConfig.getAdminMail())
                                .build()
                );
            }
        }
    }
}
