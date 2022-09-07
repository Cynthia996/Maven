package org.pb.contactos.jdbc;

import org.pb.contactos.jdbc.modelo.Contacto;
import org.pb.contactos.jdbc.recursosConexion.Conexiondb;
import org.pb.contactos.jdbc.repositorio.ContactoRepositorio;
import org.pb.contactos.jdbc.repositorio.ContactoRepositoriolmpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class EliminarContacto {
    public static void main(String[] args) {

        try(Connection conn = Conexiondb.getInstance()){
            ContactoRepositorio<Contacto> repositorio = new ContactoRepositoriolmpl();

            repositorio.listar().forEach(c -> System.out.println(c.getId()+"|"+c.getNombre()+"|"+c.getApellido()+
                    "|"+c.getTelefono()+"|"+c.getEmail()));

            System.out.println("-------------------------------------------");


            System.out.println("=============Elimina Contacto===============");

            Contacto Eliminarcontacto = new Contacto();
            Scanner id_ = new Scanner(System.in);
            System.out.println("Posicion del cantacto a eliminar (id)"); //ingrsar Id por consola
            int A = id_.nextInt();
            Eliminarcontacto.setId((Integer) A); //asigna el Id

            repositorio.eliminar(Eliminarcontacto.getId()); //Elimina los datos en la posicion del Id
            System.out.println("========Contacto Eliminado=========");
            System.out.println("");
            repositorio.listar().forEach(c -> System.out.println(c.getId()+"|"
                    +c.getNombre()+"|"+c.getApellido()+"|"+c.getTelefono()+"|"+c.getEmail()));//listas de contactos


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
        }
    }
}
