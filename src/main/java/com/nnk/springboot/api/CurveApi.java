package com.nnk.springboot.api;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.payload.response.MessageResponse;
import com.nnk.springboot.service.CurvePointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CurveApi {

    CurvePointService curvePointService;

    public CurveApi(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @GetMapping("/curvePoint")
    public ResponseEntity<List<CurvePoint>> getCurve() {
        List<CurvePoint> curvePoints = curvePointService.list();

        Logger.info("curvePoint listé !");

        return ResponseEntity.ok().body(curvePoints);

    }

    @PostMapping("/curvePoint")
    public ResponseEntity<MessageResponse> createCurvePoint(@Valid @RequestBody CurvePoint curvePoint) {
        curvePointService.save(curvePoint);
        return ResponseEntity.ok(new MessageResponse("CurvePoint saved !"));
    }

    @PutMapping("/curvePoint/{id}")
    public ResponseEntity<CurvePoint> updateCurve(@PathVariable("id") int id, @RequestBody CurvePoint curvePoint) throws  Exception{

        CurvePoint result = curvePointService.update(curvePoint,id);

        Logger.info("La curvePoint l'id n° "+id +" a été modifié !");

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/curvePoint/{id}")
    public ResponseEntity<MessageResponse> deleteCurve(@PathVariable int id) {
        curvePointService.deleteById(id);

        Logger.warn("Curve id "+id+" a été supprimé !");

        return ResponseEntity.ok(new MessageResponse("Curve id "+id+" a été supprimé !"));
    }

}
