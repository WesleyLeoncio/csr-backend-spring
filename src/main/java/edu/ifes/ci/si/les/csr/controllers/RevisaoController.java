package edu.ifes.ci.si.les.csr.controllers;

import edu.ifes.ci.si.les.csr.model.Revisao;
import edu.ifes.ci.si.les.csr.services.RevisaoService;
import edu.ifes.ci.si.les.csr.services.exceptions.ConstraintException;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/revisao")
public class RevisaoController {

    @Autowired
    private RevisaoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Revisao>> findAll() {
        List<Revisao> collection = service.findAll();
        return ResponseEntity.ok().body((collection));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Revisao> find(@PathVariable Integer id) {
        Revisao obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/revisaoPendente/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Revisao>> findRevisaoPendente(@PathVariable Integer id) {
        List<Revisao> collection = service.findRevisaoPendente(id);
        return ResponseEntity.ok().body((collection));
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Revisao> insert(@Valid @RequestBody Revisao obj, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Revisao> update(@Valid @RequestBody Revisao obj, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
