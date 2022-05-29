package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

	@InjectMocks
	private TradeService tradeService;

	@Mock
	private TradeRepository tradeRepository;


	private static Trade getTrade(){
		Trade trade = new Trade();
		trade.setId(1);
		trade.setSide("test");
		return trade;
	}

	/**
	 * Test de méthode findAll de l'interface tradeRepository et méthode list de la class tradeService
	 */
	@Test
	public void list(){
		List<Trade> trades = new ArrayList<>();
		trades.add(getTrade());

		when(tradeRepository.findAll()).thenReturn(trades);

		assertThat(tradeService.list().get(0).getId()).isEqualTo(getTrade().getId());
	}

	/**
	 * Test de méthode save de l'interface tradeRepository et méthode save de la class tradeService
	 */
	@Test
	public void save() {

		Trade trade = getTrade();

		when(tradeRepository.save(ArgumentMatchers.any(Trade.class))).thenReturn(trade);

		assertThat(tradeService.save(trade)).isEqualTo(trade);

	}

	/**
	 * Test de méthode update de l'interface tradeRepository et méthode save de la class tradeService
	 */
	@Test
	public void update(){
		Trade trade = getTrade();

		when(tradeRepository.findById(trade.getId())).thenReturn(Optional.of(getTrade()));
		when(tradeRepository.save(ArgumentMatchers.any(Trade.class))).thenReturn(trade);

		assertThat(tradeService.update(trade, trade.getId())).isEqualTo(trade);

	}

	/**
	 * Test de méthode deleteById de l'interface tradeRepository et méthode deleteById de la class tradeService
	 */
	@Test
	public void delete() {
		Trade trade = getTrade();

		tradeService.deleteById(trade.getId());

		verify(tradeRepository, times(1)).deleteById(trade.getId());
	}
}
