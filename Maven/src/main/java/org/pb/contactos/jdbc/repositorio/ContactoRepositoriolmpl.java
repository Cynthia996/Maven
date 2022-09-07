package org.pb.contactos.jdbc.repositorio;

import org.pb.contactos.jdbc.modelo.Contacto;
import org.pb.contactos.jdbc.recursosConexion.Conexiondb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ContactoRepositoriolmpl implements ContactoRepositorio<Contacto> {
    //Metod provado para la conexion a base de datos

    private Connection getConnection() throws SQLException {
        return Conexiondb.getInstance();
    }

    @Override
    public List<Contacto> listar() {
        List<Contacto> contactos = new ArrayList<>();

        try(Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from contacto")) {
            while (rs.next()){

                Contacto c = crearContacto(rs);
                contactos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactos;
    }

    private Contacto crearContacto(ResultSet rs) throws SQLException {
        Contacto c = new Contacto();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setTelefono(rs.getString("telefono"));
        c.setEmail(rs.getString("email"));
        return c;
    }

    @Override
    public Contacto porId(int id){
        Contacto contacto = null;
        try (PreparedStatement stmt = getConnection().
                prepareStatement("select * from contacto where id=?")){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                contacto =crearContacto(rs);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacto;


    }

    @Override
    public void guardar (Contacto contacto) {
        String sql;
        if (contacto.getId() != null && contacto.getId() > 0) {
            sql = "UPDATE contacto SET nombre=?,apellido=?,telefono=?,email=? where id=?";
        } else {
            sql = "INSERT INTO contacto(nombre,apellido,telefono,email) VALUES(?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, contacto.getNombre());
            stmt.setString(2, contacto.getApellido());
            stmt.setString(3, contacto.getTelefono());
            stmt.setString(4, contacto.getEmail());

            if (contacto.getId() != null && contacto.getId() > 0) {
                stmt.setLong(5, contacto.getId());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void eliminar(Integer id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM contacto where id=?")){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

}
