/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WordGameForms;

import DI.SingleConnection;
import beans.Competitor;
import beans.Word;
import database.CompetitorDatabase;
import database.WordsDatabase;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.Timer;
import wordgame.GeneralClass;

/**
 *
 * @author Alizaman
 */
public final class GameForm extends javax.swing.JFrame {

    String answer;
    String answer1;
    int btnId=-1;
    private Timer timer;
    Connection conn = SingleConnection.getSingleConnect();
    WordsDatabase wordsDatabase = new WordsDatabase();
    List<Word> words = wordsDatabase.select(conn);
    List<JButton> buttons = new ArrayList<>(4);
    CompetitorDatabase competitorDatabase=new CompetitorDatabase();
    Competitor.CompetitorInfo competitorInfo=competitorDatabase.checkComInfo(LoginForm.getId(), conn);
    Competitor.CompetitorBase competitorBase=competitorDatabase.checkComBase(LoginForm.getId(), conn);

    /**
     * Creates new form GameForm
     */
    public GameForm() {
        initComponents();
        buttons.add(btnAnswer1);
        buttons.add(btnAnswer2);
        buttons.add(btnAnswer3);
        buttons.add(btnAnswer4);
        lblLang.setText(CompetitorForm.lang);
        List<Integer> list = randomWords();
        checkLang(lblLang.getText(), words.get(list.get(0)));
        List<Integer> list2 = randomButton();
        btnNameFill(list2, list, lblLang.getText());
        btnNext.setEnabled(false);
        timerStart();

    }

