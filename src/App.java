import java.util.Date;

import entidades.Departamento;
import entidades.Vendedor;

public class App {
    public static void main(String[] args) throws Exception {
        Departamento obj = new Departamento(1, "Books");

        Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 
        3000.0, obj);

        System.out.println(vendedor);
    }
}
