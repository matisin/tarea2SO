package tarea2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LectorArchivo {

    private BufferedReader br;
    private String sCurrentLine;

    public LectorArchivo(String archivo) throws FileNotFoundException {
        this.br = new BufferedReader(new FileReader(archivo));
    }

    public void descartarNucleotidos(int Q,String archivo) throws IOException {
        int nlinea = 0;
        ArrayList nucleotidos = new ArrayList();
        PrintWriter writer = new PrintWriter("Salida_Secuencias.txt", "UTF-8");
        br.close();
        br = new BufferedReader(new FileReader(archivo));
        while ((sCurrentLine = br.readLine()) != null) {
            nlinea++;
            if (sCurrentLine.startsWith("@")) {
                nlinea = 1;
            }
            if (nlinea == 2) {
                for (int i = 0; i < sCurrentLine.length(); i++) {
                    nucleotidos.add(sCurrentLine.charAt(i));
                }
            }
            if (nlinea == 4) {
                
                char[] linea = sCurrentLine.toCharArray();
                for (int i = 0; i < linea.length; i++) {
                    if (linea[i] - 33 >= Q) {
                       // System.out.println("caracter 'a' = " + (int)'‘');
                        System.out.print("linea[i] = " +  linea[i] + "= " + (int)linea[i] + " ");
                        System.out.print("Q[" + i + "]= " + (linea[i] -33) + " ");
                        writer.print(nucleotidos.get(i));
                    }
                }
                
                writer.println("");
                System.out.println("");
            }
        }
        writer.close();
    }

    /**
     * Verifica que la cadena de nucleótidos no contenga caracteres que no son
     * validos, es decir, cualquiera que no sea A,G,T,C.
     *
     * @throws IOException
     */
    public void comprobarNucleotidos() throws IOException {
        int count = 0, lineaError = 0, numLinea = 0;
        ArrayList lineas = new ArrayList();
        //Lee todo el archivo
        while ((sCurrentLine = br.readLine()) != null) {
            lineaError++;
            if (sCurrentLine.startsWith("@")) {
                numLinea = 1;
            }
            if (numLinea == 2) {
                char[] array = sCurrentLine.toCharArray();
                count = 0;
                //Comprueba que no tenga errores la cadena
                for (int i = 0; i < array.length; i++) {
                    if (array[i] != 'A' && array[i] != 'G' && array[i] != 'T' && array[i] != 'C') {
                        count++;
                        lineas.add(lineaError);
                    }
                }
            }
            numLinea++;
        }
        System.out.println("La cadena de nucleótidos contiene " + count + " elementos no válidos en las líneas " + lineas);
        br.close();
    }
}
