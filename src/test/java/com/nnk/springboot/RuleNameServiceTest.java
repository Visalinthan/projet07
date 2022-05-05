package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameServiceTest {

	@InjectMocks
	private RuleNameService ruleNameService;

	@Mock
	private RuleNameRepository ruleNameRepository;


	private static RuleName getRuleName(){
		RuleName ruleName = new RuleName();
		ruleName.setId(1);
		ruleName.setSqlPart("test");
		return ruleName;
	}

	@Test
	public void list(){
		List<RuleName> ruleNames = new ArrayList<>();
		ruleNames.add(getRuleName());

		when(ruleNameRepository.findAll()).thenReturn(ruleNames);

		assertThat(ruleNameService.list().get(0).getId()).isEqualTo(getRuleName().getId());
	}

	@Test
	public void save() {

		RuleName ruleName = getRuleName();

		when(ruleNameRepository.save(ArgumentMatchers.any(RuleName.class))).thenReturn(ruleName);

		assertThat(ruleNameService.save(ruleName)).isEqualTo(ruleName);

	}

	@Test
	public void update(){
		RuleName ruleName = getRuleName();

		when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.of(getRuleName()));
		when(ruleNameRepository.save(ArgumentMatchers.any(RuleName.class))).thenReturn(ruleName);

		assertThat(ruleNameService.update(ruleName, ruleName.getId())).isEqualTo(ruleName);

	}

	@Test
	public void delete() {
		RuleName ruleName = getRuleName();

		ruleNameService.deleteById(ruleName.getId());

		verify(ruleNameRepository, times(1)).deleteById(ruleName.getId());
	}
}
