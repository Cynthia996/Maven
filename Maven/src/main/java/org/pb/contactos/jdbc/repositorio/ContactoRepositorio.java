package org.pb.contactos.jdbc.repositorio;

import java.util.List;

public interface ContactoRepositorio <T> {
    List<T>listar();

    T porId (int id);

    void guardar (T t); //representa el insert y el update

    void eliminar (Integer id);
}
