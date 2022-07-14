package com.solusione.day2.service;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.hyvercode.solusione.helpers.base.ResponseBuilder;
import com.hyvercode.solusione.helpers.exception.BusinessException;
import com.hyvercode.solusione.helpers.utils.CommonUtil;
import com.hyvercode.solusione.helpers.utils.Constant;
import com.hyvercode.solusione.service.BaseService;
import com.solusione.day2.helpers.Constants;
import com.solusione.day2.model.entity.Order;
import com.solusione.day2.model.entity.Payment;
import com.solusione.day2.model.enums.Status;
import com.solusione.day2.model.request.payment.PaymentRequest;
import com.solusione.day2.model.response.book.BookResponse;
import com.solusione.day2.model.response.order.OrderResponse;
import com.solusione.day2.model.response.payment.PaymentResponse;
import com.solusione.day2.repository.OrderRepository;
import com.solusione.day2.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PaymentService implements BaseService<PaymentRequest, BaseResponse> {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public BaseResponse execute(PaymentRequest input) {
        final long start = CommonUtil.tok();

        Optional<Order> optionalOrder = orderRepository.findById(input.getOrderId());
        if (optionalOrder.isEmpty()) {
            logger.info(Constants.RESPONSE_MESSAGE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }

        Order order = optionalOrder.get();
        if (input.getStatus().equals(Status.PAID) && order.getAmount().compareTo(input.getAmount()) < 0) {
            logger.info(Constants.RESPONSE_MESSAGE_30022);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30022, Constants.RESPONSE_MESSAGE_30022);
        }

        Payment payment = Payment.builder()
                .paymentDate(new Date(System.currentTimeMillis()))
                .order(order)
                .status(input.getStatus())
                .amount(input.getAmount())
                .build();
        payment.setCreatedBy(Constant.CREATOR);
        payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        Payment newPayment = paymentRepository.save(payment);

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start, end), Constant.PROCESS_SUCCESSFULLY,
                PaymentResponse.builder()
                        .id(newPayment.getId())
                        .paymentDate(newPayment.getPaymentDate())
                        .order(OrderResponse.builder()
                                .id(newPayment.getOrder().getId())
                                .orderDate(newPayment.getOrder().getOrderDate())
                                .qty(newPayment.getOrder().getQty())
                                .amount(newPayment.getOrder().getAmount())
                                .book(BookResponse.builder()
                                        .id(newPayment.getOrder().getBook().getId())
                                        .title(newPayment.getOrder().getBook().getTitle())
                                        .description(newPayment.getOrder().getBook().getDescription())
                                        .author(newPayment.getOrder().getBook().getAuthor())
                                        .discount(newPayment.getOrder().getBook().getDiscount())
                                        .price(newPayment.getOrder().getBook().getPrice())
                                        .stock(newPayment.getOrder().getBook().getStock())
                                        .active(newPayment.getOrder().getBook().getActive())
                                        .build())
                                .build())
                        .status(newPayment.getStatus())
                        .amount(newPayment.getAmount())
                        .build());
    }
}
