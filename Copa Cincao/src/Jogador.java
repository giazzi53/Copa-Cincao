public class Jogador {
    private String nome;
    private Time time;
    private int saldo;
    private int pts;
    private int golsMataMata;
    
    public Jogador(String nome, Time time){
        this.nome = nome;
        this.time = time;
        this.pts = 0;
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
    
    public void setGolsMataMata(int golsMataMata) {
        this.golsMataMata = golsMataMata;
    }
    
    public int getGolsMataMata(){
        return golsMataMata;
    }
}
