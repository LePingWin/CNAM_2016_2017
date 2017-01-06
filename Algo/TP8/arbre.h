#ifndef arbre
#define arbre
typedef struct Noeud{
	int label;
	struct Noeud* fd;
	struct Noeud* fg;
} Arbre;

typedef int boolean;
#define True 1 
#define False 0
#ifndef max
 #define max(a,b) ((a) > (b) ? (a) : (b))
#endif

Arbre* nouvArbre();
Arbre* enrac(int racine, Arbre* fg, Arbre* fd);
Arbre* creerFeuille(int val);
int racine(Arbre* a);
Arbre* fg(Arbre* a);
Arbre* fd(Arbre* a);
int taille(Arbre* a);
int hauteur(Arbre* a);
void sauve_dot(Arbre* a,char* filename);
void parcoursPrefixe(Arbre* a);
void parcoursInfixe(Arbre* a);
void parcoursPostFixe(Arbre* a);
#endif
