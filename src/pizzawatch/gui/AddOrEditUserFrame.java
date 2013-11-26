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
    private final int USER_ID_VAR = 1, FIRST_NAME_VAR = 2, LAST_NAME_VAR = 3, PASSWORD_VAR = 4, ADDRESS_VAR = 5;

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
            tfUserID.setEnabled(false);
            tfAddress.setEnabled(false);
            createOrEditAccountLabel.setText("EDIT ACCOUNT DETAILS");
            loadPrevData();
        }
    }

    private AbstractFormatterFactory getCreditCardMaskFormatterFactory()
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
        tfUserID.setText(currentUser.getUserID());
        tfFirstName.setText(currentUser.getFirstName());
        tfLastName.setText(currentUser.getLastName());
        //The plaintext password isn't stored in the DB
        //But should be ok, just check hash of password field vs prev hash
        cardNumber.setText(currentUser.getCreditCardNumber());
    }

    /**
     * Checks if the string is strictly alphanumeric
     */
    private boolean isAlphaNumeric(String str) {
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if ( !Character.isLetterOrDigit(c) )
                return false;
        }

        return true;
    }

    /**
     * Checks if the string is alphanumeric, allowing for whitespace
     */
    private boolean isAlphaNumericAllowWhitespace(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(Character.isLetterOrDigit(c) == false && Character.isWhitespace(c) == false) {
                return false;
            }
        }

        return true;
    }

    //Checks if the string meets the maximum length for charaters
    //Returns true if the entered string meets max length
    private boolean verifyMaxLength (int type,String string){
        switch(type){
            case USER_ID_VAR: {
                return string.length() <= 30;
            }
            case FIRST_NAME_VAR:
            case LAST_NAME_VAR: {
                return string.length() <= 40;
            }
            case PASSWORD_VAR:
            case ADDRESS_VAR: {
                return string.length() <= 100;
            }
            default: {
                return false;
            }
        }
    }

    /**
     * Verifies if input meets a minimum length
     * @param type The type of input this is, defined by the constants in this file
     * @param input The input to verify the length of
     * @return Whether the input meets the minimum length depending on its type
     */
    private boolean verifyMinLength(int type, String input) {
        switch(type) {
            case USER_ID_VAR:
            case PASSWORD_VAR: {
                return input.length() >= 6;
            }
            case FIRST_NAME_VAR:
            case LAST_NAME_VAR: {
                return input.length() >= 1;
            }
            case ADDRESS_VAR: {
                return input.length() >= 15;
            }
            default: {
                return false;
            }
        }
    }

    private void handleSubmitAttempt()
    {
        String newUserID = tfUserID.getText();
        String newPassword = new String (password.getPassword());
        String newPasswordReType = new String (passwordReType.getPassword());
        String newFirstName = tfFirstName.getText();
        String newLastName = tfLastName.getText();
        String newCardNumber = cardNumber.getText();
        String newAddress = tfAddress.getText();

        //Checks if the two passwords match
        if (!newPassword.equals(newPasswordReType)){
            JOptionPane.showMessageDialog (this, "Passwords do not match. Please try again.");
            return;
        }

        //Checks if the user ID entered meets restrictions
        if(isEditMode == false &&
           (verifyMaxLength(USER_ID_VAR, newUserID) == false ||
            verifyMinLength(USER_ID_VAR, newUserID) == false ||
            isAlphaNumeric(newUserID) == false)) {
            JOptionPane.showMessageDialog(this, "User ID entered is invalid. Please enter a minimum of 6 and a maximum of 30 alphanumeric characters. No spaces.");
            return;
        }

        //Checks if the password entered meets restrictions
        if(verifyMaxLength(PASSWORD_VAR, newPassword) == false ||
           verifyMinLength(PASSWORD_VAR, newPassword) == false) {
           JOptionPane.showMessageDialog (this, "Password entered is invalid. Please enter a minimum of 6 and a maximum of 100 characters.");
            return;
        }

        //Checks if the the first name entered meets restrictions
        if(verifyMaxLength(FIRST_NAME_VAR, newFirstName) == false ||
           verifyMinLength(FIRST_NAME_VAR, newFirstName) == false ||
           isAlphaNumericAllowWhitespace(newFirstName) == false) {
            JOptionPane.showMessageDialog (this, "First name entered is invalid. Please enter a minimum of 1 and a maximum of 40 alphanumeric characters.");
            return;
        }

        //Checks if the last name entered meets restrictions
        if(verifyMaxLength(LAST_NAME_VAR, newLastName) == false ||
           verifyMinLength(LAST_NAME_VAR, newLastName) == false ||
           isAlphaNumericAllowWhitespace(newLastName) == false) {
            JOptionPane.showMessageDialog (this, "Last name entered is invalid. Please enter a minimum of 1 and a maximum of 40 alphanumeric characters.");
            return;
        }

        //Check the credit card number
        if(newCardNumber.equals("0000000000000000")) {
            JOptionPane.showMessageDialog(this, "The given credit card number is invalid.");
            return;
        }

        //Checks if the address entered meets restrictions
        if(isEditMode == false &&
           (verifyMaxLength(ADDRESS_VAR, newAddress) == false ||
            verifyMinLength(ADDRESS_VAR, newAddress) == false ||
            isAlphaNumericAllowWhitespace(newAddress) == false)) {
            JOptionPane.showMessageDialog(this, "Address entered is invalid. Please enter a minimum of 15 and a maximum of 100 alphanumeric characters.");
            return;
        }

        if(isEditMode) {
            String newPasswordHash = UserUtils.hashPassword(newPassword);
            if(newPasswordHash != null) {
                UserUtils.updateUserAttribute(currentUser.getUserID(), "passwordHash", newPasswordHash);
            } else {
                JOptionPane.showMessageDialog(this, "A failure occured when trying to update your account details.");
                return;
            }
            if(currentUser.getFirstName().equals(newFirstName) == false) {
                UserUtils.updateUserAttribute(currentUser.getUserID(), "firstName", newFirstName);
                currentUser.setFirstName(newFirstName);
            }
            if(currentUser.getLastName().equals(newLastName) == false) {
                UserUtils.updateUserAttribute(currentUser.getUserID(), "lastName", newLastName);
                currentUser.setLastName(newLastName);
            }
            if(currentUser.getCreditCardNumber().equals(newCardNumber) == false) {
                UserUtils.updateUserAttribute(currentUser.getUserID(), "cardNumber", newCardNumber);
                currentUser.setCreditCardNumber(newCardNumber);
            }
            JOptionPane.showMessageDialog(this, "Successfully updated your account details.");
            setVisible(false);
            dispose();
        }
        else {
            User user = new User(newUserID, false, newFirstName, newLastName, newCardNumber);
            if(UserUtils.addUser(user, newPassword, newAddress)) {
                JOptionPane.showMessageDialog(this, "Successfully made a new user account.");
                setVisible(false);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "A failure occured when trying to make a new user account.");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

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
        tfAddress = new javax.swing.JTextField();
        tfLastName = new javax.swing.JTextField();
        tfFirstName = new javax.swing.JTextField();
        lbAddress = new javax.swing.JLabel();
        tfUserID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        firstNameLabel.setText("First Name");

        lastNameLabel.setText("Last Name");

        lbTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitle.setText("TMNT Pizza Watch");

        cardNumberLabel.setText("Card Number");

        userIDLabel.setText("User ID");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                submitButtonActionPerformed(evt);
            }
        });

        passwordLabel.setText("Password");

        reTypePasswordLabel.setText("Re-type Password");

        cardNumber.setFormatterFactory(getCreditCardMaskFormatterFactory());

        createOrEditAccountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createOrEditAccountLabel.setText("CREATE A NEW ACCOUNT");

        lbAddress.setText("Address");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbAddress))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(295, 295, 295))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardNumber)
                            .addComponent(passwordReType)
                            .addComponent(password)
                            .addComponent(tfAddress)
                            .addComponent(tfUserID)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbTitle)
                                    .addComponent(reTypePasswordLabel)
                                    .addComponent(passwordLabel)
                                    .addComponent(cardNumberLabel)
                                    .addComponent(createOrEditAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstNameLabel)
                                    .addComponent(userIDLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lastNameLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(tfLastName))))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(passwordLabel)
                .addGap(5, 5, 5)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reTypePasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordReType, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(lastNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(tfFirstName))
                .addGap(13, 13, 13)
                .addComponent(cardNumberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbAddress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        handleSubmitAttempt();
    }//GEN-LAST:event_submitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField cardNumber;
    private javax.swing.JLabel cardNumberLabel;
    private javax.swing.JLabel createOrEditAccountLabel;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordReType;
    private javax.swing.JLabel reTypePasswordLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    private javax.swing.JTextField tfUserID;
    private javax.swing.JLabel userIDLabel;
    // End of variables declaration//GEN-END:variables
}
