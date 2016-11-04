package View;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class Score implements Serializable {

	private static final long serialVersionUID = 1L;
	private int[] score = {0,0,0};
	private static int myscore=0;
	public static void up() {
		myscore++;
	}
	public static int get() {
		return myscore;
	}
	public void save() {
		// on copie les scores dans un tableau plus grand de 1 => 4
		Integer[] s = new Integer[4];
		for (int i = 0; i < score.length; i++) {
			s[i]=score[i];
		}
		// on y ajoute le score courant � la fin
		s[3] = myscore;
		// on le trie
		Arrays.sort(s, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
			
		});
		// on recopie les 3 premiers
		for (int i = 0; i < score.length; i++) {
			score[i]=s[i];
		}
	}
	public int[] getScores() {
		return score;
	}
	
	@Override
	public String toString() {
		return "[1] " + score[0] + "\n" +
		" [2] " + score[1] + "\n" +
		" [3] " + score[2] + "\n";
	}
		
	
}
