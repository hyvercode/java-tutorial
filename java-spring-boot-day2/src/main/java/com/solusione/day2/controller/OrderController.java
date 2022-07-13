package com.solusione.day2.controller;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.hyvercode.solusione.model.PageRequest;
import com.solusione.day2.model.request.order.OrderRequest;
import com.solusione.day2.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllOrders(OrderRequest request){
        return orderService.all(request);
    }

    @GetMapping(value = "/paginate", produces = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse getPaginateOrders(PageRequest request){
        return orderService.paginate(request);
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse createOrders(@RequestBody OrderRequest orderRequest){
        return orderService.create(orderRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse updateOrders(@PathVariable String id,@RequestBody OrderRequest orderRequest){
        return orderService.update(id,orderRequest);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse readOrders(@PathVariable String id){
        return orderService.read(id);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse deleteOrders(@PathVariable String id){
        return orderService.delete(id);
    }
}
