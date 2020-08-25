package edu.ifes.ci.si.les.csr.controllers;

import edu.ifes.ci.si.les.csr.model.Venda;
import edu.ifes.ci.si.les.csr.services.VendaService;
import edu.ifes.ci.si.les.csr.services.exceptions.ConstraintException;
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
@RequestMapping(value = "/vendas")
public class VendaController {

    @Autowired
    private VendaService service;
    

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Venda>> findAll() {
        List<Venda> collection = service.findAll();
        return ResponseEntity.ok().body((collection));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Venda> find(@PathVariable Integer id) {
        Venda obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

     @RequestMapping(value = "/vendaCliente/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Venda>>findVendaCliente(@PathVariable Integer id) {
        List<Venda> collection = service.findVendaCliente(id);
        return ResponseEntity.ok().body((collection));
    }
    
     @RequestMapping(value = "/vendaVeiculo",method = RequestMethod.GET)
    public ResponseEntity<List<String>>findVendaVeiculoVendido() {
        List<String> collection = service.findVendaVeiculoVendido();
        return ResponseEntity.ok().body((collection));
    }
    
     @RequestMapping(value = "/vendaMelhorFuc",method = RequestMethod.GET)
    public ResponseEntity<List<String>>findVendaMelhorFunc() {
        List<String> collection = service.findVendaMelhorFunc();
        return ResponseEntity.ok().body((collection));
    }
    
    @RequestMapping(value = "/mesAno/{ano}/{mes}",method = RequestMethod.GET)
    public ResponseEntity<List<Venda>>findVendaMesAno(@PathVariable double ano,@PathVariable double mes) {
        List<Venda> collection = service.findVendaMesAno(ano, mes);
        return ResponseEntity.ok().body((collection));
    }
    
     @RequestMapping(value = "/lucroAnoMes/{ano}/{mes}",method = RequestMethod.GET)
    public ResponseEntity<Object>findVendaLucroAnoMes(@PathVariable double ano,@PathVariable double mes) {
        Object collection = service.findVendaLucroAnoMes(ano, mes);
        return ResponseEntity.ok().body((collection));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Venda> insert(@Valid @RequestBody Venda obj, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Venda> update(@Valid @RequestBody Venda obj, BindingResult br) {
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
