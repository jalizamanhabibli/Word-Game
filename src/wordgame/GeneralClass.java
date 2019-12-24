package wordgame;

import javax.swing.JFrame;

public class GeneralClass {
    public static void closeFormOpenForm(JFrame form1,JFrame form2){
        form1.dispose();
        form2.setVisible(true);
    }
}
