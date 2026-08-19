package client;

import domm.GameState;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slovo1 = new javax.swing.JTextField();
        slovo2 = new javax.swing.JTextField();
        slovo3 = new javax.swing.JTextField();
        slovo4 = new javax.swing.JTextField();
        slovo5 = new javax.swing.JTextField();
        pokusajSlovo = new javax.swing.JTextField();
        lblPreosPokusaji = new javax.swing.JLabel();
        lblKoriscenaSlova = new javax.swing.JLabel();
        pogodi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPreosPokusaji.setText("Preostali broj pokusaja: ");

        lblKoriscenaSlova.setText("Koriscena slova:");

        pogodi.setText("Pogodi");
        pogodi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pogodiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pokusajSlovo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pogodi))
                    .addComponent(lblPreosPokusaji, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(slovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(slovo2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(slovo3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(slovo4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(slovo5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblKoriscenaSlova, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slovo2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slovo3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slovo4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slovo5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(lblPreosPokusaji)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKoriscenaSlova)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pokusajSlovo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pogodi))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pogodiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pogodiActionPerformed
        String slovo = pokusajSlovo.getText();
        try {
            CliController.getInstance(this).sendSlovo(slovo);
            pokusajSlovo.setText("");
        } catch (Exception e) {
            System.out.println("Err in btnSend: " +e.getMessage());
        }
    }//GEN-LAST:event_pogodiActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblKoriscenaSlova;
    private javax.swing.JLabel lblPreosPokusaji;
    private javax.swing.JButton pogodi;
    private javax.swing.JTextField pokusajSlovo;
    private javax.swing.JTextField slovo1;
    private javax.swing.JTextField slovo2;
    private javax.swing.JTextField slovo3;
    private javax.swing.JTextField slovo4;
    private javax.swing.JTextField slovo5;
    // End of variables declaration//GEN-END:variables

    void updateGUI(GameState game) {
        JTextField[] slova = {slovo1, slovo2, slovo3, slovo4, slovo5};
        
        for (int i = 0; i < slova.length; i++) {
            slova[i].setText(
                    String.valueOf(game.getRevealed().charAt(i))
            );
        }
        
        lblKoriscenaSlova.setText("Koriscena slova: " + String.join(", ", game.getKoriscenaSlova()));
        lblPreosPokusaji.setText("Preostali pokusaji: " + game.getPreostaliPokusaji());
    }

    void showGameOver(String string) {
        JOptionPane.showMessageDialog(this, string);
        System.exit(0); 
    }
}
