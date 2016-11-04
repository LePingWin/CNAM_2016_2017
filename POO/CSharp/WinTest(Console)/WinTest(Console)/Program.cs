using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WinTest_Console_
{
    class Program
    {
        static void Main(string[] args)
        {
            Dictionary<string, int> dictionary = new Dictionary<string, int>();
            dictionary.Add("apple", 100);
            dictionary.Add("windows", 500);
            foreach(var pair in dictionary)
            {
                Console.WriteLine("{0},{1}", pair.Key, pair.Value);
            }
        }
    }
}
