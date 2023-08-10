package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.VendedorDao;
import db.DB;
import db.DbException;
import entidades.Departamento;
import entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendedorDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Vendedor obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Vendedor finfById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
            "SELECT seller.*,department.Name as DepName "
            +"FROM seller INNER JOIN department "
            +"ON seller.DepartmentId = department.Id "
            +"WHERE seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            //IF para verificar se a consulta retornou alguma coisa
            if(rs.next()){
                Departamento dep = instanciarDepartamento(rs);

                Vendedor obj = instanciarVendedor(rs, dep);
                return obj;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("Id"));
        obj.setNome(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setSalarioBase(rs.getDouble("BaseSalary"));
        obj.setDataNascimento(rs.getDate("BirthDate"));
        obj.setDepartamento(dep);
        return obj;
    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNome(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Vendedor> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName "
                +"FROM seller INNER JOIN department "
                +"ON seller.DepartmentId = department.Id "
                +"ORDER BY Name");

            rs = st.executeQuery();

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while(rs.next()){

                /* Foi criado um Map para armazenar os departamentos e evitar repetição na memória.
                 * o map.get verifica se já existe um departamento com o ID atual armazenado e salva em dep.
                 * Caso não existe, o departamento é instanciado e armazenado no map, dessa forma na próxima
                 * execução ele já estará armazenado.
                 */
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                if(dep==null){
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Vendedor obj = instanciarVendedor(rs, dep);
                lista.add(obj);
                
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> finByDepartment(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName "
                +"FROM seller INNER JOIN department "
                +"ON seller.DepartmentId = department.Id "
                +"WHERE DepartmentId = ? "
                +"ORDER BY Name");

            st.setInt(1, departamento.getId());
            rs = st.executeQuery();

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while(rs.next()){

                /* Foi criado um Map para armazenar os departamentos e evitar repetição na memória.
                 * o map.get verifica se já existe um departamento com o ID atual armazenado e salva em dep.
                 * Caso não existe, o departamento é instanciado e armazenado no map, dessa forma na próxima
                 * execução ele já estará armazenado.
                 */
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                if(dep==null){
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Vendedor obj = instanciarVendedor(rs, dep);
                lista.add(obj);
                
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
}
