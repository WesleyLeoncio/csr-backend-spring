package edu.ifes.ci.si.les.csr.services;

import edu.ifes.ci.si.les.csr.model.TesteDrive;
import edu.ifes.ci.si.les.csr.repositories.TesteDriveRepository;
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
public class TesteDriveService {

    @Autowired
    private TesteDriveRepository repository;

    public TesteDrive findById(Integer id) {
        try {
            TesteDrive obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + TesteDrive.class.getName());
        }
    }

    public List<TesteDrive> findAll() {
        return repository.findAll();
    }

    public TesteDrive insert(TesteDrive obj) {
        obj.setId(null);
        try {
            if (verificarRegraNegocio(obj)) {
                return repository.save(obj);
            }

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da TesteDrive não foi(foram) preenchido(s)");
        }
        return null;
    }

    public TesteDrive update(TesteDrive obj) {
        findById(obj.getId());
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da TesteDrive não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir o TesteDrive!");
        }
    }

    public Integer findCountTesteDrive(Integer id, Date data) {
        return repository.findCountTesteDrive(id, data);
    }

    //REGRA DE NEGOCIO
    public boolean verificarRegraNegocio(TesteDrive obj) {
        int cont = findCountTesteDrive(obj.getFuncionario().getId(), obj.getDataTeste());
        boolean status;
        if (cont >= 1) {
            status = false;
            throw new BusinessRuleException("O Vendedor já tem um teste drive nesse dia, Escolha outra Data!");
        } else {
            status = true;
        }
        cont = 0;
        return status;
    }

}
