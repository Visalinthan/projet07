package com.nnk.springboot.api;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.payload.response.MessageResponse;
import com.nnk.springboot.service.BidListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BidListApi {

    private BidListService bidListService;

    public BidListApi(BidListService bidListService) {
        this.bidListService = bidListService;
    }


    @GetMapping("/bidList")
    public ResponseEntity<List<BidList>> getBid() {
        List<BidList> bidLists = bidListService.list();

        Logger.info("bidList listé !");

        return ResponseEntity.ok().body(bidLists);

    }

    @PostMapping("/bidList")
    public ResponseEntity<MessageResponse> createBid(@Valid @RequestBody BidList bid) {
        bidListService.save(bid);

        Logger.info("La bidList a été ajouté !");

        return ResponseEntity.ok(new MessageResponse("Bid saved !"));
    }

    @PutMapping("/bidList/{id}")
        public ResponseEntity<BidList> updatebidList(@PathVariable("id") int id,@RequestBody BidList bidList) throws  Exception{

        BidList result = bidListService.update(bidList,id);

        Logger.info("La bidList l'id n° "+id +" a été modifié !");

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/bidList/{id}")
    public ResponseEntity<MessageResponse> deleteBid(@PathVariable int id) {
        bidListService.deleteById(id);

        Logger.warn("Bid id "+id+" a été supprimé !");

        return ResponseEntity.ok(new MessageResponse("Bid id "+id+" a été supprimé !"));
    }

}
