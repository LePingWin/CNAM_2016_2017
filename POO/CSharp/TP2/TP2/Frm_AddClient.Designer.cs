namespace TP2
{
    partial class Frm_AddClient
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.cbx_Pays = new System.Windows.Forms.ComboBox();
            this.gbx_ClientsAdd = new System.Windows.Forms.GroupBox();
            this.lbl_Nom = new System.Windows.Forms.Label();
            this.txtB_Nom = new System.Windows.Forms.TextBox();
            this.txtB_Prenom = new System.Windows.Forms.TextBox();
            this.lbl_Prenom = new System.Windows.Forms.Label();
            this.txtB_Age = new System.Windows.Forms.TextBox();
            this.lbl_Age = new System.Windows.Forms.Label();
            this.lbl_Pays = new System.Windows.Forms.Label();
            this.btn_Valid = new System.Windows.Forms.Button();
            this.btn_Quit = new System.Windows.Forms.Button();
            this.gbx_ClientsAdd.SuspendLayout();
            this.SuspendLayout();
            // 
            // cbx_Pays
            // 
            this.cbx_Pays.FormattingEnabled = true;
            this.cbx_Pays.Location = new System.Drawing.Point(87, 130);
            this.cbx_Pays.Name = "cbx_Pays";
            this.cbx_Pays.Size = new System.Drawing.Size(166, 21);
            this.cbx_Pays.TabIndex = 0;
            // 
            // gbx_ClientsAdd
            // 
            this.gbx_ClientsAdd.Controls.Add(this.btn_Quit);
            this.gbx_ClientsAdd.Controls.Add(this.btn_Valid);
            this.gbx_ClientsAdd.Controls.Add(this.lbl_Pays);
            this.gbx_ClientsAdd.Controls.Add(this.txtB_Age);
            this.gbx_ClientsAdd.Controls.Add(this.cbx_Pays);
            this.gbx_ClientsAdd.Controls.Add(this.lbl_Age);
            this.gbx_ClientsAdd.Controls.Add(this.txtB_Prenom);
            this.gbx_ClientsAdd.Controls.Add(this.lbl_Prenom);
            this.gbx_ClientsAdd.Controls.Add(this.txtB_Nom);
            this.gbx_ClientsAdd.Controls.Add(this.lbl_Nom);
            this.gbx_ClientsAdd.Location = new System.Drawing.Point(13, 13);
            this.gbx_ClientsAdd.Name = "gbx_ClientsAdd";
            this.gbx_ClientsAdd.Size = new System.Drawing.Size(259, 236);
            this.gbx_ClientsAdd.TabIndex = 1;
            this.gbx_ClientsAdd.TabStop = false;
            this.gbx_ClientsAdd.Text = "Client";
            this.gbx_ClientsAdd.Enter += new System.EventHandler(this.groupBox1_Enter);
            // 
            // lbl_Nom
            // 
            this.lbl_Nom.AutoSize = true;
            this.lbl_Nom.Location = new System.Drawing.Point(7, 20);
            this.lbl_Nom.Name = "lbl_Nom";
            this.lbl_Nom.Size = new System.Drawing.Size(29, 13);
            this.lbl_Nom.TabIndex = 0;
            this.lbl_Nom.Text = "Nom";
            // 
            // txtB_Nom
            // 
            this.txtB_Nom.Location = new System.Drawing.Point(10, 36);
            this.txtB_Nom.Name = "txtB_Nom";
            this.txtB_Nom.Size = new System.Drawing.Size(243, 20);
            this.txtB_Nom.TabIndex = 1;
            // 
            // txtB_Prenom
            // 
            this.txtB_Prenom.Location = new System.Drawing.Point(10, 80);
            this.txtB_Prenom.Name = "txtB_Prenom";
            this.txtB_Prenom.Size = new System.Drawing.Size(243, 20);
            this.txtB_Prenom.TabIndex = 3;
            // 
            // lbl_Prenom
            // 
            this.lbl_Prenom.AutoSize = true;
            this.lbl_Prenom.Location = new System.Drawing.Point(7, 64);
            this.lbl_Prenom.Name = "lbl_Prenom";
            this.lbl_Prenom.Size = new System.Drawing.Size(43, 13);
            this.lbl_Prenom.TabIndex = 2;
            this.lbl_Prenom.Text = "Prenom";
            this.lbl_Prenom.Click += new System.EventHandler(this.label1_Click);
            // 
            // txtB_Age
            // 
            this.txtB_Age.Location = new System.Drawing.Point(10, 130);
            this.txtB_Age.Name = "txtB_Age";
            this.txtB_Age.Size = new System.Drawing.Size(57, 20);
            this.txtB_Age.TabIndex = 5;
            // 
            // lbl_Age
            // 
            this.lbl_Age.AutoSize = true;
            this.lbl_Age.Location = new System.Drawing.Point(7, 114);
            this.lbl_Age.Name = "lbl_Age";
            this.lbl_Age.Size = new System.Drawing.Size(26, 13);
            this.lbl_Age.TabIndex = 4;
            this.lbl_Age.Text = "Age";
            this.lbl_Age.Click += new System.EventHandler(this.label1_Click_1);
            // 
            // lbl_Pays
            // 
            this.lbl_Pays.AutoSize = true;
            this.lbl_Pays.Location = new System.Drawing.Point(87, 114);
            this.lbl_Pays.Name = "lbl_Pays";
            this.lbl_Pays.Size = new System.Drawing.Size(30, 13);
            this.lbl_Pays.TabIndex = 6;
            this.lbl_Pays.Text = "Pays";
            // 
            // btn_Valid
            // 
            this.btn_Valid.Location = new System.Drawing.Point(97, 207);
            this.btn_Valid.Name = "btn_Valid";
            this.btn_Valid.Size = new System.Drawing.Size(75, 23);
            this.btn_Valid.TabIndex = 7;
            this.btn_Valid.Text = "Ajouter";
            this.btn_Valid.UseVisualStyleBackColor = true;
            this.btn_Valid.Click += new System.EventHandler(this.btn_Valid_Click);
            // 
            // btn_Quit
            // 
            this.btn_Quit.Location = new System.Drawing.Point(178, 207);
            this.btn_Quit.Name = "btn_Quit";
            this.btn_Quit.Size = new System.Drawing.Size(75, 23);
            this.btn_Quit.TabIndex = 8;
            this.btn_Quit.Text = "Quitter";
            this.btn_Quit.UseVisualStyleBackColor = true;
            this.btn_Quit.Click += new System.EventHandler(this.btn_Quit_Click);
            // 
            // Frm_AddClient
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.gbx_ClientsAdd);
            this.Name = "Frm_AddClient";
            this.Text = "Frm_AddClient";
            this.gbx_ClientsAdd.ResumeLayout(false);
            this.gbx_ClientsAdd.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ComboBox cbx_Pays;
        private System.Windows.Forms.GroupBox gbx_ClientsAdd;
        private System.Windows.Forms.Label lbl_Nom;
        private System.Windows.Forms.TextBox txtB_Prenom;
        private System.Windows.Forms.Label lbl_Prenom;
        private System.Windows.Forms.TextBox txtB_Nom;
        private System.Windows.Forms.TextBox txtB_Age;
        private System.Windows.Forms.Label lbl_Age;
        private System.Windows.Forms.Button btn_Valid;
        private System.Windows.Forms.Label lbl_Pays;
        private System.Windows.Forms.Button btn_Quit;
    }
}