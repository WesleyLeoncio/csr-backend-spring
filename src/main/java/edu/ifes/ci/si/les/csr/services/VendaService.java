package edu.ifes.ci.si.les.csr.services;

import edu.ifes.ci.si.les.csr.model.Funcionario;
import edu.ifes.ci.si.les.csr.model.Venda;
import edu.ifes.ci.si.les.csr.repositories.VendaRepository;
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
public class VendaService {

    @Autowired
    private VendaRepository repository;
    @Autowired
    private FuncionarioService funcService;
    @Autowired
    private VeiculoService veiculoService;

    public Venda findById(Integer id) {
        try {
            Venda obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Venda.class.getName());
        }
    }

    public List<Venda> findAll() {
        return repository.findAll();
    }
    
    public List<Venda> findVendaMesAno(double ano, double mes) {
        return repository.findVendaMesAno(ano, mes);
    }
    
    public List<String> findVendaVeiculoVendido() {
        return repository.findVendaVeiculoVendido();
    }
    
    public List<String> findVendaMelhorFunc() {
        return repository.findVendaMelhorFunc();
    }
    
    public Object findVendaLucroAnoMes(double ano, double mes) {
        return repository.findVendaLucroAnoMes(ano, mes);
    }
    public List<Venda> findVendaCliente(int id) {
        return repository.findVendaCliente(id);
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW) //Todas as operações de persistência no BD serão realizadas em 
    public Venda insert(Venda obj) {
        obj.setId(null);
        try {
            obj.getVeiculo().setDisponivel(false);
            countVenda(obj.getFuncionario().getId());
            comissaoVenda(obj);
            veiculoService.update(obj.getVeiculo());
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Venda não foi(foram) preenchido(s)");
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Venda update(Venda obj) {
        findById(obj.getId());
        try {
            alterarDados(obj);
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Venda não foi(foram) preenchido(s)");
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Integer id) {
        findById(id);
        try {
            Venda venda = repository.findById(id).get();
            venda.getVeiculo().setDisponivel(true);
            veiculoService.update(venda.getVeiculo());
            removerComissao(venda);
            removerBonus(venda.getFuncionario().getId());
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir essa Venda!");
        }
    }

    // REGRA DE NEGOCIO
    //Regra de Negócio: Comissão de 1% com base no valor do veiculo vendido 
    public void comissaoVenda(Venda obj) {
        if (obj != null) {
            Funcionario func = funcService.findById(obj.getFuncionario().getId());
            double comissao = obj.getValorFinal() * 0.01;
            func.setSalario(func.getSalario() + comissao);
            funcService.update(func);
        }

    }


    public void removerComissao(Venda obj) {
        if (obj != null) {
            Funcionario func = funcService.findById(obj.getFuncionario().getId());
            double comissao = obj.getValorFinal() * 0.01;
            func.setSalario(func.getSalario() - comissao);
            funcService.update(func);
        }
    }

    //Regra de Negócio: Bônus com base na quantidade de veiculos vendidos 
    @Transactional(propagation = Propagation.REQUIRES_NEW) //Todas as operações de persistência no BD serão realizadas em 
    public void countVenda(int id) {
        Funcionario func = funcService.findById(id);
        Integer quantidade = repository.findCountVenda(id);
        if (func != null) {
            switch (quantidade + 1) {
                case 5:
                    func.setSalario(func.getSalario() + 2000);
                    funcService.update(func);
                    break;

                case 10:
                    func.setSalario(func.getSalario() + 4000);
                    funcService.update(func);
                    break;

                case 15:
                    func.setSalario(func.getSalario() + 6000);
                    funcService.update(func);
                    break;
            }
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) //Todas as operações de persistência no BD serão realizadas em 
    public void removerBonus(int id) {
        Funcionario func = funcService.findById(id);
        Integer quantidade = repository.findCountVenda(id);
        if (func != null) {
            switch (quantidade) {
                case 5:
                    func.setSalario(func.getSalario() - 2000);
                    funcService.update(func);
                    break;

                case 10:
                    func.setSalario(func.getSalario() - 4000);
                    funcService.update(func);
                    break;

                case 15:
                    func.setSalario(func.getSalario() - 6000);
                    funcService.update(func);
                    break;
            }
        }

    }
    @Transactional(propagation = Propagation.REQUIRES_NEW) //Todas as operações de persistência no BD serão realizadas em 
    public void alterarDados(Venda venda) {
        double p = 0.05;
        double veiculo = venda.getVeiculo().getValorVenda();
        double desconto = veiculo * p;
        
        if (venda.getCliente().getTipoCliente().equals("COMUM")) {
            venda.setValorFinal(veiculo - desconto);   
            removerComissao(venda);
            venda.setValorFinal(veiculo);
            comissaoVenda(venda);
        } else if (venda.getCliente().getTipoCliente().equals("FUNCIONARIO")) {
            venda.setValorFinal(veiculo);
            removerComissao(venda);
            venda.setValorFinal(veiculo - desconto); 
            comissaoVenda(venda);
        }
    }
    
   

}
