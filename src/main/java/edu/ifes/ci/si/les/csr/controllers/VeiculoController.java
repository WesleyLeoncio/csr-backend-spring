package edu.ifes.ci.si.les.csr.controllers;

import edu.ifes.ci.si.les.csr.model.Veiculo;
import edu.ifes.ci.si.les.csr.services.VeiculoService;
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
@RequestMapping(value = "/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> collection = service.findAll();
        return ResponseEntity.ok().body((collection));
    }
    
    @RequestMapping(value = "/veiculoDisponivel", method = RequestMethod.GET)
    public ResponseEntity<List<Veiculo>> findVeiculoDisponivel() {
        List<Veiculo> collection = service.findVeiculoDisponivel();
        return ResponseEntity.ok().body((collection));
    }
    
    @RequestMapping(value = "/veiculoModelo/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Veiculo>> findVeiculoModelo(@PathVariable Integer id) {
        List<Veiculo> collection = service.findVeiculoModelo(id);
        return ResponseEntity.ok().body((collection));
    }
    
    @RequestMapping(value = "/veiculoMarca/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Veiculo>> findVeiculoMarca(@PathVariable Integer id) {
        List<Veiculo> collection = service.findVeiculoMarca(id);
        return ResponseEntity.ok().body((collection));
    }
    
    @RequestMapping(value = "/veiculoCliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Veiculo>> findVeiculoCliente(@PathVariable Integer id) {
        List<Veiculo> collection = service.findVeiculoCliente(id);
        return ResponseEntity.ok().body((collection));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Veiculo> find(@PathVariable Integer id) {
        Veiculo obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Veiculo> insert(@Valid @RequestBody Veiculo obj, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Veiculo> update(@Valid @RequestBody Veiculo obj, BindingResult br) {
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
