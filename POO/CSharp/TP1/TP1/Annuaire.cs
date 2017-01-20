using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    class Annuaire : List<Personne>
    {
        public override string ToString()
        {
            string res = "";
            foreach(var p in this)
            {
                res += p.ToString() + "\n";
            }
            return res;
        }
    }
}
