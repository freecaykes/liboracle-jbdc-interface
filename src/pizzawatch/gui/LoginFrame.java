/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pizzawatch.gui;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import pizzawatch.sql.connection.JBDCSQLConnection;
import pizzawatch.sql.sqlreader.SqlScriptReader;
import pizzawatch.utils.UserUtils;

@SuppressWarnings("serial")
public class LoginFrame extends javax.swing.JFrame
{
	private static final SqlScriptReader SQL_READER = SqlScriptReader.getInstance();
	
    public LoginFrame()
    {
        initComponents();

        attemptAndHandleConnection();
    }

    private void attemptAndHandleConnection()
    {
        JBDCSQLConnection sqlcon = new JBDCSQLConnection();
        boolean connectionSucceeded = sqlcon.setOracleConnection();

        lbConnectionError1.setVisible(!connectionSucceeded); //Hide if connection fails
        lbConnectionError2.setVisible(!connectionSucceeded); //Hide if connection fails
        btRetryConnection.setVisible(!connectionSucceeded); //Hide if connection fails

        tfUserID.setEnabled(connectionSucceeded); //Show if connection succeeds
        jpPassword.setEnabled(connectionSucceeded); //Show if connection succeeds
        btLogin.setEnabled(connectionSucceeded); //Show if connection succeeds
        btNewUser.setEnabled(connectionSucceeded); //Show if connection succeeds
    }

    private void handleLoginAttempt()
    {
        //SqlScriptReader.getInstance().insertUpdateCreateDelete("SQL_Scripts/dataInserts.sql");
        //SqlScriptReader.getInstance().insertUpdateCreateDelete("SQL_Scripts/projectDefs.sql");
        //SqlScriptReader.getInstance().insertUpdateCreateDelete("SQL_Scripts/dropTables.sql");
        //SqlScriptReader.getInstance().insertUpdateCreateDelete("DROP TABLE Users");
        String userID = tfUserID.getText();
    	String userPass = new String(jpPassword.getPassword());

        if(userID.isEmpty() || userPass.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "User ID or password empty");
            return;
        }

        jpPassword.setText(null);

    	boolean passwordCorrect = UserUtils.isPasswordCorrect(userID, userPass);
    	if(passwordCorrect == false)
        {
            JOptionPane.showMessageDialog(this, "User ID and password combination invalid");
            return;
    	}

    	MainFrame mf = new MainFrame(this, UserUtils.getUserFromDB(userID));
        mf.setVisible(true);
        this.setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lbTitle = new javax.swing.JLabel();
        tfUserID = new javax.swing.JTextField();
        lbUserID = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        jpPassword = new javax.swing.JPasswordField();
        btLogin = new javax.swing.JButton();
        btNewUser = new javax.swing.JButton();
        lbConnectionError1 = new javax.swing.JLabel();
        lbConnectionError2 = new javax.swing.JLabel();
        btRetryConnection = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitle.setText("TMNT Pizza Watch");

        tfUserID.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                tfUserIDKeyReleased(evt);
            }
        });

        lbUserID.setText("User ID:");

        lbPassword.setText("Password:");

        jpPassword.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jpPasswordKeyReleased(evt);
            }
        });

        btLogin.setText("Login");
        btLogin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btLoginActionPerformed(evt);
            }
        });

        btNewUser.setText("New User");
        btNewUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btNewUserActionPerformed(evt);
            }
        });

        lbConnectionError1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbConnectionError1.setForeground(new java.awt.Color(255, 0, 0));
        lbConnectionError1.setText("Unable to connect to the database.");

        lbConnectionError2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbConnectionError2.setForeground(new java.awt.Color(255, 0, 0));
        lbConnectionError2.setText("Check your internet connection and/or try again later.");

        btRetryConnection.setText("Retry Connection");
        btRetryConnection.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btRetryConnectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbTitle)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbUserID)
                                .addComponent(lbPassword))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btNewUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(0, 182, Short.MAX_VALUE))
                                .addComponent(jpPassword)
                                .addComponent(tfUserID))))
                    .addComponent(lbConnectionError1)
                    .addComponent(lbConnectionError2)
                    .addComponent(btRetryConnection))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUserID)
                    .addComponent(tfUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassword)
                    .addComponent(jpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNewUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbConnectionError1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbConnectionError2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btRetryConnection)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNewUserActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btNewUserActionPerformed
    {//GEN-HEADEREND:event_btNewUserActionPerformed
        AddOrEditUserFrame frame = new AddOrEditUserFrame(null);
        frame.setVisible(true);
    }//GEN-LAST:event_btNewUserActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btLoginActionPerformed
    {//GEN-HEADEREND:event_btLoginActionPerformed
    	handleLoginAttempt();
    }//GEN-LAST:event_btLoginActionPerformed

    private void jpPasswordKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jpPasswordKeyReleased
    {//GEN-HEADEREND:event_jpPasswordKeyReleased
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            handleLoginAttempt();
        }
    }//GEN-LAST:event_jpPasswordKeyReleased

    private void btRetryConnectionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btRetryConnectionActionPerformed
    {//GEN-HEADEREND:event_btRetryConnectionActionPerformed
        attemptAndHandleConnection();
    }//GEN-LAST:event_btRetryConnectionActionPerformed

    private void tfUserIDKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tfUserIDKeyReleased
    {//GEN-HEADEREND:event_tfUserIDKeyReleased
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            handleLoginAttempt();
        }
    }//GEN-LAST:event_tfUserIDKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {}
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	if(SQL_READER.query("SQL_Scripts/testQueries.sql") == null)
            	{
            		SQL_READER.insertUpdateCreateDelete("SQL_Scripts/projectDefs.sql");
            		SQL_READER.insertUpdateCreateDelete("SQL_Scripts/dataInserts.sql");
            	}
            		
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogin;
    private javax.swing.JButton btNewUser;
    private javax.swing.JButton btRetryConnection;
    private javax.swing.JPasswordField jpPassword;
    private javax.swing.JLabel lbConnectionError1;
    private javax.swing.JLabel lbConnectionError2;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUserID;
    private javax.swing.JTextField tfUserID;
    // End of variables declaration//GEN-END:variables

}
