/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.IntBuffer;
import java.util.Arrays;
import static javafx.scene.paint.Color.rgb;
import static javafx.scene.paint.Color.rgb;
import static javafx.scene.paint.Color.rgb;
import javax.imageio.ImageIO;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import static java.util.Arrays.fill;

/**
 *
 * @author Hillary
 */
public class bitmap {
        //lectura del archivo
        InputStream archivo;
        int PosicionActual = 0;
        
        short tipoArchivo = 0x4d42;//"BM"
                int tamArchivo;          
                short reservado1 = 0;    
                short reservado2 = 0; 
        
        //lectura de la imagen- bitmap header
        int tamRealBitmap;
        int tamScan;
        int cantColores;
        int bmpOffset;               //posicion inicial de los datos de la imagen
        int ancho;                              
        int alto;                            
        short bitsPorPixel;             
        int comprimido;                
        
        //lectura de la imagen- paleta
        byte r[], g[], b[];             
        int entradas;
        //lectura de la imagen- contenido pxl
        byte[] Data; 
        byte[] dataEnByte;                
        int[] dataEnInt;                 
        boolean leer;
        
        
        int size;                               
        short planos;                   
        int tamBMP;               
        int ResHor;             
        int ResVert;             
        int coloresPaleta;                
        int coloresImportantes;    
        int cantPixl;
        long salto;
        

        
        public void read(InputStream archivo) throws IOException, Exception {
                this.archivo = archivo;
                //14 BYTES FILE HEADER
                tipoArchivo = leerShort();
                tamArchivo = leerEnteros();
                reservado1 = leerShort();
                reservado2 = leerShort();
                bmpOffset = leerEnteros();
                
                //BMP HEADER 40 BYTES
                // Actual contents (40 bytes):
                size = leerEnteros();
                ancho = leerEnteros();
                alto = leerEnteros();
                planos = leerShort();
                bitsPorPixel = leerShort();
                comprimido = leerEnteros();
                tamBMP = leerEnteros();
                ResHor = leerEnteros();
                ResVert = leerEnteros();
                coloresPaleta = leerEnteros();
                coloresImportantes = leerEnteros();
                
                    if (bitsPorPixel==24)
                        coloresPaleta = coloresImportantes = 0;

                    leer = (alto < 0);
                    if (leer) alto = -alto;
                    cantPixl = ancho * alto;
                    System.out.println("TIENE bpp " + bitsPorPixel);  //ruta
                    // Scan line archivo con padding de 4
                    tamScan = ((ancho * bitsPorPixel + 31) / 32) * 4;

                    tamRealBitmap = tamScan * alto;
                     
                     System.out.println("colores usados" + coloresPaleta); 
                     System.out.println("colores importantes" + coloresImportantes); 

                    if (coloresPaleta != 0)
                            cantColores = coloresPaleta;
                    else
                           //los colores son los bits por pixel
                            if (bitsPorPixel < 16)
                                    cantColores = 1 << bitsPorPixel;
                            else
                                    cantColores = 0;   

                    //fin del header

                    //si esta comprimido
                    if (comprimido!=0)
                            throw new Exception("Error: Archivo comprimido");

                    //PALETA DE COLORES  para todos menos el de 24
                    //System.out.println("la paleta tiene colores" + cantColores);
                
                    entradas = cantColores;
                    //si el numero de colores es 0, no hay paleta
                    if (entradas>0) {
                            r = new byte[entradas];
                            g = new byte[entradas];
                            b = new byte[entradas];

                            int reserved;
                            for (int i = 0; i < entradas; i++) {
                                    b[i] = (byte)archivo.read();
                                    g[i] = (byte)archivo.read();
                                    r[i] = (byte)archivo.read();
                                    reserved = archivo.read();
                                    PosicionActual += 4;
                            }
                    }
                

                    //Arreglo para la data de la imagen
                    //byte[] Data;                 
                    // saltar a la data
                    salto = bmpOffset - PosicionActual;
                    if (salto > 0) {
                            archivo.skip(salto);
                            PosicionActual += salto;
                    }
                    System.out.println("ski" + salto);
                    int len = tamScan;
                    if (bitsPorPixel > 8)
                            dataEnInt = new int[ancho * alto];
                            else
                                dataEnByte = new byte[ancho * alto];
                    System.out.println("tamScan" + tamScan);
                    System.out.println("tamRBMP" + tamRealBitmap);
                    Data = new byte[tamRealBitmap];
                    int dataOffset = 0;
                    int offset = (alto - 1) * ancho;
                     System.out.println("alto-1" + alto); 
                    for (int i = alto - 1; i >= 0; i--) {
                            int n = archivo.read(Data, dataOffset, len);
                            //System.out.println("n" + n); 
                            if (n < len) throw new Exception("Error de linea"+ n);
                            if (bitsPorPixel==24 ){//24 bits
                                    int j = offset;
                                    int ro = dataOffset;
                                    int mascara = 0xff;
                                    for (int g = 0; g < ancho; g++) {
                                            int b0 = (((int)(Data[ro++])) & mascara);
                                            int b1 = (((int)(Data[ro++])) & mascara) << 8;
                                            int b2 = (((int)(Data[ro++])) & mascara) << 16;
                                            dataEnInt[j] = 0xff000000 | b0 | b1 | b2;
                                             //System.out.println("data en int" + b0 +" " +b1+ " "+b2 ); 
                                             //System.out.println("data en int" + dataEnInt[j] ); 
                                             
                                            j++;
                                    }
                            }
                                    // 8 bits y menos
                            else {
                                    extraerDatos(Data, dataOffset, bitsPorPixel, dataEnByte, offset, ancho);
                                    
                                }
                            dataOffset += len;
                            offset -= ancho;
                    }
                         
                
        }


