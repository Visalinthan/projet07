package com.nnk.springboot.service;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Component
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

    public BidList update(BidList newBid, int id){
        Optional<BidList> bidListFind = this.bidListRepository.findById(id);

        BidList bidUpdated = null;

        if(bidListFind.isPresent()){
            BidList ruleNameUpdate = bidListFind.get();
            ruleNameUpdate.setAccount(newBid.getAccount());
            ruleNameUpdate.setType(newBid.getType());
            ruleNameUpdate.setBidQuantity(newBid.getBidQuantity());
            ruleNameUpdate.setAskQuantity(newBid.getAskQuantity());
            ruleNameUpdate.setBid(newBid.getBid());
            ruleNameUpdate.setAsk(newBid.getAsk());
            ruleNameUpdate.setBenchmark(newBid.getBenchmark());
            ruleNameUpdate.setruleNameDate(newBid.getruleNameDate());
            ruleNameUpdate.setCommentary(newBid.getCommentary());
            ruleNameUpdate.setSecurity(newBid.getSecurity());
            ruleNameUpdate.setStatus(newBid.getStatus());
            ruleNameUpdate.setTrader(newBid.getTrader());
            ruleNameUpdate.setBook(newBid.getBook());
            ruleNameUpdate.setCreationName(newBid.getCreationName());
            ruleNameUpdate.setCreationDate(newBid.getCreationDate());
            ruleNameUpdate.setRevisionName(newBid.getRevisionName());
            ruleNameUpdate.setRevisionDate(newBid.getRevisionDate());
            ruleNameUpdate.setDealName(newBid.getDealName());
            ruleNameUpdate.setDealType(newBid.getDealType());
            ruleNameUpdate.setSourceListId(newBid.getSourceListId());
            ruleNameUpdate.setSide(newBid.getSide());

            bidUpdated = bidListRepository.save(ruleNameUpdate);
        }

        return bidUpdated;
    }

    public void deleteById(Integer id){
         this.bidListRepository.deleteById(id);
    }
}
