package server;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.io.PrintWriter;
import java.net.Socket;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatServerProcessThread extends Thread {

   private String nickname = null;
   private Socket socket = null;
   private JTextArea X;
   private JScrollPane D;
   List<PrintWriter> listWriters = null;
   List<String> nicknames = null;
   ArrayList<Integer> SCORE; // SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
   Integer Avd;
   List<String> players;
   private String answer;
   int playerScore[] = { 0, 0, 0, 0 };
   int[][] score;
   ArrayList<Integer> round;
   People[] rank = new People[4];

   public ChatServerProcessThread(Socket socket, List<PrintWriter> listWriters, List<String> nicknames, JTextArea X,
         JScrollPane D, Integer Avd, List<String> players, ArrayList<Integer> SCORE, int[][] score,
         ArrayList<Integer> round) {
      this.X = X;
      this.round = round;
      this.SCORE = SCORE;
      this.score = score;
      this.players = players;
      this.Avd = Avd;
      this.D = D;
      this.socket = socket;
      this.listWriters = listWriters;
      this.nicknames = nicknames;
   }

   @Override
   public void run() {
      answer = "";
      try {
         BufferedReader buffereedReader = new BufferedReader(
               new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
         PrintWriter printWriter = new PrintWriter(
               new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
         while (true) { // 클라에서 받은 스트링
                     // 처리**************************************************************************
            String request = buffereedReader.readLine();
            if (request == null) {
               X.append("클라이언트로부터 연결 끊김");
               D.getVerticalScrollBar().setValue(D.getVerticalScrollBar().getMaximum());
               doQuit(printWriter);
               break;
            }

            String[] tokens = request.split(":");

            if ("join".equals(tokens[0])) {

               doJoin(tokens[1], printWriter);
               int v = nicknames.indexOf(this.nickname);
               printWriter.println("INDEX:" + v);
               printWriter.flush();
            }

            if ("message".equals(tokens[0])) {
               doMessage(tokens[1]);
            }

            if ("quit".equals(tokens[0])) {
               doQuit(printWriter);
            }

            if ("Button".equals(tokens[0])) {
               Player_num_synchro(nickname);
               broadcast("score:" + tokens[1] + ":" + score[round.size() - 1][Integer.parseInt(tokens[1])]);
               playerScore[Integer.parseInt(tokens[2])] += score[round.size() - 1][Integer.parseInt(tokens[1])];// score합산

               synchronized (SCORE) {
                  SCORE.set(nicknames.indexOf(this.nickname), SCORE.get(nicknames.indexOf(this.nickname))
                        + score[round.size() - 1][Integer.parseInt(tokens[1])]);
               }
               sendScore(SCORE);

            }

            if ("START".equals(tokens[0])) {
               printWriter.println("START:CLIENT");
               printWriter.flush();
               printWriter.println("start2:a");
               printWriter.flush();
               broadcast("format:"+nicknames.get(0)+":"+nicknames.get(1)+":"+nicknames.get(2)+":"+nicknames.get(3));
               Player_num_synchro(nickname);

            }
            if("TIMESUP".equals(tokens[0]))
            {
            	Player_num_synchro(nickname);
            }
            if (players.size() == 4) { // PLAYER@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

               synchronized (round) {
                  round.add(0);
               }
               if (round.size() == 3+1) {
                  String ENDGAME ="ENDGAME";
                  
                  
                  for(int g=0;g<4;g++)
                  {
                     rank[g] = new People(nicknames.get(g), SCORE.get(g));
                  }
                  Arrays.sort(rank);
                  
                  for(int i=0;i<4;i++)
                  {
                     ENDGAME+= ":"+ rank[i].getID() +":"+rank[i].getScore();
                  }
                  
                  broadcast(ENDGAME);
                  
                  //this.interrupt();
                  
                  players.removeAll(players);
               } else {
                  broadcast("ANSWER:" + new submit(round.size() + "").text);
                  players.removeAll(players);
               }
            }

            if ("Answer".equals(tokens[0])) {
               if (tokens[1].equals(new submit2(round.size() + "").text)) {
                  

                  broadcast("Correct: " + this.nickname + "\n");
                  printWriter.flush();
                  printWriter.println("yourright:");
                  printWriter.flush();

               } else {
                  doMessage(tokens[1]);
               }
            }

         }
      } catch (IOException e) {
      }
   }

   private void Player_num_synchro(String A) {
      synchronized (players) {
         players.add(A);
      }
   }

   private void doQuit(PrintWriter writer) {
      removeWriter(writer);
      String data = this.nickname + "님이 퇴장했습니다.";
      X.append(data + "\n");
      D.getVerticalScrollBar().setValue(D.getVerticalScrollBar().getMaximum());
      String SEAT_data = "!";
      int i = 0;

      for (String x : nicknames) {
         SEAT_data += ":" + x;
         i++;
      }

      while (i < 4) {
         SEAT_data += ":" + "EMPTY";
         i++;
      }

      broadcast(SEAT_data);
      broadcast(data);
   }

   private void removeWriter(PrintWriter writer) {

      synchronized (listWriters) {

         listWriters.remove(writer);

      }
      synchronized (nicknames) {
         nicknames.remove(nickname);
      }

   }

   private void doMessage(String data) {

      broadcast(this.nickname + ":" + data);
      X.append(this.nickname + ":" + data + "\n");
      D.getVerticalScrollBar().setValue(D.getVerticalScrollBar().getMaximum());
   }

   private void sendScore(ArrayList<Integer> sc) {
      broadcast("player:" + sc.get(0) + ":" + sc.get(1) + ":" + sc.get(2) + ":" + sc.get(3));
      D.getVerticalScrollBar().setValue(D.getVerticalScrollBar().getMaximum());
   }

   private void doJoin(String nickname, PrintWriter writer) {

      this.nickname = nickname;

      String data = nickname + "님이 입장하였습니다.";
      X.append(data + "\n");
      D.getVerticalScrollBar().setValue(D.getVerticalScrollBar().getMaximum());
      addWriter(writer, nickname);

      broadcast(data);

      String SEAT_data = "!";
      int i = 0;

      for (String x : nicknames) {
         SEAT_data += ":" + x;
         i++;
      }
      while (i < 4) {
         SEAT_data += ":" + "EMPTY";
         i++;
      }

      broadcast(SEAT_data);
      // writer pool에 저장
      // !:nickname:e:e:e

   }

   private void addWriter(PrintWriter writer, String nick) {

      synchronized (listWriters) {

         listWriters.add(writer);

      }
      synchronized (nicknames) {
         nicknames.add(nick);
      }
   }

   private void rank() {

   }

   private void broadcast(String data) {

      synchronized (listWriters) {

         for (PrintWriter writer : listWriters) {

            writer.println(data);

            writer.flush();

         }

      }

   }

   private void consoleLog(String log) {

      System.out.println(log);

   }

}