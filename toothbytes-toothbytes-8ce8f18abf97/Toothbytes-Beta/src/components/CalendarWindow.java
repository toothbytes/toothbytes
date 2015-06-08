/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import models.Appointment;
import utilities.DBAccess;
import window.forms.SetAppointment;

/**
 *
 * @author Ecchi Powa
 */
public class CalendarWindow extends ModuleWindow {

    /**
     * Creates new form Calendar
     */
    
    
    private Calendar now = Calendar.getInstance();
    private int monthMod = now.get(Calendar.MONTH);
    private int yearMod = now.get(Calendar.YEAR);
    private ArrayList<Appointment> appointmentList = DBAccess.getAppointmentData("");
    private ImageIcon teeth = new ImageIcon("res/images/logo_smaller.png");
    
    public CalendarWindow() {
        initComponents();
        
        calendarSetUp();
    }
    
    public void calendarSetUp(){
        
        if(monthMod > 11){
           yearMod = yearMod + 1;
            now.set(Calendar.YEAR, yearMod);
            now.set(Calendar.MONTH, 0);
            monthMod = 0;
        } else if (monthMod < -1){
            yearMod = yearMod - 1;
            now.set(Calendar.YEAR, yearMod);
            now.set(Calendar.MONTH, 11);
            monthMod = 11;
        } else {
            now.set(Calendar.MONTH, monthMod);
        }
        
        String month = new SimpleDateFormat("MMMM").format(now.getTime());
        cdwMonth.setText(month);
        cdwYear.setText(String.valueOf(now.get(Calendar.YEAR)));
        
        genCalendar();
        
        now.set(Calendar.DAY_OF_MONTH, 1);
        int currDay = now.get(Calendar.DAY_OF_WEEK);
        int weekNo = 0;
        
        try{
            for(int i = 1; i < now.getActualMaximum(Calendar.DAY_OF_MONTH)+1; i++){
                int appNo;
                if(now.get(Calendar.MONTH) < 10){
                    if(i < 10){
                        appNo = calendarInput(now.get(Calendar.YEAR)+"-0"+(now.get(Calendar.MONTH)+1)+"-0"+i);
                    } else {
                        appNo = calendarInput(now.get(Calendar.YEAR)+"-0"+(now.get(Calendar.MONTH)+1)+"-"+i);
                    }
                } else {
                    if(i < 10){
                        appNo = calendarInput(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-0"+i);
                    } else {
                        appNo = calendarInput(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+i);
                    }
                }
                
                if(appNo > 0){
                    cdwCalendar.getModel().setValueAt(i + "," +appNo, weekNo, currDay-1);
                } else {
                    cdwCalendar.getModel().setValueAt(i, weekNo, currDay-1);
                }
                currDay++;
                if(currDay > 7){
                    currDay = currDay - 7;
                    weekNo++;
                }                
            }
        }catch(Exception e){
            System.out.println("CalendarWindow - calendarSetUp Error: " + e);
        }
    }
    
    public void genCalendar(){
        cdwCalendar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            
            
        });
        
//        for(int i = 0; i < 6; i++){
//            cdwCalendar.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer());
//        }
        
