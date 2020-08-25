package edu.ifes.ci.si.les.csr.services;


import edu.ifes.ci.si.les.csr.model.Compra;
import edu.ifes.ci.si.les.csr.repositories.CompraRepository;
import edu.ifes.ci.si.les.csr.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.csr.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CompraService {

    @Autowired
    private CompraRepository repository;
    @Autowired
    private VeiculoService veiculoService;
    

    public Compra findById(Integer id) {
        try {
            Compra obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Compra.class.getName());
        }
    }

    public List<Compra> findAll() {
        return repository.findAll();
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Esta notação tem objetivo de controlar a propagação da transação (garantindo que sejam realizadas todas as modificações no BD, ou nada)
    public Compra insert(Compra obj) {
        obj.setId(null);
        try {
            veiculoService.insert(obj.getVeiculo());
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Compra não foi(foram) preenchido(s)");
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Esta notação tem objetivo de controlar a propagação da transação (garantindo que sejam realizadas todas as modificações no BD, ou nada)
    public Compra update(Compra obj) {   
        try {
            findById(obj.getId());
            veiculoService.update(obj.getVeiculo());
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Compra não foi(foram) preenchido(s)");
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Esta notação tem objetivo de controlar a propagação da transação (garantindo que sejam realizadas todas as modificações no BD, ou nada)
    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
            veiculoService.delete(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir essa Compra!");
        }
    }
    
  

   
}
