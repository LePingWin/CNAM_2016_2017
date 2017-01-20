using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP2
{
    public class Personne
    {
        /// <summary>
        /// Nom de la personne
        /// </summary>
        public string Nom { get; private set; }
        /// <summary>
        /// Prénom de la personne
        /// </summary>
        public string Prenom { get; private set; }
        /// <summary>
        /// Age de la personne
        /// </summary>
        public int Age { get; private set; }

        public Personne(string nom, string prenom, int age)
        {
            Nom = nom;
            Prenom = prenom;
            Age = age;
        }
    }
}
