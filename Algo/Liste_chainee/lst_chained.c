#include <stdio.h>
#include <stdlib.h>


typedef int Bool;
#define true 1;
#define false 0;

typedef struct SNoeud
{
	int value;
	struct SNoeud* next;
	
} Noeud;
typedef struct SListe
{
	Noeud* tete;
	int nbNoeud;
	
}Liste;

void print(Liste* l){
	if(l->nbNoeud <=0){
		printf("\n%s\n","Liste Vide");
		return;
	}
	Noeud* na = l->tete;
	printf("%d",na->value);
	while(na->next != NULL){
		printf(", ");
		na = na->next;
		printf("%d",na->value);
	}
}

Bool est_vide(Liste* l){
	if(l->nbNoeud == 0){
		return true;
	}
	return false;
}

Liste* liste_vide(){
	Liste* l1 = malloc(sizeof(Liste));
	l1->tete = NULL;
	l1->nbNoeud = 0;
	return l1;
}


Noeud* push_front(Liste* l, int v){
	Noeud* n1 = malloc(sizeof(Noeud));
	n1->value = v;
	n1->next = l->tete;
	l->tete = n1;
	l->nbNoeud++;
	return n1; 
}

void pop_front(Liste* l){
	Noeud* tmp = l->tete;
	l->tete = l->tete->next;
	free(tmp);
	l->nbNoeud--;
}

int front_val(Liste* l){
	if(est_vide(l) == 1){
		return 0;
	}
	return l->tete->value;
}

Noeud* trouve_premier(Liste* l, int v){
	if(est_vide(l)){
		return NULL;
	}
	Noeud* na = l->tete;
	while(na != NULL){
		if(na->value == v){
			return na;
		}
		na = na->next;
	}
	return NULL;
}

int occurence(Liste* l, int v){
	int cpt = 0;
	if(est_vide(l) == 1){
		return 0;
	}
	Noeud* na = l->tete;
	while(na != NULL){
		if(na->value == v){
		cpt++;
		}
		na = na->next;
	}
	return cpt;
}

Noeud* insert_after(Liste* l,Noeud* c, int v){
	Noeud* n = malloc(sizeof(Noeud));
	n->value = v;
	if(c->next != NULL){
		n->next = c->next;
	}else{
		n->next = NULL;
	}
	c->next = n;
	l->nbNoeud++;
	return n;
}

void retire(Liste* l, Noeud* n){
	if(est_vide(l) == 1){
		return;
	}
	Noeud* na = l->tete;
	//Si le noeud est la tête
	if(na == n){
		//Si le noeud est l'unique noeud
		if(l->nbNoeud == 1){
			l->tete = NULL;
		}else{
			l->tete = na->next;
		}
		l->nbNoeud--;
		free(n);
		return;
	}
	while(na != NULL){
		//Si le noeud suivant est celui qu'on veut retirer
		if(na->next == n){
			na->next = n->next;
			l->nbNoeud--;
			free(n);
			return;
		}
		na = na->next;
	}
	return;
}

Noeud* push_back(Liste* l, int v){
	Noeud* na = l->tete;
	Noeud* nr = malloc(sizeof(Noeud));
	nr->value = v;
	nr->next = NULL;
	while(na->next != NULL){
		na = na->next;
	}
	na->next = nr;
	return nr;
}

void pop_back(Liste* l){
	if(est_vide(l) == 1){
		return;
	}
	Noeud* na = l->tete;
	while(na->next != NULL){
		na = na->next;
	}
	retire(l,na);
	return;
}

int back_val(Liste* l){
	if(est_vide(l) ==1){
		return (int)NULL;
	}
	Noeud* na = l->tete;
	while(na->next != NULL){
		na = na->next;
	}
	return na->value;
}

void apply(Liste* l,void (*fonction)(int*)){
	Noeud* na = l->tete;
	while(na != NULL){
		fonction(&na->value);
		na = na->next;
	}
}

void foisdeux(int* v){
	*v*=2;
}

int main(){
	Liste* l = liste_vide();
	push_front(l,3);
	print(l);
	pop_front(l);
	print(l);
	push_front(l,9);
	push_front(l,98);
	push_front(l,56);
	pop_back(l);
	print(l);
	printf("\nEnd Value : \n");
	printf("%d\n",back_val(l));
	apply(l,foisdeux);
	print(l);
	free(l);
}
