/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.MemoryImageSource;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.paint.Color.color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import sun.java2d.SunGraphicsEnvironment;



/**
 *
 * @author Hillary
 */
public class controlador  {
    static public interfaz inicio;
    String ruta;
    BufferedImage original= null;
    BufferedImage img = null;
    Boolean viewH = false;
    String content=("h");
    histogram histograma;
    bitmap bmp = new bitmap();
    int oBrillo=0;
    double angulo=0;
    int ancho, alto, tipo = 0; 
    
  
    public controlador()
    {     
        inicio= new interfaz();    
        inicio.show();   
        inicio.setTitle("PDI: Tarea 2");
        inicio.setVisible(true);
        //inicio.setResizable(true);
        inicio.setLocationRelativeTo(null);
        inicio.pack();

    }
   
   
    public void seleccionOpcion (int z) throws IOException, Exception
   {
           switch (z)
           {
               case 1:
               {   
                  //ELEGIR UN ARCHIVO//
                   //EN CASO DE QUERER CAMBIAR EL TIPO DE ARCHIVO.
                   FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "bmp");  
                   JFileChooser abrir = new JFileChooser();
                   abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
                   abrir.setFileFilter(filter);
                   abrir.setCurrentDirectory(new File(System.getProperty("user.home")));
                   int result = abrir.showOpenDialog(inicio);
                   if (result == JFileChooser.APPROVE_OPTION) {
                        // se seleciona el archivo de imagen original
                        File selectedFile = abrir.getSelectedFile();
                        ruta = selectedFile.getAbsolutePath();
                        System.out.println("El archivo es: " + ruta);  //ruta
                        img = ImageIO.read(new File(ruta)); //se lee el archivo
                        inicio.jLabel3.setBounds(0,0,ancho,alto);
                        ImageIcon icon = new ImageIcon(img); //se despliega la imagen en el jlabel3
                        inicio.jLabel3.setIcon(icon);
                        inicio.setTitle("PDI: Tarea 2 -"+ruta); 
                  
                   }
                }
                break;//end case 1
                
                case 2: //imagen en negativo
                {
                    
                   
                         
                            //se crea un buffer
                            BufferedImage imagenNegativa = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                            //se convierten los colores a negativo y se va guardando en el buffer
                            for(int y = 0; y < alto; y++){
                              for(int x = 0; x < ancho; x++){
                                int p = img.getRGB(x,y);
                                //obtenermos el valor r g b a de cada pixel
                               // int a = (p>>24)&0xff;
                                int r = (p>>16)&0xff;
                                int g = (p>>8)&0xff;
                                int b = p&0xff;
                                //se resta el rbg
                                r = truncate(255 - r);
                                g = truncate (255 - g);
                                b = truncate(255 - b);
                                //se guarda el rgb
                                p =(r<<16) | (g<<8) | b;
                                imagenNegativa.setRGB(x, y, p);
                              }
                            }
                            //PARA LOS ROTACIONES
                            img= imagenNegativa;
                            
                            ancho = original.getWidth();
                            alto = original.getHeight();
                            //se crea un buffer
                            imagenNegativa = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                            //se convierten los colores a negativo y se va guardando en el buffer
                            for(int y = 0; y < alto; y++){
                              for(int x = 0; x < ancho; x++){
                                int p = original.getRGB(x,y);
                                //obtenermos el valor r g b a de cada pixel
                                int a = (p>>24)&0xff;
                                int r = (p>>16)&0xff;
                                int g = (p>>8)&0xff;
                                int b = p&0xff;
                                //se resta el rbg
                                r = 255 - r;
                                g = 255 - g;
                                b = 255 - b;
                                //se guarda el rgb
                                p = (a<<24) | (r<<16) | (g<<8) | b;
                                imagenNegativa.setRGB(x, y, p);
                              }
                            }
                            //se reemplaza la imagen original cargada por esta
                            original= imagenNegativa;
                            
                            
                    
                    inicio.jLabel3.setBounds(0,0,ancho,alto);
                    ImageIcon icon = new ImageIcon(img); //se despliega la imagen en el jlabel3
                    inicio.jLabel3.setIcon(icon);
                } 
                break;//end case 2
                
                case 3: //flip imagen vertical
                {
                    
                    
                        //buffer para la imagen
                        BufferedImage mirrorimgV = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                        //recorremos pixel a pixel tooooooooooooodo el buffer
                        for(int i = 0; i < alto; i++){
                          for(int izquierda = 0, derecha = ancho - 1; izquierda  < alto; izquierda ++, derecha--){
                            int p = img.getRGB(izquierda , i);
                            mirrorimgV.setRGB(derecha, i, p);
                          }
                        }
                        img = mirrorimgV;
                        original = img;
                        inicio.jLabel3.setBounds(0,0,ancho,alto);
                        ImageIcon icon = new ImageIcon(img); 
                        inicio.jLabel3.setIcon(icon);
                
                }
                break;//end case 3
                
                
                case 4://flip imagen horizontal
                {
                    
                   
                        BufferedImage mirrorimgH = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
              
                        for(int i = 0; i < ancho; i++){
                            for(int arriba = 0, abajo = alto - 1; arriba <alto; arriba++, abajo--){
                                int p = img.getRGB(i, arriba);
                                mirrorimgH.setRGB(i, abajo, p);
                          }
                        }
                        img = mirrorimgH;
                        original = img;
                        inicio.jLabel3.setBounds(0,0,ancho,alto);
                        ImageIcon icon = new ImageIcon(mirrorimgH); 
                        inicio.jLabel3.setIcon(icon);
                
                }
                break;//end case 4
                
                case 5:{ //boton de reset
                    
                    //RESET
                    File f = null;
                    //leer image
                        try{
                          f = new File(ruta);
                          img = ImageIO.read(f);
                        }catch(IOException e){
                          System.out.println(e);
                        } 
                   
                   
                    inicio.jLabel3.setBounds(10,10,ancho,alto);
                    ImageIcon icon = new ImageIcon(img); 
                    inicio.jLabel3.setIcon(icon);
                
                
                }break; //end case 5
                
                
                case 6:{ //leer en formato binario
                    
                   FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "bmp");  
                   JFileChooser abrir = new JFileChooser();
                   abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
                   abrir.setFileFilter(filter);
                   abrir.setCurrentDirectory(new File(System.getProperty("user.home")));
                   int result = abrir.showOpenDialog(inicio);
                   if (result == JFileChooser.APPROVE_OPTION) {
                        
                       try {
                           File selectedFile = abrir.getSelectedFile();
                           ruta = selectedFile.getAbsolutePath();
                           
                          
                           FileInputStream  is = null;
                           
                           is = new FileInputStream(ruta);
                           bmp.read(is);
                           System.out.println("aqui");
                           MemoryImageSource mis = bmp.crearImageSource();
                           System.out.println("hola");
                           Image im = Toolkit.getDefaultToolkit().createImage(mis);
                           //Para poder colorcarlo en el label
                           //Image image = createImage(new MemoryImageSource(bmp.crearImageSource()));
                           BufferedImage newImage = new BufferedImage(im.getWidth(null), im.getHeight(null),BufferedImage.TYPE_INT_RGB);
                           //obtenemos la imagen que si se puede desplgar
                           Graphics2D g = newImage.createGraphics();
                           g.drawImage(im, 0, 0, null);
                           g.dispose();
                           
                           img = newImage;
                           original=img;
                           inicio.jLabel3.setBounds(0,0,ancho,alto);
                           ImageIcon icon = new ImageIcon(img); 
                           inicio.jLabel3.setIcon(icon);
                           inicio.jLabel2.setIcon(icon);
                        
                           
                           //add img info
                           inicio.setTitle("PDI: Tarea 2 -"+ruta); 
                           //dimensiones, profundidad de bits, Mb ocupados
                           content = ("Size: " + (bmp.tamArchivo)/1000 +"kb\nDimension: "+ bmp.ancho + " x "+ bmp.alto +"\nBpp: " + bmp.bitsPorPixel+"bits");
                           ancho=  bmp.ancho;
                           alto =  bmp.alto;
                           
                        } 
                    catch (Exception ex) {
                           Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }//end approval if
                }break; //end case 6
                
                //girar CW
                case 7:{ 
                    
                   
                    BufferedImage new_Image = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_RGB);
                            for (int i = 0; i < ancho; i++) {
                                    for (int j = 0; j <alto; j++) {
                                            int p = img.getRGB(i, j);
                                            new_Image.setRGB(alto - j - 1, i, p);
                                            
                                    }
                            }
                            
                    img= new_Image;
                    original = img;
                    inicio.jLabel3.setBounds(0,0,ancho,alto);
                    ImageIcon icon = new ImageIcon(img);
                    inicio.jLabel3.setIcon(icon);

                   
                }break;//end case 7
                
