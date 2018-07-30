/*Projeto desenvolvido por Guilherme Silvestre Giazzi

Bug: se a funcao de passar de fase der errado (opcao 5),
escolher essa opcao novamente, pois na segunda vai certo. Esse erro nao acontece todas as vezes

*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static int fazMenu(){
        Scanner sc = new Scanner(System.in);
        String[] menu = {"\n          MENU  ",
                         "1 - Mostrar a tabela da copa",
                         "2 - Mostrar partidas",
                         "3 - Mostrar resultados ate aqui",
                         "4 - Inserir placar",
                         "5 - Ir para as semi finais",
                         "6 - Ir para a final",
                         "0 - Sair"};
        for(int idx = 0; idx < menu.length; idx++){
            System.out.println(menu[idx]);
        }
        
        System.out.print("\nSelecione uma opcao: ");
        int op = sc.nextInt();
        while(op < 0 || op > menu.length){
            System.out.print("\nOpcao invalida, digite novamente: ");
            op = sc.nextInt();
        }
        return op;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in); //criando um input
        
        System.out.print("Digte o numero de jogadores que vao participar dessa copa (2-10): ");
        String x = sc.nextLine();                       //feita essa conversao para corrigir o bug do Netbeans
        int numJogadores = Integer.parseInt(x);
        while(numJogadores != 9){
            System.out.print("\nDesculpe, a Copa Cincao apenas suporta de 2 a 10 jogadores. Digite novamente: ");
            x = sc.nextLine();
            numJogadores = Integer.parseInt(x);
        }
        
        Copa copa = new Copa(); //criando a copa
        
        int contPartidasJogadas = 0; //variavel usada para contar as partidas jogadas, permitindo ou nao passar de fase
        int partidasJogadasSemi = 0;
        //System.out.println("\nPara que a Copa Cincao seja realizada, sao necessarios 9 jogadores");
        for(int cont = 1; cont < numJogadores+1; cont++){
            System.out.print("\nDigite o nome do jogador " + cont + ": ");
            String nomeJog = sc.nextLine();
            
            System.out.print("\nDigite o time do " + nomeJog + ": ");
            String nomeTime = sc.nextLine();
            Time time = new Time(nomeTime); //criando o time
            
            Jogador jogador = new Jogador(nomeJog, time); //criando o jogador
            
            copa.addJogCopa(jogador); //adicionando o jogador na copa
        }
        
        copa.organizaGrupos(); //separando os grupos
        
        //organizando os jogos de cada grupo
        int aux = 1;
        for(int a = 0; a < copa.getNumGrupos(); a++){
            copa.getGrupo(a).organizaJogos(aux, copa.getNumGrupos());
            aux+=1;
        }
        //
        
        //copa.printaGrupos();
        
        boolean sair = false;
        //apresentando o menu
        int op = fazMenu();
        while(!sair){
            
            for(int a = 0; a < copa.getNumGrupos(); a++){
                /*Loop para fazer cada grupo ficar dinamico, ou seja, o que tem mais pontos fica 
                em cima, e vai decrescendo ate chegar ao que tem menos pontos, que fica em baixo*/
                copa.getGrupo(a).arrumaGrupo();
            }
            
            if(op == 1){
                System.out.println("");
                //apresentando os grupos
                copa.printaGrupos();
            }else if(op == 2){
                //apresentando os jogos
                for(int a = 0; a < copa.getNumGrupos(); a++){
                   //System.out.println("\nGrupo " + copa.getGrupo(a).getNumGrupo());
                   for(int b = 0; b < copa.getNumJogGrupo(); b++){
                       System.out.print("\n("+ copa.getGrupo(b).getJogo(a).getNumJogo() + ") " + copa.getGrupo(b).getJogo(a).getJogo());
                   }
                } 
                System.out.println("");
                
             } else if(op == 3){
                for(int a = 0; a < copa.getNumGrupos(); a++){
                   for(int b = 0; b < copa.getNumJogGrupo(); b++){
                       System.out.println("\n" + copa.getGrupo(b).getJogo(a).getJogo() + " - " + copa.getGrupo(b).getJogo(a).getPlacar());
                   }
                } 
                
               } else if(op == 4){
                    //apresentando os jogos
                    for(int a = 0; a < copa.getNumGrupos(); a++){
                       for(int b = 0; b < copa.getNumJogGrupo(); b++){
                           System.out.print("\n("+ copa.getGrupo(b).getJogo(a).getNumJogo() + ") " + copa.getGrupo(b).getJogo(a).getJogo());
                       }
                    }
                    System.out.print("\n(0) Voltar\n");
                    System.out.print("\nDigite o numero do jogo que deseja inserir um placar, ou 0 para voltar: ");
                    int op2 = sc.nextInt();
                    while(op2 < 0 || op2 > copa.getNumGrupos() * copa.getGrupo(0).getNumJogos()){
                        System.out.print("\nNumero invalido, digite novamente: ");
                        op2 = sc.nextInt();
                    }

                    if(op2 == 0){
                        //opcao voltar
                        System.out.print("");
                    } else{
                       for(int a = 0; a < copa.getNumGrupos(); a++){
                           for(int b = 0; b < copa.getNumJogGrupo(); b++){
                               if(op2 == copa.getGrupo(b).getJogo(a).getNumJogo()){
                                  System.out.println("\nDefinindo placar para o jogo " + op2 + ": " + copa.getGrupo(b).getJogo(a).getJogo());
                                  System.out.print("\nDigite os gols do " + copa.getGrupo(b).getJogo(a).getJog1().getNome() + ": ");
                                  int gols1 = sc.nextInt();
                                  System.out.print("\nDigite os gols do " + copa.getGrupo(b).getJogo(a).getJog2().getNome() + ": ");
                                  int gols2 = sc.nextInt();

                                  copa.getGrupo(b).getJogo(a).setPlacar(gols1,gols2);
                                  contPartidasJogadas ++;
                                  copa.setPartidasJogadas(contPartidasJogadas);
                               }

                            }
                        }
                    }
                } else if(op == 5){
                    //verificando se todas as partidas ja foram jogadas para passar para a proxima fase
                                       
                    if(copa.getPartidasJogadas() == 9){
                        copa.passarFase();
                        
                        
                    
                    ///////CONTINUAR A REFATORAR A PARTIR DESTE PONTO
                    
                    
                    
                        copa.geraSemis();
                        copa.printaSemis();
                        System.out.print("(0) Voltar");
                        System.out.println("");
                        System.out.print("\nDigite o numero do jogo que deseja inserir um placar, ou 0 para voltar: ");
                        int op3 = sc.nextInt();
                        while(op3 != 0 && op3 != 10 && op3 != 11){
                            System.out.print("\nNumero invalido, digite novamente: ");
                            op3 = sc.nextInt();
                        } if(op3 == 0){
                            System.out.print("");
                        } else{
                            System.out.println("\nDefinindo placar para o jogo " + op3 + ": " + copa.getSemi(op3-10).getJogo());
                            System.out.print("\nDigite os gols do " + copa.getSemi(op3-10).getJog1().getNome() + ": ");
                            int gols1 = sc.nextInt();
                            //copa.getSemi(op3-10).getJog1().setGolsMataMata(gols1);
                            System.out.print("\nDigite os gols do " + copa.getSemi(op3-10).getJog2().getNome() + ": ");
                            int gols2 = sc.nextInt();
                            //copa.getSemi(op3-10).getJog2().setGolsMataMata(gols2);
                            while(gols1 == gols2){
                                System.out.println("\nComo houve um empate, a partida deve ser decidia nos penaltis");
                                System.out.print("\nDigite os penaltis convertidos por " + copa.getSemi(op3-10).getJog1().getNome() + ": ");
                                int pen1 = sc.nextInt();
                                System.out.print("\nDigite os penaltis convertidos por " + copa.getSemi(op3-10).getJog2().getNome() + ": ");
                                int pen2 = sc.nextInt();
                                gols1 = pen1;
                                gols2 = pen2;
                                
                            } 
                            
                            copa.getSemi(op3-10).setPlacar(gols1, gols2);
                            copa.getSemi(op3-10).getJog1().setGolsMataMata(gols1);
                            copa.getSemi(op3-10).getJog2().setGolsMataMata(gols2);
                            
                            partidasJogadasSemi ++;
                            copa.setPartidasJogadasSemi(partidasJogadasSemi);
                        }
                       
                    } else{
                        System.out.println("\nAinda nao eh possivel ir para as semi finais. Todas as partidas da fase de grupos precisam ser jogadas");
                    }
                } else if(op == 6){
                    if(copa.getPartidasJogadasSemi() == 2){
                        System.out.println("\n        Final da copa");
                        copa.passarFinal();
                        //copa.geraFinal();
                        System.out.println("\n(" + copa.getFinalCopa().getNumJogo() + ") " + copa.getFinalCopa().getJogo());
                        System.out.print("(0) Voltar");
                        System.out.println("");
                        System.out.print("\nDigite o numero do jogo que deseja inserir um placar, ou 0 para voltar: ");
                        int op4 = sc.nextInt();
                        while(op4 != 0 && op4 != copa.getFinalCopa().getNumJogo()){
                            System.out.print("\nNumero invalido, digite novamente: ");
                            op4 = sc.nextInt();
                        } if(op4 == 0){
                            System.out.print("");
                        } else{
                            System.out.println("\nDefinindo placar para o jogo 12" + ": " + copa.getFinalCopa().getJogo());
                            System.out.print("\nDigite os gols do " + copa.getFinalCopa().getJog1().getNome() + ": ");
                            int gols1 = sc.nextInt();
                            //copa.getSemi(op3-10).getJog1().setGolsMataMata(gols1);
                            System.out.print("\nDigite os gols do " + copa.getFinalCopa().getJog2().getNome() + ": ");
                            int gols2 = sc.nextInt();
                            //copa.getSemi(op3-10).getJog2().setGolsMataMata(gols2);
                            while(gols1 == gols2){
                                System.out.println("\nComo houve um empate, a partida deve ser decidia nos penaltis");
                                System.out.print("\nDigite os penaltis convertidos por " + copa.getFinalCopa().getJog1().getNome() +": ");
                                int pen1 = sc.nextInt();
                                System.out.print("\nDigite os penaltis convertidos por " + copa.getFinalCopa().getJog2().getNome() +": ");
                                int pen2 = sc.nextInt();
                                gols1 = pen1;
                                gols2 = pen2;
                            }
                            
                            copa.defineCampeao(gols1, gols2);
                            System.out.println("\nO campeao da Copa Cincao eh " + copa.getCampeao().getNome());
                            sair = true;
                            break;
                            //copa.jogaFinal(gols1, gols2);
                        } 
                            
                            /*copa.getSemi(op3-10).setPlacar(gols1, gols2);
                            copa.getSemi(op3-10).getJog1().setGolsMataMata(gols1);
                            copa.getSemi(op3-10).getJog2().setGolsMataMata(gols2);
                        copa.jogaFinal(gols1, gols2);*/
                    } else{
                        System.out.println("\nAinda nao eh possivel ir para a final. Todas as partidas das semi finais precisam ser jogadas");
                    }
                    
                
                }else{
                    System.out.println("\nTem certeza que deseja sair do programa? Seu progresso sera perdido!" +
                                       "\n\n1 - Sim, quero sair" +
                                       "\n2 - Nao, quero ficar");
                    System.out.print("\nDigite uma opcao: ");
                    int op3 = sc.nextInt();
                    while (op3 < 1 || op3 > 2){
                        System.out.print("\nOpao invalida, digite novamente: ");
                        op3 = sc.nextInt();
                    }
                    if(op3 == 1){
                        sair = true;
                        break;
                    } else{
                        System.out.print("");
                    }
                }
            //copa.printaGrupos();
            op = fazMenu();
        }
        
        System.out.println("\nSaiu!");   
        
       
           
       }
    }

