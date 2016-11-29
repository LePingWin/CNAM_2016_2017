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
    public partial class SimpleWindow : Form
    {
        private Bitmap image;
        public SimpleWindow()
        {
            InitializeComponent();
            Text = "Image";
            loadImage();
            ClientSize = new Size(image.Width, image.Height);
            Paint += new PaintEventHandler(OnPaint);
            CenterToScreen();
        }
        
        void loadImage()
        {
            try
            {
                OpenFileDialog img = new OpenFileDialog();
                if (img.ShowDialog() == DialogResult.OK)
                {
                    image = new Bitmap(img.FileName);
                }
            }
            catch(Exception e)
            {
                Console.WriteLine(e.Message);
                Environment.Exit(1);
            }
        }

        void OnPaint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            Rectangle r = new Rectangle(1, 1, image.Width, image.Height);
            g.DrawImage(image, r);
        }
    }
}
