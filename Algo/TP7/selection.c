#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

typedef int bool;
#define true 1
#define false 0

int countSwap = 0;
int countComparison = 0;
int countAffectation = 0;
void print(int array[], int size){
int i;
printf("[ ");
for(i = 0;i<size;i++){
printf("%d ",array[i]);
}	
printf("]\n");
return;
}

void print_char(char array[], int size){
int i;
for(i = 0;i<size;i++){
printf("%c",array[i]);
}	
puts("");
return;
}

void readTab(int array[], int size){
int i;
for(i = 0;i<size;i++){
scanf("%d",&array[i]);
}	
return;
}

int valeur_max(int array[], int size){
int i;
int max = 0;
for(i = 0;i<size;i++){
	if(max<array[i]){
		max = array[i];
	}
}
return max;	
}

void swap_elt( int array[], int size, int i, int j ){
int save = array[i];
array[i] = array[j];
array[j] = save;
return;
}

void swap_elt_char( char array[], int size, int i, int j ){
int save = array[i];
array[i] = array[j];
array[j] = save;
return;
}

int occurrences( int array[], int size, int el ){
int i;
int count = 0;
for(i = 0;i<size;i++){
	if(array[i] == el){
		count++;
	}	
}
return count;
}

int find_first( int array[ ], int size, int el ){
int i;
for(i = 0;i<size;i++){
	if(array[i] == el){
		return i;
	}	
}
return -1;
}

int find_last( int array[ ], int size, int el ){
int i;
for(i = size;i>=0;i--){
	if(array[i] == el){
		return i;
	}	
}
return -1;
}

void reverse( int array[ ], int size ){
int i;
for(i = 0;i<size;i++){
	int save = array[size-i];
	array[size-i] = array[i];
	array[i] = save;
}	
return;
}

void rotate_right( int array[ ], int size ){
int i;
int save = array[size-1];
int tab[size];
memcpy(tab,array,sizeof(tab));
for(i = 0;i<size-1;i++){
array[i+1] = tab[i]; 
}
array[0] = save;
return;	
}

void rotate_left( int array[ ], int size ){
int i;
int save = array[0];
int tab[size];
memcpy(tab,array,sizeof(tab));
for(i = 0;i<size-1;i++){
array[i] = tab[i+1]; 
}
array[size-1] = save;
return;	
}

bool is_palindrome(int array[], int size){
int i;
for(i = 0;i<size;i++){
	if(array[i] != array[size-1-i]){
		return 0;
	}
}
return 1;
}

int* init_alea(int size){
	int* tab = malloc(size*sizeof(int));
	int i;
	for(i=0;i<size;i++){
		tab[i] = rand() % 100;
	}
	return tab;
}

int* init_decroiss(int size){
	int* tab = malloc(size*sizeof(int));
	int i;
	for(i=0;i<size;i++){
		tab[i] = size-i;
	}
	return tab;
}

void printInRealTime(int* tab, int size, int enCoursIndice, int tmpIndice) {
    int i;
    for(i = 0; i< size; i++) {
    if(i == enCoursIndice) {
          printf("\x1B[32m[%2d]\x1B[0m", tab[i]);
      } else if (i == tmpIndice){
          printf("\x1B[36m[%2d]\x1B[0m", tab[i]);
    } else {
      printf("[%2d]", tab[i]);
    }
    fflush(stdout);
    usleep(15000);
  }
    printf("\r");

}
void tri_select(int* tab,int size){
	int i,tmp;
	countSwap = 0;
	for(i=0;i<size;i++){
		for(tmp=i;tmp<size;tmp++){
			if(tab[tmp]<tab[i]){
				swap_elt(tab,size,i,tmp);
				countSwap++;
				printInRealTime(tab,size,i,tmp);
			}
			countComparison++;
		}
	}
}

void tri_insert(int* tab,int size){
	int i;
	for(i=0;i<size;i++){
		int tmp = tab[i];
		int j = i;
		while(j>0 && tab[j-1] > tmp){
			countComparison++;
			tab[j] = tab[j-1];
			j=j-1;
		}
		tab[j]=tmp;
		countSwap++;
		printInRealTime(tab,size,i,tmp);
	}
}



