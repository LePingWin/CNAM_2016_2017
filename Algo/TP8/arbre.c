#include <stdio.h>
#include <stdlib.h>
#include "arbre.h"

Arbre* nouvArbre(){
	return NULL;
}

Arbre* enrac(int racine, Arbre* fg, Arbre* fd){
	Arbre* a = malloc(sizeof(Arbre));
	a->label = racine;
	a->fg = fg;
	a->fd = fd;
	return a;
}

Arbre* creerFeuille(int val){
	return enrac(val,NULL,NULL);
}

int racine(Arbre* a){
	return a->label;
}

Arbre* fg(Arbre* a){
	return a->fg;
}

Arbre* fd(Arbre* a){
	return a->fd;
}

boolean estVide(Arbre* a){
	if(a == NULL){
		return True;
	}
	return False;
}

boolean estFeuille(Arbre* a){
	if(fg(a) == NULL && fd(a) == NULL && racine(a) != (int)NULL){
		return True;
	}
	return False;
}

int taille(Arbre* a){
	if(estVide(a) == True){
		return 0;
	}else{
		return taille(fg(a))+taille(fd(a)) + 1;
	}
}

int hauteur(Arbre* a){
 if(estVide(a) == True){
	return -1;
 }else{
	int hg = hauteur(fg(a));
	int hd = hauteur(fd(a));
	return max(hg,hd);
 }
}

void writeArbre(Arbre* a,FILE* fp){
	if(estVide(fg(a)) == False){
		fprintf(fp,"%d -> %d\n",racine(a),racine(fg(a)));
		writeArbre(fg(a),fp);
	}
	if(estVide(fd(a)) == False){
		fprintf(fp,"%d -> %d\n",racine(a),racine(fd(a)));	
		writeArbre(fd(a),fp);
	}
	
}

void sauve_dot(Arbre* a,char* filename){
	FILE* fp;
	fp = fopen(filename,"w+");
	fprintf(fp,"digraph G\n{\n");
	if(estVide(a) == False){

		writeArbre(a,fp);
	}
	fprintf(fp,"}");
	fclose(fp);
}

void parcoursPrefixe(Arbre* a){
	if(estVide(a) == False){
		printf("%d" ,racine(a));
		parcoursPrefixe(fg(a));
		parcoursPrefixe(fd(a));
	}
}

void parcoursInfixe(Arbre* a){
	if(estVide(a) == False){
		parcoursInfixe(fg(a));
		printf("%d" ,racine(a));
		parcoursInfixe(fd(a));
	}
}

void parcoursPostFixe(Arbre* a){
	if(estVide(a) == False){
		parcoursPostFixe(fg(a));
		parcoursPostFixe(fd(a));
		printf("%d" ,racine(a));
	}
}

int main(){
Arbre* a = creerFeuille(3);
Arbre* b = creerFeuille(5);
Arbre* d = enrac(65,b,NULL);
Arbre* c = enrac(1,a,d);
parcoursPrefixe(a);
sauve_dot(c,"graph.dot");
return 0;	
}
