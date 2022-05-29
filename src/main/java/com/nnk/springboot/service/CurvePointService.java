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

    /**
     * Recherche une liste de point de courbe avec la méthode findAll de l'interface curvePointRepository
     * @return une liste curvePoint
     */
    public List<CurvePoint> list(){
        return this.curvePointRepository.findAll();
    }

    /**
     * Recherche un point de courbe en fonction de l'id dans le paramètre avec la méthode findAll de curvePointRepository
     * @param id
     * @return curvePoint
     */
    public Optional<CurvePoint> findById(Integer id){return this.curvePointRepository.findById(id);}

    /**
     * Sauvergarde d'un point de courbe avec la méthode save de curvePointRepository
     * @param curvePoint
     * @return une sauvergade curvePoint
     */
    public CurvePoint save(CurvePoint curvePoint){
        return this.curvePointRepository.save(curvePoint);
    }

    /**
     * Modification d'un point de courbe en récupérant les éléménts dans les paramètres avec la méthode save de curvePointRepository
     * @param newCurvePoint
     * @param id
     * @return un curvePoint modifié
     */
    public CurvePoint update(CurvePoint newCurvePoint, int id){
        Optional<CurvePoint> curvePointFind = this.curvePointRepository.findById(id);
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

    /**
     * Suppression d'un point de courbe en récupérant l'id dans le paramètre avec la méthode deleteById de curvePointRepository
     * @param id
     */
    public void deleteById(Integer id){
        this.curvePointRepository.deleteById(id);
    }
}
