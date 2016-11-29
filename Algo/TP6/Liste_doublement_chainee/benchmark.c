#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "lst_double.h"

int main(){
	int i;
	int nb = 10000000;
	Liste* lv = liste_vide();
	for(i = 0;i<nb;i++){
		push_front(lv,i);
	}
	for(i = 0;i<nb;i++){
		pop_back(lv);
	}
	for(i=0;i<nb;i++){
		push_back(lv,i);
	}
	for(i=0;i<nb;i++){
		pop_front(lv);
	}
	return 0;
}
