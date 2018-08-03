    
import java.util.Random;

public class Jogador {
    private String nome;
    private Time time;
    private int saldo;
    private int pts = 0;
    private int golsMataMata;
    
    public Jogador(String nome, Time time){
        this.nome = nome;
        this.time = time;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    public void setResult(int pts, int saldo){
        this.pts += pts;
        this.saldo += saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
    
    /*public Jogador getMelhorJog(Jogador jog1, Jogador jog2){
        
        for(int a = 0; a < listaJog.size()-1; a++){
            
        }
        
    }*/
        /*Jogador maiorJog = null;
        if(jog1.getPts() > jog2.getPts()){
            maiorJog = jog1;
        } else if(jog2.getPts() > jog1.getPts()){
            maiorJog = jog2;
        } else{
            if(jog1.getSaldo() > jog2.getSaldo()){
                maiorJog = jog1;
            } else if(jog2.getSaldo() > jog1.getSaldo()){
                maiorJog = jog2;
            } else{
                System.out.println("\nOs dois jogadores estão empatados em pontos e saldo de gols. O resultado será sorteado");
                Random r = new Random();
                int aleatorio = r.nextInt(2);
                if (aleatorio == 0){
                    maiorJog = jog1;
                } else{
                    maiorJog = jog2;
                }
                System.out.println("\nO jogador sorteado foi o " + maiorJog.getNome());
            }
        }
        
        return maiorJog;
    }*/

    public void setGolsMataMata(int golsMataMata) {
        this.golsMataMata = golsMataMata;
    }
    
    public int getGolsMataMata(){
        return golsMataMata;
    }
    
    
    
}