                 //girar CCW
                case 8:{ 
                    
                    
                    BufferedImage new_Image = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_RGB);
                            for (int i = 0; i < ancho; i++) {
                                    for (int j = 0; j < alto; j++) {
                                            int p = img.getRGB(i, j);
                                            new_Image.setRGB(j,ancho - i - 1, p);
                                            
                                    }
                            }
                            
                    img= new_Image;
                    original = img;
                    inicio.jLabel3.setBounds(0,0,ancho,alto);
                    ImageIcon icon = new ImageIcon(img); 
                    inicio.jLabel3.setIcon(icon);
                   
                }break;//end case 8
                
                case 9:{ //Guardar Imagen
                   
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "bmp");  
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(filter);
                    fileChooser.setDialogTitle("Save");   
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int userSelection = fileChooser.showSaveDialog(inicio);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        System.out.println("Save as file: " + fileToSave.getAbsolutePath()+".bmp");
                        System.out.println("Save as: " + fileToSave.getName());
                        //BMPFile save= new BMPFile();
                        //save.saveBitmap (fileToSave.getAbsolutePath()+".BMP",img, img.getWidth(),img.getHeight());
                        bmp.saveMyLifeTonight (fileToSave, img);
                        //ImageIO.write(img, "BMP", new File(fileToSave.getAbsolutePath()+".bmp"));
                        //bmp.writeToFileAtPath (fileToSave.getAbsolutePath()+".bmp");
                    }
                }break;
                
                case 10:{ 
                //free rotation
                double anguloCartesiano= inicio.optionr;
                double aux;
                
                //para la ilusion de rotar sobre la "misma imagen"
                if(anguloCartesiano < 0){
                    
                    aux= anguloCartesiano;   
                    anguloCartesiano =anguloCartesiano + angulo;
                    angulo= anguloCartesiano;
                
                   

                }else if (anguloCartesiano > 0){
                        
                        
                        aux= anguloCartesiano;   
                        anguloCartesiano = angulo + anguloCartesiano;
                        angulo = anguloCartesiano;
                
                     }
                
                anguloCartesiano = anguloCartesiano * Math.PI / 180;
                
                //CC coordinates
                int x, y;
                double distance, anguloPolar;
                int pisoX, techoX, pisoY, techoY;
                double rasterX, rasterY;
                // colores de los pixeles
                Color colorTL = null, colorTR, colorBL, colorBR = null;
                // interpolaciones
                double intX, intY;
                double rojoT, verdeT, azulT;
                double rojoB, verdeB, azulB;
                
                int centroX, centroY;

                centroX = original.getWidth() / 2;
                centroY = original.getHeight() / 2;
              
                BufferedImage imagenRotada =new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);//fondo transparente
                
                for (int i = 0; i < original.getHeight(); ++i)
                    for (int j = 0; j < original.getWidth(); ++j)
                    {
                        // convert raster to Cartesian
                        x = j - centroX;
                        y = centroY - i;

                        // convert Cartesian to polar
                        distance = Math.sqrt(x * x + y * y);
                        anguloPolar = 0.0;
                        if (x == 0)
                        {
                            if (y == 0)
                            {
                                // centre of image, no rotation needed
                                imagenRotada.setRGB(j, i, original.getRGB(j, i));

                                continue;
                            }
                            else if (y < 0)
                                anguloPolar = 1.5 * Math.PI;
                            else
                                anguloPolar = 0.5 * Math.PI;
                        }
                        else
                            anguloPolar = Math.atan2((double)y, (double)x);

                        // 
                        anguloPolar -= anguloCartesiano;

                        //polr a carte
                        rasterX = distance * Math.cos(anguloPolar);
                        rasterY = distance * Math.sin(anguloPolar);

                        // cartesiano a raster
                        rasterX = rasterX + (double)centroX;
                        rasterY = (double)centroY - rasterY;

                        pisoX = (int)(Math.floor(rasterX));
                        pisoY = (int)(Math.floor(rasterY));
                        techoX = (int)(Math.ceil(rasterX));
                        techoY = (int)(Math.ceil(rasterY));

                        // check bounds /// AQUIWWIUEI
                        if (pisoX < 0 || techoX < 0 || pisoX >= original.getWidth() || techoX >= original.getWidth() || pisoY < 0 || techoY < 0 || pisoY >= original.getHeight() || techoY >= original.getHeight()) continue;

                        intX = rasterX - (double)pisoX;
                        intY = rasterY - (double)pisoY;

                        
                        colorTL = new Color (original.getRGB(pisoX, pisoY));
                        colorTR =new Color (original.getRGB (techoX, pisoY));
                        colorBL = new Color (original.getRGB(pisoX, techoY));
                        colorBR =new Color (original.getRGB(techoX, techoY));

                        // interpolacion horizontal top
                        rojoT = (1 - intX) * colorTL.getRed() + intX * colorTR.getRed();
                        verdeT = (1 - intX) * colorTL.getGreen() + intX * colorTR.getGreen();
                        azulT = (1 - intX) * colorTL.getBlue() + intX * colorTR.getBlue();
                        // interpolacion horizontal bot
                        rojoB = (1 - intX) * colorBL.getRed() + intX * colorBR.getRed();
                        verdeB = (1 - intX) * colorBL.getGreen() + intX * colorBR.getGreen();
                        azulB = (1 - intX) * colorBL.getBlue()+ intX * colorBR.getBlue();
                        // interpolacion vertical
                                int p = original.getRGB(j, i);
                                int a = (p>>24)&0xff;
                                int r = (p>>16)&0xff;
                                int g = (p>>8)&0xff;
                                int b = p&0xff;
                                r = truncate(Math.round((1 - intY) * rojoT + intY * rojoB));
                                g = truncate(Math.round((1 - intY) * verdeT + intY * verdeB));
                                b = truncate(Math.round((1 - intY) * azulT + intY * azulB));
                                p = (a<<24) | (r<<16) | (g<<8) | b;
                        imagenRotada.setRGB(j, i, p);
                        

                
                    }
                        img= imagenRotada;
                        inicio.jLabel3.setBounds(0,0,ancho,alto);
                        ImageIcon icon = new ImageIcon(img); 
                        inicio.jLabel3.setIcon(icon);
                
                }break; //case 10
                
                case 11:{ //histogram
                    
                    //para recorrer todos los valores y obtener los samples
                    /*
                    for (y) {
                        for (x) {
                           pixel = raster.getDataElements(x, y, pixel);
                        }
                    }
                                        */
                    int BINS = 256;
                    HistogramDataset dataset = new HistogramDataset();
                    Raster raster = img.getRaster();
                    
                    double[] r = new double[ancho * alto];
                    ChartPanel panelB=null;
                    ChartPanel panelG=null;
                    ChartPanel panelR=null;
                    ChartPanel panel;
                    
                    if(bmp.bitsPorPixel==1){
                        
                    r = raster.getSamples(0, 0, ancho, alto, 0, r);
                    ColorModel ColorM = img.getColorModel();
                        
                    dataset.addSeries("Grey", r , BINS);
                    
                    
                    //de aqui para abajo es el plotting
                    // chart all
                    JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value","Count", dataset, PlotOrientation.VERTICAL, true, true, false);
                    XYPlot plot = (XYPlot) chart.getPlot();
                    XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
                    renderer.setBarPainter(new StandardXYBarPainter());
                    
                    Paint[] paintArray = {
                        new Color(0x80ff0000, true)
                    };
                     plot.setDrawingSupplier(new DefaultDrawingSupplier(
                        paintArray,
                        DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
                    panel = new ChartPanel(chart);
                    panel.setMouseWheelEnabled(true);
                    
                    
                    
                    
                    } else{
                        
                        r = raster.getSamples(0, 0, ancho, alto, 0, r);
                        dataset.addSeries("Red", r, BINS);
                        r = raster.getSamples(0, 0, ancho, alto, 1, r);
                        dataset.addSeries("Green", r, BINS);
                        r = raster.getSamples(0, 0, ancho, alto, 2, r);
                        dataset.addSeries("Blue", r, BINS);

                        //de aqui para abajo es el plotting
                        // chart all
                        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value","Count", dataset, PlotOrientation.VERTICAL, true, true, false);
                        XYPlot plot = (XYPlot) chart.getPlot();
                        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
                        renderer.setBarPainter(new StandardXYBarPainter());
                        // translucent red, green & blue
                        Paint[] paintArray = {
                            new Color(0x80ff0000, true),
                            new Color(0x8000ff00, true),
                            new Color(0x800000ff, true)
                        };
                         plot.setDrawingSupplier(new DefaultDrawingSupplier(
                            paintArray,
                            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
                        panel = new ChartPanel(chart);
                        panel.setMouseWheelEnabled(true);

                        //CHART Red
                        HistogramDataset datasetR = new HistogramDataset();
                         r = raster.getSamples(0, 0, ancho, alto, 0, r);
                        datasetR.addSeries("Red", r, BINS);
                        JFreeChart chartR = ChartFactory.createHistogram("Histogram B", "Value","Count", datasetR, PlotOrientation.VERTICAL, true, true, false);
                        XYPlot plotR = (XYPlot) chartR.getPlot();
                        XYBarRenderer rendererR = (XYBarRenderer) plotR.getRenderer();
                        rendererR.setBarPainter(new StandardXYBarPainter());
                        // translucent red, green & blue
                        Paint[] paintArrayR = {
                            new Color(0x80ff0000, true)

                        };
                         plotR.setDrawingSupplier(new DefaultDrawingSupplier(
                            paintArrayR,
                            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
                        panelR = new ChartPanel(chartR);
                        panelR.setMouseWheelEnabled(true);


                        //CHART GREEN

                        HistogramDataset datasetG = new HistogramDataset();
                         r = raster.getSamples(0, 0, ancho, alto, 1, r);
                        datasetG.addSeries("Green", r, BINS);
                        JFreeChart chartG = ChartFactory.createHistogram("Histogram G ", "Value","Count", datasetG, PlotOrientation.VERTICAL, true, true, false);
                        XYPlot plotG = (XYPlot) chartG.getPlot();
                        XYBarRenderer rendererG = (XYBarRenderer) plotG.getRenderer();
                        rendererG.setBarPainter(new StandardXYBarPainter());
                        // translucent red, green & blue
                        Paint[] paintArrayG = {
                              new Color(0x8000ff00, true)

                        };
                         plotG.setDrawingSupplier(new DefaultDrawingSupplier(
                            paintArrayG,
                            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
                        panelG = new ChartPanel(chartG);
                        panelG.setMouseWheelEnabled(true);


                         //CHART BLUE

                        HistogramDataset datasetB = new HistogramDataset();
                        r = raster.getSamples(0, 0, ancho, alto, 2, r);
                        datasetB.addSeries("Blue", r, BINS);
                        JFreeChart chartB = ChartFactory.createHistogram("Histogram B ", "Value","Count", datasetB, PlotOrientation.VERTICAL, true, true, false);
                        XYPlot plotB = (XYPlot) chartB.getPlot();
                        XYBarRenderer rendererB = (XYBarRenderer) plotB.getRenderer();
                        rendererB.setBarPainter(new StandardXYBarPainter());
                        // translucent red, green & blue
                        Paint[] paintArrayB = {
                              new Color(0x800000ff, true)

                        };
                         plotB.setDrawingSupplier(new DefaultDrawingSupplier(
                            paintArrayB,
                            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
                        panelB = new ChartPanel(chartB);
                        panelB.setMouseWheelEnabled(true);
                    
                    }
                    
                    //JTabbedPane jtp=new JTabbedPane();
                    if(!viewH){
                        
                    inicio.jTabbedPane1.addTab("Histogram", panel);
                    inicio.jTabbedPane1.addTab("Histogram R", panelR);
                    inicio.jTabbedPane1.addTab("Histogram G", panelG);
                    inicio.jTabbedPane1.addTab("Histogram B", panelB);
                    viewH=true;
                    }
                    else{
                    inicio.jTabbedPane1.remove(inicio.jTabbedPane1.indexOfTab("Histogram"));
                    inicio.jTabbedPane1.remove(inicio.jTabbedPane1.indexOfTab("Histogram R"));
                    inicio.jTabbedPane1.remove(inicio.jTabbedPane1.indexOfTab("Histogram G"));
                    inicio.jTabbedPane1.remove(inicio.jTabbedPane1.indexOfTab("Histogram B"));
                    viewH=false;
                    }

                
                }break;
                
                case 12:{
                    //BRILLO
                    int dif =inicio.brillo;
                   
                    
                            int ancho = original.getWidth();
                            int alto = original.getHeight();
                            //se crea un buffer
                            BufferedImage brillito = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                            //se convierten los colores a negativo y se va guardando en el buffer
                            for(int y = 0; y < alto; y++){
                              for(int x = 0; x < ancho; x++){
                                int p = original.getRGB(x,y);
                                //obtenermos el valor r g b a de cada pixel
                                int a = (p>>24)&0xff;
                                int r = (p>>16)&0xff;
                                int g = (p>>8)&0xff;
                                int b = p&0xff;
                                //se resta el rbg
                                r = truncate (r + dif);
                                g = truncate (g + dif);
                                b = truncate (b + dif) ;
                                //se guarda el rgb
                                p = (r<<16) | (g<<8) | b;
                                brillito.setRGB(x, y, p);
                              }
                            }
                    img = brillito;
                    inicio.jLabel3.setBounds(0,0,ancho,alto);
                    ImageIcon icon = new ImageIcon(img); //se despliega la imagen en el jlabel3
                    inicio.jLabel3.setIcon(icon);
                    
                
                }break; //end case 12
                
                case 13:{
                //CONTRAST
                double dif =inicio.contraste;
                double level =Math.pow(((100.0+dif)/100.0), 2.0);
                   
                    
                            int ancho = original.getWidth();
                            int alto = original.getHeight();
                            BufferedImage contraste = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                            for(int y = 0; y < alto; y++){
                              for(int x = 0; x < ancho; x++){
                                int p = original.getRGB(x,y);
                                int a = (p>>24)&0xff;
                                int r = (p>>16)&0xff;
                                int g = (p>>8)&0xff;
                                int b = p&0xff;
                                
                                   b=truncate((int)((((((double)b/255.0)-0.5)*level)+0.5)*255.0));
                                   g=truncate((int)((((((double)g/255.0)-0.5)*level)+0.5)*255.0));
                                   r=truncate((int)((((((double)r/255.0)-0.5)*level)+0.5)*255.0));

                                p = (r<<16) | (g<<8) | b;
                                contraste.setRGB(x, y, p);
                              }
                            }
                    img = contraste;
                    inicio.jLabel3.setBounds(0,0,ancho,alto);
                    ImageIcon icon = new ImageIcon(img); 
                    inicio.jLabel3.setIcon(icon);
                
                
                
                }break;// case 13
                
                case 14:{
                //UMBRALIZACION
                double u =inicio.umbral;
                if (inicio.jCheckBox1.isSelected()){
                
                int ancho = original.getWidth();
                int alto = original.getHeight();
                         
                            BufferedImage contraste = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                           
                            for(int y = 0; y < alto; y++){
                              for(int x = 0; x < ancho; x++){
                                int p = original.getRGB(x,y);
                               
                                int a = (p>>24)&0xff;
                                int r = (p>>16)&0xff;
                                int g = (p>>8)&0xff;
                                int b = p&0xff;
                                
                                double mediana=(double)(r+b+g);
                                mediana/=3;
                                int med=(int)Math.round(mediana);

                                b=med;
                                g=med;
                                r=med;

                                if(r<=u) r=0;
                                    else r=255;

                                if(g<=u) g=0;
                                    else g=255;

                                if(b<=u) b=0;
                                else b=255; 
                                
                                p = (r<<16) | (g<<8) | b;
                                contraste.setRGB(x, y, p);
                              }
                            }
                    img = contraste;
                    inicio.jLabel3.setBounds(0,0,ancho,alto);
                    ImageIcon icon = new ImageIcon(img); 
                    inicio.jLabel3.setIcon(icon);
                    
                }else
                    img = original;
                    inicio.jLabel3.setBounds(0,0,ancho,alto);
                    ImageIcon icon = new ImageIcon(img); 
                    inicio.jLabel3.setIcon(icon);
                    
                    
                }break;
                
                case 15:{
                    BufferedImage equalized = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                    int r,g,b,a;
                    int pixel = 0;

                    
                    //look up table rgb 
                    int[] rhist = new int[256];
                    int[] ghist = new int[256];
                    int[] bhist = new int[256];

                    for(int i=0; i<rhist.length; i++) rhist[i] = 0;
                    for(int i=0; i<ghist.length; i++) ghist[i] = 0;
                    for(int i=0; i<bhist.length; i++) bhist[i] = 0;

                    for(int i=0; i<img.getWidth(); i++) {
                        for(int j=0; j<img.getHeight(); j++) {

                            int red = new Color(img.getRGB (i, j)).getRed();
                            int green = new Color(img.getRGB (i, j)).getGreen();
                            int blue = new Color(img.getRGB (i, j)).getBlue();
                            rhist[red]++; ghist[green]++; bhist[blue]++;

                        }
                    }
                    
                   //histograma color
                    ArrayList<int[]> imageHist = new ArrayList<int[]>();
                    imageHist.add(rhist);
                    imageHist.add(ghist);
                    imageHist.add(bhist);
                    //lookup table
                    ArrayList<int[]> imgLT = new ArrayList<int[]>();
                    // llenar 
                    rhist = new int[256];
                    ghist = new int[256];
                    bhist = new int[256];
                    
                    for(int i=0; i<rhist.length; i++) rhist[i] = 0;
                    for(int i=0; i<ghist.length; i++) ghist[i] = 0;
                    for(int i=0; i<bhist.length; i++) bhist[i] = 0;

                    long rojosT = 0;
                    long verdesT = 0;
                    long azulT = 0;

                    // 
                    float factorDeEscala = (float) (255.0 / (ancho* alto));

                    for(int i=0; i<rhist.length; i++) {
                        rojosT += imageHist.get(0)[i];
                        int valor = (int) (rojosT * factorDeEscala);
                        if(valor > 255) {
                            rhist[i] = 255;
                        }
                        else rhist[i] = valor;

                        verdesT += imageHist.get(1)[i];
                        int valg = (int) (verdesT * factorDeEscala);
                        if(valg > 255) {
                            ghist[i] = 255;
                        }
                        else ghist[i] = valg;

                        azulT += imageHist.get(2)[i];
                        int valb = (int) (azulT * factorDeEscala);
                        if(valb > 255) {
                            bhist[i] = 255;
                        }
                        else bhist[i] = valb;
                    }

                    imgLT.add(rhist);
                    imgLT.add(ghist);
                    imgLT.add(bhist);

                    for(int i=0; i<ancho; i++) {
                        for(int j=0; j<alto; j++) {

                            // colores
                            a = new Color(img.getRGB (i, j)).getAlpha();
                            r = new Color(img.getRGB (i, j)).getRed();
                            g = new Color(img.getRGB (i, j)).getGreen();
                            b = new Color(img.getRGB (i, j)).getBlue();

                            // nuevos valoooooores
                            r = imgLT.get(0)[r];
                            g = imgLT.get(1)[g];
                            b = imgLT.get(2)[b];
                            

                            // rgb otra vez
                            pixel = colorToRGB(a, r, g, b);

                            //imagen final
                            equalized.setRGB(i, j, pixel);

                    }
                }
                    
                   img = equalized;
                   inicio.jLabel3.setBounds(0,0,ancho,alto);
                   ImageIcon icon = new ImageIcon(img); 
                   inicio.jLabel3.setIcon(icon);
                
                }break;
                
                case 16:{
                //zoom 
                double du =inicio.zoom;
                double u= du/100;
                int escalaX= (int) (ancho * u);
                int escalaY=  (int) (alto * u);
                
                
               BufferedImage zoom = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
               
                  for (int i = 0; i < zoom.getHeight(); ++i)
                    for (int j = 0; j < zoom.getWidth(); ++j)
                    {
                        //R(x,y):= A(x/ax, y/ay) 
                        //R(x,y):= A(Floor x/10 ,Floor /10˚)
                        //nearest
                        if(tipo == 1){
                         
                        int ax = (int) (Math.floor(i/u));
                        int ay = (int) (Math.floor(j/u));
                        
                        int p = original.getRGB(ax,ay);
                        zoom.setRGB(i, j, p);
                        }
                        
                        //bilinear
                        if (tipo == 2){
                            
                           
                          
                          
                        }
                        
                        
                        
                        //no loss
                        if (tipo == 0){
                            
                        int ax = (int) (i/ u);
                        int ay = (int) (j/ u);
                        
                        int p = original.getRGB(ax,ay);
                        zoom.setRGB(i, j, p);
                        }

                       
                }
                //BufferedImage zoom2 = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
                
                img=zoom;    
                inicio.jLabel3.setBounds(0,0,zoom.getWidth(),zoom.getHeight());
                System.out.println("ancho: "+zoom .getWidth());
                ImageIcon icon = new ImageIcon(zoom); 
                inicio.jLabel3.setIcon(icon);
                
                
                }break;
                
                case 17:{
                //zoom 
                double du =inicio.escala;
                double u= du/100;
                int escalaX= (int) (ancho *u);
                
                
                int escalaY=  (int) (alto * u);
                
                
               BufferedImage escala = new BufferedImage(escalaX,escalaY, BufferedImage.TYPE_INT_RGB);
               
             
                  for (int i = 0; i < escala .getHeight(); ++i)
                    for (int j = 0; j < escala.getWidth(); ++j)
                    {
                        //R(x,y):= A(x/ax, y/ay) 
                        //R(x,y):= A(Floor x/10 ,Floor /10˚)
                        //nearest
                        if(tipo == 1){
                         
                        int ax = (int) (Math.floor(i/u));
                        int ay = (int) (Math.floor(j/u));
                        
                        int p = original.getRGB(ax,ay);
                        escala.setRGB(i, j, p);
                        }
                        
                        //bilinear
                        if (tipo == 2){
                            
                           
                        
                        }
                        
                        
                        
                        //no loss
                        if (tipo == 0){
                            
                        int ax = (int) (i/u);
                        int ay = (int) (j/u);
                        
                        int p = original.getRGB(ax,ay);
                        escala.setRGB(i, j, p);
                        }

                       
                }
                    
                img=escala ;    
                inicio.jLabel3.setBounds(0,0,escala.getWidth(),escala.getHeight());
                //System.out.println("ancho: "+escala .getWidth());
                ImageIcon icon = new ImageIcon(escala); 
                inicio.jLabel3.setIcon(icon);
                content = ("Dimension: "+ img.getWidth() + " x "+ img.getHeight() +"\nBpp: " + bmp.bitsPorPixel+"bits");
                //inicio.jTextArea1
                
                }break;
              
                
            } //end switch
           
           
    
   }//end function 
    

    private static int colorToRGB(int alpha, int red, int green, int blue) {
 
        int newPixel = 0;
        newPixel += alpha; newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
 
        return newPixel;
 
    }
 
    public int truncate(double num){
            if (num < 0)
                return 0;
            if (num > 255)
                return 255;
            return (int)Math.floor(num);
        }
   public int truncate (int value){
        if (value < 0) { value = 0;}
        if (value > 255) {value = 255;}
        return value;
  }
 

}

