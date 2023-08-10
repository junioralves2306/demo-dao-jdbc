import java.util.Date;

import dao.DaoFactory;
import dao.VendedorDao;
import entidades.Departamento;
import entidades.Vendedor;

public class App {
    public static void main(String[] args) throws Exception {
        Departamento obj = new Departamento(1, "Books");

        Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 
        3000.0, obj);

        VendedorDao vendedorDao = DaoFactory.criarVendedorDao();

        System.out.println(vendedor);
    }
}
