/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cesar.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

    public Montador(String path, String out) {
        this.path = path;
        this.out = out;
    }

    public void Monta() throws FileNotFoundException {
        File file = new File(this.path);
        FileWriter fw;
        PrintWriter pw = null;
        BufferedReader br;
        BufferedWriter bw;
        String currentLine;
        String[] line;
        try {
            fw = new FileWriter(file.getPath().replace(file.getName(), "") + this.out);
            pw = new PrintWriter(fw);
            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            while (null != (currentLine = br.readLine())) {
                //System.out.println(currentLine);
                line = currentLine.split(" ");
                String inst = line[0];
                //System.out.println(line[0]);
                int mmm1 = 0, rrr1 = 0, mmm2 = 0, ddd = 0, rrr2 = 0, b1, b2;
                switch (inst) {
                    case "NOP":
                        pw.printf("0\n");
                        break;
                    case "HLT":
                        pw.printf("255\n");
                        break;
                    case "JMP":
                        //0100xxxx xxmmmrrr
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 64;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "BR":
                        //BR 127
                        //0011cccc dddddddd
                        //00110000
                        ddd = Integer.parseInt(line[1]);
                        b1 = 48;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BNE":
                        //00110001
                        ddd = Integer.parseInt(line[1]);
                        b1 = 49;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BEQ":
                        //00110010
                        ddd = Integer.parseInt(line[1]);
                        b1 = 50;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BPL":
                        //00110011
                        ddd = Integer.parseInt(line[1]);
                        b1 = 51;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BMI":
                        //00110100
                        ddd = Integer.parseInt(line[1]);
                        b1 = 52;
                        pw.printf("%d %d\n", b1, ddd);
                    case "BVC":
                        //00110101
                        ddd = Integer.parseInt(line[1]);
                        b1 = 53;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BVS":
                        //00110110
                        ddd = Integer.parseInt(line[1]);
                        b1 = 54;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BCC":
                        //00110111
                        ddd = Integer.parseInt(line[1]);
                        b1 = 55;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BCS":
                        //00111000
                        ddd = Integer.parseInt(line[1]);
                        b1 = 56;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BGE":
                        //00111001
                        ddd = Integer.parseInt(line[1]);
                        b1 = 57;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BLT":
                        //00111010
                        ddd = Integer.parseInt(line[1]);
                        b1 = 58;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BGT":
                        //00111011
                        ddd = Integer.parseInt(line[1]);
                        b1 = 59;
                        pw.printf("%d %d\n", b1, ddd);
                        System.out.println(b1 + " " + ddd);
                        break;
                    case "BLE":
                        //00111100
                        ddd = Integer.parseInt(line[1]);
                        b1 = 60;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BHI":
                        //00111101
                        ddd = Integer.parseInt(line[1]);
                        b1 = 61;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "BLS":
                        //00111110
                        ddd = Integer.parseInt(line[1]);
                        b1 = 62;
                        pw.printf("%d %d\n", b1, ddd);
                        break;
                    case "CLR":
                        // 1000cccc xxmmmrrr
                        //10000000
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 128;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "NOT":
                        //10000001
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 129;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "INC":
                        //10000010
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 130;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "DEC":
                        //10000011
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 131;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "NEG":
                        //10000100
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 132;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "TST":
                        //10000101
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 133;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "ROR":
                        //10000110
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 134;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "ROL":
                        //10000111
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 135;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "ASR":
                        //10001000
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 136;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "ASL":
                        //10001001
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 137;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "ADC":
                        //10001010
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 138;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "SBC":
                        //10001011
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        b1 = 139;
                        b2 = mmm1 * 8 + rrr1;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "MOV":
                        // Precisa de todos os modos de end
                        //1001mmmrrrmmmrrr
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        if (line[2].charAt(0) == 'R') {
                            mmm2 = 0;
                            rrr2 = line[2].charAt(1) - 48;
                        }
                        //System.out.println(rrr1);
                        b1 = 144 + mmm1 * 2 + rrr1 / 4;
                        b2 = (rrr1 % 4) * 64 + mmm2 * 8 + rrr2;
                        System.out.println(rrr1 + " " + rrr2);
                        pw.printf("%d %d\n", b1, b2);
                        //pw.write("a");
                        break;
                    case "ADD":
                        //ADD R2 R7
                        //1cccmmmr
                        //rrmmmrrr
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        if (line[2].charAt(0) == 'R') {
                            mmm2 = 0;
                            rrr2 = line[2].charAt(1) - 48;
                        }
                        b1 = 160 + mmm1 * 2 + rrr1 / 4;
                        b2 = (rrr1 % 4) * 64 + mmm2 * 8 + rrr2;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "SUB":
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        if (line[2].charAt(0) == 'R') {
                            mmm2 = 0;
                            rrr2 = line[2].charAt(1) - 48;
                        }
                        b1 = 176 + mmm1 * 2 + rrr1 / 4;
                        b2 = (rrr1 % 4) * 64 + mmm2 * 8 + rrr2;
                        pw.printf("%d %d\n", b1, b2);
                        break;
                    case "CMP":
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        if (line[2].charAt(0) == 'R') {
                            mmm2 = 0;
                            rrr2 = line[2].charAt(1) - 48;
                        }
                        b1 = 192 + mmm1 * 2 + rrr1 / 4;
                        b2 = (rrr1 % 4) * 64 + mmm2 * 8 + rrr2;
                        pw.printf("%d %d\n", b1, b2);
                        //11000000
                        break;
                    case "AND":
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        if (line[2].charAt(0) == 'R') {
                            mmm2 = 0;
                            rrr2 = line[2].charAt(1) - 48;
                        }
                        b1 = 208 + mmm1 * 2 + rrr1 / 4;
                        b2 = (rrr1 % 4) * 64 + mmm2 * 8 + rrr2;
                        pw.printf("%d %d\n", b1, b2);
                        //11010000
                        break;
                    case "OR":
                        if (line[1].charAt(0) == 'R') {
                            mmm1 = 0;
                            rrr1 = line[1].charAt(1) - 48;
                        }
                        if (line[2].charAt(0) == 'R') {
                            mmm2 = 0;
                            rrr2 = line[2].charAt(1) - 48;
                        }
                        b1 = 224 + mmm1 * 2 + rrr1 / 4;
                        b2 = (rrr1 % 4) * 64 + mmm2 * 8 + rrr2;
                        pw.printf("%d %d\n", b1, b2);
                        //11100000
                        break;
                }
            }
        } catch (Exception Ex) {
            System.out.println(Ex);
        }
        pw.close();
    }
}
