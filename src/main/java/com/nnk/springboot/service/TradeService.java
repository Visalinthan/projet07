package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TradeService {

    private TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Recherche une liste d'échange avec la méthode findAll de l'interface tradeRepository
     * @return une liste trade
     */
    public List<Trade> list(){
        return this.tradeRepository.findAll();
    }

    /**
     * Recherche une trade en fonction de l'id dans le paramètre avec la méthode findAll de tradeRepository
     * @param id
     * @return trade
     */
    public Optional<Trade> findById(Integer id){return this.tradeRepository.findById(id);}

    /**
     * Sauvergarde d'une trade avec la méthode save de tradeRepository
     * @param trade
     * @return une sauvergade trade
     */
    public Trade save(Trade trade){
        return this.tradeRepository.save(trade);
    }

    /**
     * Modification d'une échange en récupérant les éléménts dans les paramètres avec la méthode save de tradeRepository
     * @param newTrade
     * @param id
     * @return une trade modifié
     */
    public Trade update(Trade newTrade, int id){
        Optional<Trade> tradeFind = this.tradeRepository.findById(newTrade.getId());
        Trade ratingUpdated = null;

        if (tradeFind.isPresent()){
            Trade ratingUpdate = tradeFind.get();

            ratingUpdate.setAccount(newTrade.getAccount());
            ratingUpdate.setType(newTrade.getType());
            ratingUpdate.setBuyQuantity(newTrade.getBuyQuantity());
            ratingUpdate.setSellQuantity(newTrade.getSellQuantity());
            ratingUpdate.setBuyPrice(newTrade.getBuyPrice());
            ratingUpdate.setSellPrice(newTrade.getSellPrice());
            ratingUpdate.setBenchmark(newTrade.getBenchmark());
            ratingUpdate.setTradeDate(newTrade.getTradeDate());
            ratingUpdate.setSecurity(newTrade.getSecurity());
            ratingUpdate.setStatus(newTrade.getStatus());
            ratingUpdate.setTrader(newTrade.getTrader());
            ratingUpdate.setBook(newTrade.getBook());
            ratingUpdate.setCreationName(newTrade.getCreationName());
            ratingUpdate.setCreationDate(newTrade.getCreationDate());
            ratingUpdate.setRevisionName(newTrade.getRevisionName());
            ratingUpdate.setRevisionDate(newTrade.getRevisionDate());
            ratingUpdate.setDealName(newTrade.getDealName());
            ratingUpdate.setDealType(newTrade.getDealType());
            ratingUpdate.setSourceListId(newTrade.getSourceListId());
            ratingUpdate.setSide(newTrade.getSide());

            ratingUpdated = this.tradeRepository.save(ratingUpdate);
        }

        return ratingUpdated;
    }


    /**
     * Suppression d'une échange en récupérant l'id dans le paramètre avec la méthode deleteById de tradeRepository
     * @param id
     */
    public void deleteById(Integer id){
        this.tradeRepository.deleteById(id);
    }
}


