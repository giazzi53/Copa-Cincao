
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
    private int numJogos;
    private Jogador primeiro;
    private Jogador segundo;
    
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
        this.primeiro = listaJog.get(0);
        return this.primeiro;
    }

    public void setPrimeiro(Jogador primeiro) {
        this.primeiro = primeiro;
    }

    public Jogador getSegundo() {
        this.segundo = listaJog.get(1);
        return this.segundo;
    }

    public void setSegundo(Jogador segundo) {
        this.segundo = segundo;
    }  
    
    public void organizaJogos(int cont, int numGrupos){
        
        /*Separando as disputas dos grupos. Os numeros dos jogos sao adicionados ao numero de grupos
        para que os jogos nao fiquem nas mesmas sequencias dos grupos (no caso de 9 jogadores, um jogo
        é adicionado a 3, pois esse é o numero de grupos). Por exemlo:
        
                            Em vez de                                            Deve ficar
        
          Grupo1            Grupo2            Grupo3       |       Grupo1            Grupo2            Grupo3
        (1) A x B         (4) D x E         (7) G x H      |     (1) A x B         (2) D x E         (3) G x H
        (2) A x C         (5) D x F         (8) G x I      |     (4) A x C         (5) D x F         (6) G x I
        (3) B x C         (6) E x F         (9) H x I      |     (7) B x C         (8) E x F         (9) H x I
        
        */
        int auxNumJogo = 0;
        for(int a = 0; a < listaJog.size(); a++){
            for(int b = a+1; b < listaJog.size(); b++){
                int numJogo = cont + auxNumJogo;
                Jogo jogo = new Jogo(listaJog.get(a), listaJog.get(b), numJogo);   
                jogos.add(jogo);
                auxNumJogo += numGrupos;
            }
        }
    }
    
    public void getJogos(){
        for(Jogo j : jogos){
            System.out.println(" (" + j.getNumJogo() + ") " + j.getJogo());
        }
    }
    
    public int getNumJogos(){
        numJogos = jogos.size();
        return numJogos;
    }
    
    public Jogo getJogo(int num){
        return jogos.get(num);
    }
       
    public void arrumaGrupo(){
        /*Funcao que faz os grupos ficarem dinamicos, ou seja, o que tem mais pontos fica 
        em cima, e vai decrescendo ate chegar ao que tem menos pontos, que fica em baixo*/
        
        List <Jogador> auxListaJog = new ArrayList();
        auxListaJog.add(null);
        
        for(int a = 0; a < listaJog.size()-1; a++){
            for(int b = a+1; b < listaJog.size(); b++){
                if(listaJog.get(a).getPts() > listaJog.get(b).getPts()){
                    System.out.print("");
                    //nao há ação, pois o A ja esta acima do B
                    //deixei esse if para facilitar na compreensao, caso me esqueça
                } else if(listaJog.get(b).getPts() > listaJog.get(a).getPts()){
                    auxListaJog.set(0,listaJog.get(b));
                    listaJog.set(b, listaJog.get(a));
                    listaJog.set(a, auxListaJog.get(0));
                } else{ 
                    //se os dois jogadores tiverem a mesma pontuacao, o criterio de desempate é o saldo de gols
                    if(listaJog.get(a).getSaldo() >= listaJog.get(b).getSaldo()){
                        System.out.print("");
                    } else if(listaJog.get(b).getSaldo() > listaJog.get(a).getSaldo()){
                        auxListaJog.set(0,listaJog.get(b));
                        listaJog.set(b, listaJog.get(a));
                        listaJog.set(a, auxListaJog.get(0));
                    } 
                }
            }        
        }
    }
}