void tri_bulle(int* tab, int taille)
{
  int passage = 0;
  bool permut  = true;
  int i;
  while(permut)
  {
    permut = false;
    passage++;
    for(i = 0; i< taille && permut==false; i++)
    {
				countComparison++;
      if(tab[i] > tab[i+1])
      {

        permut = true;
        int temp = tab[i];
        tab[i] = tab[i+1];
        tab[i+1] = temp;
        countSwap++;
        printInRealTime(tab,taille,i,temp);
      }
    }
  }
}

void tri_shaker(int* tab, int taille)
{
  bool permutation;
    int en_cours=0, sens=1;
    int debut=1, fin=taille;
    do {
        permutation=false;
        while (((sens==1) && (en_cours<fin)) || ((sens==-1) && (en_cours>debut))) {
            en_cours += sens;
            if (tab[en_cours]<tab[en_cours-1]) {
				countComparison++;
                int temp = tab[en_cours];
                tab[en_cours]=tab[en_cours-1];
                tab[en_cours-1]=temp;
                countSwap++;
                permutation=true;
                printInRealTime(tab,taille,en_cours,temp);
            }
        }
        if (sens==1) fin--; else debut++;
        sens = -sens;
    } while (permutation);

}

void tri_rapide(int* tab, int taille){
	int mur,courant,pivot,tmp;
	if(taille<2) return;
	pivot = tab[taille-1];
	mur = courant = 0;
	while(courant<taille){
		if(tab[courant] <= pivot){
			countComparison++;
			if(mur != courant){
				countComparison++;
				tmp = tab[courant];
				tab[courant] = tab[mur];
				tab[mur]=tmp;
				countSwap++;
			}
			mur++;
		}
		courant++;
		printInRealTime(tab,taille,pivot,courant);
	}
	tri_rapide(tab,mur-1);
	tri_rapide(tab+mur-1,taille-mur+1);
}
 
// Fusion des listes t(de1..vers1) et t(de2..vers2) dans tmp(posInTmp..posInTmp+count-1)
void fusion(int* t, int* tmp, int de1, int vers1, int de2, int vers2, int count, int posInTmp) 
{
     int i;
     for(i = 0 ; i < count ; i++)
     {
		 countComparison++;
              if (de2 > vers2){   // Si fin de la liste 2, on prend dans liste 1
                      tmp[posInTmp++] = t[de1++];
                      countAffectation++;
              }else if (de1 > vers1){   // Idem si fin de la liste 1
                      tmp[posInTmp++] = t[de2++];
                      countAffectation++;
              }else if (t[de1] <= t[de2]){ // Enfin, sinon, on compare
                      tmp[posInTmp++] = t[de1++];
                      countAffectation++;
              }else {
                      tmp[posInTmp++] = t[de2++];
                      countAffectation++;
			  }
      }
}
 
// Tri de tout le tableau t par fusions successives
void trifusion(int* t,int size)
{
      int* tmp = malloc(size*sizeof(int));
      int sortLength = 1, de1, de2, de3, i;
      while(sortLength < size)
      {
              de1 = 0;
              while(de1 < size)
              {
				  printInRealTime(t,size,de1,de1);
                    de2 = de1 + sortLength;
                    de3 = de2 + sortLength;
                    if(de2>size) de2 = size;
                    if(de3>size) de3 = size;
                    fusion(t, tmp, de1, de2-1, de2, de3-1, de3-de1, de1);
                    de1 = de3;
              }
              for(i = 0 ; i < size ; i++) t[i] = tmp[i];
              sortLength *= 2;
       }
 }



int main(){
	int size = 10;
	int* tab = init_alea(size);
	print(tab,size);
	trifusion(tab,size);
	printf("\n");
	fflush(stdout);
	print(tab,size);
	free(tab);
	printf("Nb Affectations : %d\n",countAffectation);
	printf("Nb Comparaisons : %d\n",countComparison);
	printf("Nb Echanges : %d\n",countSwap);
}
