package edu.ifes.ci.si.les.csr.services;

import edu.ifes.ci.si.les.csr.model.Cliente;
import edu.ifes.ci.si.les.csr.model.Compra;
import edu.ifes.ci.si.les.csr.model.Funcionario;
import edu.ifes.ci.si.les.csr.model.Marca;
import edu.ifes.ci.si.les.csr.model.Modelo;
import edu.ifes.ci.si.les.csr.model.Revisao;
import edu.ifes.ci.si.les.csr.model.TesteDrive;
import edu.ifes.ci.si.les.csr.model.Veiculo;
import edu.ifes.ci.si.les.csr.model.Venda;
import edu.ifes.ci.si.les.csr.repositories.ClienteRepository;
import edu.ifes.ci.si.les.csr.repositories.CompraRepository;
import edu.ifes.ci.si.les.csr.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.csr.repositories.MarcaRepository;
import edu.ifes.ci.si.les.csr.repositories.ModeloRepository;
import edu.ifes.ci.si.les.csr.repositories.RevisaoRepository;
import edu.ifes.ci.si.les.csr.repositories.TesteDriveRepository;
import edu.ifes.ci.si.les.csr.repositories.VeiculoRepository;
import edu.ifes.ci.si.les.csr.repositories.VendaRepository;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class _DBService {

    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    CompraRepository  compraRepository;
    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    TesteDriveRepository testeDriveRepository;
    @Autowired
    RevisaoRepository revisaoRepository;
    @Autowired
    CompraService compraService;
    
    public void instantiateTestDatabase()throws ParseException, IOException {

        // Instanciando os objetos de modelo
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy"); 
        Date data = formato.parse("10-07-2020");
        /////////// Marca ////////////////////////////////
        Marca mc1 = new Marca(null, "BMW");
        Marca mc2 = new Marca(null, "FERRARI");
        Marca mc3 = new Marca(null, "LAMBORGHINI");

        marcaRepository.saveAll(Arrays.asList(mc1, mc2, mc3));
        ///////////////////////////////////////////////////
        
        /////////// Modelo ////////////////////////////////
        Modelo mo1 = new Modelo(null,"BMW I8",mc1);
        Modelo mo2 = new Modelo(null,"488 SPIDER",mc2);
        Modelo mo3 = new Modelo(null,"AVENTADOR",mc3);
        Modelo mo4 = new Modelo(null,"M 850I",mc1);
        Modelo mo5 = new Modelo(null,"488 PISTA",mc2);
        Modelo mo6 = new Modelo(null,"HURACÁN",mc3);

        modeloRepository.saveAll(Arrays.asList(mo1, mo2,mo3,mo4,mo5,mo6));
        ///////////////////////////////////////////////////
        
        /////////// Veiculo ///////////////////////////////
        String chass1 = "BW000000000000000";
        String chass2 = "FR000000000000000";
        String chass3 = "LB000000000000000";
        String chass4 = "BW100000000000000";
        String chass5 = "FR100000000000000";
        String chass6 = "LB100000000000000";
        String img1 = "/icons/veiculos/BMW_I8.png";
        String img2 = "/icons/veiculos/FERRARI_488_SPIDER.png";
        String img3 = "/icons/veiculos/LAMBORGHINI_AVENTADOR.png";
        String img4 = "/icons/veiculos/BMW_M_850I.png";
        String img5 = "/icons/veiculos/FERRARI_488_PISTA.png";
        String img6 = "/icons/veiculos/LAMBORGHINI_HURACAN.png";
        
        Veiculo v1 = new Veiculo(null, chass1,"Gasolina",780000.00,"2020",2,"LARANJA",img1,true,mo1);
        Veiculo v2 = new Veiculo(null, chass2,"Gasolina",3900000.00,"2020",2,"AZUL",img2,true,mo2);
        Veiculo v3 = new Veiculo(null, chass3,"Gasolina",8450000.00,"2020",2,"VERDE",img3,true,mo3);
        
        Veiculo v4 = new Veiculo(null, chass4,"Gasolina",910000.00,"2020",2,"AZUL",img4,true,mo4);
        Veiculo v5 = new Veiculo(null, chass5,"Gasolina",5200000.00,"2020",2,"VERMELHO",img5,true,mo5);
        Veiculo v6 = new Veiculo(null, chass6,"Gasolina",2600000.00,"2020",2,"VERMELHO",img6,true,mo6);
        //veiculoRepository.saveAll(Arrays.asList(v1,v2,v3));
        /////////////////////////////////////////////////////
        
        /////////// Funcionario ///////////////////////////////
        Funcionario f1 = new Funcionario("login1","12345678", "GERENTE",12000.000,null,"Wesley", data,"000.000.000-00","000 00000-0000","00000-000", "ES","CIDADE 1","BAIRRO 1","RUA 1",522);
        Funcionario f2 = new Funcionario("login2","12345678", "MECANICO",3000.000,null,"Geraldo", data,"000.000.000-00","000 00000-0000","00000-000", "ES","CIDADE 1","BAIRRO 2","RUA 2",523);
        Funcionario f3 = new Funcionario("login3","12345678", "VENDEDOR",2000.000,null,"Carlos", data,"000.000.000-00","000 00000-0000","00000-000", "ES","CIDADE 1","BAIRRO 3","RUA 3",524);
        funcionarioRepository.saveAll(Arrays.asList(f1,f2,f3));
        //////////////////////////////////////////////////////////
        
        /////////// Cliente //////////////////////////////////////
        Cliente c1 = new Cliente("COMUM",null,"Neuza",data,"000.000.000-00","000 00000-0000","00000-000", "ES","CIDADE 1","BAIRRO 1","RUA 1",522);
        Cliente c2 = new Cliente("COMUM",null,"Leticia", data,"000.000.000-00","000 00000-0000","00000-000", "ES","CIDADE 1","BAIRRO 1","RUA 1",522);
        Cliente c3 = new Cliente("FUNCIONARIO",null,"Carlos", data,"000.000.000-00","000 00000-0000","00000-000", "ES","CIDADE 1","BAIRRO 3","RUA 3",564);
        clienteRepository.saveAll(Arrays.asList(c1,c2,c3));
        ///////////////////////////////////////////////////////////
        
       ////////// Compra //////////////////////////////////////
       Compra cp1 = new Compra(null,600000.00,data,f1,v1);
       Compra cp2 = new Compra(null,3000000.00,data,f1,v2);
       Compra cp3 = new Compra(null,6500000.00,data,f1,v3);
       Compra cp4 = new Compra(null,700000.00,data,f1,v4);
       Compra cp5 = new Compra(null,4000000.00,data,f1,v5);
       Compra cp6 = new Compra(null,2000000.00,data,f1,v6);
       compraService.insert(cp1);
       compraService.insert(cp2);
       compraService.insert(cp3);
       compraService.insert(cp4);
       compraService.insert(cp5);
       compraService.insert(cp6);
       //////////////////////////////////////////////////////////
       
        /////////// Venda //////////////////////////////////////
        Venda vd1 = new Venda(null,"COMUM",data,780000.000, c2, f3, v2);
        Venda vd2 = new Venda(null,"FUNCIONARIO",data,3900000.000, c3, f3, v1);
        v2.setDisponivel(false);
        v1.setDisponivel(false);
        veiculoRepository.saveAll(Arrays.asList(v1,v2));
        vendaRepository.saveAll(Arrays.asList(vd1,vd2));
        //////////////////////////////////////////////////////////
       
        /*////////// TesteDrive //////////////////////////////////////
        TesteDrive td1 = new TesteDrive(null, data,"10:00",c1, f3, v1);
        TesteDrive td2 = new TesteDrive(null, data,"10:00",c2, f3, v2);
        testeDriveRepository.saveAll(Arrays.asList(td1,td2));
        //////////////////////////////////////////////////////////////*/
        
        /*////////// Revisão //////////////////////////////////////
        Revisao rv1 = new Revisao(null, data,"14:00",false, c3, f2, v1);
        Revisao rv2 = new Revisao(null, data,"14:00",false, c3, f2, v2);
        revisaoRepository.saveAll(Arrays.asList(rv1,rv2));
        /////////////////////////////////////////////////////////*/
    }
}
