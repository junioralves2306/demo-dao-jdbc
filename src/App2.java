import java.util.List;

import dao.DaoFactory;
import dao.DepartamentoDao;
import entidades.Departamento;

public class App2 {
    public static void main(String[] args) {
        DepartamentoDao departamentoDao = DaoFactory.criDepartamentoDao();

        System.out.println("=== TESTE 1 - Departamento - FindById");
        Departamento departamento = departamentoDao.finfById(2);
        System.out.println(departamento);

        System.out.println("\n=== TESTE 2 - Departamento - FindAll");
        List<Departamento> lista = departamentoDao.findAll();
        for(Departamento x : lista){
            System.out.println(x);
        }

        /* 
        System.out.println("\n=== TESTE 3 - Departamento - Insert");
        departamento = new Departamento(13, "Corno");
        departamentoDao.insert(departamento);
        System.out.println("Insert finalizado! Novo ID: "+departamento.getId()); */

        System.out.println("\n=== TESTE 4 - Departamento - Update");
        departamento = departamentoDao.finfById(6);
        departamento.setNome("Palhaço");
        departamentoDao.update(departamento);
        System.out.println("Update finalizado!");

        System.out.println("\n=== TESTE 5 - Departamento - Delete");
        departamentoDao.deleteById(6);
        System.out.println("Delete finalizado");
    }
}
