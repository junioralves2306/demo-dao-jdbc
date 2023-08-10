package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DepartamentoDao;
import db.DB;
import db.DbException;
import entidades.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {

    Connection conn = null;

    public DepartamentoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Departamento obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("INSERT INTO department (Id, Name) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, obj.getId());
            st.setString(2, obj.getNome());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected>0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    obj.setId(rs.getInt(1));
                }
            }
            else{
                throw new DbException("Erro inesperado! Nenhuma linha afetada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Departamento obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE department SET Name=? WHERE Id=?");
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "DELETE FROM department WHERE Id = ?");
                st.setInt(1, id);
                st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Departamento finfById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st =conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()){
                Departamento dep = instanciarDepartamento(rs);
                return dep;
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

    public Departamento instanciarDepartamento(ResultSet rs) throws SQLException{
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("Id"));
        dep.setNome(rs.getString("Name"));
        return dep;
    }

    @Override
    public List<Departamento> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();

            List<Departamento> lista = new ArrayList<>();
            while(rs.next()){
                Departamento dep = instanciarDepartamento(rs);
                lista.add(dep);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
}
