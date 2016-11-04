using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WinTest_Form_
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            Label label = new Label();
            label.Text = "My first System.Windows.Forms applicaton";
            label.Dock = DockStyle.Fill;

            this.Controls.Add(label);
            this.Text = "Hello";
            this.Size = new Size(250, 250);
            this.CenterToScreen();
        }
    }
}
