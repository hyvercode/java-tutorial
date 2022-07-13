package com.solusione.day2.service;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.hyvercode.solusione.helpers.base.ResponseBuilder;
import com.hyvercode.solusione.helpers.exception.BusinessException;
import com.hyvercode.solusione.helpers.utils.CommonUtil;
import com.hyvercode.solusione.helpers.utils.Constant;
import com.hyvercode.solusione.helpers.utils.PageableUtil;
import com.hyvercode.solusione.model.PageRequest;
import com.hyvercode.solusione.service.CrudService;
import com.solusione.day2.helpers.Constants;
import com.solusione.day2.model.entity.Book;
import com.solusione.day2.model.entity.Order;
import com.solusione.day2.model.request.order.OrderRequest;
import com.solusione.day2.model.response.book.BookResponse;
import com.solusione.day2.model.response.order.ListOrderResponse;
import com.solusione.day2.model.response.order.OrderResponse;
import com.solusione.day2.repository.BookRepository;
import com.solusione.day2.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService implements CrudService<OrderRequest, BaseResponse, PageRequest, String> {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    public OrderService(BookRepository bookRepository, OrderRepository orderRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public BaseResponse create(OrderRequest input) {
        final long start = CommonUtil.tok();

        /**
         * Check stock book
         */
        Book book = checkStock(input.getBookId());
        if (book.getStock()<input.getQty()) {
            logger.info(Constants.RESPONSE_MESSAGE_30021);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30021, Constants.RESPONSE_MESSAGE_30021);
        }

        /**
         * Insert new order
         */
        BigDecimal amount = book.getPrice().subtract(book.getDiscount()).multiply(new BigDecimal(input.getQty()));
        Order order = Order.builder()
                .orderDate(new Date(System.currentTimeMillis()))
                .book(book)
                .qty(input.getQty())
                .amount(amount)
                .build();
        order.setCreatedBy(Constant.CREATOR);
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        Order newOrder = orderRepository.save(order);

        /**
         * Update stock Book
         */
        book.setStock(book.getStock() - input.getQty());
        updateStockBook(book);

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start, end), Constant.PROCESS_SUCCESSFULLY,
                OrderResponse.builder()
                        .id(newOrder.getId())
                        .orderDate(newOrder.getOrderDate())
                        .bookResponse(BookResponse.builder()
                                .id(newOrder.getBook().getId())
                                .title(newOrder.getBook().getTitle())
                                .price(newOrder.getBook().getDiscount())
                                .price(newOrder.getBook().getPrice())
                                .build())
                        .qty(newOrder.getQty())
                        .amount(newOrder.getAmount())
                        .build()
        );
    }

    @Override
    public BaseResponse read(String id) {
        final long start = CommonUtil.tok();

        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isEmpty()) {
            logger.info(Constants.RESPONSE_MESSAGE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }

        Order order = optional.get();

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start, end), Constant.PROCESS_SUCCESSFULLY,
                OrderResponse.builder()
                        .id(order.getId())
                        .orderDate(order.getOrderDate())
                        .bookResponse(BookResponse.builder()
                                .id(order.getBook().getId())
                                .title(order.getBook().getTitle())
                                .price(order.getBook().getDiscount())
                                .price(order.getBook().getPrice())
                                .build())
                        .qty(order.getQty())
                        .amount(order.getAmount())
                        .build());
    }

    @Override
    public BaseResponse update(String id, OrderRequest input) {
        final long start = CommonUtil.tok();

        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isEmpty()) {
            logger.info(Constants.RESPONSE_MESSAGE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }

        Order order = optional.get();
        BeanUtils.copyProperties(input, order);
        order.setUpdatedBy(Constant.CREATOR);
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start, end), Constant.PROCESS_SUCCESSFULLY,
                OrderResponse.builder()
                        .id(order.getId())
                        .orderDate(order.getOrderDate())
                        .bookResponse(BookResponse.builder()
                                .id(order.getBook().getId())
                                .title(order.getBook().getTitle())
                                .price(order.getBook().getDiscount())
                                .price(order.getBook().getPrice())
                                .build())
                        .qty(order.getQty())
                        .amount(order.getAmount())
                        .build());
    }

    @Override
    public BaseResponse delete(String id) {
        final long start = CommonUtil.tok();
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isEmpty()) {
            logger.info(Constants.RESPONSE_MESSAGE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }
        orderRepository.deleteById(id);
        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start,end), Constant.PROCESS_SUCCESSFULLY);
    }

    @Override
    public BaseResponse all(OrderRequest input) {
        final long start = CommonUtil.tok();
        Iterable<Order> orders = orderRepository.findAll();
        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start,end), Constant.PROCESS_SUCCESSFULLY,
                orders);
    }

    @Override
    public  BaseResponse paginate(PageRequest input) {
        final long start = CommonUtil.tok();

        Page<Order> page = this.getPageResultByInput(input);

        Set<OrderResponse> orderResponses = page.getContent().stream().map(outlet -> {
            OrderResponse response = new OrderResponse();
            BeanUtils.copyProperties(outlet, response);
            return response;
        }).collect(Collectors.toSet());

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start,end), Constant.PROCESS_SUCCESSFULLY,
                ListOrderResponse.builder()
                        .content(orderResponses)
                        .pagination(PageableUtil.pageToPagination(page))
                        .build());
    }

    private Page<Order> getPageResultByInput(PageRequest pageRequest) {
        String sortBy = pageRequest.getSortBy() != null && !pageRequest.getSortBy().isEmpty() ? pageRequest.getSortBy() : "created_at";
        Pageable pageable = PageableUtil.createPageRequest(pageRequest, pageRequest.getPageSize(), pageRequest.getPageNumber(),
                sortBy, pageRequest.getSortType());
        return orderRepository.findAll(pageable);
    }

    /**
     * Check Stock book
     * @param bookId
     * @return
     */
    private Book checkStock(String bookId){
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            logger.info(Constants.RESPONSE_MESSAGE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }

        return bookOptional.get();
    }

    /**
     * Update stock book
     * @param book
     */
    private void updateStockBook(Book book){
        bookRepository.save(book);
    }
}