        public MemoryImageSource crearImageSource() {
                MemoryImageSource futuraimagen;
                ColorModel model;
                //necesario para poder desplegarlo despues en el label

                // Con paleta
                if (entradas>0 && bitsPorPixel!=24) {
                    model = new IndexColorModel(bitsPorPixel, entradas, r, g, b);
                } else {
                        model = ColorModel.getRGBdefault(); //sin
                }

                if (bitsPorPixel > 8) {
                        // use one int per pixel
                        futuraimagen = new MemoryImageSource(ancho,
                                alto, model, dataEnInt, 0, ancho);
                }  
                else {
                            // use one byte per pixel
                            futuraimagen = new MemoryImageSource(ancho,
                                alto, model, dataEnByte, 0, ancho);
                    }
               
                return futuraimagen;      
        }


        private int leerEnteros() throws IOException {
            int bit1 = archivo.read();
            int bit2 = archivo.read();
            int bit3 = archivo.read();
            int bit4 = archivo.read();
            PosicionActual += 4;
            return ((bit4 << 24) + (bit3 << 16) + (bit2 << 8) + (bit1 << 0));
        }

        private short leerShort() throws IOException {
            int bit1 = archivo.read();
            int bit2 = archivo.read();
            PosicionActual += 2;
            return (short)((bit2 << 8) + bit1);
        }
        
        private byte [] intToWord (int parValue) {
            byte retValue [] = new byte [2];
            retValue [0] = (byte) (parValue & 0x00FF);
            retValue [1] = (byte) ((parValue >> 8) & 0x00FF);
            return (retValue);
          }
        
         private byte [] intToDWord (int parValue) {
            byte retValue [] = new byte [4];
            retValue [0] = (byte) (parValue & 0x00FF);
            retValue [1] = (byte) ((parValue >> 8) & 0x000000FF);
            retValue [2] = (byte) ((parValue >> 16) & 0x000000FF);
            retValue [3] = (byte) ((parValue >> 24) & 0x000000FF);
            return (retValue);
          } 

         

        

