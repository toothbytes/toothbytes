/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BoxLayout;
import models.Appointment;
import utilities.DBAccess;


/**
 *
 * @author Ecchi Powa
 */
public class CalendarDay extends javax.swing.JPanel {

    /**
     * Creates new form CalendarMiniDay
     */
    private String date;
    private ArrayList<Appointment> appointmentX;
    
    public CalendarDay(String date) throws Exception{
        initComponents();
        MiniHolder.setLayout(new BoxLayout(MiniHolder, BoxLayout.Y_AXIS));
        this.date = date;
        appointmentX = new ArrayList<>();
        appointmentX = DBAccess.getAppointmentData(" WHERE APPOINTMENTDATE = '"+date+"'");
        headerSetup();
        
        for(int i = 0; i < appointmentX.size(); i++){
            MiniHolder.add(new CalendarMiniDay(appointmentX.get(i)));
        }
    }
    
    private void headerSetup() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar dateOf = Calendar.getInstance();
        dateOf.setTime(sdf.parse(date));
        
        dayDateTime.setText(date + " " + new SimpleDateFormat("EE").format(dateOf.getTime()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dayDateTime = new javax.swing.JLabel();
        MiniHolder = new javax.swing.JPanel();

        jLabel1.setText("Day");

        setPreferredSize(new java.awt.Dimension(100, 200));

        dayDateTime.setText("Date // Day");

        MiniHolder.setLayout(new javax.swing.BoxLayout(MiniHolder, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MiniHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(dayDateTime)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(dayDateTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MiniHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MiniHolder;
    private javax.swing.JLabel dayDateTime;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
