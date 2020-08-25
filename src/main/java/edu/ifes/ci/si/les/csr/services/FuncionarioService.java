package edu.ifes.ci.si.les.csr.services;

import edu.ifes.ci.si.les.csr.model.Funcionario;
import edu.ifes.ci.si.les.csr.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.csr.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.csr.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.csr.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public Funcionario findById(Integer id) {
        try {
            Funcionario obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName());
        }
    }

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Funcionario insert(Funcionario obj) {
        obj.setId(null);
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Funcionario não foi(foram) preenchido(s)");
        }
    }

    public Funcionario update(Funcionario obj) {
        findById(obj.getId());
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Funcionario não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Funcionario que estejá vinculado a uma ou mais operação!");
        }
    }

    public Funcionario findVerificarLogin(String login, String senha) {
        Funcionario obj = repository.findVerificarLogin(login, senha);
        if(obj == null){
            throw new BusinessRuleException("Login ou Senha Incorretos!");
        }
        return obj;
    }

}
