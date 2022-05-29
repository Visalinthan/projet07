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

    /**
     * Recherche une liste d'offre avec la méthode findAll de l'interface bidListRepository
     * @return une liste bidList
     */
    public List<BidList> list(){return this.bidListRepository.findAll();}

    /**
     * Recherche une offre en fonction de l'id dans le paramètre avec la méthode findAll de bidListRepository
     * @param id
     * @return bidList
     */
    public Optional<BidList> findById(Integer id){return this.bidListRepository.findById(id);}

    /**
     * Sauvergarde d'une offre avec la méthode save de bidListRepository
     * @param bidList
     * @return une sauvergade bidList
     */
    public BidList save(BidList bidList){
        return this.bidListRepository.save(bidList);
    }

    /**
     * Modification d'une offre en récupérant les éléménts dans les paramètre avec la méthode save de bidListRepository
     * @param newBid
     * @param id
     * @return une offre modifié
     */
    public BidList update(BidList newBid, int id){
        Optional<BidList> bidListFind = this.bidListRepository.findById(id);

        BidList bidUpdated = null;

        if(bidListFind.isPresent()){
            BidList bidUpdate = bidListFind.get();
            bidUpdate.setAccount(newBid.getAccount());
            bidUpdate.setType(newBid.getType());
            bidUpdate.setBidQuantity(newBid.getBidQuantity());
            bidUpdate.setAskQuantity(newBid.getAskQuantity());
            bidUpdate.setBid(newBid.getBid());
            bidUpdate.setAsk(newBid.getAsk());
            bidUpdate.setBenchmark(newBid.getBenchmark());
            bidUpdate.setBidListDate(newBid.getBidListDate());
            bidUpdate.setCommentary(newBid.getCommentary());
            bidUpdate.setSecurity(newBid.getSecurity());
            bidUpdate.setStatus(newBid.getStatus());
            bidUpdate.setTrader(newBid.getTrader());
            bidUpdate.setBook(newBid.getBook());
            bidUpdate.setCreationName(newBid.getCreationName());
            bidUpdate.setCreationDate(newBid.getCreationDate());
            bidUpdate.setRevisionName(newBid.getRevisionName());
            bidUpdate.setRevisionDate(newBid.getRevisionDate());
            bidUpdate.setDealName(newBid.getDealName());
            bidUpdate.setDealType(newBid.getDealType());
            bidUpdate.setSourceListId(newBid.getSourceListId());
            bidUpdate.setSide(newBid.getSide());

            bidUpdated = bidListRepository.save(bidUpdate);
        }

        return bidUpdated;
    }

    /**
     * Suppression d'une offre en récupérant l'id dans le paramètre avec la méthode deleteById de bidListRepository
     * @param id
     */
    public void deleteById(Integer id){
         this.bidListRepository.deleteById(id);
    }
}
