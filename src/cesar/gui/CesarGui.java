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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author idsro
 */
public class CesarGui extends javax.swing.JFrame {
    private int pc;
    private int [] R;
    /**
     * Creates new form CesarGui
     */
   
    
    public CesarGui() {
        initComponents();
        this.pc = 0;
        
        // Trabalhar com bytes como se fossem inteiros
        //byte [] b = hexStringToByteArray("");
//        k = (byte) (i & j);  // & and
//        k = (byte) (i | j);  // | OR
//        k = (byte) ~j ;      // ~ NOT
//        k = i ^ j;           // ^ XOR
//        k = (byte) (j>>1);   // SHIFT RIGHT
//        k = (byte) (j<<1);   // SHIFT LEFT
//        System.out.println(b.length);
//        for(int i=0; i<b.length;i++){
//            System.out.println(b[i]);
//        }
       
        
        
       //System.out.println(k); 
        DefaultTableModel dtm = (DefaultTableModel) tb_cod_mem.getModel();
        dtm.setRowCount(0);
        tb_cod_mem.setModel(dtm);
        dtm = (DefaultTableModel) tb_bd.getModel();
        dtm.setRowCount(0);
        tb_bd.setModel(dtm);
        R = new int[8];
        for (int i = 0; i < 8; i++) {
            R[i] = 0;
        }
    }
    public String getInstruction(String inst){
        int cod = Integer.parseInt(inst) / 16;
        int codm = Integer.parseInt(inst) % 16;
        switch (cod){
            case 0:
                return "NOP";
            case 15:
                return "HLT";
            case 4:
                return "JMP";
                // Mais coisas no case 3
            case 3:
                switch (codm){
                    case 0:
                        return "BR";
                    case 1:
                        return "BNE";
                    case 2:
                        return "BEQ";
                    case 3:
                        return "BPL";
                    case 4:
                        return "BPL";
                    case 5:
                        return "BVC";
                    case 6:
                        return "BVS";
                    case 7:
                        return "BCC";
                    case 8:
                        return "BCS";
                    case 9:
                        return "BGE";
                    case 10:
                        return "BLT";
                    case 11:
                        return "BGT";
                    case 12:
                        return "BLE";
                    case 13:
                        return "BHI";
                    case 14:
                        return "BLS";
                }
                // Mais coisas no case 8
            case 8:
                switch (codm) {
                    case 0:
                        return "CLR";
                    case 1:
                        return "NOT";
                    case 2:
                        return "INC";
                    case 3:
                        return "DEC";
                    case 4:
                        return "NEG";
                    case 5:
                        return "TST";
                    case 6:
                        return "ROR";
                    case 7:
                        return "ROL";
                    case 8:
                        return "ASR";
                    case 9:
                        return "ASL";
                    case 10:
                        return "ADC";
                    case 11:
                        return "SBC";
                }
            case 9:
                return "MOV";
            case 10:
                return "ADD";
            case 11:
                return "SUB";
            case 12:
                return "COMP";
            case 13:
                return "AND";
            case 14: 
                return "OR";
            default:
                return "";
        }
             
        
    }
    public int getInstructionSize(String inst){
        if(null == inst){
            return 0;
        } else switch (inst) {
            case "NOP":
            case "HLT":
                return 0;
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
                return 1;
            default:
                return 1;
        }
    }
    public void execInst(String inst){
        System.out.println(inst);
            int i, mmm, rrr, op, des, carry, pos; 
            int mmm2, rrr2, i2, op1, op2;
            boolean N, Z, V, C;
            switch (inst) {
                case "NOP":
                    R[7] += 1;
                    break;
                case "JMP":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    switch(mmm){
                        case 0:
                            R[7] += 1;
                            break;
                        case 1:
                            R[7] = R[rrr];
                            R[rrr] += 1;
                            break;
                        case 2:
                            R[7] = R[rrr];
                            R[rrr] -= 1;
                            break;
                        case 3:
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                            R[7] = R[rrr] + des;
                            break;
                        case 4:
                            R[7] = R[rrr];
                            break;
                        case 5:
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            R[7] = pos;
                            R[rrr] += 1;
                            break; 
                        case 6:
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            R[7] = pos;
                            R[rrr] -= 1;
                            break;
                        case 7:
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            R[7] = pos;
                            break;
                    }
                    break;                        
                case "BR":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    R[7] = R[7] + i; 
                    break;
                case "BNE":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    Z = jRadioButton2.isSelected();
                    if(!Z){
                        R[7] = R[7] + i;
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BEQ":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    Z = jRadioButton2.isSelected();
                    if(Z){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BPL":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    N = jRadioButton5.isSelected();
                    if(!N){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BMI":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    N = jRadioButton5.isSelected();
                    if(N){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BVC":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    V = jRadioButton4.isSelected();
                    if(!V){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2; 
                    }
                    break;
                case "BVS":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    V = jRadioButton4.isSelected();
                    if(V){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BCC":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    C = jRadioButton6.isSelected();
                    if(!C){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BCS":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    C = jRadioButton6.isSelected();
                    if(C){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BGE":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    N = jRadioButton5.isSelected();
                    V = jRadioButton4.isSelected();
                    if(N == V){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BLT":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    N = jRadioButton5.isSelected();
                    V = jRadioButton4.isSelected();
                    if(N != V){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BGT":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    N = jRadioButton5.isSelected();
                    V = jRadioButton4.isSelected();
                    Z = jRadioButton2.isSelected();
                    if((N == V) && !Z){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2; 
                    }
                    break;
                case "BLE":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    N = jRadioButton5.isSelected();
                    V = jRadioButton4.isSelected();
                    Z = jRadioButton2.isSelected();
                    if((N != V) || Z){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BHI":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    C = jRadioButton6.isSelected();
                    Z = jRadioButton2.isSelected();
                    if(!C && !Z){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2;
                    }
                    break;
                case "BLS":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    C = jRadioButton6.isSelected();
                    Z = jRadioButton2.isSelected();
                    if(C || Z){
                        R[7] = R[7] + i; 
                    }
                    else{
                        R[7] = R[7] + 2; 
                    }
                    break;
                case "CLR":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    jRadioButton6.setSelected(false); // desliga o C
                    jRadioButton4.setSelected(false); // desliga o V
                    jRadioButton5.setSelected(false); // desliga o N
                    jRadioButton2.setSelected(true); // liga o Z
                    switch(mmm){
                        case 0: // Modo registrador                            
                            R[rrr] = 0;                            
                            break;
                        case 1: // Modo registrador pos-incremento                          
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(0, R[rrr] , 1);
                            R[rrr] += 1; // Estamos considerando a memória endereçada a 16 bits caso contrario teria que somar 2 para pular 2 bytes
                            break;
                        case 2: // Modo registrador pré-decremento
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(0, R[rrr] , 1);
                            R[rrr] -= 1;
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(0, R[rrr] + des , 1);
                            break;
                        case 4: // Modo registrador indireto
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(0, R[rrr] , 1);
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(0, pos , 1);
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(0, pos , 1);
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(0, pos , 1);
                            break;           
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }                       
                    break;
                case "NOT":
                    String dado = "", not = "", complet = "";
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));
                    mmm = (i/8)%8;
                    rrr = i % 8;                    
                    jRadioButton6.setSelected(true); // liga o C
                    jRadioButton4.setSelected(false); // desliga o V
                    switch(mmm){
                        case 0: // Modo registrador
                            dado = Integer.toBinaryString(R[rrr]);
                            for (int j = 0; j < 16; j++) {
                                try{
                                    if (dado.charAt(j) == '0') {
                                        not += "1";
                                    } else {
                                        not+= "0";
                                    }
                                }
                                catch(Exception e){
                                    if(i >= dado.length()){
                                        complet += "1";
                                    }
                                }
                            }
                            not = complet + not;
                            R[rrr] = Integer.parseInt(not, 2);
                            if(R[rrr] == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            dado = Integer.toBinaryString(op);
                            for (int j = 0; j < 16; j++) {
                                try{
                                    if (dado.charAt(j) == '0') {
                                        not += "1";
                                    } else {
                                        not+= "0";
                                    }
                                }
                                catch(Exception e){
                                    if(i >= dado.length()){
                                        complet += "1";
                                    }
                                }
                            }
                            not = complet + not;
                            op = Integer.parseInt(not, 2);
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] += 1;
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            dado = Integer.toBinaryString(op);
                            for (int j = 0; j < 16; j++) {
                                try{
                                    if (dado.charAt(j) == '0') {
                                        not += "1";
                                    } else {
                                        not+= "0";
                                    }
                                }
                                catch(Exception e){
                                    if(i >= dado.length()){
                                        complet += "1";
                                    }
                                }
                            }
                            not = complet + not;
                            op = Integer.parseInt(not, 2);
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] -= 1;
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            dado = Integer.toBinaryString(op);
                            for (int j = 0; j < 16; j++) {
                                try{
                                    if (dado.charAt(j) == '0') {
                                        not += "1";
                                    } else {
                                        not+= "0";
                                    }
                                }
                                catch(Exception e){
                                    if(i >= dado.length()){
                                        complet += "1";
                                    }
                                }
                            }
                            not = complet + not;
                            op = Integer.parseInt(not, 2);
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            dado = Integer.toBinaryString(op);
                            for (int j = 0; j < 16; j++) {
                                try{
                                    if (dado.charAt(j) == '0') {
                                        not += "1";
                                    } else {
                                        not+= "0";
                                    }
                                }
                                catch(Exception e){
                                    if(i >= dado.length()){
                                        complet += "1";
                                    }
                                }
                            }
                            not = complet + not;
                            op = Integer.parseInt(not, 2);
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            dado = Integer.toBinaryString(op);
                            for (int j = 0; j < 16; j++) {
                                try{
                                    if (dado.charAt(j) == '0') {
                                        not += "1";
                                    } else {
                                        not+= "0";
                                    }
                                }
                                catch(Exception e){
                                    if(i >= dado.length()){
                                        complet += "1";
                                    }
                                }
                            }
                            not = complet + not;
                            op = Integer.parseInt(not, 2);
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            dado = Integer.toBinaryString(op);
                            for (int j = 0; j < 16; j++) {
                                try{
                                    if (dado.charAt(j) == '0') {
                                        not += "1";
                                    } else {
                                        not+= "0";
                                    }
                                }
                                catch(Exception e){
                                    if(i >= dado.length()){
                                        complet += "1";
                                    }
                                }
                            }
                            not = complet + not;
                            op = Integer.parseInt(not, 2);
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            dado = Integer.toBinaryString(op);
                            for (int j = 0; j < 16; j++) {
                                try{
                                    if (dado.charAt(j) == '0') {
                                        not += "1";
                                    } else {
                                        not+= "0";
                                    }
                                }
                                catch(Exception e){
                                    if(i >= dado.length()){
                                        complet += "1";
                                    }
                                }
                            }
                            not = complet + not;
                            op = Integer.parseInt(not, 2);
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            break;                     
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "INC":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    switch(mmm){
                        case 0: // Modo registrador
                            R[rrr] += 1;
                            if(R[rrr] == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            op += 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] += 1;    
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            op += 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] -= 1; 
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            op += 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op += 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op += 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] += 2;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op += 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op += 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;                    
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "DEC":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    switch(mmm){
                        case 0: // Modo registrador
                            R[rrr] -= 1;
                            if(R[rrr] == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            op -= 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] += 1;    
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            op -= 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] -= 1; 
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            op -= 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op -= 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op -= 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op -= 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op -= 1;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;                    
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "NEG":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    switch(mmm){
                        case 0: // Modo registrador
                            R[rrr] = -R[rrr];
                            if(R[rrr] == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            op = -op;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] += 1;    
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            op = -op;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] -= 1; 
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            op = -op;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = -op;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op = -op;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op = -op;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            op = -op;
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;                   
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "TST":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    jRadioButton6.setSelected(false); // desliga o C
                    jRadioButton4.setSelected(false); // desliga o V
                    switch(mmm){
                        case 0: // Modo registrador
                            if(R[rrr] == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            if(op == 0){
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] += 1;    
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            if(op == 0){
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] -= 1; 
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            if(op == 0){
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            if(op == 0){
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if(op == 0){
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if(op == 0){
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if(op == 0){
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "ROR":                    
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));                    
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    C = jRadioButton6.isSelected();                 
                    switch(mmm){
                        case 0: // Modo registrador
                            carry = R[rrr]%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                R[rrr] = (R[rrr]/2)+32768; 
                            }
                            else{
                                R[rrr] = R[rrr]/2;
                            }
                            if(R[rrr] == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            carry = op%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] += 1;
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            carry = op%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] -= 1;
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            carry = op%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            carry = op%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = op%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = op%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = op%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            break;                    
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "ROL":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));                    
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    C = jRadioButton6.isSelected();                 
                    switch(mmm){
                        case 0: // Modo registrador
                            carry = (R[rrr]/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                R[rrr] = ((R[rrr]*2)%65536)+1; 
                            }
                            else{
                                R[rrr] = (R[rrr]*2)%65536;
                            }
                            if(R[rrr] == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = ((op*2)%65536)+1; 
                            }
                            else{
                                op = (op*2)%65536;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] += 1;
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = ((op*2)%65536)+1; 
                            }
                            else{
                                op = (op*2)%65536;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] -= 1;
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = ((op*2)%65536)+1; 
                            }
                            else{
                                op = (op*2)%65536;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = ((op*2)%65536)+1; 
                            }
                            else{
                                op = (op*2)%65536;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = ((op*2)%65536)+1; 
                            }
                            else{
                                op = (op*2)%65536;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = ((op*2)%65536)+1; 
                            }
                            else{
                                op = (op*2)%65536;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(C){
                                op = ((op*2)%65536)+1; 
                            }
                            else{
                                op = (op*2)%65536;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            break;                    
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "ASR":
                    int msb;
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));                    
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    C = jRadioButton6.isSelected();              
                    switch(mmm){
                        case 0: // Modo registrador
                            carry = R[rrr]%2;
                            msb = (R[rrr]/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(msb == 1){
                                R[rrr] = (R[rrr]/2)+32768; 
                            }
                            else{
                                R[rrr] = R[rrr]/2;
                            }
                            if(R[rrr] == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            carry = op%2;
                            msb = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(msb == 1){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] += 1;
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            carry = op%2;
                            msb = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(msb == 1){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] -= 1;
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            carry = op%2;
                            msb = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(msb == 1){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            carry = op%2;
                            msb = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(msb == 1){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = op%2;
                            msb = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(msb == 1){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = op%2;
                            msb = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(msb == 1){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = op%2;
                            msb = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            if(msb == 1){
                                op = (op/2)+32768; 
                            }
                            else{
                                op = op/2;
                            }
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            break;                    
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "ASL":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));                    
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    C = jRadioButton6.isSelected();                 
                    switch(mmm){
                        case 0: // Modo registrador
                            carry = (R[rrr]/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            R[rrr] = (R[rrr]*2)%65536;                         
                            if(R[rrr] == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            op = (op*2)%65536;
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] += 1;
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            op = (op*2)%65536;
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] -= 1;
                            break;
                        case 3: // Modo registrador indexado
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // desliga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            op = (op*2)%65536;
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            op = (op*2)%65536;
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            op = (op*2)%65536;
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            op = (op*2)%65536;
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            carry = (op/32768)%2;
                            if (carry == 1){
                                jRadioButton6.setSelected(true); // liga o C
                            }
                            else{
                                jRadioButton6.setSelected(false); // desliga o C
                            }
                            op = (op*2)%65536;
                            if(op == 0){                                
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            N = jRadioButton5.isSelected();
                            jRadioButton4.setSelected((N ^ C));
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            break;                    
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "ADC":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));                    
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    C = jRadioButton6.isSelected(); 
                    switch(mmm){
                        case 0: // Modo registrador
                            if (C){
                                R[rrr] += 1;
                            }
                            else{
                                R[rrr] += 0;
                            }
                            if(R[rrr] == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            if (C){
                                op += 1;
                            }
                            else{
                                op += 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] += 1;
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            if (C){
                                op += 1;
                            }
                            else{
                                op += 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] -= 1;
                            break;
                        case 3: // Modo registrador indexado,
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            if (C){
                                op += 1;
                            }
                            else{
                                op += 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            if (C){
                                op += 1;
                            }
                            else{
                                op += 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if (C){
                                op += 1;
                            }
                            else{
                                op += 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if (C){
                                op += 1;
                            }
                            else{
                                op += 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if (C){
                                op += 1;
                            }
                            else{
                                op += 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            break;                     
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "SBC":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1 , 1));                    
                    mmm = (i/8)%8;
                    rrr = i % 8;
                    C = jRadioButton6.isSelected(); 
                    switch(mmm){
                        case 0: // Modo registrador
                            if (C){
                                R[rrr] -= 1;
                            }
                            else{
                                R[rrr] -= 0;
                            }
                            if(R[rrr] == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (R[rrr] < 0){
                                if(R[rrr] < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(R[rrr] > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            break;
                        case 1: // Modo registrador pos-incremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            if (C){
                                op -= 1;
                            }
                            else{
                                op -= 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] += 1;
                            break;
                        case 2: // Modo registrador pré-decremento
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                            if (C){
                                op -= 1;
                            }
                            else{
                                op -= 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            R[rrr] -= 1;
                            break;
                        case 3: // Modo registrador indexado,
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                            if (C){
                                op -= 1;
                            }
                            else{
                                op -= 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] + des , 1);
                            break;
                        case 4: // Modo registrador indireto
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            if (C){
                                op -= 1;
                            }
                            else{
                                op -= 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, R[rrr] , 1);
                            break;
                        case 5: // Modo registrador pos-incremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if (C){
                                op -= 1;
                            }
                            else{
                                op -= 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] += 1;
                            break;
                        case 6: // Modo registrador pre-dercremento indireto
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if (C){
                                op -= 1;
                            }
                            else{
                                op -= 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            R[rrr] -= 1;
                            break;
                        case 7: // Modo registrador indexado indireto
                            des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                            pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                            op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                            if (C){
                                op -= 1;
                            }
                            else{
                                op -= 0;
                            }
                            if(op == 0){
                                jRadioButton6.setSelected(false); // desliga o C
                                jRadioButton4.setSelected(false); // desliga o V
                                jRadioButton5.setSelected(false); // desliga o N
                                jRadioButton2.setSelected(true); // liga o Z
                            }
                            else if (op < 0){
                                if(op < -32768){
                                    jRadioButton6.setSelected(true); // liga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(true); // liga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            else{
                                if(op > 32767){
                                    jRadioButton6.setSelected(true); // desliga o C
                                    jRadioButton4.setSelected(true); // liga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                                else{
                                    jRadioButton6.setSelected(false); // desliga o C
                                    jRadioButton4.setSelected(false); // desliga o V
                                    jRadioButton5.setSelected(false); // desliga o N
                                    jRadioButton2.setSelected(false); // desliga o Z
                                }
                            }
                            ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                            break;                     
                    }
                    if(mmm == 3 || mmm == 7){
                        R[7] += 3;
                    }
                    else{
                        R[7] += 2;
                    }
                    break;
                case "MOV":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    i2 = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2, 1));
                    mmm = (i/2)%8;
                    rrr = ((i % 2) * 4) + (i2 / 64);
                    mmm2 = (i2 / 8) % 8;
                    rrr2 = i2 % 8;
                    op1 = this.get_op(mmm, rrr);
                    op2 = this.get_op(mmm2, rrr2);
                    op2 = op1;
                    if(op2 == 0){
                        jRadioButton4.setSelected(false); // desliga o V
                        jRadioButton5.setSelected(false); // desliga o N
                        jRadioButton2.setSelected(true); // liga o Z
                    }
                    else if (op2 < 0){
                        if(op2 < -32768){
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    else{
                        if(op2 > 32767){
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    this.set_op(mmm, rrr, op1);
                    this.set_op(mmm2, rrr2, op2);
                    break;
                case "ADD":
                    i = Integer.parseInt((String)((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    i2 = Integer.parseInt((String)((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2, 1));
                    mmm = (i/2)%8;
                    rrr = ((i % 2) * 4) + (i2 / 64);
                    mmm2 = (i2 / 8) % 8;
                    rrr2 = i2 % 8;
                    op1 = this.get_op(mmm, rrr);
                    op2 = this.get_op(mmm2, rrr2);
                    op2 = op2 + op1;
                    if(op2 == 0){
                        jRadioButton6.setSelected(false); // desliga o C
                        jRadioButton4.setSelected(false); // desliga o V
                        jRadioButton5.setSelected(false); // desliga o N
                        jRadioButton2.setSelected(true); // liga o Z
                    }
                    else if (op2 < 0){
                        if(op2 < -32768){
                            jRadioButton6.setSelected(true); // liga o C
                            jRadioButton4.setSelected(true); // liga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton6.setSelected(false); // desliga o C
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    else{
                        if(op2 > 32767){
                            jRadioButton6.setSelected(true); // desliga o C
                            jRadioButton4.setSelected(true); // liga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton6.setSelected(false); // desliga o C
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    this.set_op(mmm, rrr, op1);
                    this.set_op(mmm2, rrr2, op2);
                    R[7] += 2;
                    break;
                case "SUB":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    i2 = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2, 1));
                    mmm = (i/2)%8;
                    rrr = ((i % 2) * 4) + (i2 / 64);
                    mmm2 = (i2 / 8) % 8;
                    rrr2 = i2 % 8;
                    op1 = this.get_op(mmm, rrr);
                    op2 = this.get_op(mmm2, rrr2);
                    op2 = op2 - op1;
                    if(op2 == 0){
                        jRadioButton6.setSelected(false); // desliga o C
                        jRadioButton4.setSelected(false); // desliga o V
                        jRadioButton5.setSelected(false); // desliga o N
                        jRadioButton2.setSelected(true); // liga o Z
                    }
                    else if (op2 < 0){
                        if(op2 < -32768){
                            jRadioButton6.setSelected(true); // liga o C
                            jRadioButton4.setSelected(true); // liga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton6.setSelected(false); // desliga o C
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    else{
                        if(op2 > 32767){
                            jRadioButton6.setSelected(true); // desliga o C
                            jRadioButton4.setSelected(true); // liga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton6.setSelected(false); // desliga o C
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    this.set_op(mmm, rrr, op1);
                    this.set_op(mmm2, rrr2, op2);
                    R[7] += 2;
                    break;
                case "CMP":
                    int comp;
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    i2 = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2, 1));
                    mmm = (i/2)%8;
                    rrr = ((i % 2) * 4) + (i2 / 64);
                    mmm2 = (i2 / 8) % 8;
                    rrr2 = i2 % 8;
                    op1 = this.get_op(mmm, rrr);
                    op2 = this.get_op(mmm2, rrr2);
                    comp = op1 - op2;
                    if(comp == 0){
                        jRadioButton6.setSelected(false); // desliga o C
                        jRadioButton4.setSelected(false); // desliga o V
                        jRadioButton5.setSelected(false); // desliga o N
                        jRadioButton2.setSelected(true); // liga o Z
                    }
                    else if (comp < 0){
                        if(comp < -32768){
                            jRadioButton6.setSelected(true); // liga o C
                            jRadioButton4.setSelected(true); // liga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton6.setSelected(false); // desliga o C
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    else{
                        if(comp > 32767){
                            jRadioButton6.setSelected(true); // desliga o C
                            jRadioButton4.setSelected(true); // liga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton6.setSelected(false); // desliga o C
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    this.set_op(mmm, rrr, op1);
                    this.set_op(mmm2, rrr2, op2);
                    break;
                case "AND":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    i2 = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2, 1));
                    mmm = (i/2)%8;
                    rrr = ((i % 2) * 4) + (i2 / 64);
                    mmm2 = (i2 / 8) % 8;
                    rrr2 = i2 % 8;
                    op1 = this.get_op(mmm, rrr);
                    op2 = this.get_op(mmm2, rrr2);
                    op2 = op2 & op1;
                    if(op2 == 0){
                        jRadioButton4.setSelected(false); // desliga o V
                        jRadioButton5.setSelected(false); // desliga o N
                        jRadioButton2.setSelected(true); // liga o Z
                    }
                    else if (op2 < 0){
                        if(op2 < -32768){
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    else{
                        if(op2 > 32767){
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    this.set_op(mmm, rrr, op1);
                    this.set_op(mmm2, rrr2, op2);
                    break;
                case "OR":
                    i = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 1, 1));
                    i2 = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2, 1));
                    mmm = (i/2)%8;
                    rrr = ((i % 2) * 4) + (i2 / 64);
                    mmm2 = (i2 / 8) % 8;
                    rrr2 = i2 % 8;
                    op1 = this.get_op(mmm, rrr);
                    op2 = this.get_op(mmm2, rrr2);
                    op2 = op2 | op1;
                    if(op2 == 0){
                        jRadioButton4.setSelected(false); // desliga o V
                        jRadioButton5.setSelected(false); // desliga o N
                        jRadioButton2.setSelected(true); // liga o Z
                    }
                    else if (op2 < 0){
                        if(op2 < -32768){
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(true); // liga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    else{
                        if(op2 > 32767){
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                        else{
                            jRadioButton4.setSelected(false); // desliga o V
                            jRadioButton5.setSelected(false); // desliga o N
                            jRadioButton2.setSelected(false); // desliga o Z
                        }
                    }
                    this.set_op(mmm, rrr, op1);
                    this.set_op(mmm2, rrr2, op2);
                    break;
            }
            tp_r0.setText(Integer.toString(R[0]));
            tp_r1.setText(Integer.toString(R[1]));
            tp_r2.setText(Integer.toString(R[2]));
            tp_r3.setText(Integer.toString(R[3]));
            tp_r4.setText(Integer.toString(R[4]));
            tp_r5.setText(Integer.toString(R[5]));
            tp_r6.setText(Integer.toString(R[6]));
            tp_r7.setText(Integer.toString(R[7]));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_cod_mem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_bd = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tp_r0 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tp_r2 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tp_r4 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tp_r6 = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        tp_r1 = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        tp_r3 = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        tp_r5 = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        tp_r7 = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        btn_start = new javax.swing.JButton();
        btn_passo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane9 = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextPane10 = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_cod_mem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PC", "Dado", "Instrução"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_cod_mem);

        jLabel1.setText("Código-Memória");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 238, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(158, 158, 158))
        );

        jLabel2.setText("Banco de Dados");

        tb_bd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Endereço", "Dado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_bd);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(79, 79, 79))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addGap(150, 150, 150))
        );

        jScrollPane3.setViewportView(tp_r0);

        jScrollPane4.setViewportView(tp_r2);

        jScrollPane5.setViewportView(tp_r4);

        jScrollPane6.setViewportView(tp_r6);

        jScrollPane7.setViewportView(tp_r1);

        jScrollPane8.setViewportView(tp_r3);

        jScrollPane9.setViewportView(tp_r5);

        jScrollPane10.setViewportView(tp_r7);

        jLabel3.setText("R4:");

        jLabel4.setText("R0:");

        jLabel5.setText("R1:");

        jLabel6.setText("R5:");

        jLabel7.setText("R2:");

        jLabel9.setText("R6: (SP)");

        jLabel10.setText("R3:");

        jLabel11.setText("R7: (PC)");

        btn_start.setText("START");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        btn_passo.setText("Passo-a-Passo");
        btn_passo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_passoActionPerformed(evt);
            }
        });

        jScrollPane11.setViewportView(jTextPane9);

        jScrollPane12.setViewportView(jTextPane10);

        jLabel8.setText("Acessos:");

        jLabel12.setText("Instr.:");

        jLabel13.setText("Execução:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane11)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jRadioButton5.setText("N");

        jRadioButton2.setText("Z");

        jRadioButton4.setText("V");

        jRadioButton6.setText("C");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane6)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator4)
                            .addComponent(jSeparator7))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btn_passo)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jRadioButton5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jRadioButton4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton6)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel5)
                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                                .addComponent(jSeparator6))
                                            .addGap(13, 13, 13))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap()))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addContainerGap()))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(13, 13, 13)))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_start, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_passo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton4)
                                        .addComponent(jRadioButton6))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton5)
                                .addComponent(jRadioButton2)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270))
        );

        jMenu1.setText("File");

        jMenuItem1.setText("Open ...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        // TODO add your handling code here:
        R[7] = 0;
        String inst;
        while(!"HLT".equals(inst = (String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7], 2))){
            execInst(inst);
        }   
    }//GEN-LAST:event_btn_startActionPerformed

    private int get_op(int mmm, int rrr){
        int op = 0, des = 0, pos = 0;
        switch(mmm){
            case 0:
                return R[rrr];
            case 1:
                op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                return op;
            case 2:
                op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1)); 
                return op;
            case 3:
                des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1)); 
                return op;
            case 4:
                op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                return op;
            case 5:
                pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                return op;
            case 6:
                pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                return op;
            case 7:
                des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                op = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(pos , 1));
                return op;
        }
        return op;
    }
    
    private void set_op(int mmm, int rrr, int res){
        int op = 0, des = 0, pos = 0;
        switch(mmm){
            case 0:
                R[rrr] = res;
                break;
            case 1:
                ((DefaultTableModel) tb_bd.getModel()).setValueAt(res, R[rrr] , 1);
                R[rrr] += 1;
                break;
            case 2:
                ((DefaultTableModel) tb_bd.getModel()).setValueAt(res, R[rrr] , 1);
                R[rrr] -= 1; 
                break;
            case 3:
                des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                ((DefaultTableModel) tb_bd.getModel()).setValueAt(res, R[rrr] + des , 1);
                break;
            case 4:
                ((DefaultTableModel) tb_bd.getModel()).setValueAt(res, R[rrr] , 1);
                break;
            case 5:
                pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                R[rrr] += 1;
                break;
            case 6:
                pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] , 1));
                ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                R[rrr] -= 1;
                break;
            case 7:
                des = Integer.parseInt((String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7] + 2 , 1));
                pos = Integer.parseInt((String) ((DefaultTableModel) tb_bd.getModel()).getValueAt(R[rrr] + des , 1));
                ((DefaultTableModel) tb_bd.getModel()).setValueAt(op, pos , 1);
                break;
        }
    }

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
        fc.setCurrentDirectory(new File("/home/vitor/Documentos/Faculdade/05 - Quinto Semestre/PS/Cesar-PS"));
        fc.setAcceptAllFileFilterUsed(false);
        int retVal = fc.showOpenDialog(jMenu1);
        if(retVal == JFileChooser.APPROVE_OPTION){
            File prog = fc.getSelectedFile();
            //System.out.println("Opening: " + file.getAbsolutePath());

            Montador mont = new Montador(prog.getAbsolutePath(), "out.txt");
            BufferedReader br;
            try {
                String currentLine;
                int count = 0;
                int iCount = 0;
                mont.Monta();
                File f = new File("out.txt");
                br = new BufferedReader(new FileReader (f.getAbsolutePath()));
                while(null != (currentLine = br.readLine())){
                    String [] row = new String[3];
                    String [] rowBD = new String[2];
                    String [] aux = currentLine.split(" ");
                    for(int i=0; i<aux.length;i++){
                        row[0] = Integer.toString(count);
                        row[1] = aux[i];
                        rowBD[0] = Integer.toString(count);
                        rowBD[1] = aux[i];
                        if(iCount == 0){
                            row[2] = getInstruction(aux[i]);
                            iCount = getInstructionSize(row[2]) + 1;
                        } else{
                            row[2] = "";
                        }
                        iCount--;
                        ((DefaultTableModel) tb_cod_mem.getModel()).insertRow(count, row);
                        ((DefaultTableModel) tb_bd.getModel()).insertRow(count, rowBD);
                        count += 1;
                    }
                }
                br.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
           
            
        } else{
            System.out.println("Fail to Open!");
        }
        
        for (int i = ((DefaultTableModel) tb_bd.getModel()).getRowCount(); i < 65536; i++) {
            String [] rowBD = new String[2];
            rowBD[0] = Integer.toString(i);
            rowBD[1] = "0";
            ((DefaultTableModel) tb_bd.getModel()).insertRow(i, rowBD);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_passoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_passoActionPerformed
        // TODO add your handling code here:
        
        String inst;
        if(!"HLT".equals(inst = (String) ((DefaultTableModel) tb_cod_mem.getModel()).getValueAt(R[7], 2))){
            execInst(inst);
        }
        
    }//GEN-LAST:event_btn_passoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    } 
    
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
            java.util.logging.Logger.getLogger(CesarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CesarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CesarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CesarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CesarGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_passo;
    private javax.swing.JButton btn_start;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextPane jTextPane10;
    private javax.swing.JTextPane jTextPane9;
    private javax.swing.JTable tb_bd;
    private javax.swing.JTable tb_cod_mem;
    private javax.swing.JTextPane tp_r0;
    private javax.swing.JTextPane tp_r1;
    private javax.swing.JTextPane tp_r2;
    private javax.swing.JTextPane tp_r3;
    private javax.swing.JTextPane tp_r4;
    private javax.swing.JTextPane tp_r5;
    private javax.swing.JTextPane tp_r6;
    private javax.swing.JTextPane tp_r7;
    // End of variables declaration//GEN-END:variables
}
