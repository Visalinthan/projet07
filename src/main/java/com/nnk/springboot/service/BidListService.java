package com.nnk.springboot.service;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BidListService {

    private BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository){
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> list(){return this.bidListRepository.findAll();}

    public Optional<BidList> findById(Integer id){return this.bidListRepository.findById(id);}

    public BidList save(BidList bidList){
        return this.bidListRepository.save(bidList);
    }

    public BidList update(BidList newBidList){
        Optional<BidList> bidListFind = this.bidListRepository.findById(newBidList.getId());

        BidList bidListUpdated = null;

        if(bidListFind.isPresent()){
            BidList bidListUpdate = bidListFind.get();
            bidListUpdate.setAccount(newBidList.getAccount());
            bidListUpdate.setType(newBidList.getType());
            bidListUpdate.setBidQuantity(newBidList.getBidQuantity());
            bidListUpdate.setAskQuantity(newBidList.getAskQuantity());
            bidListUpdate.setBid(newBidList.getBid());
            bidListUpdate.setAsk(newBidList.getAsk());
            bidListUpdate.setBenchmark(newBidList.getBenchmark());
            bidListUpdate.setBidListDate(newBidList.getBidListDate());
            bidListUpdate.setCommentary(newBidList.getCommentary());
            bidListUpdate.setSecurity(newBidList.getSecurity());
            bidListUpdate.setStatus(newBidList.getStatus());
            bidListUpdate.setTrader(newBidList.getTrader());
            bidListUpdate.setBook(newBidList.getBook());
            bidListUpdate.setCreationName(newBidList.getCreationName());
            bidListUpdate.setCreationDate(newBidList.getCreationDate());
            bidListUpdate.setRevisionName(newBidList.getRevisionName());
            bidListUpdate.setRevisionDate(newBidList.getRevisionDate());
            bidListUpdate.setDealName(newBidList.getDealName());
            bidListUpdate.setDealType(newBidList.getDealType());
            bidListUpdate.setSourceListId(newBidList.getSourceListId());
            bidListUpdate.setSide(newBidList.getSide());

            bidListUpdated = this.bidListRepository.save(bidListUpdate);
        }

        return bidListUpdated;
    }

    public void deleteById(Integer id){
         this.bidListRepository.deleteById(id);
    }
}
