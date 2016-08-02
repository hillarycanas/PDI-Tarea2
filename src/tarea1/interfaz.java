/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static tarea1.Tarea1.contr;

/**
 *
 * @author Hillary
 * 
 * 
 */

/* LISTENERS */
class BoundedChangeListener implements ChangeListener {
    
  public void stateChanged(ChangeEvent changeEvent) {
    Object source = changeEvent.getSource();
    if (source instanceof BoundedRangeModel) {
      BoundedRangeModel aModel = (BoundedRangeModel) source;
      if (!aModel.getValueIsAdjusting()) {
        System.out.println("Changed: " + aModel.getValue());
      }
    } else if (source instanceof JSlider) {
      JSlider theJSlider = (JSlider) source;
      if (!theJSlider.getValueIsAdjusting()) {
        System.out.println("Slider changed: " + theJSlider.getValue());
          try {
              //System.out.println("Source: " + source.toString());
             
                contr.seleccionOpcion(12);
              
          } catch (IOException ex) {
              Logger.getLogger(BoundedChangeListener.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
              Logger.getLogger(BoundedChangeListener.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    } else {
      System.out.println("Something changed: " + source);
    }
  }
}

class BoundedChangeListener2 implements ChangeListener {
    
  public void stateChanged(ChangeEvent changeEvent) {
    Object source = changeEvent.getSource();
    if (source instanceof BoundedRangeModel) {
      BoundedRangeModel aModel = (BoundedRangeModel) source;
      if (!aModel.getValueIsAdjusting()) {
        System.out.println("Changed: " + aModel.getValue());
      }
    } else if (source instanceof JSlider) {
      JSlider theJSlider = (JSlider) source;
      if (!theJSlider.getValueIsAdjusting()) {
        System.out.println("Slider changed: " + theJSlider.getValue());
          try {
              
                  //contraste
                contr.seleccionOpcion(13);
              
              
          } catch (IOException ex) {
              Logger.getLogger(BoundedChangeListener.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
              Logger.getLogger(BoundedChangeListener2.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    } else {
      System.out.println("Something changed: " + source);
    }
  }
}

class BoundedChangeListener3 implements ChangeListener {
    
  public void stateChanged(ChangeEvent changeEvent) {
    Object source = changeEvent.getSource();
    if (source instanceof BoundedRangeModel) {
      BoundedRangeModel aModel = (BoundedRangeModel) source;
      if (!aModel.getValueIsAdjusting()) {
        System.out.println("Changed: " + aModel.getValue());
      }
    } else if (source instanceof JSlider) {
      JSlider theJSlider = (JSlider) source;
      if (!theJSlider.getValueIsAdjusting()) {
        System.out.println("Slider changed: " + theJSlider.getValue());
          try {
              
                
                contr.seleccionOpcion(14);
              
              
          } catch (IOException ex) {
              Logger.getLogger(BoundedChangeListener.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
              Logger.getLogger(BoundedChangeListener3.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    } else {
      System.out.println("Something changed: " + source);
    }
  }
}

class BoundedChangeListener4 implements ChangeListener {
    
  public void stateChanged(ChangeEvent changeEvent) {
    Object source = changeEvent.getSource();
    if (source instanceof BoundedRangeModel) {
      BoundedRangeModel aModel = (BoundedRangeModel) source;
      if (!aModel.getValueIsAdjusting()) {
        System.out.println("Changed: " + aModel.getValue());
      }
    } else if (source instanceof JSlider) {
      JSlider theJSlider = (JSlider) source;
      if (!theJSlider.getValueIsAdjusting()) {
        System.out.println("Slider changed: " + theJSlider.getValue());
          try {
              
                
                contr.seleccionOpcion(16);
              
              
          } catch (IOException ex) {
              Logger.getLogger(BoundedChangeListener.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
              Logger.getLogger(BoundedChangeListener3.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    } else {
      System.out.println("Something changed: " + source);
    }
  }
}

class BoundedChangeListener5 implements ChangeListener {
    
  public void stateChanged(ChangeEvent changeEvent) {
    Object source = changeEvent.getSource();
    if (source instanceof BoundedRangeModel) {
      BoundedRangeModel aModel = (BoundedRangeModel) source;
      if (!aModel.getValueIsAdjusting()) {
        System.out.println("Changed: " + aModel.getValue());
      }
    } else if (source instanceof JSlider) {
      JSlider theJSlider = (JSlider) source;
      if (!theJSlider.getValueIsAdjusting()) {
        System.out.println("Slider changed: " + theJSlider.getValue());
          try {
              
                
                contr.seleccionOpcion(17);
               
              
              
              
          } catch (IOException ex) {
              Logger.getLogger(BoundedChangeListener.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
              Logger.getLogger(BoundedChangeListener3.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    } else {
      System.out.println("Something changed: " + source);
    }
  }
}
public class interfaz extends javax.swing.JFrame {

    int optionr;
    public int brillo, contraste, zoom,escala, umbral =0;

    /**
     * Creates new form interfaz
     */
    public interfaz() {
        initComponents();
        setJMenuBar(menu());
        IU();
        //tabs();
        
       
    }
    public JMenuBar menu() {
        JMenuBar menuBar;
        JMenu file;
        JMenuItem save;
        JMenuItem load;
        JMenuItem reset;
       

        JMenu edit;
        JMenu subRotate;
        JMenuItem rotateLeft;
        JMenuItem rotateRight;
        JMenuItem rotateFree;
        JMenu subFlip;
        JMenuItem flipVertical;
        JMenuItem flipHorizontal;
        JMenuItem negative;
        JMenuItem scale;
        JMenuItem Equalization;
        JMenu subInterpolation;
        
        JMenu histogram;

        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        histogram = new JMenu("View");
        //  COSAS EN FILE
        load = new JMenuItem("Load file", KeyEvent.VK_L);
        load.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(6);
                String Stuff= contr.content;
                jTextArea1.setText(Stuff);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        save = new JMenuItem("Save file", KeyEvent.VK_S);
        save.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(9);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        reset = new JMenuItem("Reset file", KeyEvent.VK_R);
        reset.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(5);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        menuBar.add(file);
        file.add(save);
        file.add(load);
        file.add(reset);
        
        //COSAS EN EDIT;

        menuBar.add(edit);
        
        //---Rotate---
        subRotate = new JMenu("Rotate");
        rotateLeft = new JMenuItem("Rotate Left", KeyEvent.VK_1);
        rotateLeft.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(8);
                String Stuff= contr.content;
                jTextArea1.setText(Stuff);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        rotateRight = new JMenuItem("Rotate Right", KeyEvent.VK_2);
        rotateRight.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(7);
                String Stuff= contr.content;
                jTextArea1.setText(Stuff);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
       
        edit.add(subRotate);
        subRotate.add(rotateLeft);
        subRotate.add(rotateRight);
     
        //---Mirror flips---
        subFlip = new JMenu("Flip");
        flipVertical = new JMenuItem("Flip Vertical", KeyEvent.VK_3);
        flipVertical.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(4);
                String Stuff= contr.content;
                jTextArea1.setText(Stuff);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        flipHorizontal = new JMenuItem("Flip Horizontal", KeyEvent.VK_4);
        flipHorizontal.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(3);
                String Stuff= contr.content;
                jTextArea1.setText(Stuff);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        edit.add(subFlip);
        subFlip.add(flipVertical);
        subFlip.add(flipHorizontal);
        
        //Scale
        scale = new JMenuItem("Scale", KeyEvent.VK_4);
        edit.add(scale);
       
       //Negative
       negative = new JMenuItem("Negative",KeyEvent.VK_5);
       edit.add(negative);
       negative.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(2);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
       //Equalization
       Equalization = new JMenuItem("Equalization",KeyEvent.VK_6);
       edit.add(Equalization);
       Equalization.addActionListener((ActionEvent event) -> {
            try {
                contr.seleccionOpcion(15);
                contr.seleccionOpcion(11);
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
       subInterpolation = new JMenu("Interpolation");
       JMenuItem inter1;
       JMenuItem inter2;
       JMenuItem inter3;
       
        inter1 = new JMenuItem("No Interpolation", KeyEvent.VK_8);
        inter1.addActionListener((ActionEvent event) -> {
            try {
                contr.tipo=0;  
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        inter2 = new JMenuItem("Nearest Neighbor", KeyEvent.VK_9);
        inter2.addActionListener((ActionEvent event) -> {
            try {
                contr.tipo=1;  
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
         inter3 = new JMenuItem("Bilinear Interpolation", KeyEvent.VK_9);
        inter3.addActionListener((ActionEvent event) -> {
            try {
                contr.tipo=2;  
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        edit.add(subInterpolation);
        subInterpolation.add(inter1);
        subInterpolation.add(inter2);
        subInterpolation.add(inter3);
        
        //COSAS EN HISTOGRAM
        
        menuBar.add(histogram);
        //Histogram
        JMenuItem hist = new JMenuItem("histogram rgb", KeyEvent.VK_H);
        histogram.add(hist);
        hist.addActionListener((ActionEvent event) -> {
            try {
                System.out.println("up");
                contr.seleccionOpcion(11);
                System.out.println("down");
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        return menuBar;
    }
    
    private void IU(){
    jSplitPane1.setDividerLocation( 0.5);
    
    //BRILLO
        jSlider1.setValue(0);
        jSlider1.addChangeListener(new BoundedChangeListener());
        
        jSlider2.setValue(0);
        jSlider2.addChangeListener(new BoundedChangeListener2());
        
        jSlider3.setValue(128);
        jSlider3.addChangeListener(new BoundedChangeListener3());
        
        jSlider4.setValue(100);
        jSlider4.addChangeListener(new BoundedChangeListener4());
        
        jSlider5.setValue(100);
        jSlider5.addChangeListener(new BoundedChangeListener5());
        
        
           
          //SwingUtilities.updateComponentTreeUI(this);
       
      
    }
    
   
        
    
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jSlider5 = new javax.swing.JSlider();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSlider4 = new javax.swing.JSlider();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSlider3 = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jTextArea2 = new javax.swing.JTextArea();
        jTextArea1 = new javax.swing.JTextArea();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1300, 700));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Escala:");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(1140, 460, 150, 17);

        jSlider5.setMaximum(200);
        jSlider5.setMinimum(100);
        jSlider5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider5StateChanged(evt);
            }
        });
        jPanel1.add(jSlider5);
        jSlider5.setBounds(1140, 480, 150, 26);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("100%");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(1140, 430, 50, 17);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("200%");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(1250, 430, 50, 17);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Zoom:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(1140, 380, 150, 17);

        jSlider4.setMaximum(200);
        jSlider4.setMinimum(100);
        jSlider4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider4StateChanged(evt);
            }
        });
        jPanel1.add(jSlider4);
        jSlider4.setBounds(1140, 400, 150, 26);

        jCheckBox1.setBackground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Umbral: ");
        jCheckBox1.setOpaque(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1);
        jCheckBox1.setBounds(1140, 290, 150, 25);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("0");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(1140, 350, 50, 17);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("255");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(1260, 350, 50, 17);

        jSlider3.setMaximum(255);
        jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider3StateChanged(evt);
            }
        });
        jPanel1.add(jSlider3);
        jSlider3.setBounds(1140, 320, 150, 26);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("-100");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(1140, 270, 50, 17);

        jSlider2.setMinimum(-100);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });
        jPanel1.add(jSlider2);
        jSlider2.setBounds(1140, 240, 150, 26);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Contrast:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(1140, 220, 150, 17);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("100");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(1260, 270, 50, 17);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("-255");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(1140, 190, 50, 17);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("255");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(1260, 190, 50, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Brightness:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(1140, 140, 150, 17);

        jSlider1.setMaximum(255);
        jSlider1.setMinimum(-255);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jPanel1.add(jSlider1);
        jSlider1.setBounds(1140, 160, 150, 26);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setPreferredSize(new java.awt.Dimension(224, 78));
        jPanel1.add(jTextArea2);
        jTextArea2.setBounds(1140, 100, 60, 30);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setOpaque(false);
        jPanel1.add(jTextArea1);
        jTextArea1.setBounds(1140, 20, 150, 70);

        jButton7.setText("Rotate");
        jButton7.setToolTipText("Vuelve a la imagen original");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(1200, 100, 90, 30);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rotateccw.png"))); // NOI18N
        jButton3.setToolTipText("Rotacion CCW");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(1150, 590, 110, 60);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rotatecw.png"))); // NOI18N
        jButton2.setToolTipText("Rotacion CW");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(1150, 520, 110, 60);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane1.setViewportView(jLabel3);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane2.setViewportView(jLabel2);

        jSplitPane1.setRightComponent(jScrollPane2);

        jTabbedPane1.addTab("Image", jSplitPane1);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 1130, 670);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bg.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 2560, 1573);
        jLabel1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try {
            // TODO add your handling code here:
            contr.seleccionOpcion(7);
        } catch (IOException ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
      
       String option = jTextArea2.getText();
       optionr = Integer.parseInt(option); 
        try {
            // TODO add your handling code here
            contr.seleccionOpcion(10);
        } catch (IOException ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            contr.seleccionOpcion(8);
        } catch (IOException ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
      
            JSlider j=(JSlider)evt.getSource();
            brillo=j.getValue();
            jLabel4.setText("Brightness: "+Integer.toString(brillo));
       
             
    }//GEN-LAST:event_jSlider1StateChanged

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        // TODO add your handling code here:
         JSlider j=(JSlider)evt.getSource();
         contraste=j.getValue();
         jLabel7.setText("Contrast: "+Integer.toString(contraste));
    }//GEN-LAST:event_jSlider2StateChanged

    private void jSlider3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider3StateChanged
        // TODO add your handling code here:
         JSlider j=(JSlider)evt.getSource();
         umbral=j.getValue();
         jCheckBox1.setText("Umbral: "+Integer.toString(umbral));
    }//GEN-LAST:event_jSlider3StateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        
    
        try {
            contr.seleccionOpcion(14);
        } catch (IOException ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
;

        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jSlider4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider4StateChanged
        // TODO add your handling code here:
         JSlider j=(JSlider)evt.getSource();
         zoom=j.getValue();
         jLabel10.setText("Zoom: "+Integer.toString(zoom));
    }//GEN-LAST:event_jSlider4StateChanged

    private void jSlider5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider5StateChanged
        // TODO add your handling code here:
         JSlider j=(JSlider)evt.getSource();
        escala=j.getValue();
         jLabel15.setText("Escala: "+Integer.toString(escala));
          String Stuff= contr.content;
           jTextArea1.setText(Stuff);
    }//GEN-LAST:event_jSlider5StateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    public javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    private javax.swing.JSlider jSlider5;
    private javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

    private JLabel setText(String hola) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
