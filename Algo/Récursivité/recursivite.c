#include <stdio.h>
#include <stdlib.h>

int suite(int terme)
{
	if(terme == 0){
		printf("%d\n",5);
		return 5;
	}
	int n = 2 * suite(terme-1) - 1;
	printf("%d\n",n);
	return n;
}

int syracuse(int terme)
{
	printf("%d\n",terme);
	if(terme <= 0 || terme == 1){
		return 1;
	}
	if(terme % 2 == 0){
		int r = syracuse(terme/2);
		return r;
	}
	int n = syracuse(3*terme+1);
	return n;
}

int chiffres(int chif){

	if(chif<=0){
		return chif;
	}
	printf("%d\n",chif%10);
	return chiffres(chif/10);
}

// Retourne longueur d'une chaine de caracteres
int longueur(char* c){
    int res = 0;
    while(*c!='\0'){
        res++;
        c++;
    }
    return res;
}
char* sub_string(char* chaine, int pos, int len){
            
    char* res = malloc(sizeof(char)*len);
    
    for(int i = 0; i < len; i++) {
        *(res + i ) = *(chaine + i + pos);
    }
    
    return res;
}

int palindrome(char* chaine){
	int l = longueur(chaine);
    if(l==0 || l==1){
        return 0;
	}
	if(chaine[0] == chaine[l-1]){  
		return palindrome(sub_string(chaine, 1, l-2));
	}else{
		return 1;
	}
}

int fibonnacci(int n,char* str)
{
	if(n>=2){
			
		 n = fibonnacci(n-1,str) + fibonnacci(n-2,str);
		 
	}
	sprintf(str,"%d",n);
	printf("%s\n",str);

	return n;
}

int fibonnacciIt(int n, char *str){
	int first = 0;
	int second = 1;
	int tmp;
	while(n--){
		tmp = first+second;
		first = second;
		second = tmp;
		sprintf(str,"%d",first);
	printf("%s\n",str);
	}
	
	return first;
}



int main()
{
	char str[120];
	fibonnacciIt(6,str);
	printf("%s\n",str);
	return 0;
}
