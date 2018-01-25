/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Modelo;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AlumnoTarde
 */
public class Controlador implements ActionListener{
    Vista vista;
    Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.jButtonAgregar.addActionListener(this);
        this.vista.jButtonBuscar.addActionListener(this);
        this.vista.jButtonModificar.addActionListener(this);
        this.vista.jButtonEliminar.addActionListener(this);
        this.vista.jButtonLimpiar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==this.vista.jButtonAgregar){
            if(validar()){
                try{
                    this.modelo.insertar(Integer.parseInt(this.vista.jTextFieldEmpno.getText()),
                            this.vista.jTextFieldEname.getText(), 
                            this.vista.jTextFieldJob.getText(),
                            Integer.parseInt(this.vista.jTextFieldMgr.getText()), 
                            Float.parseFloat(this.vista.jTextFieldSal.getText()), 
                            Float.parseFloat(this.vista.jTextFieldComm.getText()), 
                            Integer.parseInt(this.vista.jTextFieldDeptno.getText()));
                    JOptionPane.showMessageDialog(null, "Insertado con éxito.");
                }catch(SQLException sqle){
                    sqle.printStackTrace();
                }catch(NumberFormatException ne){
                    JOptionPane.showMessageDialog(null, "Error de formato en alguno de los campos numéricos.");
                }
            }else
                JOptionPane.showMessageDialog(null, "Error, campos vacíos.");
        }else if(e.getSource()==this.vista.jButtonBuscar){
            if(!this.vista.jTextFieldEmpno.getText().equals("")){
                try{
                    String[] datos = this.modelo.buscar(Integer.parseInt(this.vista.jTextFieldEmpno.getText()));
                    this.vista.jTextFieldEname.setText(datos[0]);
                    this.vista.jTextFieldJob.setText(datos[1]);
                    this.vista.jTextFieldMgr.setText(datos[2]);
                    this.vista.jTextFieldSal.setText(datos[3]);
                    this.vista.jTextFieldComm.setText(datos[4]);
                    this.vista.jTextFieldDeptno.setText(datos[5]);
                }catch(SQLException sqle){
                    sqle.printStackTrace();
                }catch(NumberFormatException ne){
                    JOptionPane.showMessageDialog(null, "Error de formato en el código del empleado al buscar.");
                }
            }
        }else if(e.getSource()==this.vista.jButtonModificar){
            if(validar()){
                try{
                    this.modelo.modificar(Integer.parseInt(this.vista.jTextFieldEmpno.getText()),
                            this.vista.jTextFieldEname.getText(), 
                            this.vista.jTextFieldJob.getText(),
                            Integer.parseInt(this.vista.jTextFieldMgr.getText()), 
                            Float.parseFloat(this.vista.jTextFieldSal.getText()), 
                            Float.parseFloat(this.vista.jTextFieldComm.getText()), 
                            Integer.parseInt(this.vista.jTextFieldDeptno.getText()));
                    JOptionPane.showMessageDialog(null, "Modificado con éxito.");
                }catch(SQLException sqle){
                    sqle.printStackTrace();
                }catch(NumberFormatException ne){
                    JOptionPane.showMessageDialog(null, "Error de formato en alguno de los campos numéricos.");
                }
            }
        }else if(e.getSource()==this.vista.jButtonEliminar){
            if(!this.vista.jTextFieldEmpno.getText().equals("")){
                try{
                    this.modelo.eliminar(Integer.parseInt(this.vista.jTextFieldEmpno.getText()));
                    JOptionPane.showMessageDialog(null, "Eliminado con éxito.");
                }catch(SQLException sqle){
                    sqle.printStackTrace();
                }catch(NumberFormatException ne){
                    JOptionPane.showMessageDialog(null, "Error de formato en el código del empleado al buscar.");
                }
            }
        }else if(e.getSource()==this.vista.jButtonLimpiar){
            this.vista.jTextFieldEmpno.setText("");
            this.vista.jTextFieldEname.setText("");
            this.vista.jTextFieldJob.setText("");
            this.vista.jTextFieldMgr.setText("");
            this.vista.jTextFieldSal.setText("");
            this.vista.jTextFieldComm.setText("");
            this.vista.jTextFieldDeptno.setText("");
        }
    }
    
    public boolean validar(){
        if(!this.vista.jTextFieldEmpno.getText().equals("") && !this.vista.jTextFieldEname.getText().equals("") && 
                !this.vista.jTextFieldJob.getText().equals("") && !this.vista.jTextFieldMgr.getText().equals("") && 
                !this.vista.jTextFieldSal.getText().equals("") && !this.vista.jTextFieldComm.getText().equals("") && 
                !this.vista.jTextFieldDeptno.getText().equals(""))
            return true;
        else
            return false;
    }
}
