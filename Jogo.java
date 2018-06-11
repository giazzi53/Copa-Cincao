/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GuilhermeSilvestreGi
 */
public class Jogo {
    private Jogador jog1;
    private Jogador jog2;
    private int numJogo;
    private String placar = "";
    
    public Jogo(){
        
    }
    
    public Jogo(Jogador jog1, Jogador jog2, int numJogo){
        this.jog1 = jog1;
        this.jog2 = jog2;
        this.numJogo = numJogo;
    }

    public Jogador getJog1() {
        return jog1;
    }

    public void setJog1(Jogador jog1) {
        this.jog1 = jog1;
    }

    public Jogador getJog2() {
        return jog2;
    }

    public void setJog2(Jogador jog2) {
        this.jog2 = jog2;
    }

    public int getNumJogo() {
        return numJogo;
    }

    public void setNumJogo(int numJogo) {
        this.numJogo = numJogo;
    }
    
    public String getJogo(){
        return jog1.getNome() + " X " + jog2.getNome();
    }
    
    public String getPlacar(){
        return placar;
    }
    
    boolean jaFoi = false;
    public void setPlacar(int gols1, int gols2){
        //placar = gols1 + " X " + gols2;
        
        if(!jaFoi){
            int saldo1 = gols1 - gols2;
            int saldo2 = gols2 - gols1;
            if(gols1 > gols2){
                jog1.setResult(3, saldo1);
                jog2.setResult(0, saldo2);
            } else if(gols2 > gols1){
                jog2.setResult(3, saldo2);
                jog1.setResult(0, saldo1);
            } else{
                jog1.setResult(1, saldo1);
                jog2.setResult(1, saldo2);
            }
            
            placar = gols1 + " X " + gols2;
                    
        } else{
            System.out.println("\nEsta partida ja foi jogada, ela ja possui um placar");
        }
        jaFoi = true;
    }
}
