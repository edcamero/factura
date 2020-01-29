/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Cajero;
import java.util.ArrayList;

/**
 *
 * @author blade
 * @param <T>
 */
public interface InterfazDao<T> {
    public boolean registrar(T elemento,Cajero cajero);
    public ArrayList<T> obtener();
    public T buscar(int id);
    public boolean actualizar(T articulo,Cajero cajero);
    public boolean eliminar(int id,Cajero cajero);
}
