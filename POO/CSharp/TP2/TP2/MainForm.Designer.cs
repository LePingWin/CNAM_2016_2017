namespace TP2
{
    partial class MainForm
    {
        /// <summary>
        /// Variable nécessaire au concepteur.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Nettoyage des ressources utilisées.
        /// </summary>
        /// <param name="disposing">true si les ressources managées doivent être supprimées ; sinon, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Code généré par le Concepteur Windows Form

        /// <summary>
        /// Méthode requise pour la prise en charge du concepteur - ne modifiez pas
        /// le contenu de cette méthode avec l'éditeur de code.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.dgw_Clients = new System.Windows.Forms.DataGridView();
            this.lstBox_Pays = new System.Windows.Forms.ListBox();
            this.errorProvider1 = new System.Windows.Forms.ErrorProvider(this.components);
            this.btn_AddClient = new System.Windows.Forms.Button();
            this.lbl_LstBoxPays = new System.Windows.Forms.Label();
            this.lbl_dgwClient = new System.Windows.Forms.Label();
            this.btn_Quit = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgw_Clients)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).BeginInit();
            this.SuspendLayout();
            // 
            // dgw_Clients
            // 
            this.dgw_Clients.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgw_Clients.Location = new System.Drawing.Point(15, 149);
            this.dgw_Clients.Name = "dgw_Clients";
            this.dgw_Clients.Size = new System.Drawing.Size(631, 173);
            this.dgw_Clients.TabIndex = 0;
            // 
            // lstBox_Pays
            // 
            this.lstBox_Pays.FormattingEnabled = true;
            this.lstBox_Pays.Location = new System.Drawing.Point(15, 25);
            this.lstBox_Pays.Name = "lstBox_Pays";
            this.lstBox_Pays.Size = new System.Drawing.Size(631, 95);
            this.lstBox_Pays.TabIndex = 1;
            this.lstBox_Pays.SelectedValueChanged += new System.EventHandler(this.lstBox_Pays_SelectedValueChanged);
            // 
            // errorProvider1
            // 
            this.errorProvider1.ContainerControl = this;
            // 
            // btn_AddClient
            // 
            this.btn_AddClient.Location = new System.Drawing.Point(445, 328);
            this.btn_AddClient.Name = "btn_AddClient";
            this.btn_AddClient.Size = new System.Drawing.Size(119, 23);
            this.btn_AddClient.TabIndex = 2;
            this.btn_AddClient.Text = "Ajouter un Client";
            this.btn_AddClient.UseVisualStyleBackColor = true;
            this.btn_AddClient.Click += new System.EventHandler(this.btn_AddClient_Click);
            // 
            // lbl_LstBoxPays
            // 
            this.lbl_LstBoxPays.AutoSize = true;
            this.lbl_LstBoxPays.Location = new System.Drawing.Point(12, 9);
            this.lbl_LstBoxPays.Name = "lbl_LstBoxPays";
            this.lbl_LstBoxPays.Size = new System.Drawing.Size(92, 13);
            this.lbl_LstBoxPays.TabIndex = 3;
            this.lbl_LstBoxPays.Text = "Sélection du Pays";
            this.lbl_LstBoxPays.Click += new System.EventHandler(this.label1_Click);
            // 
            // lbl_dgwClient
            // 
            this.lbl_dgwClient.AutoSize = true;
            this.lbl_dgwClient.Location = new System.Drawing.Point(15, 130);
            this.lbl_dgwClient.Name = "lbl_dgwClient";
            this.lbl_dgwClient.Size = new System.Drawing.Size(83, 13);
            this.lbl_dgwClient.TabIndex = 4;
            this.lbl_dgwClient.Text = "Clients Associés";
            // 
            // btn_Quit
            // 
            this.btn_Quit.Location = new System.Drawing.Point(571, 328);
            this.btn_Quit.Name = "btn_Quit";
            this.btn_Quit.Size = new System.Drawing.Size(75, 23);
            this.btn_Quit.TabIndex = 5;
            this.btn_Quit.Text = "Quitter";
            this.btn_Quit.UseVisualStyleBackColor = true;
            this.btn_Quit.Click += new System.EventHandler(this.btn_Quit_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(656, 358);
            this.Controls.Add(this.btn_Quit);
            this.Controls.Add(this.lbl_dgwClient);
            this.Controls.Add(this.lbl_LstBoxPays);
            this.Controls.Add(this.btn_AddClient);
            this.Controls.Add(this.lstBox_Pays);
            this.Controls.Add(this.dgw_Clients);
            this.Name = "MainForm";
            this.Text = "TP2 - Loïc Bine";
            ((System.ComponentModel.ISupportInitialize)(this.dgw_Clients)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dgw_Clients;
        private System.Windows.Forms.ListBox lstBox_Pays;
        private System.Windows.Forms.ErrorProvider errorProvider1;
        private System.Windows.Forms.Button btn_AddClient;
        private System.Windows.Forms.Label lbl_LstBoxPays;
        private System.Windows.Forms.Label lbl_dgwClient;
        private System.Windows.Forms.Button btn_Quit;
    }
}

