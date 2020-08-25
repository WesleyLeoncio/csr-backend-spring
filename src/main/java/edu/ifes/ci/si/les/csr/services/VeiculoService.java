
package edu.ifes.ci.si.les.csr.services;

import edu.ifes.ci.si.les.csr.model.Veiculo;
import edu.ifes.ci.si.les.csr.repositories.VeiculoRepository;
import edu.ifes.ci.si.les.csr.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.csr.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository repository;

    public Veiculo findById(Integer id) {
        try {
            Veiculo obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Veiculo.class.getName());
        }
    }

    public List<Veiculo> findAll() {
        return repository.findAll();
    }
    
    public List<Veiculo> findVeiculoDisponivel() {
        return repository.findVeiculoDisponivel();
    }
    
    public List<Veiculo> findVeiculoModelo(Integer id) {
        return repository.findVeiculoModelo(id);
    }
    
     public List<Veiculo> findVeiculoMarca(Integer id) {
        return repository.findVeiculoMarca(id);
    }
    
     public List<Veiculo> findVeiculoCliente(Integer id) {
        return repository.findVeiculoCliente(id);
    } 
     
    public Veiculo insert(Veiculo obj) {
        obj.setId(null);
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Veiculo não foi(foram) preenchido(s)");
        }
    }

    public Veiculo update(Veiculo obj) {
        findById(obj.getId());
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Veiculo não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esse Veiculo!");
        }
    }

}
