package edu.ifes.ci.si.les.csr.controllers;

import edu.ifes.ci.si.les.csr.model.TesteDrive;
import edu.ifes.ci.si.les.csr.services.TesteDriveService;
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
@RequestMapping(value = "/testeDrives")
public class TesteDriveController {

    @Autowired
    private TesteDriveService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TesteDrive>> findAll() {
        List<TesteDrive> collection = service.findAll();
        return ResponseEntity.ok().body((collection));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TesteDrive> find(@PathVariable Integer id) {
        TesteDrive obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TesteDrive> insert(@Valid @RequestBody TesteDrive obj, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TesteDrive> update(@Valid @RequestBody TesteDrive obj, BindingResult br) {
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
