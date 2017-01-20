using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    class Program
    {
        static void Main(string[] args)
        {
            Annuaire persList = new Annuaire();
            do
            {
                string nom = "";
                string prenom = "";
                int age = 0;
                Console.WriteLine("Entrer dans la console le Nom");
                nom = Console.ReadLine();
                if(nom == "")
                {
                    break;
                }
                Console.WriteLine("Entrer dans la console le Prenom");
                prenom = Console.ReadLine();
                Console.WriteLine("Entrer dans la console l'âge");
                while (!int.TryParse(Console.ReadLine(), out age))
                {
                    Console.WriteLine("Entrer dans la console l'âge");
                }


                    Console.Write("Saisir une entreprise");
                    string sEnt = Console.ReadLine();
                    Console.Write("Saisir l'ancienneté : ");
                    int iAnc = int.Parse(Console.ReadLine());
                    persList.Add(new Employe(nom, prenom, age, sEnt, iAnc));
                
            Console.Write("Voulez-vous créer un patron ?");
            if (Console.ReadLine() == "o")
            {
                    Console.Write("Saisir le nombre d'employés");
                    int iNb = int.Parse(Console.ReadLine());

                    persList.Add(new Patron(nom, prenom, age, sEnt, iAnc, iNb));

                }
                else
                {
                    persList.Add(new Employe(nom, prenom, age, sEnt, iAnc));
                }
                
            } while (true);
            
            persList.Sort();
            Console.WriteLine(persList);
            Console.ReadLine();
        }
    }
}
