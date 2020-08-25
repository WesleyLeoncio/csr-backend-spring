package edu.ifes.ci.si.les.csr.services;

import edu.ifes.ci.si.les.csr.model.Revisao;
import edu.ifes.ci.si.les.csr.repositories.RevisaoRepository;
import edu.ifes.ci.si.les.csr.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.csr.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.csr.services.exceptions.ObjectNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RevisaoService {

    @Autowired
    private RevisaoRepository repository;

    public Revisao findById(Integer id) {
        try {
            Revisao obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Revisao.class.getName());
        }
    }

    public List<Revisao> findAll() {
        return repository.findAll();
    }
    
     public List<Revisao> findRevisaoPendente(Integer id) {
        return repository.findRevisaoPendente(id);
    }
    
    public Revisao insert(Revisao obj) {
        obj.setId(null);
        try {
            if(verificarRegraNegocio(obj)){
                return repository.save(obj);
            }
            
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Revisao não foi(foram) preenchido(s)");
        }
        return null;
    }

    public Revisao update(Revisao obj) {
        findById(obj.getId());
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Revisao não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir o Revisao!");
        }
    }
    
    public Integer findCountRevisao(Integer id, Date data) {
        return repository.findCountRevisao(id, data);
    }

     //REGRA DE NEGOCIO
    public boolean verificarRegraNegocio(Revisao obj) {
        int cont = findCountRevisao(obj.getFuncionario().getId(), obj.getDataRevisao());
        boolean status;
        if (cont >= 3) {
            status = false;
            throw new BusinessRuleException("O Mecânico já tem 3 revisões nesse dia, Escolha outra Data!");
        } else {
            status = true;
        }
        cont = 0;
        return status;
    }
}
