using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP2
{
    public class Client : Personne
    {
        /// <summary>
        /// Numéro du Pays
        /// </summary>
        public int NumeroPays{ get; private set; }

        public Client(string nom, string prenom, int age,int numPays) : base(nom,prenom,age)
        {
            NumeroPays = numPays;
        }
    }
}
