/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cesar.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author vitor
 */
public class Montador {
    private String path;
    private String out;
    public Montador(String path, String out){
        this.path = path;
        this.out = out;
    }
    public void Monta() throws FileNotFoundException{
        File file = new File(this.path);
        FileWriter fw;
        PrintWriter pw;
        BufferedReader br;
        String currentLine;
        String [] line;
        try{
            fw = new FileWriter(file.getPath().replace(file.getName(), "") + this.out);
            pw = new PrintWriter(fw);
            br = new BufferedReader(new FileReader (file.getAbsolutePath()));
            while(null != (currentLine = br.readLine())){
                line = currentLine.split(" ");
                String inst = line[0];
                switch (inst){
                    case "NOP":
                        pw.printf("0");
                    case "HLT":
                        pw.printf("255");
                    case "JMP":
                    case "BR":
                    case "BNE":
                    case "BEQ":
                    case "BPL":
                    case "BVC":
                    case "BVS":
                    case "BCC":
                    case "BCS":
                    case "BGE":
                    case "BLT":
                    case "BGT":
                    case "BLE":
                    case "BHI":
                    case "BLS":
                    case "CLR":
                    case "NOT":
                    case "INC":
                    case "DEC":
                    case "NEG":
                    case "TST":
                    case "ROR":
                    case "ROL":
                    case "ASR":
                    case "ASL":
                    case "ADC":
                    case "SBC":
                    case "MOV":
                        int mmm1=0, rrr1=0, mmm2=0, rrr2=0, b1,b2,b3;
                        // Precisa de todos os modos de end
                        
                        if(line[1].charAt(0) == 'R'){
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        if(line[2].charAt(0) == 'R'){
                            mmm2 = 0;
                            rrr2 = line[2].charAt(1) - 48;
                        }
                        b1 = 144 + mmm1*2 + rrr1/4;
                        b2 = (rrr1%4)*64 + mmm2*8 + rrr2;
                        pw.printf("%d %d",b1,b2);
                    case "ADD":
                    case "SUB":
                    case "CMP":
                    case "AND":
                    case "OR":
                }
            }
        } catch(Exception Ex){
            System.out.println(Ex);
        }
    }
        
    
}
