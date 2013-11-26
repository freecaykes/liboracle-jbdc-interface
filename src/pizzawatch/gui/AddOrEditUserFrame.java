/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pizzawatch.gui;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import pizzawatch.datamodels.User;
import pizzawatch.utils.UserUtils;

/**
 *
 * @author John
 */
@SuppressWarnings("serial")
public class AddOrEditUserFrame extends javax.swing.JFrame
{
    private final boolean isEditMode;
    private final User currentUser;
    /**
     * Creates new form NewUserFrame
     * If user is null, the frame is in add mode, otherwise it is in edit mode
     * @param user The user that will have their account details edited, or null if adding a new user
     */
    public AddOrEditUserFrame(User user) {
        this.isEditMode = user != null;
        this.currentUser = user;
        initComponents();

        if(isEditMode) {
            userID.setEnabled(false);
            createOrEditAccountLabel.setText("EDIT ACCOUNT DETAILS");
            loadPrevData();
        }
    }

    private AbstractFormatterFactory getMaskFormatterFactory()
    {
        AbstractFormatterFactory dff = new AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                try {
                    MaskFormatter mf = new MaskFormatter("################");
                    mf.setPlaceholderCharacter('0');
                    return mf;
                } catch (ParseException ex) {
                    return new MaskFormatter();
                }
            }
        };

        return dff;
    }

    private void loadPrevData()
    {
        userID.setText(currentUser.getUserID());
        firstName.setText(currentUser.getFirstName());
        lastName.setText(currentUser.getLastName());
        //The plaintext password isn't stored in the DB
        //But should be ok, just check hash of password field vs prev hash
        cardNumber.setText(currentUser.getCreditCardNumber());
    }
    private boolean verifyMaxLength (int type,String string){
        boolean verify = true;
        switch(type){
            case 1: if(string.length() > 30){
                        verify = false;
                    }
                    break;
            case 2: if(string.length() > 40){
                        verify = false;
                    }
                    break;
            case 3: if(string.length() > 40){
                        verify = false;
                    }
                    break;
            case 4: if(string.length() > 16){
                        verify = false;
                    }
                    break;
            default:verify = true;
                    break;
        }
        return(verify);
    }
    private boolean veryChar (int type, String string){
          boolean verify = true;
        switch(type){
            case 1: if(string.length() > 30){
                        verify = false;
                    }
                    break;
            case 2: if(string.length() > 40){
                        verify = false;
                    }
                    break;
            case 3: if(string.length() > 40){
                        verify = false;
                    }
                    break;
            case 4: if(string.length() > 16){
                        verify = false;
                    }
                    break;
            default:verify = true;
                    break;
        }
        return(verify);
    }
    private void handleSubmitAttempt()
    {
        String newUserID = userID.getText();
        String newPassword = new String (password.getPassword());
        String newPasswordReType = new String (passwordReType.getPassword());
        String newFirstName = firstName.getText();
        String newLastName = lastName.getText();
        String newCardNumber = cardNumber.getText();
        int userIDVar = 1,cardNumberVar = 2,firstNameVar = 3, lastNameVar = 4; 
        //Checks if all fields are filled / correct
        if (newUserID.isEmpty() || newPassword.isEmpty() || newPasswordReType.isEmpty() ||
            newFirstName.isEmpty() || newLastName.isEmpty() || newCardNumber.equals("0000000000000000")) {
            JOptionPane.showMessageDialog(this, "Some fields are blank. All fields are required.");
            return;
        }
        
        //Checks if the two passwords match
        if (!newPassword.equals(newPasswordReType)){
            JOptionPane.showMessageDialog (this, "Passwords do not match. Please try again.");
            return;
        }
        //Checks if the User ID is compose of only numbers and letters
        //If in edit mode, check (via hash) if the password in the DB matches the one in the password field
        //If they don't match, ask if the user wanted to change their password
        //TODO store hash in User.java so that we can avoid calling the DB again?
        if(isEditMode && UserUtils.isPasswordCorrect(currentUser.getUserID(), newPassword) == false) {
            int result = JOptionPane.showConfirmDialog(this,
                                                       "The password you have entered is different from the stored. Do you wish to update your password?");
            if(result != JOptionPane.YES_OPTION) {
                return;
            }
        }

        //Do actual updating here
        String dummy = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        cardNumberLabel = new javax.swing.JLabel();
        userIDLabel = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        passwordLabel = new javax.swing.JLabel();
        reTypePasswordLabel = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        passwordReType = new javax.swing.JPasswordField();
        cardNumber = new javax.swing.JFormattedTextField();
        createOrEditAccountLabel = new javax.swing.JLabel();
        userID = new javax.swing.JFormattedTextField();
        firstName = new javax.swing.JFormattedTextField();
        lastName = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        firstNameLabel.setText("First Name");

        lastNameLabel.setText("Last Name");

        lbTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitle.setText("TMNT Pizza Watch");

        cardNumberLabel.setText("Card Number");

        userIDLabel.setText("User ID");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        passwordLabel.setText("Password");

        reTypePasswordLabel.setText("Re-type Password");

        cardNumber.setFormatterFactory(getMaskFormatterFactory());

        createOrEditAccountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createOrEditAccountLabel.setText("CREATE A NEW ACCOUNT");

        try {
            userID.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        userID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userIDActionPerformed(evt);
            }
        });

        try {
            firstName.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            lastName.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cardNumber)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle)
                            .addComponent(reTypePasswordLabel)
                            .addComponent(passwordLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardNumberLabel)
                            .addComponent(createOrEditAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(firstNameLabel)
                                .addGap(274, 274, 274)
                                .addComponent(lastNameLabel)))
                        .addContainerGap(285, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordReType, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(userIDLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addComponent(submitButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userID)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastName)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createOrEditAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(userIDLabel)
                .addGap(7, 7, 7)
                .addComponent(userID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel)
                .addGap(5, 5, 5)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reTypePasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordReType, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(lastNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(cardNumberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(submitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        handleSubmitAttempt();
    }//GEN-LAST:event_submitButtonActionPerformed

    private void userIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userIDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField cardNumber;
    private javax.swing.JLabel cardNumberLabel;
    private javax.swing.JLabel createOrEditAccountLabel;
    private javax.swing.JFormattedTextField firstName;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JFormattedTextField lastName;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordReType;
    private javax.swing.JLabel reTypePasswordLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JFormattedTextField userID;
    private javax.swing.JLabel userIDLabel;
    // End of variables declaration//GEN-END:variables
}
