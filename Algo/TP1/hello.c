#include <stdio.h>
#include <math.h>
#include <time.h>

/*
 * 
 * Affiche un entier et un réel saisi par l'utilisateur 
 * 
 */
void intReal(){
float reel;
int entier;
printf("Entrez un réel puis un entier\n");
scanf("%F %d",&reel,&entier);
printf("Un magnifique entier %d et un fantastique réel %f\n",entier,reel);
return;
}
/*
 * 
 * Affiche le périmètre, la surface et la diagonale du côté du carré
 * 
 */
void square(){
float squareside;
float perimeter;
float surface;
float diagonal;

printf("Saisissez le côté de votre carré\n");
scanf("%F",&squareside);

perimeter = squareside * 4;
surface = squareside * squareside;
diagonal = squareside * sqrt(2);

printf("Périmètre : %F \n Surface : %F \n Diagonale : %F\n",perimeter, surface, diagonal);
return;
}
/*
 * 
 * Affiche le nombre de jour dans un mois 
 * 
 */
void nbdays(){
int monthnb;
printf("Choisissez le numéro du mois \n");
scanf("%d",&monthnb);
if(monthnb==1||monthnb==3||monthnb==5||monthnb==7||monthnb==8||monthnb==10||monthnb==12){
	printf("31 jours\n");
}else if(monthnb == 2){
	printf("29 en année bisextile et 28 sinon");	 
}else{
	printf("29 jours\n");
}
return;
}
/*
 * 
 * Affiche la durée en secondes entre deux dates 
 * 
 */
void duree(){
int heures1;
int minutes1;
int secondes1;
int jour1;
int mois1;
int annee1;
time_t date1T;
struct tm date1;

int heures2;
int minutes2;
int secondes2;
int jour2;
int mois2;
int annee2;
time_t date2T;
struct tm date2;
/*
 * Saisie de la date 1
 */
printf("Saisissez votre première date : jour, mois, annee, heures, minutes, secondes\n");
scanf("%d %d %d %d %d %d",&jour1,&mois1,&annee1,&heures1,&minutes1,&secondes1);
date1.tm_hour = heures1;
date1.tm_min = minutes1;
date1.tm_sec = secondes1;
date1.tm_mday = jour1;
date1.tm_mon = mois1-1;
date1.tm_year = annee1;
/*
 * Saisie de la date 2
 */

printf("Saisissez votre seconde date : jour, mois, annee, heures, minutes, secondes\n");
scanf("%d %d %d %d %d %d",&jour2,&mois2,&annee2,&heures2,&minutes2,&secondes2);
date2.tm_hour = heures2;
date2.tm_min = minutes2;
date2.tm_sec = secondes2;
date2.tm_mday = jour2;
date2.tm_mon = mois2-1;
date2.tm_year = annee2;

/*
 * Conversion en time_t
 */
date1T = mktime(&date1);
date2T = mktime(&date2);
/*
 * Calcul de la durée
 */
double totalsecs = difftime(date2T,date1T);

printf("Durée : %F",totalsecs);
return;
}
/*
 * 
 * Affiche le jour suivant de celui en entrée
 * 
 */
void nextday(){
int jour1;
int mois1;
int annee1;
time_t date1T;
struct tm date1;
char buffer[80];

/*
 * Saisie de la date
 */
printf("Saisissez votre date : jour, mois, annee\n");
scanf("%d %d %d",&jour1,&mois1,&annee1);
date1.tm_hour = 0;
date1.tm_min = 0;
date1.tm_sec = 0;
date1.tm_mday = jour1+1;
date1.tm_mon = mois1-1;
date1.tm_year = annee1-1900;
date1T = mktime(&date1);

/*
 * Ajustement du temps
 */
strftime(buffer,80,"%d/%m/%Y", localtime(&date1T));

printf("|%s|\n", buffer );
return;
}

int main(){
nextday();

return 0;
}
