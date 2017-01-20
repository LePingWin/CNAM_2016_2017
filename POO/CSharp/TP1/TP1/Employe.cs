using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    class Employe : Personne
    {
        public string Entreprise{ get; set; }
        public int Anciennete{ get; set; }

        public Employe(string nom, string prenom, int age,string entreprise, int anciennete) : base(nom,prenom,age)
        {
            this.Entreprise = entreprise;
            Anciennete = anciennete;
        }
    }
}
