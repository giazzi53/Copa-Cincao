
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GuilhermeSilvestreGi
 */

public class Copa {
    Scanner sc = new Scanner(System.in); //input caso alguma partida va para os penaltis
    private Jogador campeao;
    private List <Jogador> listaJog = new ArrayList();
    private List<Grupo> grupos = new ArrayList();
    private int numGrupos;
    private int numJogGrupo;
    //private List<Integer> auxJogos = new ArrayList();
    private int partidasJogadasGrupos;
    private int partidasJogadasSemi;
    private Jogador melhorSegundo;
    private List <Jogador> jogadoresMataMata = new ArrayList();
    private List <Jogo> semis = new ArrayList();
    private List<Jogador> jogadoresFinal = new ArrayList();
    private Jogo finalCopa;
       
    public Copa(){
        //loops para sobrepor os jogadores do mata mata caso seja necessario executar a funcao passarFase() outra vez devido ao bug
        for(int a = 0; a < 4; a++){
            jogadoresMataMata.add(null);
        }
        
        for(int a = 0; a < 2; a++){
            semis.add(null);
        }
    }
    
    public Jogador getCampeao() {
        return campeao;
    }

    public void setCampeao(Jogador campeao) {
        this.campeao = campeao;
    }
    
    public void addJogCopa(Jogador jogador){
        listaJog.add(jogador);
    }
    
    public void getJogCopa(){
        for (Jogador j : listaJog){
            System.out.println(j.getNome());
        }
    }
    
    public List getGrupos(){
        return grupos;
    }
    
    public Grupo getGrupo(int num){
        return this.grupos.get(num);
    }

    public int getPartidasJogadasGrupos() {
        return partidasJogadasGrupos;
    }

    public void setPartidasJogadasGrupos(int partidasJogadasGrupos) {
        this.partidasJogadasGrupos = partidasJogadasGrupos;
    }

    public int getPartidasJogadasSemi() {
        return partidasJogadasSemi;
    }

    public void setPartidasJogadasSemi(int partidasJogadasSemi) {
        this.partidasJogadasSemi = partidasJogadasSemi;
    }
    
    public List getJogadoresMataMata() {
        return jogadoresMataMata;
    }

    public Jogo getFinalCopa() {
        return finalCopa;
    }

    public int getNumGrupos() {
        return numGrupos;
    }

    public void setNumGrupos(int numGrupos) {
        this.numGrupos = numGrupos;
    }
    
    public int getNumJogGrupo(){
        return this.numJogGrupo;
    }
    
    public void setFinalCopa(Jogo finalCopa) {
        this.finalCopa = finalCopa;
    }
    
    public void organizaGrupos(){
        Collections.shuffle(listaJog); //embaralhando os jogadores
        int cont = 0; //variavel criada como indice para adicionar um jogador da lista de jogadores em um grupo
        
        if(listaJog.size() == 9){
            numGrupos = 3;
            numJogGrupo = 3;
        }
        
        for(int a = 1; a < numGrupos + 1; a++){
            Grupo grupo = new Grupo(a); //criando os grupos
            grupos.add(grupo);
            for(int b = 0; b < numJogGrupo; b++){
                grupo.addJogGrupo(listaJog.get(cont)); //adicionando os jogadores em um grupo
                cont++;
            }
        }
    }
    
    boolean jaGerouSemis = false;
    public void geraSemis(){
        if(!jaGerouSemis){
            Collections.shuffle(jogadoresMataMata);
            int cont = 0;
            int auxNumJogo = 10;
            for(int a = 0; a < 2; a++){
                Jogo semi = new Jogo(jogadoresMataMata.get(cont), jogadoresMataMata.get(cont + 1), auxNumJogo);   
                cont += 2;
                auxNumJogo++;
                semis.set(a,semi);
            }
            jaGerouSemis = true;
        }
    }
    
    boolean jaGerouFinal = false;
    public void passarFinal(){
        if(!jaGerouFinal){
            for(Jogo s : semis){
                if(s.getJog1().getGolsMataMata() > s.getJog2().getGolsMataMata()){
                    jogadoresFinal.add(s.getJog1());
                } else if(s.getJog2().getGolsMataMata() > s.getJog1().getGolsMataMata()){
                    jogadoresFinal.add(s.getJog2());
                } 
            }
            Jogo finalCopa = new Jogo(jogadoresFinal.get(0), jogadoresFinal.get(1), 12); 
            this.finalCopa = finalCopa;
            jaGerouFinal = true;
        }
    }
    
    public Jogador defineCampeao(int gols1, int gols2){
        if(gols1 > gols2){
            campeao = jogadoresFinal.get(0);
        } else{
            campeao = jogadoresFinal.get(1);
        }
        return campeao;
    }
    
    public void printaSemis(){
        System.out.println("\n     Jogos das semi finais");
        for(Jogo s : semis){
            System.out.println("(" + s.getNumJogo() + ") " +  s.getJogo());
        }
    }
    
    public List getSemis(){
        return semis;
    }
    
    public Jogo getSemi(int num){
        return semis.get(num);
    }
    
    public void printaGrupos(){
        for(Grupo g : grupos){
            g.arrumaGrupo();
            System.out.println("\n          Grupo " + g.getNumGrupo());
            g.getListaJog();
        }
    }
    
    public void passarFase(){
        System.out.println("\n         Fase mata mata");
        
        //mostrando os primeiros colocados de cada grupo
        for(int a = 0; a < numGrupos; a++){
            System.out.println(grupos.get(a).getPrimeiro().getNome() + " passou pois ficou em primeiro do Grupo " + grupos.get(a).getNumGrupo());
            jogadoresMataMata.set(a,grupos.get(a).getPrimeiro());
        }
        
        //definindo o melhor segundo colocado. Se a pontuacao for igual, o criterio de desempate eh o saldo de gols. 
        //Se o saldo tambem for igual, o melhor segundo colocado eh definido por um sorteio
        for(int a = 0; a < numGrupos - 1; a++){
            if(grupos.get(a).getSegundo().getPts() > grupos.get(a+1).getSegundo().getPts()){
                System.out.println("grupo de cima tem o melhor segundo");
                melhorSegundo = grupos.get(a).getSegundo();
            } else if(grupos.get(a+1).getSegundo().getPts() > grupos.get(a).getSegundo().getPts()){
                melhorSegundo = grupos.get(a+1).getSegundo();
            } else{
                if(grupos.get(a).getSegundo().getSaldo() > grupos.get(a+1).getSegundo().getSaldo()){
                    melhorSegundo = grupos.get(a).getSegundo();
                } else if(grupos.get(a+1).getSegundo().getSaldo() > grupos.get(a).getSegundo().getSaldo()){
                    melhorSegundo = grupos.get(a+1).getSegundo();
                }else{
                    Random r = new Random();
                    int aleatorio = r.nextInt(2);
                    if(aleatorio == 0){
                        melhorSegundo = grupos.get(a).getSegundo();
                    } else{
                        melhorSegundo = grupos.get(a+1).getSegundo();
                    }
                }
            }
        }
        System.out.println(melhorSegundo.getNome() + " passou pois foi o melhor segundo colocado da copa");
        jogadoresMataMata.set(3,melhorSegundo);
    }
}
