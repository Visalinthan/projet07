package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
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
public class CurvePointServiceTest {

	@InjectMocks
	private CurvePointService curvePointService;

	@Mock
	private CurvePointRepository curvePointRepository;


	private static CurvePoint getCurvePoint(){
		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setId(1);
		curvePoint.setValue(2.0);


		return curvePoint;
	}

	@Test
	public void list(){
		List<CurvePoint> curvePoints = new ArrayList<>();
		curvePoints.add(getCurvePoint());

		when(curvePointRepository.findAll()).thenReturn(curvePoints);

		assertThat(curvePointService.list().get(0).getId()).isEqualTo(getCurvePoint().getId());
	}

	@Test
	public void save() {

		CurvePoint curvePoint = getCurvePoint();

		when(curvePointRepository.save(ArgumentMatchers.any(CurvePoint.class))).thenReturn(curvePoint);

		assertThat(curvePointService.save(curvePoint)).isEqualTo(curvePoint);

	}

	@Test
	public void update(){
		CurvePoint curvePoint = getCurvePoint();

		when(curvePointRepository.findById(curvePoint.getId())).thenReturn(Optional.of(getCurvePoint()));
		when(curvePointRepository.save(ArgumentMatchers.any(CurvePoint.class))).thenReturn(curvePoint);

		assertThat(curvePointService.update(curvePoint, curvePoint.getId())).isEqualTo(curvePoint);

	}

	@Test
	public void delete() {
		CurvePoint curvePoint = getCurvePoint();

		curvePointService.deleteById(curvePoint.getId());

		verify(curvePointRepository, times(1)).deleteById(curvePoint.getId());
	}
}
