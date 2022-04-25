package com.nnk.springboot.api;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.payload.response.MessageResponse;
import com.nnk.springboot.service.TradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TradeApi {

    private TradeService tradeService;

    public TradeApi(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/trade")
    public ResponseEntity<List<Trade>> getTrade() {
        List<Trade> trades = tradeService.list();

        Logger.info("trade listé !");

        return ResponseEntity.ok().body(trades);

    }

    @PostMapping("/trade")
    public ResponseEntity<MessageResponse> createTrade(@Valid @RequestBody Trade trade) {
        tradeService.save(trade);
        return ResponseEntity.ok(new MessageResponse("trade saved !"));
    }

    @PutMapping("/trade/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable("id") int id,@RequestBody Trade trade) throws  Exception{

        Trade result = tradeService.update(trade,id);

        Logger.info("Trade id n° "+id +" a été modifié !");

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/trade/{id}")
    public ResponseEntity<MessageResponse> deleteTrade(@PathVariable int id) {
        tradeService.deleteById(id);

        Logger.warn("trade id "+id+" a été supprimé !");

        return ResponseEntity.ok(new MessageResponse("trade id "+id+" a été supprimé !"));
    }
}
