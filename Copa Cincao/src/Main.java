/*Projeto desenvolvido por Guilherme Silvestre Giazzi

Bug: se a funcao de passar de fase der errado (opcao 5),
escolher essa opcao novamente, pois na segunda vai certo. Esse erro nao acontece todas as vezes

*/
import java.util.List;
import java.util.Scanner;


public class Main {
    
    public static int fazMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n          MENU  " +
                           "\n1 - Mostrar a tabela da copa" +
                           "\n2 - Mostrar partidas" +
                           "\n3 - Mostrar resultados até aqui" +
                           "\n4 - Inserir placar" +
                           "\n5 - Ir para as semi finais" +
                           "\n6 - Ir para a final" +
                           "\n0 - Sair");
        System.out.print("\nSelecione uma opção: ");
        int op = sc.nextInt();
        while(op < 0 || op > 6){
            System.out.print("\nOpção inválida, digite novamente: ");
            op = sc.nextInt();
        }
        
        return op;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Copa copa = new Copa();
        int contPartidasJogadas = 0;
        int partidasJogadasSemi = 0;
        System.out.println("\nPara que a Copa Cincão seja realizada, são necessários 9 jogadores");
        for(int cont = 1; cont < 10; cont++){
            System.out.print("\nDigite o nome do jogador " + cont + ": ");
            String nomeJog = sc.nextLine();
            
            System.out.print("\nDigite o time do " + nomeJog + ": ");
            String nomeTime = sc.nextLine();
            Time time = new Time(nomeTime);
            
            Jogador jogador = new Jogador(nomeJog, time);
            
            //adicionando jogadores na copa
            copa.addJogCopa(jogador);
        }
        
        //separando os grupos
        copa.organizaGrupos();
        
        //organizando os jogos
        copa.addNumJogo();
        List <Integer> numJogos = copa.getNumJogos();
        int aux = 1;
        for(int a = 0; a < 3; a++){
            
            //System.out.println(numJogos);
            copa.getGrupo(a).organizaJogos(aux);
            aux+=1;
        }
        
        boolean sair = false;
        //apresentando o menu
        int op = fazMenu();
        while(!sair){
            for(int a = 0; a < 3; a++){
                copa.getGrupo(a).arrumaGrupo();
            }
            
            if(op == 1){
                //apresentando os grupos
                copa.printaGrupos();
            }else if(op == 2){
                //apresentando os jogos
                
                for(int a = 0; a < 3; a++){
                   //System.out.println("\nGrupo " + copa.getGrupo(a).getNumGrupo());
                   for(int b = 0; b < 3; b++){
                       System.out.print("\n("+ copa.getGrupo(b).getJogo(a).getNumJogo() + ") " + copa.getGrupo(b).getJogo(a).getJogo());
                   }
                } 
                
                System.out.println("");
                
             } else if(op ==3){
                 for(int a = 0; a < 3; a++){
                   for(int b = 0; b < 3; b++){
                       System.out.println("\n" + copa.getGrupo(b).getJogo(a).getJogo() + " - " + copa.getGrupo(b).getJogo(a).getPlacar());
                   }
                } 
                
               } else if(op == 4){
                     //apresentando os jogos

                    for(int a = 0; a < 3; a++){
                       //System.out.println("\nGrupo " + copa.getGrupo(a).getNumGrupo());
                       for(int b = 0; b < 3; b++){
                           System.out.print("\n("+ copa.getGrupo(b).getJogo(a).getNumJogo() + ") " + copa.getGrupo(b).getJogo(a).getJogo());
                       }
                    }
                    System.out.print("\n(0) Voltar");
                    System.out.println("");
                    System.out.print("\nDigite o numero do jogo que deseja inserir um placar, ou 0 para voltar: ");
                    int op2 = sc.nextInt();
                    while(op2 < 0 || op2 > 9){
                        System.out.print("\nNumero inválido, digite novamente: ");
                        op2 = sc.nextInt();
                    }

                    if(op2 == 0){
                        System.out.print("");
                    } else{
                       for(int a = 0; a < 3; a++){
                           for(int b = 0; b < 3; b++){
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
                        copa.geraSemis();
                        copa.printaSemis();
                        System.out.print("(0) Voltar");
                        System.out.println("");
                        System.out.print("\nDigite o numero do jogo que deseja inserir um placar, ou 0 para voltar: ");
                        int op3 = sc.nextInt();
                        while(op3 != 0 && op3 != 10 && op3 != 11){
                            System.out.print("\nNumero inválido, digite novamente: ");
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
                        System.out.println("\nAinda não é possível ir para as semi finais. Todas as partidas da fase de grupos precisam ser jogadas");
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
                            System.out.print("\nNumero inválido, digite novamente: ");
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
                            System.out.println("\nO campeão da Copa Cincão é " + copa.getCampeao().getNome());
                            sair = true;
                            break;
                            //copa.jogaFinal(gols1, gols2);
                        } 
                            
                            /*copa.getSemi(op3-10).setPlacar(gols1, gols2);
                            copa.getSemi(op3-10).getJog1().setGolsMataMata(gols1);
                            copa.getSemi(op3-10).getJog2().setGolsMataMata(gols2);
                        copa.jogaFinal(gols1, gols2);*/
                    } else{
                        System.out.println("\nAinda não é possível ir para a final. Todas as partidas das semi finais precisam ser jogadas");
                    }
                    
                
                }else{
                    System.out.println("\nTem certeza que deseja sair do programa? Seu progresso será perdido!" +
                                       "\n\n1 - Sim, quero sair" +
                                       "\n2 - Não, quero ficar");
                    System.out.print("\nDigite uma opção: ");
                    int op3 = sc.nextInt();
                    while (op3 < 1 || op3 > 2){
                        System.out.print("\nOpão inválida, digite novamente: ");
                        op3 = sc.nextInt();
                    }
                    if(op3 == 1){
                        sair = true;
                        break;
                    } else{
                        System.out.print("");
                    }
                }
            op = fazMenu();
        }
        
        System.out.println("\nSaiu!");   
        
       
           
       }
    }