        cdwCalendar.setRowHeight(408/6);
        cdwCalendar.setAutoscrolls(false);
        cdwCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    class ImageRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,boolean hasFocus, int row, int column){
            JLabel label = new JLabel();
            JLabel image = new JLabel(teeth);
            try{
                String[] input = String.valueOf(value).split(",");
                teeth.setDescription(input[1]);
                label = new JLabel(input[0] ,JLabel.LEFT);
                label.add(image);
            }catch(Exception e){
                label.setText(String.valueOf(value));
            }
            
            return label;
        }
    }
    
    public int calendarInput(String date){
        int appointmentCount = 0;
        
        for(int i = 0; i < appointmentList.size(); i++){
            if(date.equals(appointmentList.get(i).getAppointmentDate())){
                appointmentCount++;
                System.out.println(appointmentCount);
            }
        }
        
        return appointmentCount;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cdwPrevious = new javax.swing.JButton();
        cdwNext = new javax.swing.JButton();
        cdwMonth = new javax.swing.JLabel();
        cdwYear = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cdwCalendar = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(686, 546));

        jPanel1.setBackground(new java.awt.Color(250, 255, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Appointment Viewer"));

        cdwPrevious.setText("Previous");
        cdwPrevious.setMaximumSize(new java.awt.Dimension(75, 23));
        cdwPrevious.setMinimumSize(new java.awt.Dimension(75, 23));
        cdwPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdwPreviousActionPerformed(evt);
            }
        });

        cdwNext.setText("Next");
        cdwNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdwNextActionPerformed(evt);
            }
        });

        cdwMonth.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        cdwMonth.setText("Month");

        cdwYear.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        cdwYear.setText("Year");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(cdwPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(cdwYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cdwMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(cdwNext, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cdwPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdwNext)
                    .addComponent(cdwMonth)
                    .addComponent(cdwYear))
                .addContainerGap())
        );

        cdwCalendar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cdwCalendar.setAutoscrolls(false);
        cdwCalendar.setCellSelectionEnabled(true);
        cdwCalendar.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cdwCalendar.setRowHeight(68);
        cdwCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cdwCalendarMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(cdwCalendar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cdwPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdwPreviousActionPerformed
        monthMod --;
        calendarSetUp();
    }//GEN-LAST:event_cdwPreviousActionPerformed

    private void cdwNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdwNextActionPerformed
        monthMod ++;
        calendarSetUp();
    }//GEN-LAST:event_cdwNextActionPerformed
 
    private void cdwCalendarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdwCalendarMouseReleased
        int x = cdwCalendar.getSelectedRow();
        int y = cdwCalendar.getSelectedColumn();
        
        
        JPopupMenu calendarPopup = new JPopupMenu("Calendar");
        boolean check = false;
        
        try{
            String value = cdwCalendar.getModel().getValueAt(x, y).toString();
            String[] values = value.split(",");
            String mode = yearMod + "-" + monthMod + "-" + values[0];
            System.out.println(mode);
            check = true;
            
            JMenuItem addAppointment = new JMenuItem("Set Appointment");
            addAppointment.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    java.awt.EventQueue.invokeLater(new Runnable(){
                        public void run(){
                            JDialog nA = new JDialog();
                            SetAppointment sA = new SetAppointment(mode);

                            nA.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                            nA.setPreferredSize(sA.getPreferredSize());
                            nA.add(sA);
                            nA.pack();
                            nA.setVisible(true);
                            nA.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        }
                    });
                }
            });

            calendarPopup.add(addAppointment);
            
            if(values.length > 1){
                ArrayList<Appointment> appointmentDate = getAppointment(formatDate(mode));
                for(int i = 0; i < appointmentDate.size(); i++){
                    JMenuItem editAppointment = new JMenuItem("Edit Appointment: " + appointmentDate.get(i).getAppointmentTime());
                    int spec = i;
                    editAppointment.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent evt){
                            java.awt.EventQueue.invokeLater(new Runnable(){
                                public void run(){
                                    JDialog nA = new JDialog();
                                    SetAppointment sA = new SetAppointment(appointmentDate.get(spec));

                                    nA.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                                    nA.setPreferredSize(sA.getPreferredSize());
                                    nA.add(sA);
                                    nA.pack();
                                    nA.setVisible(true);
                                    nA.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                }
                            });
                        }
                    });
                    calendarPopup.add(editAppointment);
                }
                
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(evt.getButton()== 3 && check){
            calendarPopup.show(evt.getComponent(), evt.getX(), evt.getY());
            calendarPopup.setVisible(true);
        }
        
    }//GEN-LAST:event_cdwCalendarMouseReleased
    
    private String formatDate(String date){
        String[] dateArr = date.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]) + 1;
        int day = Integer.parseInt(dateArr[2]);
        
        if(month < 10){
            if(day < 10){
                date = year+"-0"+month+"-0"+day;
            } else {
                date = year+"-0"+month+"-"+day;
            }
        } else {
            if(day < 10){
                date = year+"-0"+month+"-0"+day;
            } else {
                date = year+"-0"+month+"-"+day;
            }
        }
        return date;
    }
    
    private ArrayList<Appointment> getAppointment(String date){
        ArrayList<Appointment> dateAppointments = new ArrayList<>();
        
        for(int i = 0; i < appointmentList.size(); i++){
            if(date.equals(appointmentList.get(i).getAppointmentDate())){
                dateAppointments.add(appointmentList.get(i));
            }
        }
        
        return dateAppointments;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cdwCalendar;
    private javax.swing.JLabel cdwMonth;
    private javax.swing.JButton cdwNext;
    private javax.swing.JButton cdwPrevious;
    private javax.swing.JLabel cdwYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
