package dao;

import java.util.List;

import entidades.Departamento;

public interface DepartamentoDado {

    void insert(Departamento obj);
    void update(Departamento obj);
    void deleteById(Integer id);
    Departamento finfById(Integer id);
    List<Departamento> findAll();
    
}