    public void timerStart() {
        timer = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pbrTime.setString(pbrTime.getValue() + 1 + "");
                pbrTime.setValue(pbrTime.getValue() + 1);
                checkAnswer();
            }
        });
        timer.start();

    }

    private void checkAnswer() {
        if (pbrTime.getValue() == 10) {
            if (btnId != -1) {
                if (answer.equals(answer1)) {
                    buttons.get(btnId).setBackground(Color.GREEN);
                    timer.stop();
                    btnNext.setEnabled(true);
                    competitorInfo.setPoint(competitorInfo.getPoint()+10);
                    competitorDatabase.uptade(new Competitor(competitorBase, competitorInfo), conn);
                } else {
                    buttons.get(btnId).setBackground(Color.RED);
                    timer.stop();
                    for (int i = 0; i < 4; i++) {
                    if(buttons.get(i).getText().equals(answer)){
                        buttonEnable(i);
                        buttons.get(i).setBackground(Color.GREEN);
                        btnNext.setEnabled(true);
                    }
                }
                }
            } else {
                timer.stop();
                for (int i = 0; i < 4; i++) {
                    if(buttons.get(i).getText().equals(answer)){
                        buttonEnable(i);
                        buttons.get(i).setBackground(Color.RED);
                        btnNext.setEnabled(true);
                    }
                }
            }

        }
    }

    private List randomWords() {
        Random r = new Random();
        List<Integer> questionWordsList = new ArrayList<>(4);
        while (questionWordsList.size() != 4) {
            int id = r.nextInt(words.size());
            if (!questionWordsList.contains(id)) {
                questionWordsList.add(id);
            }
        }
        return questionWordsList;
    }

    private List randomButton() {
        Random r = new Random();
        List<Integer> buttons = new ArrayList<>(4);
        while (buttons.size() != 4) {
            int id = r.nextInt(4);
            if (!buttons.contains(id)) {
                buttons.add(id);
            }
        }
        return buttons;
    }

    private void btnNameFill(List button, List word, String lang) {
        for (int i = 0; i < 4; i++) {
            if (lang.equals("EN-AZ")) {
                buttons.get((int) button.get(i)).setText(words.get((int) word.get(i)).getWordAz());
            } else {
                buttons.get((int) button.get(i)).setText(words.get((int) word.get(i)).getWordEn());
            }
        }
    }

    private void checkLang(String lang, Word word) {
        if (lang.equals("EN-AZ")) {
            lblQuestion.setText("What is '" + word.getWordEn() + "' word's translate?");
            answer = word.getWordAz();
        } else {
            lblQuestion.setText("'" + word.getWordAz() + "' sözünün tərcüməsi nədir?");
            answer = word.getWordEn();
        }
    }

    private void buttonEnable(int num) {
        buttons.get(num).setBackground(Color.GRAY);
        answer1 = buttons.get(num).getText();
        for (int i = 0; i < 4; i++) {
            buttons.get(i).setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblClose = new javax.swing.JLabel();
        lblMin = new javax.swing.JLabel();
        lblLang = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnAnswer1 = new javax.swing.JButton();
        btnAnswer2 = new javax.swing.JButton();
        btnAnswer3 = new javax.swing.JButton();
        btnAnswer4 = new javax.swing.JButton();
        lblQuestion = new javax.swing.JLabel();
        pbrTime = new javax.swing.JProgressBar();
        btnNext = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 140, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(185, 51));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        lblClose.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblClose.setForeground(new java.awt.Color(255, 255, 255));
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setText("x");
        lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblClose.setPreferredSize(new java.awt.Dimension(18, 51));
        lblClose.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblCloseMouseMoved(evt);
            }
        });
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseMouseExited(evt);
            }
        });

        lblMin.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblMin.setForeground(new java.awt.Color(255, 255, 255));
        lblMin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMin.setText("_");
        lblMin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblMin.setPreferredSize(new java.awt.Dimension(13, 51));
        lblMin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblMinMouseMoved(evt);
            }
        });
        lblMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinMouseExited(evt);
            }
        });

        lblLang.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        lblLang.setForeground(new java.awt.Color(255, 255, 255));
        lblLang.setText("Az-En");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLang)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(47, 79, 79));

        btnAnswer1.setBackground(new java.awt.Color(80, 127, 127));
        btnAnswer1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnswer1.setForeground(new java.awt.Color(255, 255, 255));
        btnAnswer1.setText("Alma");
        btnAnswer1.setFocusPainted(false);
        btnAnswer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswer1ActionPerformed(evt);
            }
        });

        btnAnswer2.setBackground(new java.awt.Color(80, 127, 127));
        btnAnswer2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnswer2.setForeground(new java.awt.Color(255, 255, 255));
        btnAnswer2.setText("Gülüş");
        btnAnswer2.setFocusPainted(false);
        btnAnswer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswer2ActionPerformed(evt);
            }
        });

        btnAnswer3.setBackground(new java.awt.Color(80, 127, 127));
        btnAnswer3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnswer3.setForeground(new java.awt.Color(255, 255, 255));
        btnAnswer3.setText("Söz");
        btnAnswer3.setFocusPainted(false);
        btnAnswer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswer3ActionPerformed(evt);
            }
        });

        btnAnswer4.setBackground(new java.awt.Color(80, 127, 127));
        btnAnswer4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnswer4.setForeground(new java.awt.Color(255, 255, 255));
        btnAnswer4.setText("Armud");
        btnAnswer4.setFocusPainted(false);
        btnAnswer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswer4ActionPerformed(evt);
            }
        });

        lblQuestion.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblQuestion.setForeground(new java.awt.Color(255, 255, 255));
        lblQuestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuestion.setText("What is 'Apple' word's translate?");

        pbrTime.setBackground(new java.awt.Color(255, 255, 255));
        pbrTime.setForeground(new java.awt.Color(168, 50, 9));
        pbrTime.setMaximum(10);
        pbrTime.setBorderPainted(false);
        pbrTime.setString("0");
        pbrTime.setStringPainted(true);

        btnNext.setText("Next >");
        btnNext.setFocusPainted(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFinish.setText("Finish");
        btnFinish.setFocusPainted(false);
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnFinish)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pbrTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAnswer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAnswer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAnswer4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAnswer4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnswer2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(btnAnswer3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(pbrTime, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnFinish))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblCloseMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseMoved
        lblClose.setBackground(Color.RED);
        lblClose.setOpaque(true);
    }//GEN-LAST:event_lblCloseMouseMoved

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        lblClose.setBackground(new Color(255, 140, 0));
        lblClose.setOpaque(true);
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblMinMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinMouseMoved
        lblMin.setBackground(new Color(255, 100, 0));
        lblMin.setOpaque(true);
    }//GEN-LAST:event_lblMinMouseMoved

    private void lblMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinMouseClicked
        this.setState(LoginForm.ICONIFIED);
    }//GEN-LAST:event_lblMinMouseClicked

    private void lblMinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinMouseExited
        lblMin.setBackground(new Color(255, 140, 0));
        lblMin.setOpaque(true);
    }//GEN-LAST:event_lblMinMouseExited
    int x, y, x1, y1;
    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        x1 = evt.getXOnScreen();
        y1 = evt.getYOnScreen();
        this.setLocation(this.getX() + x1 - x, this.getY() + y1 - y);
        x = x1;
        y = y1;
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        x = evt.getXOnScreen();
        y = evt.getYOnScreen();
    }//GEN-LAST:event_jPanel1MousePressed

    private void btnAnswer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswer1ActionPerformed
        buttonEnable(0);
        btnId = 0;
    }//GEN-LAST:event_btnAnswer1ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        GeneralClass.closeFormOpenForm(this, new GameForm());
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        GeneralClass.closeFormOpenForm(this, new CompetitorForm());
    }//GEN-LAST:event_btnFinishActionPerformed

    private void btnAnswer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswer2ActionPerformed
        buttonEnable(1);
        btnId = 1;
    }//GEN-LAST:event_btnAnswer2ActionPerformed

    private void btnAnswer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswer3ActionPerformed
        buttonEnable(2);
        btnId = 2;
    }//GEN-LAST:event_btnAnswer3ActionPerformed

    private void btnAnswer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswer4ActionPerformed
        buttonEnable(3);
        btnId = 3;
    }//GEN-LAST:event_btnAnswer4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnswer1;
    private javax.swing.JButton btnAnswer2;
    private javax.swing.JButton btnAnswer3;
    private javax.swing.JButton btnAnswer4;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnNext;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblLang;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JProgressBar pbrTime;
    // End of variables declaration//GEN-END:variables
}
