package dao;

import dao.impl.SellerDaoJDBC;

public class DaoFactory {
    
    public static VendedorDao criarVendedorDao(){
        return new SellerDaoJDBC();
    }
}
