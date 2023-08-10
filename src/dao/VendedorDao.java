package dao;

import java.util.List;

import entidades.Vendedor;

public interface VendedorDao {

    void insert(Vendedor obj);
    void update(Vendedor obj);
    void deleteById(Integer id);
    Vendedor finfById(Integer id);
    List<Vendedor> findAll();

}
