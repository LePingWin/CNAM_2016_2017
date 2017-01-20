using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;
using TP2;

namespace TP2
{
    public partial class MainForm : Form
    {
        /// <summary>
        /// Chemin d'accès au fichier client
        /// </summary>
        public static string ClientsPath { get; private set; } = @"D:\Users\Loïc\Documents\1ère Année CNAM\CNAM_Git\CNAM_2016_2017\POO\CSharp\TP2\TP2\ClientsListe.xml";
        /// <summary>
        /// Chemin d'accès au fichier Pays
        /// </summary>
        public static string PaysPath { get; private set; } = @"D:\Users\Loïc\Documents\1ère Année CNAM\CNAM_Git\CNAM_2016_2017\POO\CSharp\TP2\TP2\PaysListe.xml";
        /// <summary>
        /// Fichier Client
        /// </summary>
        public static XDocument Clients{ get; private set; }
        /// <summary>
        /// Fichier Pays
        /// </summary>
        public static XDocument Pays { get; private set; }

        /// <summary>
        /// Liste de Pays 
        /// </summary>
        public static List<Pays> PaysList { get; private set;}

        public MainForm()
        {
            InitializeComponent();
            this.BackColor = Color.FromArgb(192, 57, 43);
            Clients = XDocument.Load(ClientsPath);
            Pays = XDocument.Load(PaysPath);
            PaysList = (from pays in MainForm.Pays.Root.Elements("Pays")
                                    select new Pays(int.Parse(pays.Element("NumeroPays").Value), pays.Element("NomFrancais").Value, pays.Element("NomAnglais").Value)).ToList();
            fillListBox();
        }

        /// <summary>
        /// Rempli le dgw avec les infos clients
        /// </summary>
        /// <param name="nom"></param>
        public void fillDataGridView()
        {

            

            var jointure = (from client in Clients.Root.Elements("Client")
                            join pays in PaysList
                            on int.Parse(client.Element("NumeroPays").Value) equals pays.NumeroPays
                            where lstBox_Pays.SelectedValue.ToString() == pays.NomAnglais
                            select
                            new
                            { clientS = new Client(client.Element("Nom").Value, client.Element("Prenom").Value, int.Parse(client.Element("Age").Value), int.Parse(client.Element("NumeroPays").Value))
                            , pays
                            });
            dgw_Clients.DataSource = (from client in jointure
                                      select new { client.clientS.Nom, client.clientS.Prenom, client.clientS.Age, client.pays.NomAnglais }).ToList();
        }

        /// <summary>
        /// Rempli la listBox avec les pays
        /// </summary>
        public void fillListBox()
        {
            lstBox_Pays.DataSource = (from p in PaysList select p.NomAnglais).ToList();
        }

        /// <summary>
        /// Evenement de changement de selection dans la listBox Pays
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lstBox_Pays_SelectedValueChanged(object sender, EventArgs e)
        {
            fillDataGridView();
        }

        /// <summary>
        /// Bouton d'ajout des clients
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btn_AddClient_Click(object sender, EventArgs e)
        {
            Frm_AddClient clients = new Frm_AddClient();
            if(clients.ShowDialog() == DialogResult.OK)
            {
                fillDataGridView();
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        /// <summary>
        /// Bouton pour quitter l'application
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btn_Quit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
