using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    abstract class Personne : IComparable<Personne>
    {
        public string Nom { get; set; }
        public string Prenom { get; set; }
        private int age;
        public int Age
        {
            get { return age; }
            set
            {
                if (age < 0)
                {
                    throw new Exception("L'âge doit être positif !");
                }
                age = value;
            }
        }

        public Personne()
        {

        }

        public Personne(string nom, string prenom, int age)
        {
            Nom = nom;
            Prenom = prenom;
            Age = age;
        }

        public override string ToString()
        {
            return Nom + " " + Prenom + " - " + Age + "ans";
        }
        public int CompareTo(Personne other)
        {
            if (Nom.CompareTo(other.Nom) == 0)
            {
                return this.Prenom.CompareTo(other.Prenom);
            }
            else
            {
                return this.Nom.CompareTo(other.Nom);
            }
        }
    }
    
}
