package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {

	@InjectMocks
	private BidListService bidListService;

	@Mock
	private BidListRepository bidListRepository;


	private static BidList getBidList(){
		BidList bid = new BidList();
		bid.setId(1);
		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10.0);
		bid.setAskQuantity(10.0);
		bid.setBid(10.0);
		bid.setAsk(10.0);
		bid.setBenchmark("bench");
		bid.setBidListDate("2022-01-20");
		bid.setCommentary("comment");
		bid.setSecurity("security");
		bid.setStatus("status");
		bid.setTrader("trader");
		bid.setBook("book");
		bid.setCreationName("test");
		bid.setCreationDate("2022-01-20");
		bid.setRevisionName("revision");
		bid.setRevisionDate("2022-01-20");
		bid.setDealName("deal name");
		bid.setDealType("deal type");
		bid.setSourceListId("test");
		bid.setSide("side");

		return bid;
	}

	@Test
	public void save() {

		BidList bid = getBidList();

		when(bidListRepository.save(ArgumentMatchers.any(BidList.class))).thenReturn(bid);

		assertThat(bidListService.save(bid)).isEqualTo(bid);

	}
}
