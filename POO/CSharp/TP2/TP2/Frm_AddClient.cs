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

namespace TP2
{
    public partial class Frm_AddClient : Form
    {
        public Frm_AddClient()
        {
            InitializeComponent();
            this.BackColor = Color.FromArgb(192, 57, 43);
            fillCbx();
        }

        public void fillCbx()
        {
            cbx_Pays.DataSource = (from p in MainForm.PaysList select p.NomAnglais).ToList();
        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label1_Click_1(object sender, EventArgs e)
        {

        }

        private void btn_Valid_Click(object sender, EventArgs e)
        {
            if(txtB_Nom.Text != "")
            {
                if(txtB_Prenom.Text != "")
                {
                    int age = 0;
                    if(txtB_Age.Text != "" && int.TryParse(txtB_Age.Text,out age))
                    {

                        this.DialogResult = DialogResult.OK;
                        //Ecriture XML
                        MainForm.Clients.Root.Add(new XElement("Client",
                            new XElement("Nom", txtB_Nom.Text.Trim()),
                            new XElement("Prenom", txtB_Prenom.Text.Trim()),
                            new XElement("Age", age),
                            new XElement("NumeroPays", (from p in MainForm.PaysList 
                                                       where p.NomAnglais == cbx_Pays.SelectedValue.ToString()
                                                       select p.NumeroPays).FirstOrDefault())));
                        MainForm.Clients.Save(MainForm.ClientsPath);
                        this.Close();
                    }
                    else
                    {
                        MessageBox.Show("L'âge n'est pas renseigné");
                    }
                }else
                {
                    MessageBox.Show("Le Prénom n'est pas renseigné");
                }
            }
            else
            {
                MessageBox.Show("Le nom n'est pas renseigné");
            }
        }

        private void btn_Quit_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
            this.Close();
        }
    }
}
