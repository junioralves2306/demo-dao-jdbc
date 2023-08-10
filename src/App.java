import java.util.Date;
import java.util.List;
import dao.DaoFactory;
import dao.VendedorDao;
import entidades.Departamento;
import entidades.Vendedor;

public class App {
    public static void main(String[] args) throws Exception {
        VendedorDao vendedorDao = DaoFactory.criarVendedorDao();

        System.out.println("=== TEST 1: Vendedor findById ===");
        Vendedor vendedor = vendedorDao.finfById(3);
        System.out.println(vendedor);

        System.out.println("\n=== TEST 2: Vendedor findByDepartment ===");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> lista = vendedorDao.finByDepartment(departamento);
        for(Vendedor obj : lista){
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 3: Vendedor findAll ===");
        lista = vendedorDao.findAll();
        for(Vendedor obj : lista){
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 4: Vendedor Insert ===");
        Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 
        4000.0, departamento);
        vendedorDao.insert(novoVendedor);
        System.out.println("Inserido! Novo ID = "+ novoVendedor.getId());

        System.out.println("\n=== TEST 5: Vendedor Update ===");
        vendedor = vendedorDao.finfById(1);
        vendedor.setNome("Corno Manso");
        vendedorDao.update(vendedor);
        System.out.println("Update finalizado");

        System.out.println("\n=== TEST 6: Vendedor Delete ===");
        vendedorDao.deleteById(8);
        System.out.println("Delete completo");
    }
}
