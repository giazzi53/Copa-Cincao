
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GuilhermeSilvestreGi
 */
public class Grupo {
    private int numGrupo;
    private List<Jogador> listaJog = new ArrayList();
    private List<Jogo> jogos = new ArrayList(); 
    private Jogador primeiro;
    private Jogador segundo;
    
    public Grupo(){
        
    }
    
    public Grupo(int numGrupo){
        this.numGrupo = numGrupo;
    }

    public int getNumGrupo() {
        return numGrupo;
    }

    public void getListaJog() {
        for(Jogador j : listaJog){
            System.out.println(j.getNome() + "(" + j.getTime().getNomeTime() + ") tem " + j.getPts() + " pts e " + j.getSaldo() + " de saldo");
        }
    }

    public void setListaJog(List<Jogador> listaJog) {
        this.listaJog = listaJog;
    }
    
    

    public void setNumGrupo(int numGrupo) {
        this.numGrupo = numGrupo;
    }
    
    
    public void addJogGrupo(Jogador jogador){
        listaJog.add(jogador);
    }
    
    public List getJogGrupo(){
        return this.listaJog;
    }

    public Jogador getPrimeiro() {
        return listaJog.get(0);
    }

    public void setPrimeiro(Jogador primeiro) {
        this.primeiro = primeiro;
    }

    public Jogador getSegundo() {
        return listaJog.get(1);
    }

    public void setSegundo(Jogador segundo) {
        this.segundo = segundo;
    }
    
    
    
    
    
    public void organizaJogos(int cont){
        //separando as disputas
        int numJogo = 0;
        
        //System.out.println("aaaa");
        //System.out.println(result);
        for(int a = 0; a < listaJog.size(); a++){
            for(int b = a+1; b < listaJog.size(); b++){
                int result = cont + numJogo;
                Jogo jogo = new Jogo(listaJog.get(a), listaJog.get(b), result);   
                jogos.add(jogo);
                numJogo += 3;
            }
         } 
        
        //separando os numeros dos jogos
        
        
    }
    
    public void getJogos(){
//        Collections.sort(jogos);
        for(Jogo j : jogos){
            System.out.println(" (" + j.getNumJogo() + ") " + j.getJogo());
        }
    }
    
    public Jogo getJogo(int num){
        return jogos.get(num);
    }
    
       
    public void arrumaGrupo(){
        /*//for(int a = 0; a < listaJog.size(); a++){
        for(Jogador j : listaJog){
            if(j.){
                
            }
        }*/
        List <Jogador> auxListaJog = new ArrayList();
        auxListaJog.add(null);
        //auxListaJog.set(0, listaJog.get(0));
        
        for(int a = 0; a < listaJog.size()-1; a++){
            for(int b = a+1; b < listaJog.size(); b++){
                if(listaJog.get(a).getPts() > listaJog.get(b).getPts()){
                    System.out.print("");
                    /*listaJog.set(a, listaJog.get(a));
                    listaJog.set(b, listaJog.get(b));*/
                } else if(listaJog.get(b).getPts() > listaJog.get(a).getPts()){
                    auxListaJog.set(0,listaJog.get(b));
                    listaJog.set(b, listaJog.get(a));
                    listaJog.set(a, auxListaJog.get(0));
                } else{
                    if(listaJog.get(a).getSaldo() >= listaJog.get(b).getSaldo()){
                        System.out.print("");
                        /*listaJog.set(a, listaJog.get(a));
                        listaJog.set(b, listaJog.get(b));*/
                    } else if(listaJog.get(b).getSaldo() > listaJog.get(a).getSaldo()){
                        auxListaJog.set(0,listaJog.get(b));
                        listaJog.set(b, listaJog.get(a));
                        listaJog.set(a, auxListaJog.get(0));
                    } /*else{
                        //System.out.println("\nOs dois jogadores estão empatados em pontos e saldo de gols. O resultado será sorteado");
                        Random r = new Random();
                        int aleatorio = r.nextInt(2);
                        if (aleatorio == 0){
                            System.out.print("");
                            //listaJog.set(a, listaJog.get(a));
                        } else{
                            auxListaJog.set(0,listaJog.get(b));
                            listaJog.set(b, listaJog.get(a));
                            listaJog.set(a, auxListaJog.get(0));
                        }
                        //System.out.println("\nO jogador sorteado foi o " + maiorJog.getNome());
                    }*/
                  
                }
            }
            
            
        }
        /*for(int a = 0; a < listaJog.size()-1; a++){
            for(int b = a+1; b < listaJog.size(); b++){
                //auxListaJog.add(listaJog.get(a).getMelhorJog(listaJog.get(a), listaJog.get(b)));
                //auxListaJog.add(listaJog.get(a).getMelhorJog(listaJog.get(a), listaJog.get(a+1)));
            }
            
        }
        
        listaJog = auxListaJog;*/
    }
}
