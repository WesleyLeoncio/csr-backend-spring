package edu.ifes.ci.si.les.csr.services;

import edu.ifes.ci.si.les.csr.model.Marca;
import edu.ifes.ci.si.les.csr.model.Modelo;
import edu.ifes.ci.si.les.csr.repositories.ModeloRepository;
import edu.ifes.ci.si.les.csr.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.csr.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository repository;

    public Modelo findById(Integer id) {
        try {
            Modelo obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Modelo.class.getName());
        }
    }

    public List<Modelo> findAll() {
        return repository.findAll();
    }
    
     public List<Modelo> findByMarca(Marca marca) {
        return repository.findByMarca(marca);
    }

    public Modelo insert(Modelo obj) {
        obj.setId(null);
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Modelo não foi(foram) preenchido(s)");
        }
    }

    public Modelo update(Modelo obj) {
        findById(obj.getId());
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Modelo não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Modelo que possui um Veículo!");
        }
    }
}
