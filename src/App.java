import java.util.Date;

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
    }
}
