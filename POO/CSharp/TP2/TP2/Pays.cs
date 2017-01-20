using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP2
{
    public class Pays
    {
        /// <summary>
        /// Numéro du Pays
        /// </summary>
        public int NumeroPays { get; private set; }
        /// <summary>
        /// Nom Français du Pays
        /// </summary>
        public string NomFrancais { get; private set; }
        /// <summary>
        /// Nom Anglais du Pays
        /// </summary>
        public string NomAnglais { get; private set; }

        public Pays(int numPays, string nomFrancais, string nomAnglais)
        {
            NumeroPays = numPays;
            NomFrancais = nomFrancais;
            NomAnglais = nomAnglais;
        }
    }
}
