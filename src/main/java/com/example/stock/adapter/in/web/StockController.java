package com.example.stock.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.application.usecase.ReservationCancelUseCase;
import com.example.stock.application.usecase.ReserveStockUseCase;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final ReserveStockUseCase reserveStockUseCase;
    private final ReservationCancelUseCase reservationCancelUseCase;

    public StockController(ReserveStockUseCase reserveStockUseCase, ReservationCancelUseCase reservationCancelUseCase) {
        this.reserveStockUseCase = reserveStockUseCase;
        this.reservationCancelUseCase = reservationCancelUseCase;
    }

    @PostMapping("/reserve")
    public void reserve(@RequestBody ReserveRequest req) {
        reserveStockUseCase.execute(req.productId(), req.quantity());
    }

    @PostMapping("/cancel")
    public void cancel(@RequestBody CancelRequest req) {
        reservationCancelUseCase.execute(req.reservationId(), req.productId());
    }
}
