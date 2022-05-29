package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
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
public class BidListServiceTest {

	@InjectMocks
	private BidListService bidListService;

	@Mock
	private BidListRepository bidListRepository;


	private static BidList getBidList(){
		BidList bid = new BidList();
		bid.setId(1);
		bid.setAccount("Account Test");

		return bid;
	}

	/**
	 * Test de méthode findAll de l'interface bidListRepository et méthode list de la class bidListService
	 */
	@Test
	public void list(){
		List<BidList> bidLists = new ArrayList<>();
		bidLists.add(getBidList());

		when(bidListRepository.findAll()).thenReturn(bidLists);

		assertThat(bidListService.list().get(0).getId()).isEqualTo(getBidList().getId());
	}

	/**
	 * Test de méthode save de l'interface bidListRepository et méthode save de la class bidListService
	 */
	@Test
	public void save() {

		BidList bid = getBidList();

		when(bidListRepository.save(ArgumentMatchers.any(BidList.class))).thenReturn(bid);

		assertThat(bidListService.save(bid)).isEqualTo(bid);

	}

	/**
	 * Test de méthode save de l'interface bidListRepository et méthode update de la class bidListService
	 */
	@Test
	public void update(){
		BidList bidList = getBidList();

		when(bidListRepository.findById(bidList.getId())).thenReturn(Optional.of(getBidList()));
		when(bidListRepository.save(ArgumentMatchers.any(BidList.class))).thenReturn(bidList);

		assertThat(bidListService.update(bidList, bidList.getId())).isEqualTo(bidList);

	}

	/**
	 * Test de méthode deleteById de l'interface bidListRepository et méthode deleteById de la class bidListService
	 */
	@Test
	public void delete() {
		BidList bidList = getBidList();

		bidListService.deleteById(bidList.getId());

		verify(bidListRepository, times(1)).deleteById(bidList.getId());
	}
}
