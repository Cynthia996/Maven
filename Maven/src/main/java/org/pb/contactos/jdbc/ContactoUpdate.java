package org.pb.contactos.jdbc;

import org.pb.contactos.jdbc.modelo.Contacto;
import org.pb.contactos.jdbc.repositorio.ContactoRepositorio;
import org.pb.contactos.jdbc.repositorio.ContactoRepositoriolmpl;

import java.sql.SQLException;
import java.util.Scanner;
public class ContactoUpdate {
    public static void main(String[] args) {
        ContactoRepositorio<Contacto> repositorio = new ContactoRepositoriolmpl();
        System.out.println("================ Contactos ================");

        repositorio.listar().forEach(c -> System.out.println(c.getId()+"|"+c.getNombre()+"|"+c.getApellido()+"|"+c.getTelefono()+"|"+c.getEmail()));

        System.out.println("--------------------------------------------");



        System.out.println("============= Actualizar Contacto =============");
        Contacto contacto = new Contacto();

        Scanner id_ = new Scanner(System.in);   //ingrsar datos por consola
        System.out.println("Posicion del cantacto '(id)'");
        String A = id_.nextLine();
        contacto.setId(Integer.valueOf(A));


        Scanner Datos = new Scanner(System.in);
        System.out.println("Nuevo Nombre");
        String nombre = Datos.nextLine();

        System.out.println("Nuevo Apellido");
        String apellido = Datos.nextLine();

        System.out.println("Nuevo Email");
        String email = Datos.nextLine();

        System.out.println("Nuevo Telefono");
        String telefono = Datos.nextLine();
        Datos.close();



        contacto.setNombre(nombre); //inserta datos ingresados por consola
        contacto.setApellido(apellido);
        contacto.setTelefono(telefono);
        contacto.setEmail(email);
        repositorio.guardar(contacto);
        System.out.println("Contacto Actualizado");//mensaje
        repositorio.listar().forEach(c -> System.out.println(c.getId()+"|"
                +c.getNombre()+"|"+c.getApellido()+"|"+c.getTelefono()+"|"+c.getEmail()));//Lista contactos

    }
}
