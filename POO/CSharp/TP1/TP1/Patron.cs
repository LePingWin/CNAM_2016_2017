using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    class Patron : Employe
    {
        public int NombreEmployes { get; set; }
        public Patron(string nom, string prenom, int age, string entreprise, int anciennete,int nbEmployes) : base(nom, prenom, age, entreprise, anciennete)
        {
            NombreEmployes = nbEmployes;
        }
    }
}
