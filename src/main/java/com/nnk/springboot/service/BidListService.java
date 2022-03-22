package com.nnk.springboot.service;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BidListService {

    private BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository){
        this.bidListRepository = bidListRepository;
    }

    public BidList saveBid(BidList bidList){
        return this.bidListRepository.save(bidList);
    }

    public BidList updateBid(BidList bidList){
        return this.bidListRepository.save(bidList);
    }

    public void deleteBid(BidList bidList){
         this.deleteBid(bidList);
    }
}