        void extraerDatos(byte[] Data, int dataOffset, int bpp, byte[] dataEnByte, int byteOffset, int w) throws Exception {
            byte mascara = 0;
            int pixPerByte = 0;
            int j = byteOffset;
            int k = dataOffset;
                

            switch (bpp) {
                case 1:{
                    mascara = (byte)0x01;
                    pixPerByte = 8; 
                    }break; 
                    
                case 4: {
                    mascara = (byte)0x0f;
                    pixPerByte = 2;
                    } break;
                    
                case 8:{
                    mascara = (byte)0xff;
                    pixPerByte = 1;
                } break;
                
               
             }
            int ite = 0;
            while(true){
                 int mover = 8 - bpp;
                    for (int h = 0; h < pixPerByte; h++) {
                            byte byte1 = Data[k];
                            byte1 >>= mover;
                            dataEnByte[j] = (byte)(byte1 & mascara);
                            j++;
                            ite++;
                            if (ite == w) return;
                                mover =mover- bpp;
                        }
                        k++;
                }
        }
     
   
    void saveMyLifeTonight (File fileToSave, BufferedImage img) throws FileNotFoundException, IOException, Exception{    
  
    
            int i,j;
            int value;
            byte[] imageInByte = null;
            int[] startPixels, endPixels = null, imR, imG, imB, cool=null;
            byte[] cool2 = null;
            byte[] datas =null;
  
    //CONVERT TO BYTE ARRAY
    
   
    
    if(bitsPorPixel !=24){
        
       
         
     /*
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write( img, "bmp", baos );
    baos.flush();
    imageInByte = baos.toByteArray();
    baos.close(); */
    /* 
   
    ByteBuffer buf = ByteBuffer.allocate(cool.length*4).order(ByteOrder.LITTLE_ENDIAN);
    buf.asIntBuffer().put(cool);
    imageInByte = buf.array(); 
    */
    //cool = ((DataBufferInt)img.getRaster().getDataBuffer()).getData(); 
    /*
    imageInByte = new byte[cool.length * 4];
            for (i = 0; i < cool.length; i++) {
                 imageInByte[i * 4] = (byte) (cool[i] & 0xFF);
                 imageInByte[i * 4 + 1] = (byte) ((cool[i] & 0xFF00) >> 8);
                 imageInByte[i * 4 + 2] = (byte) ((cool[i] & 0xFF0000) >> 16);
                 imageInByte[i * 4 + 3] = (byte) ((cool[i] & 0xFF000000) >> 24);
            } */
      //Bitmap bm8bit = new Bitmap("E:\\8bit.bmp");
    //BufferedImage convertida = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_BGR);
    //Bitmap bm24bit = new Bitmap(bm8bit.Width, bm8bit.Height, System.Drawing.Imaging.PixelFormat.Format24bppRgb);
    //Graphics g = Graphics.FromImage(bm24bit);
    //Graphics2D g = convertida.createGraphics();
    //g.drawImage(img, 0, 0, null);
    //g.dispose();
    
       //endPixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData(); 
               
       
    //((DataBufferByte)img.getRaster().getDataBuffer()).getData(); 
              
    //g.DrawImage(bm8bit, 0, 0, bm8bit.Width, bm8bit.Height);
    //bm24bit.Save(@"C:\bit24.bmp", System.Drawing.Imaging.ImageFormat.Bmp);
    //g.Dispose();
    //bm8bit.Dispose();
    //bm24bit.Dispose();
     
    /*
    //startPixels = new int[ancho*alto];
    //img.getRGB(0, 0, ancho, alto, startPixels, 0, ancho);
    cool = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
    
   
    imageInByte = new byte[cool.length << 2];
    
    for (i=0; i<cool.length; i++) {
        int x = cool[i];
        j = i << 2;
        imageInByte[j++] = (byte) ((x >>> 0) & 0xff);           
        imageInByte[j++] = (byte) ((x >>> 8) & 0xff);
        imageInByte[j++] = (byte) ((x >>> 16) & 0xff);
        imageInByte[j++] = (byte) ((x >>> 24) & 0xff);
    }*/
    //endPixels = startPixels;
    //imR = new int[endPixels.length];
    //imG = new int[endPixels.length];
    //imB = new int[endPixels.length];
        /*
          for(i = 0; i < endPixels.length; i++){
             imR[i] = (endPixels[i] >> 16) & 0x000000FF;
             imG[i] = (endPixels[i] >> 8) & 0x000000FF;
             imB[i] = endPixels[i] & 0x000000FF;
          } */
    
                //int[] pixels32 = (int[])ip.getPixels();
		//double[] w = ColorProcessor.getWeightingFactors();
		//if (((ColorProcessor)ip).getRGBWeights()!=null)
			//w = ((ColorProcessor)ip).getRGBWeights();
		//double rw=w[0], gw=w[1], bw=w[2];
                 startPixels = new int[ancho*alto];
                 img.getRGB(0, 0, ancho, alto, startPixels, 0, ancho);
                //double rWeight=1d/3d, gWeight=1d/3d,	bWeight=1d/3d; 
                //double rw=rWeight, gw=gWeight, bw=bWeight;
                double rw=0.33, gw=0.33, bw=0.33;
                //to use 0.299, 0.587 and 0.114.
		//byte[] pixels8 = new byte[width*height];
                 
                imageInByte = new byte[ancho*alto];
		int c, r, g, b;
		for (i=0; i < ancho*alto; i++) {
                   
			c = startPixels[i];
			r = (c&0xff0000)>>16;
			g = (c&0xff00)>>8;
			b = c&0xff;
			imageInByte[i] = (byte)(r + g + b);
                        //imageInByte[i] = (byte)(r + g + b +0.5);
                        
		} 
		//return new ByteProcessor(width, height, pixels8, null);
        
    
                //ByteBuffer wrap = ByteBuffer.wrap(imageInByte);
                
                /*
                j=0;
                for (i=0; i<cool.length; i++){
                // Copia o int, byte a byte.
                int a = cool[i];
                byte[] ret = new byte[4];
                ret[3] = (byte) (a & 0xFF);
                ret[2] = (byte) ((a >> 8) & 0xFF);
                ret[1] = (byte) ((a >> 16) & 0xFF);
                ret[0] = (byte) ((a >> 24) & 0xFF);
                imageInByte[j]= ret;
                j++;
                }
                 */
                //imageInByte.getBytes(img);
                //cool2 =(DataBufferByte) img.getRaster().getDataBuffer();
                //cool2 = ((DataBufferByte)img.getRaster().getDataBuffer()).getData();
    }
    else {
    
        startPixels = new int[ancho*alto];
        img.getRGB(0, 0, ancho, alto, startPixels, 0, ancho);
         endPixels = startPixels;
          imR = new int[endPixels.length];
          imG = new int[endPixels.length];
          imB = new int[endPixels.length];

          for(i = 0; i < endPixels.length; i++){
             imR[i] = (endPixels[i] >> 16) & 0x000000FF;
             imG[i] = (endPixels[i] >> 8) & 0x000000FF;
             imB[i] = endPixels[i] & 0x000000FF;
          }
         //or this cool version
         //int[] cool = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
      

        }
   
    
    //bitsPorPixel=24;
    //coloresPaleta=0;
    
    
    int pad;
    int BITMAPFILEHEADER_SIZE = 14;
    int BITMAPINFOHEADER_SIZE = 40;
    FileOutputStream fileStream = new FileOutputStream (fileToSave.getAbsolutePath()+".bmp");
    BufferedOutputStream bufferedStream = new BufferedOutputStream(fileStream);
    byte rgb [] = new byte [3];
    
      
    
    
            fileStream.write (intToWord (tipoArchivo));
            fileStream.write (intToDWord (tamArchivo));
            fileStream.write (intToWord (reservado1));
            fileStream.write (intToWord (reservado2));
            fileStream.write (intToDWord (bmpOffset));
            
            fileStream.write (intToDWord (size));
            fileStream.write (intToDWord (ancho));
            fileStream.write (intToDWord (alto));
            fileStream.write (intToWord (planos));
            fileStream.write (intToWord (bitsPorPixel));
            fileStream.write (intToDWord (comprimido));
            fileStream.write (intToDWord (tamBMP));
            fileStream.write (intToDWord (ResHor));
            fileStream.write (intToDWord (ResVert));
            fileStream.write (intToDWord (coloresPaleta));
            fileStream.write (intToDWord (coloresImportantes));
            
            int len = tamScan;
            int dataOffset = 0;
            int offset = (alto - 1) * ancho;
            
            //imprimir todoooooooooooo
           
            if(bitsPorPixel==24)
              pad = 4 - ((alto * 3) % 4);
            else
              pad = 4 - ((ancho) % 4);
            if (pad == 4)       // <==== Bug correction
              pad = 0;            // <==== Bug correction

            int counter=0;
            
                  
            
            if (entradas>0) {

                            int reserved;
                            for (i = 0; i < entradas; i++) {
                                    bufferedStream.write(b[i]);
                                    bufferedStream.write(g[i]);
                                    bufferedStream.write(r[i]);
                                    bufferedStream.write(0x00);
                                    
                            }
                            
          
                     
             } 
            
            /*
            if(salto>0){
            byte hola;
            
            
            for (i = 1; i <= salto; i++)
                 bufferedStream.write (0x00);
            
            } 
            }*/
           for(int row = alto; row>0; row--) {
               
                for(int col = 0; col<ancho; col++) {
                    
                    if(bitsPorPixel == 24) {
                        value = endPixels [(row-1)* ancho + col ];
                        rgb [0] = (byte) (value & 0xFF);
                        rgb [1] = (byte) ((value >> 8) & 0xFF);
                        rgb [2] = (byte) ((value >> 16) & 0xFF);
                        bufferedStream.write(rgb);
                        //bufferedStream.write( imageInByte[(row-1)* ancho + col ]);
                    } else
                        bufferedStream.write( imageInByte[(row-1)* ancho + col ]);
                        //bufferedStream.write(cool2[(row-1)* ancho + col ]);
                        //bufferedStream.write(cool2 [(row-1)* ancho + col ]);
                    ++counter;
                }
                for (i = 1; i <= pad; i++)
                bufferedStream.write (0x00);
                counter += pad;
              }
           
            bufferedStream.close();
            fileStream.close ();
    
    
    
    
    
    }
            
        
  


 
}
