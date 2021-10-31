/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        List<Persona> datos = new ArrayList<>();
        String sql = "select * from persona";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setNom(rs.getString(1));
                p.setApe(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setContra(rs.getString(4));
                datos.add(p);
            }
        } catch (Exception e) {

        }
        return datos;

    }

    public int agregar(Persona per) {
        String sql = "INSERT INTO `persona` (`nombre`, `apellido`, `correo`, `contraseña`) VALUES (?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getNom());
            ps.setString(2, per.getApe());
            ps.setString(3, per.getCorreo());
            ps.setString(4, per.getContra());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }

    public int Actualizar(Persona per) {
        int r = 0;
        String sql = "update persona set nombre=?,apellido=?,correo=?,contraseña=? where 1";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getNom());
            ps.setString(2, per.getApe());
            ps.setString(3, per.getCorreo());
            ps.setString(4, per.getContra());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return r;
    }

    public int delete(String nom) {
        int r = 0;
        String sql = "delete from persona where nombre=" + nom;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}
