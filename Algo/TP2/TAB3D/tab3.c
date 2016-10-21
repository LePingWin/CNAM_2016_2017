#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef float Mat33[3][3];

void readMat(Mat33 M){
int i;
int j;
for(i = 0;i<3;i++){
	for(j = 0;j<3;j++){
		scanf("%f",&M[i][j]);
	}
}	
return;
}
void setId(Mat33 M){ //initialize à la matrice identité (diagonale à 1, le reste à 0)
int i;
int j;
for(i = 0;i<3;i++){
	for(j = 0;j<3;j++){
		M[i][j] = 0;
	}
	M[i][i] = 1;
}
return;
} 
//faire une version optimisée (2 boucles, 1 passe et pas de if)

void printMat(Mat33 M){
int i;
int j;
for(i = 0;i<3;i++){
	for(j = 0;j<3;j++){
		printf("|%f|",M[i][j]);
	}
}	
return;
}

void somme(Mat33 A, Mat33 B, Mat33 ApB){
int i;
int j;
for(i = 0;i<3;i++){
	for(j = 0;j<3;j++){
		ApB[i][j] = A[i][j] + B[i][j];
	}
}	
return;
}
void produit(Mat33 A, Mat33 B, Mat33 AfB){
int i;
int j;
int z;
//Parcours Matrice
for(i = 0;i<3;i++){
	for(j = 0;j<3;j++){
		AfB[i][j] = 0;
		//Produit
		for(z =0;z<3;z++){
			AfB[i][j] += A[i][z] * B[z][j];
		}
	}	
}
return;
}

int main(){
Mat33 m;
Mat33 b;
Mat33 res;
printf("Première Matrice ?");
readMat(m);
printf("Deuxième Matrice ?");
readMat(b);
produit(m,b,res);
printMat(res);
return 0;
}
