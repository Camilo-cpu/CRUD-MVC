/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.PersonaDAO;
import vista.Vista;

/**
 *
 * @author Home
 */
public class Controlador implements ActionListener {

    PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Vista v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnOk.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        limpiarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnGuardar) {
            agregar();
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "debes seleccionar una fila");
            } else {
                String nom = (String) vista.tabla.getValueAt(fila, 0);
                String ape = (String) vista.tabla.getValueAt(fila, 1);
                String correo = (String) vista.tabla.getValueAt(fila, 2);
                String contra = (String) vista.tabla.getValueAt(fila, 3);
                vista.txtNombre.setText(nom);
                vista.txtApellido.setText(ape);
                vista.txtCorreo.setText(correo);
                vista.txtContraseña.setText(contra);

            }
        }
        if (e.getSource() == vista.btnOk) {
            actualizar();
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnEliminar) {
            delete();
            listar(vista.tabla);
            nuevo();
            limpiarTabla();
        }
    }

    void nuevo() {
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.txtCorreo.setText("");
        vista.txtContraseña.setText("");
        vista.txtNombre.requestFocus();
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void actualizar() {
        String nom = vista.txtNombre.getText();
        String ape = vista.txtApellido.getText();
        String correo = vista.txtCorreo.getText();
        String contra = vista.txtContraseña.getText();
        p.setNom(nom);
        p.setApe(ape);
        p.setCorreo(correo);
        p.setContra(contra);
        int r = dao.Actualizar(p);
        if (r == 0) {
            JOptionPane.showMessageDialog(vista, "Usuario Actualizado");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }

    public void agregar() {
        String nom = vista.txtNombre.getText();
        String ape = vista.txtApellido.getText();
        String correo = vista.txtCorreo.getText();
        String contra = vista.txtContraseña.getText();
        p.setNom(nom);
        p.setApe(ape);
        p.setCorreo(correo);
        p.setContra(contra);
        int r = dao.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Agregado Correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar el usuario");
        }
    }

    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Persona> lista = dao.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getNom();
            object[1] = lista.get(i).getApe();
            object[2] = lista.get(i).getCorreo();
            object[3] = lista.get(i).getContra();
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }

    public void delete() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un usuario");
        } else {
            String nom = (String) vista.tabla.getValueAt(fila, 0);
            dao.delete(nom);
            System.out.println("El Resultado es" + nom);
            JOptionPane.showMessageDialog(vista, "usuario eliminado");
        }
        limpiarTabla();
    }

}
