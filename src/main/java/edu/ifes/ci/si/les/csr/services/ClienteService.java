package edu.ifes.ci.si.les.csr.services;

import edu.ifes.ci.si.les.csr.model.Cliente;
import edu.ifes.ci.si.les.csr.repositories.ClienteRepository;
import edu.ifes.ci.si.les.csr.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.csr.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id) {
        try {
            Cliente obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
        }
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }
    
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Cliente não foi(foram) preenchido(s)");
        }
    }

    public Cliente update(Cliente obj) {
        findById(obj.getId());
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Cliente não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente que estejá em uma venda!");
        }
    }
    
    public List<Cliente> findTipoCliente(String tipo_cliente) {
        return repository.findTipoCliente(tipo_cliente); 
    }
    
    public List<Cliente> findClienteComVeiculo() {
        return repository.findClienteComVeiculo(); 
    }
}
