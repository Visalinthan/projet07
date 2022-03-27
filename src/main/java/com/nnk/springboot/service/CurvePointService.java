package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurvePointService {

    private CurvePointRepository curvePointRepository;

    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    public List<CurvePoint> list(){
        return this.curvePointRepository.findAll();
    }

    public Optional<CurvePoint> findById(Integer id){return this.curvePointRepository.findById(id);}

    public CurvePoint save(CurvePoint curvePoint){
        return this.save(curvePoint);
    }

    public CurvePoint update(CurvePoint newCurvePoint){
        Optional<CurvePoint> curvePointFind = this.curvePointRepository.findById(newCurvePoint.getId());
        CurvePoint curvePointUpdated = null;

        if (curvePointFind.isPresent()){
            CurvePoint curvePointUpdate = curvePointFind.get();
            curvePointUpdate.setCurveId(newCurvePoint.getCurveId());
            curvePointUpdate.setAsOfDate(newCurvePoint.getAsOfDate());
            curvePointUpdate.setTerm(newCurvePoint.getTerm());
            curvePointUpdate.setValue(newCurvePoint.getValue());
            curvePointUpdate.setCreationDate(newCurvePoint.getCreationDate());

            curvePointUpdated = this.curvePointRepository.save(curvePointUpdate);
        }

        return curvePointUpdated;
    }

    public void deleteById(Integer id){
        this.curvePointRepository.deleteById(id);
    }
}
