package fr.jonathan.plus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.jonathan.screen.ScreenDisplayer;

public class ChekTool {
	//private Props prop = new Props();
	private Scanner scan =new Scanner (System.in);
	private int essai = 1;
	private ScreenDisplayer screen = new ScreenDisplayer();
	private boolean isGood = false;
	private int score = 0;
	private String soluce = "";
	private String str = "";
	private String comb = "Combinaison actuel ";
	private int présent = 0;
	private int correct = 0;
	private int correctPlus = 0;
	
	
	
	public void verifPm(Solution s ,Tentative t, Mode m,Config conf) {
		check(s,t,m);
		if (correctPlus == s.getCombinaisonSize()) {
			found(s,t,m, conf);
			correctPlus=0;
		} else if (essai == conf.getNbEssai()) {
			fail(s,t,m, conf);
			correctPlus=0;
		} else {
			tryAgain(s,t,m, conf);
			correctPlus=0;
		}
		str = "";
		comb= "Combinaison actuel ";
		soluce="";
	}
	
	public void verifMaster(Solution s, Tentative t, Mode m,Config conf) {
		checkMaster(s,t,m);
		if (correct == s.getCombinaisonSize()) {
			found(s,t,m, conf);
			correct=0;
			présent=0;
		} else if (essai == conf.getNbEssai()) {
			fail(s,t,m, conf);
			correct=0;
			présent=0;
		} else {
			tryAgain(s,t,m, conf);
			correct=0;
			présent=0;
			
		}
		str = "";
		comb= "Combinaison actuel ";
		soluce="";
	}

	
	public void check (Solution s ,Tentative t, Mode m) {
		
		for (int i = 0 ; i < s.getCombinaisonSize(); i++) {

			if (s.get(i)==(t.get(i))){
				str+=("=");
				correctPlus++;

			}else if ((s.get(i))>(t.get(i))) {
				str+="+";

			}else if ((s.get(i))<(t.get(i))) {   
				str+="-";
			}else {
				str += "";
			}
			soluce+=(s.get(i)+ " , ");
			comb+=(t.get(i));
		}
	}
	
	public void checkMaster(Solution s ,Tentative t, Mode m) {
		List<Integer>sVerif = new ArrayList<Integer>();
		List<Integer>tVerif = new ArrayList<Integer>();
		for (int i = 0 ; i < s.getCombinaisonSize(); i++) {
			 	if (t.get(i)==s.get(i)) {
			 		correct++;
			 	} else {
			 		sVerif.add(s.get(i));
			 		tVerif.add(t.get(i));
			 	}
			 	soluce+=(s.get(i)+ " , ");
				comb+=(t.get(i));
		}
		str += ("bien placé : " + correct ) ;
		checkBis(sVerif, tVerif);
	}
	
	public void checkBis(List<Integer>s ,List<Integer>t) {
		for (int i = 0; i <t.size(); i++) {
			for (int j =0 ; j <s.size();j++) {
				if (t.get(i)==s.get(j)) {
				présent ++;
				s.remove(j);
				break;
				}
			}
		}
		str += (" présent : " + présent);
	}


	
	public void found(Solution s , Tentative t ,Mode m , Config conf) {
		isGood=true;
		if (m == Mode.CHAL||m == Mode.MASTER_CHAL) {
			screen.succesScreen(scan, m, conf);
		}else if (m == Mode.DEF||m== Mode.MASTER_DEF) {
		screen.gameOver(scan,m, conf);
		}else if (m == Mode.DUEL_ATK|| m==Mode.MASTER_DUEL_ATK) {
			s.clear();
			t.clear();
			System.out.println("Vous avez gagné le premier round, préparez vous à défendre");
			score ++;
		}else {
			System.out.println("Vous avez perdu ce round ");  //+ soluce);
			s.clear();
			t.clear();
			score --;
		}
	}
	
	public void fail (Solution s , Tentative t ,Mode m, Config conf) {
		essai = 1;
		isGood= true;
		if(m == Mode.CHAL || m==Mode.MASTER_CHAL) {
			System.out.println("\n la solution était " + soluce);
			screen.gameOver(scan,m, conf);
		}else if (m == Mode.DEF|| m ==Mode.MASTER_DEF){
			screen.succesScreen(scan,m, conf);
		}else if (m == Mode.DUEL_DEF|| m== Mode.MASTER_DUEL_DEF){
			System.out.println("Vous avez gagné le second round fin de la partie");
			s.clear();
			t.clear();
			score++;
		}else {
			System.out.println("\n Vous avez perdu le premier round mais vous pouvez encore vous rattraper! \n ");
			s.clear();
			t.clear();
			System.out.println(" A vous de défendre \n");
			score--;
		}
	}
	
	public void tryAgain(Solution s , Tentative t ,Mode m, Config conf) {
		
		if (s.getError()==true) {
			isGood = false;
		}else {
			essai ++;
			System.out.println(comb+ " " +str + "\n\nessayez encore\n "); //+ "solution = " +soluce);
			
			t.clear();
			System.out.println("nombre d'essai restant = " + ((conf.getNbEssai() +1)- essai)+"\n");
			isGood = false;
		}
	}
	
	public boolean getGood() {
		return isGood;
	}
	public void setGood(boolean b) {
		isGood=b;
	}
	public int getScore() {
		return score;
	}
}
