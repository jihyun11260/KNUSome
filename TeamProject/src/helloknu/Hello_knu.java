package helloknu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Hello_knu extends JFrame {
	   
   public enum Day { 월요일, 화요일, 수요일, 목요일, 금요일};
	
   private Image screenImage;
   private Graphics screenGraphic;

   private Image background = new ImageIcon(Main.class.getResource("../images/first_page.png")).getImage();
   
   private JLabel MenuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));
   
   
   private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/end.png"));
   private ImageIcon exitButtonImage = new ImageIcon(Main.class.getResource("../images/exit.png"));
   private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/back2.png"));
   private ImageIcon backButtonImage = new ImageIcon(Main.class.getResource("../images/back.png"));
   private ImageIcon it5ButtonImage = new ImageIcon(Main.class.getResource("../images/it5호관.png"));
   private ImageIcon it4ButtonImage = new ImageIcon(Main.class.getResource("../images/it4호관.png"));
   private ImageIcon room101ButtonImage = new ImageIcon(Main.class.getResource("../images/101.png"));
   private ImageIcon room102ButtonImage = new ImageIcon(Main.class.getResource("../images/102.png"));
   private ImageIcon room103ButtonImage = new ImageIcon(Main.class.getResource("../images/103.png"));
   private ImageIcon room201ButtonImage = new ImageIcon(Main.class.getResource("../images/201.png"));
   private ImageIcon room202ButtonImage = new ImageIcon(Main.class.getResource("../images/202.png"));
   private ImageIcon room203ButtonImage = new ImageIcon(Main.class.getResource("../images/203.png"));
   private ImageIcon room301ButtonImage = new ImageIcon(Main.class.getResource("../images/301.png"));
   private ImageIcon room302ButtonImage = new ImageIcon(Main.class.getResource("../images/302.png"));
   private ImageIcon room303ButtonImage = new ImageIcon(Main.class.getResource("../images/303.png"));
   private ImageIcon timemenuButtonImage = new ImageIcon(Main.class.getResource("../images/시간표조회.png"));
   private ImageIcon reservemenuButtonImage = new ImageIcon(Main.class.getResource("../images/예약.png"));
   private ImageIcon deletemenuButtonImage = new ImageIcon(Main.class.getResource("../images/예약 취소.png"));
   private ImageIcon homebuttonImage = new ImageIcon(Main.class.getResource("../images/홈.png"));
   private ImageIcon noreserveImage = new ImageIcon(Main.class.getResource("../images/예약불가능.png"));
   private ImageIcon classImage = new ImageIcon(Main.class.getResource("../images/수업.png"));
   
   
   private JButton exitButton = new JButton(exitButtonImage);
   private JButton startButton = new JButton(new ImageIcon(Main.class.getResource("../images/start.png")));
   private JButton first_backButton = new JButton(backButtonImage);
   private JButton it5Button = new JButton(it5ButtonImage);
   private JButton it4Button = new JButton(it4ButtonImage);
   private JButton selectroom_backButton = new JButton(backButtonImage);
   private JButton reserve_backButton = new JButton(backButtonImage);
   private JButton cancelok_backButton = new JButton(backButtonImage);
   private JButton room101Button = new JButton(room101ButtonImage);
   private JButton room102Button = new JButton(room102ButtonImage);
   private JButton room103Button = new JButton(room103ButtonImage);
   private JButton room201Button = new JButton(room201ButtonImage);
   private JButton room202Button = new JButton(room202ButtonImage);
   private JButton room203Button = new JButton(room203ButtonImage);
   private JButton room301Button = new JButton(room301ButtonImage);
   private JButton room302Button = new JButton(room302ButtonImage);
   private JButton room303Button = new JButton(room303ButtonImage);
   private JButton menu_backButton = new JButton(backButtonImage);
   private JButton timemenuButton = new JButton(timemenuButtonImage);
   private JButton reservemenuButton = new JButton(reservemenuButtonImage);
   private JButton deletemenuButton = new JButton(deletemenuButtonImage);
   private JButton timetable_backButton = new JButton(backButtonImage);
   private JButton returnButton = new JButton(homebuttonImage);
   private JButton reserveOK = new JButton("확인");
   private JButton reservefinalOK = new JButton("확인");
   private JButton cancelOK = new JButton("확인");
   private JButton cancelfinalYES = new JButton("YES");
   private JButton cancelfinalNO = new JButton("NO");
   private JButton noreserveButton11 = new JButton(noreserveImage);
   private JButton noreserveButton12 = new JButton(noreserveImage);
   private JButton noreserveButton13 = new JButton(noreserveImage);
   private JButton noreserveButton14 = new JButton(noreserveImage);
   private JButton noreserveButton15 = new JButton(noreserveImage);
   private JButton noreserveButton21 = new JButton(noreserveImage);
   private JButton noreserveButton22 = new JButton(noreserveImage);
   private JButton noreserveButton23 = new JButton(noreserveImage);
   private JButton noreserveButton24 = new JButton(noreserveImage);
   private JButton noreserveButton25 = new JButton(noreserveImage);
   private JButton noreserveButton31 = new JButton(noreserveImage);
   private JButton noreserveButton32 = new JButton(noreserveImage);
   private JButton noreserveButton33 = new JButton(noreserveImage);
   private JButton noreserveButton34 = new JButton(noreserveImage);
   private JButton noreserveButton35 = new JButton(noreserveImage);
   private JButton noreserveButton41 = new JButton(noreserveImage);
   private JButton noreserveButton42 = new JButton(noreserveImage);
   private JButton noreserveButton43 = new JButton(noreserveImage);
   private JButton noreserveButton44 = new JButton(noreserveImage);
   private JButton noreserveButton45 = new JButton(noreserveImage);
   private JButton noreserveButton51 = new JButton(noreserveImage);
   private JButton noreserveButton52 = new JButton(noreserveImage);
   private JButton noreserveButton53 = new JButton(noreserveImage);
   private JButton noreserveButton54 = new JButton(noreserveImage);
   private JButton noreserveButton55 = new JButton(noreserveImage);
   private JButton noreserveButton61 = new JButton(noreserveImage);
   private JButton noreserveButton62 = new JButton(noreserveImage);
   private JButton noreserveButton63 = new JButton(noreserveImage);
   private JButton noreserveButton64 = new JButton(noreserveImage);
   private JButton noreserveButton65 = new JButton(noreserveImage);
   private JButton noreserveButton71 = new JButton(noreserveImage);
   private JButton noreserveButton72 = new JButton(noreserveImage);
   private JButton noreserveButton73 = new JButton(noreserveImage);
   private JButton noreserveButton74 = new JButton(noreserveImage);
   private JButton noreserveButton75 = new JButton(noreserveImage);
   private JButton noreserveButton81 = new JButton(noreserveImage);
   private JButton noreserveButton82 = new JButton(noreserveImage);
   private JButton noreserveButton83 = new JButton(noreserveImage);
   private JButton noreserveButton84 = new JButton(noreserveImage);
   private JButton noreserveButton85 = new JButton(noreserveImage);
   private JButton noreserveButton91 = new JButton(noreserveImage);
   private JButton noreserveButton92 = new JButton(noreserveImage);
   private JButton noreserveButton93 = new JButton(noreserveImage);
   private JButton noreserveButton94 = new JButton(noreserveImage);
   private JButton noreserveButton95 = new JButton(noreserveImage);
   
   private JButton classButton11 = new JButton(classImage);
   private JButton classButton12 = new JButton(classImage);
   private JButton classButton13 = new JButton(classImage);
   private JButton classButton14 = new JButton(classImage);
   private JButton classButton15 = new JButton(classImage);
   private JButton classButton21 = new JButton(classImage);
   private JButton classButton22 = new JButton(classImage);
   private JButton classButton23 = new JButton(classImage);
   private JButton classButton24 = new JButton(classImage);
   private JButton classButton25 = new JButton(classImage);
   private JButton classButton31 = new JButton(classImage);
   private JButton classButton32 = new JButton(classImage);
   private JButton classButton33 = new JButton(classImage);
   private JButton classButton34 = new JButton(classImage);
   private JButton classButton35 = new JButton(classImage);
   private JButton classButton41 = new JButton(classImage);
   private JButton classButton42 = new JButton(classImage);
   private JButton classButton43 = new JButton(classImage);
   private JButton classButton44 = new JButton(classImage);
   private JButton classButton45 = new JButton(classImage);
   private JButton classButton51 = new JButton(classImage);
   private JButton classButton52 = new JButton(classImage);
   private JButton classButton53 = new JButton(classImage);
   private JButton classButton54 = new JButton(classImage);
   private JButton classButton55 = new JButton(classImage);
   private JButton classButton61 = new JButton(classImage);
   private JButton classButton62 = new JButton(classImage);
   private JButton classButton63 = new JButton(classImage);
   private JButton classButton64 = new JButton(classImage);
   private JButton classButton65 = new JButton(classImage);
   private JButton classButton71 = new JButton(classImage);
   private JButton classButton72 = new JButton(classImage);
   private JButton classButton73 = new JButton(classImage);
   private JButton classButton74 = new JButton(classImage);
   private JButton classButton75 = new JButton(classImage);
   private JButton classButton81 = new JButton(classImage);
   private JButton classButton82 = new JButton(classImage);
   private JButton classButton83 = new JButton(classImage);
   private JButton classButton84 = new JButton(classImage);
   private JButton classButton85 = new JButton(classImage);
   private JButton classButton91 = new JButton(classImage);
   private JButton classButton92 = new JButton(classImage);
   private JButton classButton93 = new JButton(classImage);
   private JButton classButton94 = new JButton(classImage);
   private JButton classButton95 = new JButton(classImage);
   
   private JPanel buildingPanel = new JPanel();
   private JPanel roomPanel = new JPanel();
   private JPanel reservename = new JPanel();
   private JPanel reservestudentid = new JPanel();
   private JPanel reservepasswd = new JPanel();
   private JPanel reservepasswdcheck = new JPanel();
   private JPanel reserveday = new JPanel();
   private JPanel reservetime = new JPanel();
   private JPanel cancelname = new JPanel();
   private JPanel cancelid = new JPanel();
   private JPanel cancelpwd = new JPanel();
   private JPanel cancelcheckday = new JPanel();
   private JPanel cancelchecktime = new JPanel();
   private JPanel nowinfo = new JPanel();
   
   private JTextField reserveday_textField;
   private JTextField reservetime_textField;
   private JTextField reservename_textField;
   private JTextField reservestudentid_textField;
   private JTextField reservepasswd_textField;
   private JTextField reservepasswdcheck_textField;
   private JTextField cancelname_textField;
   private JTextField cancelid_textField;
   private JTextField cancelpwd_textField;
   private JTextField cancelcheckday_textField;
   private JTextField cancelchecktime_textField;
   private JTextArea nowinfo_textArea;
   
  
   ArrayList<String> list = new ArrayList<String>();
   private String building, classroom;
   private int mouseX, mouseY;
   
   public Hello_knu() {
	  
	  buildingPanel.setLayout(null);
	  buildingPanel.setBounds(0, 200, Main.WIDTH, Main.HEIGHT - 200);
	  buildingPanel.setBackground(Color.white);
      buildingPanel.setVisible(false);
      
      roomPanel.setLayout(null);
      roomPanel.setBounds(300, 170, Main.WIDTH - 200, Main.HEIGHT - 200);
      roomPanel.setBackground(Color.white);
      roomPanel.setVisible(false);
      
      //메뉴바 없애기
      setUndecorated(true);
      setTitle("Hello KNU:)");
      setSize(Main.WIDTH, Main.HEIGHT);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setBackground(new Color(0,0,0,0));
      setLayout(null);
      
      nowinfo_textArea = new JTextArea(3, 20);
      nowinfo_textArea.setBackground(Color.WHITE);
      nowinfo_textArea.setFont(new Font("Arial", Font.BOLD, 25));
      nowinfo.add(nowinfo_textArea);
      nowinfo.setBackground(Color.WHITE);
      nowinfo.setBounds(1000, 30, 500, 92);
      add(nowinfo);
      nowinfo.setVisible(false);
      
      //text창입니다.      
      reserveday_textField = new JTextField(30);
      reserveday_textField.setBackground(Color.WHITE);
      reserveday.add(reserveday_textField);
      reserveday.setBackground(Color.WHITE);
      reserveday.setBounds(400, 278, 335, 26);
      add(reserveday);
      reserveday.setVisible(false);
      
      reservetime_textField = new JTextField(30);
      reservetime_textField.setBackground(Color.WHITE);
      reservetime.add(reservetime_textField);
      reservetime.setBackground(Color.WHITE);
      reservetime.setBounds(400, 415, 335, 26);
      add(reservetime);
      reservetime.setVisible(false);
      
      reservename_textField = new JTextField(30);
      reservename_textField.setBackground(Color.WHITE);
      reservename.add(reservename_textField);
      reservename.setBackground(Color.WHITE);
      reservename.setBounds(510, 225, 335, 26);
      add(reservename);
      reservename.setVisible(false);
      
      reservestudentid_textField = new JTextField(30);
      reservestudentid_textField.setBackground(Color.WHITE);
      reservestudentid.add(reservestudentid_textField);
      reservestudentid.setBackground(Color.WHITE);
      reservestudentid.setBounds(510, 295, 335, 26);
      add(reservestudentid);
      reservestudentid.setVisible(false);
      
      reservepasswd_textField = new JTextField(30);
      reservepasswd_textField.setBackground(Color.WHITE);
      reservepasswd.add(reservepasswd_textField);
      reservepasswd.setBackground(Color.WHITE);
      reservepasswd.setBounds(510, 365, 335, 26);
      add(reservepasswd);
      reservepasswd.setVisible(false);
      
      reservepasswdcheck_textField = new JTextField(30);
      reservepasswdcheck_textField.setBackground(Color.WHITE);
      reservepasswdcheck.add(reservepasswdcheck_textField);
      reservepasswdcheck.setBackground(Color.WHITE);
      reservepasswdcheck.setBounds(510, 435, 335, 26);
      add(reservepasswdcheck);
      reservepasswdcheck.setVisible(false);

      cancelname_textField = new JTextField(30);
      cancelname_textField.setBackground(Color.WHITE);
      cancelname.add(cancelname_textField);
      cancelname.setBackground(Color.WHITE);
      cancelname.setBounds(510, 295, 335, 26);
      add(cancelname);
      cancelname.setVisible(false);
      
      cancelid_textField = new JTextField(30);
      cancelid_textField.setBackground(Color.WHITE);
      cancelid.add(cancelid_textField);
      cancelid.setBackground(Color.WHITE);
      cancelid.setBounds(510, 365, 335, 26);
      add(cancelid);
      cancelid.setVisible(false);
      
      cancelpwd_textField = new JTextField(30);
      cancelpwd_textField.setBackground(Color.WHITE);
      cancelpwd.add(cancelpwd_textField);
      cancelpwd.setBackground(Color.WHITE);
      cancelpwd.setBounds(510, 435, 335, 26);
      add(cancelpwd);
      cancelpwd.setVisible(false);
      
      cancelcheckday_textField = new JTextField(30);
      cancelcheckday_textField.setBackground(Color.WHITE);
      cancelcheckday.add(cancelcheckday_textField);
      cancelcheckday.setBackground(Color.WHITE);
      cancelcheckday.setBounds(400, 278, 335, 26);
      add(cancelcheckday);
      cancelcheckday.setVisible(false);
      
      cancelchecktime_textField = new JTextField(30);
      cancelchecktime_textField.setBackground(Color.WHITE);
      cancelchecktime.add(cancelchecktime_textField);
      cancelchecktime.setBackground(Color.WHITE);
      cancelchecktime.setBounds(400, 415, 335, 26);
      add(cancelchecktime);
      cancelchecktime.setVisible(false);
      
      //초록수업창 입니다.
      classButton11.setBounds(241,186, 198, 57);
      classButton11.setVisible(false);
      classButton11.setBorderPainted(false);
      add(classButton11);
      
      classButton12.setBounds(441,186, 198, 57);
      classButton12.setVisible(false);
      classButton12.setBorderPainted(false);
      add(classButton12);
      
      classButton13.setBounds(641,186, 198, 57);
      classButton13.setVisible(false);
      classButton13.setBorderPainted(false);
      add(classButton13);
      
      classButton14.setBounds(841,186, 198, 57);
      classButton14.setVisible(false);
      classButton14.setBorderPainted(false);
      add(classButton14);
      
      classButton15.setBounds(1041,186, 198, 57);
      classButton15.setVisible(false);
      classButton15.setBorderPainted(false);
      add(classButton15);
      
      classButton21.setBounds(241,246, 198, 57);
      classButton21.setVisible(false);
      classButton21.setBorderPainted(false);
      add(classButton21);
      
      classButton22.setBounds(441,246, 198, 57);
      classButton22.setVisible(false);
      classButton22.setBorderPainted(false);
      add(classButton22);
      
      classButton23.setBounds(641,246, 198, 57);
      classButton23.setVisible(false);
      classButton23.setBorderPainted(false);
      add(classButton23);
      
      classButton24.setBounds(841,246, 198, 57);
      classButton24.setVisible(false);
      classButton24.setBorderPainted(false);
      add(classButton24);
      
      classButton25.setBounds(1041,246, 198, 57);
      classButton25.setVisible(false);
      classButton25.setBorderPainted(false);
      add(classButton25);
      
      classButton31.setBounds(241,303, 198, 57);
      classButton31.setVisible(false);
      classButton31.setBorderPainted(false);
      add(classButton31);
      
      classButton32.setBounds(441,303, 198, 57);
      classButton32.setVisible(false);
      classButton32.setBorderPainted(false);
      add(classButton32);
      
      classButton33.setBounds(641,303, 198, 57);
      classButton33.setVisible(false);
      classButton33.setBorderPainted(false);
      add(classButton33);
      
      classButton34.setBounds(841,303, 198, 57);
      classButton34.setVisible(false);
      classButton34.setBorderPainted(false);
      add(classButton34);
      
      classButton35.setBounds(1041,303, 198, 57);
      classButton35.setVisible(false);
      classButton35.setBorderPainted(false);
      add(classButton35);
      
      classButton41.setBounds(241,361, 198, 57);
      classButton41.setVisible(false);
      classButton41.setBorderPainted(false);
      add(classButton41);
      
      classButton42.setBounds(441,361, 198, 57);
      classButton42.setVisible(false);
      classButton42.setBorderPainted(false);
      add(classButton42);
      
      classButton43.setBounds(641,361, 198, 57);
      classButton43.setVisible(false);
      classButton43.setBorderPainted(false);
      add(classButton43);
      
      classButton44.setBounds(841,361, 198, 57);
      classButton44.setVisible(false);
      classButton44.setBorderPainted(false);
      add(classButton44);
      
      classButton45.setBounds(1041,361, 198, 57);
      classButton45.setVisible(false);
      classButton45.setBorderPainted(false);
      add(classButton45);
      
      classButton51.setBounds(241,418, 198, 57);
      classButton51.setVisible(false);
      classButton51.setBorderPainted(false);
      add(classButton51);
      
      classButton52.setBounds(441,418, 198, 57);
      classButton52.setVisible(false);
      classButton52.setBorderPainted(false);
      add(classButton52);
      
      classButton53.setBounds(641,418, 198, 57);
      classButton53.setVisible(false);
      classButton53.setBorderPainted(false);
      add(classButton53);
      
      classButton54.setBounds(841,418, 198, 57);
      classButton54.setVisible(false);
      classButton54.setBorderPainted(false);
      add(classButton54);
      
      classButton55.setBounds(1041,418, 198, 57);
      classButton55.setVisible(false);
      classButton55.setBorderPainted(false);
      add(classButton55);
      
      classButton61.setBounds(241,475, 198, 57);
      classButton61.setVisible(false);
      classButton61.setBorderPainted(false);
      add(classButton61);
      
      classButton62.setBounds(441,475, 198, 57);
      classButton62.setVisible(false);
      classButton62.setBorderPainted(false);
      add(classButton62);
      
      classButton63.setBounds(641,475, 198, 57);
      classButton63.setVisible(false);
      classButton63.setBorderPainted(false);
      add(classButton63);
      
      classButton64.setBounds(841,475, 198, 57);
      classButton64.setVisible(false);
      classButton64.setBorderPainted(false);
      add(classButton64);
      
      classButton65.setBounds(1041,475, 198, 57);
      classButton65.setVisible(false);
      classButton65.setBorderPainted(false);
      add(classButton65);
      
      classButton71.setBounds(241,532, 198, 57);
      classButton71.setVisible(false);
      classButton71.setBorderPainted(false);
      add(classButton71);
      
      classButton72.setBounds(441,532, 198, 57);
      classButton72.setVisible(false);
      classButton72.setBorderPainted(false);
      add(classButton72);
      
      classButton73.setBounds(641,532, 198, 57);
      classButton73.setVisible(false);
      classButton73.setBorderPainted(false);
      add(classButton73);
      
      classButton74.setBounds(841,532, 198, 57);
      classButton74.setVisible(false);
      classButton74.setBorderPainted(false);
      add(classButton74);
      
      classButton75.setBounds(1041,532, 198, 57);
      classButton75.setVisible(false);
      classButton75.setBorderPainted(false);
      add(classButton75);
      
      classButton81.setBounds(241,589, 198, 57);
      classButton81.setVisible(false);
      classButton81.setBorderPainted(false);
      add(classButton81);
      
      classButton82.setBounds(441,589, 198, 57);
      classButton82.setVisible(false);
      classButton82.setBorderPainted(false);
      add(classButton82);
      
      classButton83.setBounds(641,589, 198, 57);
      classButton83.setVisible(false);
      classButton83.setBorderPainted(false);
      add(classButton83);
      
      classButton84.setBounds(841,589, 198, 57);
      classButton84.setVisible(false);
      classButton84.setBorderPainted(false);
      add(classButton84);
      
      classButton85.setBounds(1041,589, 198, 57);
      classButton85.setVisible(false);
      classButton85.setBorderPainted(false);
      add(classButton85);
      
      classButton91.setBounds(241,647, 198, 57);
      classButton91.setVisible(false);
      classButton91.setBorderPainted(false);
      add(classButton91);
      
      classButton92.setBounds(441,647, 198, 57);
      classButton92.setVisible(false);
      classButton92.setBorderPainted(false);
      add(classButton92);
      
      classButton93.setBounds(641,647, 198, 57);
      classButton93.setVisible(false);
      classButton93.setBorderPainted(false);
      add(classButton93);
      
      classButton94.setBounds(841,647, 198, 57);
      classButton94.setVisible(false);
      classButton94.setBorderPainted(false);
      add(classButton94);
      
      classButton95.setBounds(1041,647, 198, 57);
      classButton95.setVisible(false);
      classButton95.setBorderPainted(false);
      add(classButton95);
      
      
    //빨간 예약불가능 창 입니다.
      noreserveButton11.setBounds(241,186, 198, 57);
      noreserveButton11.setVisible(false);
      noreserveButton11.setBorderPainted(false);
      add(noreserveButton11);
      
      noreserveButton12.setBounds(441,186, 198, 57);
      noreserveButton12.setVisible(false);
      noreserveButton12.setBorderPainted(false);
      add(noreserveButton12);
      
      noreserveButton13.setBounds(641,186, 198, 57);
      noreserveButton13.setVisible(false);
      noreserveButton13.setBorderPainted(false);
      add(noreserveButton13);
      
      noreserveButton14.setBounds(841,186, 198, 57);
      noreserveButton14.setVisible(false);
      noreserveButton14.setBorderPainted(false);
      add(noreserveButton14);
      
      noreserveButton15.setBounds(1041,186, 198, 57);
      noreserveButton15.setVisible(false);
      noreserveButton15.setBorderPainted(false);
      add(noreserveButton15);
      
      noreserveButton21.setBounds(241,246, 198, 57);
      noreserveButton21.setVisible(false);
      noreserveButton21.setBorderPainted(false);
      add(noreserveButton21);
      
      noreserveButton22.setBounds(441,246, 198, 57);
      noreserveButton22.setVisible(false);
      noreserveButton22.setBorderPainted(false);
      add(noreserveButton22);
      
      noreserveButton23.setBounds(641,246, 198, 57);
      noreserveButton23.setVisible(false);
      noreserveButton23.setBorderPainted(false);
      add(noreserveButton23);
      
      noreserveButton24.setBounds(841,246, 198, 57);
      noreserveButton24.setVisible(false);
      noreserveButton24.setBorderPainted(false);
      add(noreserveButton24);
      
      noreserveButton25.setBounds(1041,246, 198, 57);
      noreserveButton25.setVisible(false);
      noreserveButton25.setBorderPainted(false);
      add(noreserveButton25);
      
      noreserveButton31.setBounds(241,303, 198, 57);
      noreserveButton31.setVisible(false);
      noreserveButton31.setBorderPainted(false);
      add(noreserveButton31);
      
      noreserveButton32.setBounds(441,303, 198, 57);
      noreserveButton32.setVisible(false);
      noreserveButton32.setBorderPainted(false);
      add(noreserveButton32);
      
      noreserveButton33.setBounds(641,303, 198, 57);
      noreserveButton33.setVisible(false);
      noreserveButton33.setBorderPainted(false);
      add(noreserveButton33);
      
      noreserveButton34.setBounds(841,303, 198, 57);
      noreserveButton34.setVisible(false);
      noreserveButton34.setBorderPainted(false);
      add(noreserveButton34);
      
      noreserveButton35.setBounds(1041,303, 198, 57);
      noreserveButton35.setVisible(false);
      noreserveButton35.setBorderPainted(false);
      add(noreserveButton35);
      
      noreserveButton41.setBounds(241,361, 198, 57);
      noreserveButton41.setVisible(false);
      noreserveButton41.setBorderPainted(false);
      add(noreserveButton41);
      
      noreserveButton42.setBounds(441,361, 198, 57);
      noreserveButton42.setVisible(false);
      noreserveButton42.setBorderPainted(false);
      add(noreserveButton42);
      
      noreserveButton43.setBounds(641,361, 198, 57);
      noreserveButton43.setVisible(false);
      noreserveButton43.setBorderPainted(false);
      add(noreserveButton43);
      
      noreserveButton44.setBounds(841,361, 198, 57);
      noreserveButton44.setVisible(false);
      noreserveButton44.setBorderPainted(false);
      add(noreserveButton44);
      
      noreserveButton45.setBounds(1041,361, 198, 57);
      noreserveButton45.setVisible(false);
      noreserveButton45.setBorderPainted(false);
      add(noreserveButton45);
      
      noreserveButton51.setBounds(241,418, 198, 57);
      noreserveButton51.setVisible(false);
      noreserveButton51.setBorderPainted(false);
      add(noreserveButton51);
      
      noreserveButton52.setBounds(441,418, 198, 57);
      noreserveButton52.setVisible(false);
      noreserveButton52.setBorderPainted(false);
      add(noreserveButton52);
      
      noreserveButton53.setBounds(641,418, 198, 57);
      noreserveButton53.setVisible(false);
      noreserveButton53.setBorderPainted(false);
      add(noreserveButton53);
      
      noreserveButton54.setBounds(841,418, 198, 57);
      noreserveButton54.setVisible(false);
      noreserveButton54.setBorderPainted(false);
      add(noreserveButton54);
      
      noreserveButton55.setBounds(1041,418, 198, 57);
      noreserveButton55.setVisible(false);
      noreserveButton55.setBorderPainted(false);
      add(noreserveButton55);
      
      noreserveButton61.setBounds(241,475, 198, 57);
      noreserveButton61.setVisible(false);
      noreserveButton61.setBorderPainted(false);
      add(noreserveButton61);
      
      noreserveButton62.setBounds(441,475, 198, 57);
      noreserveButton62.setVisible(false);
      noreserveButton62.setBorderPainted(false);
      add(noreserveButton62);
      
      noreserveButton63.setBounds(641,475, 198, 57);
      noreserveButton63.setVisible(false);
      noreserveButton63.setBorderPainted(false);
      add(noreserveButton63);
      
      noreserveButton64.setBounds(841,475, 198, 57);
      noreserveButton64.setVisible(false);
      noreserveButton64.setBorderPainted(false);
      add(noreserveButton64);
      
      noreserveButton65.setBounds(1041,475, 198, 57);
      noreserveButton65.setVisible(false);
      noreserveButton65.setBorderPainted(false);
      add(noreserveButton65);
      
      noreserveButton71.setBounds(241,532, 198, 57);
      noreserveButton71.setVisible(false);
      noreserveButton71.setBorderPainted(false);
      add(noreserveButton71);
      
      noreserveButton72.setBounds(441,532, 198, 57);
      noreserveButton72.setVisible(false);
      noreserveButton72.setBorderPainted(false);
      add(noreserveButton72);
      
      noreserveButton73.setBounds(641,532, 198, 57);
      noreserveButton73.setVisible(false);
      noreserveButton73.setBorderPainted(false);
      add(noreserveButton73);
      
      noreserveButton74.setBounds(841,532, 198, 57);
      noreserveButton74.setVisible(false);
      noreserveButton74.setBorderPainted(false);
      add(noreserveButton74);
      
      noreserveButton75.setBounds(1041,532, 198, 57);
      noreserveButton75.setVisible(false);
      noreserveButton75.setBorderPainted(false);
      add(noreserveButton75);
      
      noreserveButton81.setBounds(241,589, 198, 57);
      noreserveButton81.setVisible(false);
      noreserveButton81.setBorderPainted(false);
      add(noreserveButton81);
      
      noreserveButton82.setBounds(441,589, 198, 57);
      noreserveButton82.setVisible(false);
      noreserveButton82.setBorderPainted(false);
      add(noreserveButton82);
      
      noreserveButton83.setBounds(641,589, 198, 57);
      noreserveButton83.setVisible(false);
      noreserveButton83.setBorderPainted(false);
      add(noreserveButton83);
      
      noreserveButton84.setBounds(841,589, 198, 57);
      noreserveButton84.setVisible(false);
      noreserveButton84.setBorderPainted(false);
      add(noreserveButton84);
      
      noreserveButton85.setBounds(1041,589, 198, 57);
      noreserveButton85.setVisible(false);
      noreserveButton85.setBorderPainted(false);
      add(noreserveButton85);
      
      noreserveButton91.setBounds(241,647, 198, 57);
      noreserveButton91.setVisible(false);
      noreserveButton91.setBorderPainted(false);
      add(noreserveButton91);
      
      noreserveButton92.setBounds(441,647, 198, 57);
      noreserveButton92.setVisible(false);
      noreserveButton92.setBorderPainted(false);
      add(noreserveButton92);
      
      noreserveButton93.setBounds(641,647, 198, 57);
      noreserveButton93.setVisible(false);
      noreserveButton93.setBorderPainted(false);
      add(noreserveButton93);
      
      noreserveButton94.setBounds(841,647, 198, 57);
      noreserveButton94.setVisible(false);
      noreserveButton94.setBorderPainted(false);
      add(noreserveButton94);
      
      noreserveButton95.setBounds(1041,647, 198, 57);
      noreserveButton95.setVisible(false);
      noreserveButton95.setBorderPainted(false);
      add(noreserveButton95);
      
      //위에 exit 버튼 입니다.
      exitButton.setBounds(1245,0, 30, 30);
      exitButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            exitButton.setIcon(exitButtonEnteredImage);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            exitButton.setIcon(exitButtonImage);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            System.exit(0);
         }
      });
      add(exitButton);
           
      //start 버튼 입니다.   
      startButton.setBounds(500, 500 , 297, 92);
      startButton.setBorderPainted(false);
      startButton.setContentAreaFilled(false);
      startButton.setFocusPainted(false);
      startButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            startButton.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            startButton.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            selectbuildingpage();
         }
      });
      add(startButton);   
      
      //빌딩 뒤로가기 버튼입니다.
      first_backButton.setBounds(20,50, 60, 60);
      first_backButton.setVisible(false);
      first_backButton.setBorderPainted(false);
      first_backButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            first_backButton.setIcon(backButtonEnteredImage);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            first_backButton.setIcon(backButtonImage);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            backfirstpage();
         }
      });
      add(first_backButton);      
      
      //강의실 선택의 돌아가기 버튼입니다.
      selectroom_backButton.setBounds(20,50, 60, 60);
      selectroom_backButton.setVisible(false);
      selectroom_backButton.setBorderPainted(false);
      selectroom_backButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            selectroom_backButton.setIcon(backButtonEnteredImage);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            selectroom_backButton.setIcon(backButtonImage);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            selectbuildingpage();
         }
      });
      add(selectroom_backButton);     
      
      //메뉴 선택의 돌아가기 버튼입니다.
      menu_backButton.setBounds(20,50, 60, 60);
      menu_backButton.setVisible(false);
      menu_backButton.setBorderPainted(false);
      menu_backButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            menu_backButton.setIcon(backButtonEnteredImage);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            menu_backButton.setIcon(backButtonImage);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            select_roomnumberpage();
         }
      });
      add(menu_backButton);     
      
      //시간표의 뒤로가기 버튼입니다.
      timetable_backButton.setBounds(20,50, 60, 60);
      timetable_backButton.setVisible(false);
      timetable_backButton.setBorderPainted(false);
      timetable_backButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            timetable_backButton.setIcon(backButtonEnteredImage);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            timetable_backButton.setIcon(backButtonImage);
         }
         @Override
         public void mousePressed(MouseEvent e) {
        	ButtonOff();
            select_menu();
         }
      });
      add(timetable_backButton);   

      //예약확인의 뒤로가기 버튼입니다.
      reserve_backButton.setBounds(20,50, 60, 60);
      reserve_backButton.setVisible(false);
      reserve_backButton.setBorderPainted(false);
      reserve_backButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	 reserve_backButton.setIcon(backButtonEnteredImage);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	 reserve_backButton.setIcon(backButtonImage);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            reserve();
         }
      });
      add(reserve_backButton);     
      
    //예약취소확인의 뒤로가기 버튼입니다.
      cancelok_backButton.setBounds(20,50, 60, 60);
      cancelok_backButton.setVisible(false);
      cancelok_backButton.setBorderPainted(false);
      cancelok_backButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	 cancelok_backButton.setIcon(backButtonEnteredImage);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	 cancelok_backButton.setIcon(backButtonImage);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            delete_reserve();
         }
      });
      add(cancelok_backButton);
      
      //it5호관 빌딩 버튼입니다
      it5Button.setBounds(100, 80, 242, 356);
      it5Button.setBorderPainted(false);
      it5Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            it5Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            it5Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
        	building = "it5호관";
            select_roomnumberpage();
         }
      });
      buildingPanel.add(it5Button);    
      
      //it4호관 빌딩 버튼입니다
      it4Button.setBounds(750, 0, 268, 485);
      it4Button.setBorderPainted(false);
      it4Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            it4Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            it4Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
        	building = "it4호관";
            select_roomnumberpage();
         }
      });
      buildingPanel.add(it4Button);
      add(buildingPanel);  
      
      //101 버튼 입니다.
      room101Button.setBounds(0, 0, 127, 86);
      room101Button.setBorderPainted(false);
      room101Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            room101Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            room101Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
        	classroom = "101호";
            select_menu();
            
         }
      });
      roomPanel.add(room101Button);
    
      //102 버튼입니다
      room102Button.setBounds(200, 0, 130, 85);
      room102Button.setBorderPainted(false);
      room102Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            room102Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            room102Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
         	classroom = "102호";
            select_menu();
         }
      });
      roomPanel.add(room102Button);
      
      //103 버튼입니다
      room103Button.setBounds(400, 0, 130, 85);
      room103Button.setBorderPainted(false);
      room103Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	 room103Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	 room103Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
         	classroom = "103호";
            select_menu();
         }
      });
      roomPanel.add(room103Button);
      
      //201 버튼 입니다.
      room201Button.setBounds(0, 150, 127, 86);
      room201Button.setBorderPainted(false);
      room201Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	room201Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	room201Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
         	classroom = "201호";
            select_menu();
         }
      });
      roomPanel.add(room201Button);
      
      //202 버튼입니다
      room202Button.setBounds(200, 150, 130, 85);
      room202Button.setBorderPainted(false);
      room202Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	room202Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	room202Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
         	classroom = "202호";
            select_menu();
         }
      });
      roomPanel.add(room202Button);
      
      //203 버튼입니다
      room203Button.setBounds(400, 150, 130, 85);
      room203Button.setBorderPainted(false);
      room203Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	 room203Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	 room203Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
         	classroom = "203호";
            select_menu();
         }
      });
      roomPanel.add(room203Button);
      
      //301 버튼 입니다.
      room301Button.setBounds(0, 300, 127, 86);
      room301Button.setBorderPainted(false);
      room301Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	room301Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	room301Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
         	classroom = "301호";
            select_menu();
         }
      });
      roomPanel.add(room301Button);
      
      //302 버튼입니다
      room302Button.setBounds(200, 300, 130, 85);
      room302Button.setBorderPainted(false);
      room302Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	room302Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	room302Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
         	classroom = "302호";
            select_menu();
         }
      });
      roomPanel.add(room302Button);
      
      //303 버튼입니다
      room303Button.setBounds(400, 300, 130, 85);
      room303Button.setBorderPainted(false);
      room303Button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
        	 room303Button.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
        	 room303Button.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
         	classroom = "303호";
            select_menu();
         }
      });
      roomPanel.add(room303Button);
      
      add(roomPanel);     
      
      //시간표조회 버튼입니다
      timemenuButton.setBounds(100,261, 310, 241);
      timemenuButton.setVisible(false);
      timemenuButton.setBorderPainted(false);
      timemenuButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            timemenuButton.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            timemenuButton.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            timetable();
         }
      });
      add(timemenuButton);
     
      //예약 버튼입니다
      reservemenuButton.setBounds(500,261, 309, 240);
      reservemenuButton.setVisible(false);
      reservemenuButton.setBorderPainted(false);
      reservemenuButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            reservemenuButton.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            reservemenuButton.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            reserve();
         }
      });
      add(reservemenuButton);

      //예약 확인 버튼입니다
      reserveOK.setBounds(850, 500, 100, 80);
      reserveOK.setVisible(false);
      reserveOK.setBorderPainted(true);
      reserveOK.setBackground(Color.LIGHT_GRAY);
      reserveOK.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
        	String day = reserveday_textField.getText();
        	String[] time = reservetime_textField.getText().split("~");
        	
        	try {
				if (reserveCheck(building, classroom, day, time[0], time[1]) && reserveCheck2(building, classroom, day, time[0], time[1])) {
					canreserve();
				} else {
					cannotreserve();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         }
      });
      add(reserveOK);
      
      //예약최종 확인 버튼입니다
      reservefinalOK.setBounds(850, 500, 100, 80);
      reservefinalOK.setVisible(false);
      reservefinalOK.setBorderPainted(true);
      reservefinalOK.setBackground(Color.LIGHT_GRAY);
      reservefinalOK.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
        	String n = reservename_textField.getText();
        	String sid = reservestudentid_textField.getText();
        	String pwd = reservepasswd_textField.getText();
        	String pwdcheck = reservepasswdcheck_textField.getText();
        	String day = reserveday_textField.getText();
        	String[] time = reservetime_textField.getText().split("~");
        	
        	if (!pwd.equals(pwdcheck)) {
        		canreserve();
        	} else {
        		ArrayList<String> saved = new ArrayList<String>();
        		String savedn = null;
        		String[] temp = null;
        		File file = new File("C:/Users/user/2018116790_workspace/TeamProject/reserve_" + building + "_" + classroom + ".dat");
        		boolean check = true;
        		if (file.exists()) {
        			try {
        				if (file.exists()) {
        					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        					saved = (ArrayList<String>)ois.readObject();
        					
        					for (String s : saved) {
        						temp = s.split(" ");
        						savedn = temp[0];
        						if(n.equals(savedn)) {
            						check = false;
            					}
        					}
            			}
        			} catch(IOException e1) {
        				e1.printStackTrace();
        			} catch(ClassNotFoundException e1) {
        				e1.printStackTrace();
        			}
        		}
        	
        		// check가 true면 저장
        		if (check) {
        			try {
        				String save = n + " " + sid + " " + pwd + " " + day + " " + time[0] + " " + time[1];
        				list.add(save);
        				
        				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("reserve_" + building + "_" + classroom + ".dat")));
        				oos.writeObject(list);
        				
        				reservefinish();
        			} catch(IOException e1) {
        				e1.getStackTrace();
        			}
        		} else {
        			canreserve();
        		}
        	}
         }
      });
      add(reservefinalOK);
    
      //예약취소 버튼입니다
      deletemenuButton.setBounds(900,261, 304, 237);
      deletemenuButton.setVisible(false);
      deletemenuButton.setBorderPainted(false);
      deletemenuButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            deletemenuButton.setBorderPainted(true);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            deletemenuButton.setBorderPainted(false);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            delete_reserve();
         }
      });
      add(deletemenuButton);
      
      //예약취소 확인 버튼 입니다.   
      cancelOK.setBounds(950, 500, 100, 80);
      cancelOK.setVisible(false);
      cancelOK.setBorderPainted(true);
      cancelOK.setBackground(Color.LIGHT_GRAY);
      cancelOK.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
        	String name = cancelname_textField.getText();
        	String sid = cancelid_textField.getText();
        	String pswd = cancelpwd_textField.getText();
        	if (reserveExist(building, classroom, name, sid, pswd)) {
        		String day = getDay(building, classroom, name, sid, pswd);
        		String time = getTime(building, classroom, name, sid, pswd);
        		cancelcheckday_textField.setText(day);
        		cancelchecktime_textField.setText(time);
        		delete_reserve_check();
        	}
        	else
        		delete_reserve_again();
         }
      });
      add(cancelOK);
      
      //예약취소 최종 확인 yes 버튼 입니다.   
      cancelfinalYES.setBounds(950, 400, 120, 92);
      cancelfinalYES.setBorderPainted(true);
      cancelfinalYES.setVisible(false);
      cancelfinalYES.setBackground(Color.BLUE);
      cancelfinalYES.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
        	ArrayList<String> saved = new ArrayList<String>();
        	String name = cancelname_textField.getText();
     		String savedn = null;
     		String[] temp = null;
     		File file = new File("C:/Users/user/2018116790_workspace/TeamProject/reserve_" + building + "_" + classroom + ".dat");
     		boolean check = true;
     		if (file.exists()) {
     			try {
     				if (file.exists()) {
     					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
     					saved = (ArrayList<String>)ois.readObject();
     					
     					int cnt = 0;
     					for (String s : saved) {
     						temp = s.split(" ");
     						savedn = temp[0];
     						if (savedn.equals(name)) {
     							saved.remove(cnt);
     							break;
     						}
     						cnt++;
     					}
         			}
     				
     				try {        				
        				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        				oos.writeObject(saved);
        			} catch(IOException e1) {
        				e1.getStackTrace();
        			}
     				
                	cancel_finish();
     				
     			} catch(IOException e1) {
     				e1.printStackTrace();
     			} catch(ClassNotFoundException e1) {
     				e1.printStackTrace();
     			}
     		}
         }
      });
      add(cancelfinalYES);
      
      //예약취소 최종 확인 no 버튼 입니다.   
      cancelfinalNO.setBounds(950, 550 , 120, 92);
      cancelfinalNO.setBorderPainted(true);
      cancelfinalNO.setVisible(false);
      cancelfinalNO.setBackground(Color.RED);
      cancelfinalNO.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
        	 delete_reserve();
         }
      });
      add(cancelfinalNO);
      
      //홈으로 돌아가기 버튼입니다
      returnButton.setBounds(850, 500, 100, 80);
      returnButton.setVisible(false);
      returnButton.setBorderPainted(false);
      returnButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            backfirstpage();
         }
      });
      add(returnButton);
      
      //위에 메뉴바 색깔 입니다.
      MenuBar.setBounds(0,0,1280,30);
      
      MenuBar.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
         }
      });
      MenuBar.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseDragged(MouseEvent e) {
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            setLocation(x-mouseX,y-mouseY);
         }
      });
      
      add(MenuBar);
   }

   //이미지 보여주는 함수 입니다.
   public void paint(Graphics g) {
      screenImage = createImage(Main.WIDTH, Main.HEIGHT);
      screenGraphic = screenImage.getGraphics();
      screenDraw(screenGraphic);
      g.drawImage(screenImage, 0, 0, null);
   }

   public void screenDraw(Graphics g) {
      g.drawImage(background, 0, 0, null);
      paintComponents(g);
      this.repaint();
   }
   
   //화면 전환입니다.
   public void backfirstpage() {
      startButton.setVisible(true);
      background = new ImageIcon(Main.class.getResource("../images/first_page.png")).getImage();
      first_backButton.setVisible(false);
      buildingPanel.setVisible(false);
      returnButton.setVisible(false);
	  nowinfo.setVisible(false);
   }
   
   public void selectbuildingpage() {
      startButton.setVisible(false);
      background = new ImageIcon(Main.class.getResource("../images/selectbuilding.png")).getImage();
      first_backButton.setVisible(true);
      selectroom_backButton.setVisible(false);
      buildingPanel.setVisible(true);
      roomPanel.setVisible(false);
	  nowinfo.setVisible(false);
   }
   
   public void select_roomnumberpage() {
      background = new ImageIcon(Main.class.getResource("../images/강의실 선택.png")).getImage();
      buildingPanel.setVisible(false);
      roomPanel.setVisible(true);
      selectroom_backButton.setVisible(true);
      first_backButton.setVisible(false);
      roomPanel.setVisible(true);
      menu_backButton.setVisible(false);
      timemenuButton.setVisible(false);
      reservemenuButton.setVisible(false);
      deletemenuButton.setVisible(false);
	  nowinfo.setVisible(false);
   }
   
   public void select_menu() {
	  roomPanel.setVisible(false);
      selectroom_backButton.setVisible(false);
      background = new ImageIcon(Main.class.getResource("../images/menuselect.png")).getImage();
      menu_backButton.setVisible(true);
      timemenuButton.setVisible(true);
      reservemenuButton.setVisible(true);
      deletemenuButton.setVisible(true);
      timetable_backButton.setVisible(false);
      reservename.setVisible(false);
	  reservestudentid.setVisible(false);
	  reservepasswd.setVisible(false);
	  reservepasswdcheck.setVisible(false);
      reserveday.setVisible(false);
      reserveday.setVisible(false);
      reservetime.setVisible(false);
      reserveOK.setVisible(false);
      reservefinalOK.setVisible(false);
      cancelname.setVisible(false);
      cancelid.setVisible(false);
      cancelpwd.setVisible(false);
      cancelOK.setVisible(false);
	  returnButton.setVisible(false);
	  settext();
	  nowinfo.setVisible(true);
   }
   
   public void timetable() {
	  background = new ImageIcon(Main.class.getResource("../images/시간표.png")).getImage();
	  menu_backButton.setVisible(false);
      timemenuButton.setVisible(false);
      reservemenuButton.setVisible(false);
      deletemenuButton.setVisible(false);
      timetable_backButton.setVisible(true);
	  classexistCheck();
	  reservationexistCheck();
   }
   
   public void reserve() {
	  reservename_textField.setText("");
	  reservestudentid_textField.setText("");
	  reservepasswd_textField.setText("");
	  reservepasswdcheck_textField.setText("");
	  reserveday_textField.setText("");
	  reservetime_textField.setText("");
      background = new ImageIcon(Main.class.getResource("../images/예약정보.png")).getImage();
      menu_backButton.setVisible(false);
      timemenuButton.setVisible(false);
      reservemenuButton.setVisible(false);
      deletemenuButton.setVisible(false);
      timetable_backButton.setVisible(true);
      reservename.setVisible(false);
	  reservestudentid.setVisible(false);
	  reservepasswd.setVisible(false);
	  reservepasswdcheck.setVisible(false);
      reserveday.setVisible(true);
      reservetime.setVisible(true);
      reserveOK.setVisible(true);
      reservefinalOK.setVisible(false);
   }
   
   public void canreserve() {
	  reservename_textField.setText("");
	  reservestudentid_textField.setText("");
	  reservepasswd_textField.setText("");
	  reservepasswdcheck_textField.setText("");
	  background = new ImageIcon(Main.class.getResource("../images/예약자 정보.png")).getImage();
	  menu_backButton.setVisible(false);
	  timemenuButton.setVisible(false);
	  reservemenuButton.setVisible(false);
	  deletemenuButton.setVisible(false);
	  reservename.setVisible(true);
	  reservestudentid.setVisible(true);
	  reservepasswd.setVisible(true);
	  reservepasswdcheck.setVisible(true);
	  reserveday.setVisible(false);
      reservetime.setVisible(false);
	  reserveOK.setVisible(false);
	  reservefinalOK.setVisible(true);
	  timetable_backButton.setVisible(false);
	  reserve_backButton.setVisible(true);
   }
   
   public void cannotreserve() {
	  reserveday_textField.setText("");
	  reservetime_textField.setText("");
	  background = new ImageIcon(Main.class.getResource("../images/예약 불가능.png")).getImage();
	  menu_backButton.setVisible(false);
	  timemenuButton.setVisible(false);
	  reservemenuButton.setVisible(false);
	  deletemenuButton.setVisible(false);
	  reserveday.setVisible(true);
	  reservetime.setVisible(true);
	  reserveOK.setVisible(true);
	  reservefinalOK.setVisible(false);
   }
   
   public void reservefinish() {
	  reserveday_textField.setText("");
	  reservetime_textField.setText("");
	  reservename_textField.setText("");
	  reservestudentid_textField.setText("");
	  reservepasswd_textField.setText("");
	  reservepasswdcheck_textField.setText("");
	  reservename.setVisible(false);
	  reservestudentid.setVisible(false);
	  reservepasswd.setVisible(false);
	  reservepasswdcheck.setVisible(false);
	  background = new ImageIcon(Main.class.getResource("../images/예약완료.png")).getImage();
	  menu_backButton.setVisible(false);
	  first_backButton.setVisible(false);
	  selectroom_backButton.setVisible(false);
	  menu_backButton.setVisible(false);
	  reserve_backButton.setVisible(false);
	  timemenuButton.setVisible(false);
	  reservemenuButton.setVisible(false);
	  deletemenuButton.setVisible(false);
	  reservefinalOK.setVisible(false);
	  timetable_backButton.setVisible(false);
	  returnButton.setVisible(true);
	  nowinfo.setVisible(false);
   }
   
   public void delete_reserve() {
      background = new ImageIcon(Main.class.getResource("../images/예약취소배경.png")).getImage();
      menu_backButton.setVisible(false);
      timemenuButton.setVisible(false);
      reservemenuButton.setVisible(false);
      deletemenuButton.setVisible(false);
      timetable_backButton.setVisible(true);
      cancelname.setVisible(true);
      cancelid.setVisible(true);
      cancelpwd.setVisible(true);
      cancelOK.setVisible(true);
      cancelname_textField.setText("");
	  cancelid_textField.setText("");
	  cancelpwd_textField.setText("");
	  cancelcheckday.setVisible(false);
	  cancelchecktime.setVisible(false);
	  cancelfinalYES.setVisible(false);
	  cancelfinalNO.setVisible(false);
   }
   
   public void delete_reserve_check() {
	  background = new ImageIcon(Main.class.getResource("../images/예약취소 가능.png")).getImage();
	  timetable_backButton.setVisible(false);
	  cancelok_backButton.setVisible(true);
	  cancelname.setVisible(false);
	  cancelid.setVisible(false);
	  cancelpwd.setVisible(false);
	  cancelOK.setVisible(false);
	  cancelcheckday.setVisible(true);
	  cancelchecktime.setVisible(true);
	  cancelfinalYES.setVisible(true);
	  cancelfinalNO.setVisible(true);
   }
   
   public void delete_reserve_again() {
	   background = new ImageIcon(Main.class.getResource("../images/예약취소 불가능.png")).getImage();
	   cancelname_textField.setText("");
	   cancelid_textField.setText("");
	   cancelpwd_textField.setText("");
	   menu_backButton.setVisible(false);
	   timemenuButton.setVisible(false);
	   reservemenuButton.setVisible(false);
	   deletemenuButton.setVisible(false);
	   timetable_backButton.setVisible(true);
	   cancelname.setVisible(true);
	   cancelid.setVisible(true);
	   cancelpwd.setVisible(true);
	   cancelOK.setVisible(true);
   }
   
   public void cancel_finish() {
	   background = new ImageIcon(Main.class.getResource("../images/예약취소 완료.png")).getImage();
	   timetable_backButton.setVisible(false);
	   cancelok_backButton.setVisible(false);
	   menu_backButton.setVisible(false);
	   first_backButton.setVisible(false);
	   selectroom_backButton.setVisible(false);
	   menu_backButton.setVisible(false);
	   reserve_backButton.setVisible(false);
	   timemenuButton.setVisible(false);
	   cancelcheckday.setVisible(false);
	   cancelchecktime.setVisible(false);
	   cancelfinalYES.setVisible(false);
	   cancelfinalNO.setVisible(false);
	   returnButton.setVisible(true);
	   nowinfo.setVisible(false);
   }
   
   public boolean classexistCheck() {
	   String savedbuilding = null, savedclassroom = null, savedday = null, savedstarttime = null, savedendtime = null;
	   String[] start, end;
	   String  temp = null;
	   int col = 0;
	   int SSTime = 0, SETime = 0;
	   
	   try {
		   Scanner reader = new Scanner(new File("C:/Users/user/2018116790_workspace/TeamProject/src/timetable.txt"));
		   while(reader.hasNext()) {
			   for (int i = 0; i < 5; i++) {
				   switch(i) {
				   case 0: 
					   savedbuilding = reader.next();
					   break;
				   case 1:
					   savedclassroom = reader.next();
					   break;
				   case 2:
					   savedday = reader.next();
					   break;
				   case 3:
					   savedstarttime = reader.next();
					   start = savedstarttime.split(":");
					   temp = start[0];
					   SSTime = Integer.parseInt(temp);
					   break;
				   case 4:
					   savedendtime = reader.next();
					   end = savedendtime.split(":");
					   temp = end[0];
					   SETime = Integer.parseInt(temp);
					   break;
				   }
			   }
			   
			   if (savedbuilding.equals(building) && savedclassroom.equals(classroom)) {
				   Day d = Day.valueOf(savedday);
				   col = d.ordinal() + 1;
				   for (int i = SSTime; i < SETime; i++) {						   
						   for (int y = 1; y < 10; y++) {
							   int k = y * 10 + col;
							   switch(k) {
							   case 11:
								   if (i - 8 == 1 && col == 1) {
									   classButton11.setVisible(true);
								   } else 
									   break;
							   case 12:
								   if (i - 8 == 1 && col == 2) {
									   classButton12.setVisible(true);
								   } else 
									   break;
							   case 13:
								   if (i - 8 == 1 && col == 3) {
									   classButton13.setVisible(true);
								   } else 
									   break;
							   case 14:
								   if (i - 8 == 1 && col == 4) {
									   classButton14.setVisible(true);
								   } else 
									   break;
							   case 15:
								   if (i - 8 == 1 && col == 5) {
									   classButton15.setVisible(true);
								   } else 
									   break;
							   case 21:
								   if (i - 8 == 2 && col == 1) {
									   classButton21.setVisible(true);
								   } else 
									   break;
							   case 22:
								   if (i - 8 == 2 && col == 2) {
									   classButton22.setVisible(true);
								   } else 
									   break;
							   case 23:
								   if (i - 8 == 2 && col == 3) {
									   classButton23.setVisible(true);
								   } else 
									   break;
							   case 24:
								   if (i - 8 == 2 && col == 4) {
									   classButton24.setVisible(true);
								   } else 
									   break;
							   case 25:
								   if (i - 8 == 2 && col == 5) {
									   classButton25.setVisible(true);
								   } else 
									   break;
							   case 31:
								   if (i - 8 == 3 && col == 1) {
									   classButton31.setVisible(true);
								   } else 
									   break;
							   case 32:
								   if (i - 8 == 3 && col == 2) {
									   classButton32.setVisible(true);
								   } else 
									   break;
							   case 33:
								   if (i - 8 == 3 && col == 3) {
									   classButton33.setVisible(true);
								   } else 
									   break;
							   case 34:
								   if (i - 8 == 3 && col == 4) {
									   classButton34.setVisible(true);
								   } else 
									   break;
							   case 35:
								   if (i - 8 == 3 && col == 5) {
									   classButton35.setVisible(true);
								   } else 
									   break;
							   case 41:
								   if (i - 8 == 4 && col == 1) {
									   classButton41.setVisible(true);
								   } else 
									   break;
							   case 42:
								   if (i - 8 == 4 && col == 2) {
									   classButton42.setVisible(true);
								   } else 
									   break;
							   case 43:
								   if (i - 8 == 4 && col == 3) {
									   classButton43.setVisible(true);
								   } else 
									   break;
							   case 44:
								   if (i - 8 == 4 && col == 4) {
									   classButton44.setVisible(true);
								   } else 
									   break;
							   case 45:
								   if (i - 8 == 4 && col == 5) {
									   classButton45.setVisible(true);
								   } else 
									   break;
							   case 51:
								   if (i - 8 == 5 && col == 1) {
									   classButton51.setVisible(true);
								   } else 
									   break;
							   case 52:
								   if (i - 8 == 5 && col == 2) {
									   classButton52.setVisible(true);
								   } else 
									   break;
							   case 53:
								   if (i - 8 == 5 && col == 3) {
									   classButton53.setVisible(true);
								   } else 
									   break;
							   case 54:
								   if (i - 8 == 5 && col == 4) {
									   classButton54.setVisible(true);
								   } else 
									   break;
							   case 55:
								   if (i - 8 == 5 && col == 5) {
									   classButton55.setVisible(true);
								   } else 
									   break;
							   case 61:
								   if (i - 8 == 6 && col == 1) {
									   classButton61.setVisible(true);
								   } else 
									   break;
							   case 62:
								   if (i - 8 == 6 && col == 2) {
									   classButton62.setVisible(true);
								   } else 
									   break;
							   case 63:
								   if (i - 8 == 6 && col == 3) {
									   classButton63.setVisible(true);
								   } else 
									   break;
							   case 64:
								   if (i - 8 == 6 && col == 4) {
									   classButton64.setVisible(true);
								   } else 
									   break;
							   case 65:
								   if (i - 8 == 6 && col == 5) {
									   classButton65.setVisible(true);
								   } else 
									   break;
							   case 71:
								   if (i - 8 == 7 && col == 1) {
									   classButton71.setVisible(true);
								   } else 
									   break;
							   case 72:
								   if (i - 8 == 7 && col == 2) {
									   classButton72.setVisible(true);
								   } else 
									   break;
							   case 73:
								   if (i - 8 == 7 && col == 3) {
									   classButton73.setVisible(true);
								   } else 
									   break;
							   case 74:
								   if (i - 8 == 7 && col == 4) {
									   classButton74.setVisible(true);
								   } else 
									   break;
							   case 75:
								   if (i - 8 == 7 && col == 5) {
									   classButton75.setVisible(true);
								   } else 
									   break;
							   case 81:
								   if (i - 8 == 8 && col == 1) {
									   classButton81.setVisible(true);
								   } else 
									   break;
							   case 82:
								   if (i - 8 == 8 && col == 2) {
									   classButton82.setVisible(true);
								   } else 
									   break;
							   case 83:
								   if (i - 8 == 8 && col == 3) {
									   classButton83.setVisible(true);
								   } else 
									   break;
							   case 84:
								   if (i - 8 == 8 && col == 4) {
									   classButton84.setVisible(true);
								   } else 
									   break;
							   case 85:
								   if (i - 8 == 8 && col == 5) {
									   classButton85.setVisible(true);
								   } else 
									   break;
							   case 91:
								   if (i - 8 == 9 && col == 1) {
									   classButton91.setVisible(true);
								   } else 
									   break;
							   case 92:
								   if (i - 8 == 9 && col == 2) {
									   classButton92.setVisible(true);
								   } else 
									   break;
							   case 93:
								   if (i - 8 == 9 && col == 3) {
									   classButton93.setVisible(true);
								   } else 
									   break;
							   case 94:
								   if (i - 8 == 9 && col == 4) {
									   classButton94.setVisible(true);
								   } else 
									   break;
							   case 95:
								   if (i - 8 == 9 && col == 5) {
									   classButton95.setVisible(true);
								   } else 
									   break;
							   }
						   }
				   }
			   }
		   }
		   return true;
	   } catch(FileNotFoundException e) {
		   return true;
	   }
   }
   
   public boolean reservationexistCheck() {
	   ArrayList<String> saved = new ArrayList<String>();
	   String savedday = null, savedstarttime = null, savedendtime = null;
	   String[] start, end;
	   String  temp = null;
	   int col = 0;
	   int SSTime = 0, SETime = 0;
	   
	   try {
		   	File f = new File("C:/Users/user/2018116790_workspace/TeamProject/" + "reserve_" + building + "_" + classroom + ".dat");
			if (f.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				saved = (ArrayList<String>)ois.readObject();
				if(saved !=null) {
					
					for (String s : saved) {
						String[] split = s.split(" ");
						savedday = split[3];
						savedstarttime = split[4];
						start = savedstarttime.split(":");
						temp = start[0];
						SSTime = Integer.parseInt(temp);
						savedendtime = split[5];
						end = savedendtime.split(":");
						temp = end[0];
						SETime = Integer.parseInt(temp);
					}
				   if (savedday == null)
					   return false;
					   Day d = Day.valueOf(savedday);
					   col = d.ordinal() + 1;
					   for (int i = SSTime; i < SETime; i++) {						   
							   for (int y = 1; y < 10; y++) {
								   int k = y * 10 + col;
								   switch(k) {
								   case 11:
									   if (i - 8 == 1 && col == 1) {
										   noreserveButton11.setVisible(true);
									   } else 
										   break;
								   case 12:
									   if (i - 8 == 1 && col == 2) {
										   noreserveButton12.setVisible(true);
									   } else 
										   break;
								   case 13:
									   if (i - 8 == 1 && col == 3) {
										   noreserveButton13.setVisible(true);
									   } else 
										   break;
								   case 14:
									   if (i - 8 == 1 && col == 4) {
										   noreserveButton14.setVisible(true);
									   } else 
										   break;
								   case 15:
									   if (i - 8 == 1 && col == 5) {
										   noreserveButton15.setVisible(true);
									   } else 
										   break;
								   case 21:
									   if (i - 8 == 2 && col == 1) {
										   noreserveButton21.setVisible(true);
									   } else 
										   break;
								   case 22:
									   if (i - 8 == 2 && col == 2) {
										   noreserveButton22.setVisible(true);
									   } else 
										   break;
								   case 23:
									   if (i - 8 == 2 && col == 3) {
										   noreserveButton23.setVisible(true);
									   } else 
										   break;
								   case 24:
									   if (i - 8 == 2 && col == 4) {
										   noreserveButton24.setVisible(true);
									   } else 
										   break;
								   case 25:
									   if (i - 8 == 2 && col == 5) {
										   noreserveButton25.setVisible(true);
									   } else 
										   break;
								   case 31:
									   if (i - 8 == 3 && col == 1) {
										   noreserveButton31.setVisible(true);
									   } else 
										   break;
								   case 32:
									   if (i - 8 == 3 && col == 2) {
										   noreserveButton32.setVisible(true);
									   } else 
										   break;
								   case 33:
									   if (i - 8 == 3 && col == 3) {
										   noreserveButton33.setVisible(true);
									   } else 
										   break;
								   case 34:
									   if (i - 8 == 3 && col == 4) {
										   noreserveButton34.setVisible(true);
									   } else 
										   break;
								   case 35:
									   if (i - 8 == 3 && col == 5) {
										   noreserveButton35.setVisible(true);
									   } else 
										   break;
								   case 41:
									   if (i - 8 == 4 && col == 1) {
										   noreserveButton41.setVisible(true);
									   } else 
										   break;
								   case 42:
									   if (i - 8 == 4 && col == 2) {
										   noreserveButton42.setVisible(true);
									   } else 
										   break;
								   case 43:
									   if (i - 8 == 4 && col == 3) {
										   noreserveButton43.setVisible(true);
									   } else 
										   break;
								   case 44:
									   if (i - 8 == 4 && col == 4) {
										   noreserveButton44.setVisible(true);
									   } else 
										   break;
								   case 45:
									   if (i - 8 == 4 && col == 5) {
										   noreserveButton45.setVisible(true);
									   } else 
										   break;
								   case 51:
									   if (i - 8 == 5 && col == 1) {
										   noreserveButton51.setVisible(true);
									   } else 
										   break;
								   case 52:
									   if (i - 8 == 5 && col == 2) {
										   noreserveButton52.setVisible(true);
									   } else 
										   break;
								   case 53:
									   if (i - 8 == 5 && col == 3) {
										   noreserveButton53.setVisible(true);
									   } else 
										   break;
								   case 54:
									   if (i - 8 == 5 && col == 4) {
										   noreserveButton54.setVisible(true);
									   } else 
										   break;
								   case 55:
									   if (i - 8 == 5 && col == 5) {
										   noreserveButton55.setVisible(true);
									   } else 
										   break;
								   case 61:
									   if (i - 8 == 6 && col == 1) {
										   noreserveButton61.setVisible(true);
									   } else 
										   break;
								   case 62:
									   if (i - 8 == 6 && col == 2) {
										   noreserveButton62.setVisible(true);
									   } else 
										   break;
								   case 63:
									   if (i - 8 == 6 && col == 3) {
										   noreserveButton63.setVisible(true);
									   } else 
										   break;
								   case 64:
									   if (i - 8 == 6 && col == 4) {
										   noreserveButton64.setVisible(true);
									   } else 
										   break;
								   case 65:
									   if (i - 8 == 6 && col == 5) {
										   noreserveButton65.setVisible(true);
									   } else 
										   break;
								   case 71:
									   if (i - 8 == 7 && col == 1) {
										   noreserveButton71.setVisible(true);
									   } else 
										   break;
								   case 72:
									   if (i - 8 == 7 && col == 2) {
										   noreserveButton72.setVisible(true);
									   } else 
										   break;
								   case 73:
									   if (i - 8 == 7 && col == 3) {
										   noreserveButton73.setVisible(true);
									   } else 
										   break;
								   case 74:
									   if (i - 8 == 7 && col == 4) {
										   noreserveButton74.setVisible(true);
									   } else 
										   break;
								   case 75:
									   if (i - 8 == 7 && col == 5) {
										   noreserveButton75.setVisible(true);
									   } else 
										   break;
								   case 81:
									   if (i - 8 == 8 && col == 1) {
										   noreserveButton81.setVisible(true);
									   } else 
										   break;
								   case 82:
									   if (i - 8 == 8 && col == 2) {
										   noreserveButton82.setVisible(true);
									   } else 
										   break;
								   case 83:
									   if (i - 8 == 8 && col == 3) {
										   noreserveButton83.setVisible(true);
									   } else 
										   break;
								   case 84:
									   if (i - 8 == 8 && col == 4) {
										   noreserveButton84.setVisible(true);
									   } else 
										   break;
								   case 85:
									   if (i - 8 == 8 && col == 5) {
										   noreserveButton85.setVisible(true);
									   } else 
										   break;
								   case 91:
									   if (i - 8 == 9 && col == 1) {
										   noreserveButton91.setVisible(true);
									   } else 
										   break;
								   case 92:
									   if (i - 8 == 9 && col == 2) {
										   noreserveButton92.setVisible(true);
									   } else 
										   break;
								   case 93:
									   if (i - 8 == 9 && col == 3) {
										   noreserveButton93.setVisible(true);
									   } else 
										   break;
								   case 94:
									   if (i - 8 == 9 && col == 4) {
										   noreserveButton94.setVisible(true);
									   } else 
										   break;
								   case 95:
									   if (i - 8 == 9 && col == 5) {
										   noreserveButton95.setVisible(true);
									   } else 
										   break;
								   }
							   }
					   }
				}
				
		   }
		   return true;
	   } catch(IOException e) {
			e.printStackTrace();
			return false;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
   } 
   

   public boolean reserveCheck(String b, String c, String day, String starttime, String endtime) {
	   String savedbuilding = null, savedclassroom = null, savedday = null, savedstarttime = null, savedendtime = null;
	   String[] start, end;
	   String  temp = null;
	   int min = 9, max = 18;
	   int SSTime = 0, SETime = 0, STime = 0, ETime = 0;
	   
	   try {
		   Scanner reader = new Scanner(new File("C:/Users/user/2018116790_workspace/TeamProject/src/timetable.txt"));
		   while(reader.hasNext()) {
			   for (int i = 0; i < 5; i++) {
				   switch(i) {
				   case 0: 
					   savedbuilding = reader.next();
					   break;
				   case 1:
					   savedclassroom = reader.next();
					   break;
				   case 2:
					   savedday = reader.next();
					   break;
				   case 3:
					   savedstarttime = reader.next();
					   start = savedstarttime.split(":");
					   temp = start[0];
					   SSTime = Integer.parseInt(temp);
					   break;
				   case 4:
					   savedendtime = reader.next();
					   end = savedendtime.split(":");
					   temp = end[0];
					   SETime = Integer.parseInt(temp);
					   break;
				   }
			   }
			   
			   start = starttime.split(":");
			   end = endtime.split(":");
			   STime = Integer.parseInt(start[0]);
			   ETime = Integer.parseInt(end[0]);
			   
			   if (savedbuilding.equals(b) && savedclassroom.equals(c)) {
				   if (STime < min) 
					   return false;
				   if (ETime > max) 
					   return false;
				   if (day.equals(savedday)) {
					   if (SSTime < ETime && STime < SSTime) {
						  return false;
					   } else if (STime < SETime && SETime < ETime) {
						   return false;
					   } else {
						   if (SSTime <= STime && ETime <= SETime) {
							   return false;
						   }
					   }
				   }
			   }
		   }
		   return true;
	   } catch(FileNotFoundException e) {
		   return true;
	   }
}

public boolean reserveCheck2(String b, String c, String day, String starttime, String endtime) throws FileNotFoundException {
	ArrayList<String> saved = new ArrayList<String>();
	String savedday = null, savedstarttime = null, savedendtime = null;
	String[] start, end;
	String  temp = null;
	int min = 9, max = 18;
	int SSTime = 0, SETime = 0, STime = 0, ETime = 0;
	
	try {
		File f = new File("C:/Users/user/2018116790_workspace/TeamProject/" + "reserve_" + building + "_" + classroom + ".dat");
		if (f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			saved = (ArrayList<String>)ois.readObject();
			
			for (String s : saved) {
				String[] split = s.split(" ");
				savedday = split[3];
				savedstarttime = split[4];
				start = savedstarttime.split(":");
				temp = start[0];
				SSTime = Integer.parseInt(temp);
				savedendtime = split[5];
				end = savedendtime.split(":");
				temp = end[0];
				SETime = Integer.parseInt(temp);
				
				 start = starttime.split(":");
				 end = endtime.split(":");
				 STime = Integer.parseInt(start[0]);
				 ETime = Integer.parseInt(end[0]);
				   
				 if (STime < min) 
					 return false;
				 if (ETime > max) 
				     return false;
				 if (day.equals(savedday)) {
				   if (SSTime < ETime && STime < SSTime) {
					  return false;
				   } else if (STime < SETime && SETime < ETime) {
					   return false;
				   } else {
					   if (SSTime <= STime && ETime <= SETime) {
						   return false;
					   }
				   }
				}
			}
			}
		return true;
		}catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean reserveExist(String b, String c, String n, String id, String p) {
		ArrayList<String> saved = new ArrayList<String>();
		String savedn = null, savedid = null, savedp = null;
		File f = new File("C:/Users/user/2018116790_workspace/TeamProject/reserve_" + b + "_" + c + ".dat");
		
		try {
			if (f.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				saved = (ArrayList<String>)ois.readObject();
				
				for (String s : saved) {
					String[] split = s.split(" ");
					savedn = split[0];
					savedid = split[1];
					savedp = split[2];
					
					if (n.equals(savedn)) {
						if (id.equals(savedid)) {
							if(p.equals(savedp))
								return true;
						}
					}
				}
			}
			return false;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getDay(String b, String c, String n, String id, String p) {
		ArrayList<String> saved = new ArrayList<String>();
		String savedn = null, savedid = null, savedp = null, savedd = null;
		File f = new File("C:/Users/user/2018116790_workspace/TeamProject/reserve_" + b + "_" + c + ".dat");
		
		try {
			if (f.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				saved = (ArrayList<String>)ois.readObject();
				
				for (String s : saved) {
					String[] split = s.split(" ");
					savedn = split[0];
					savedid = split[1];
					savedp = split[2];
					savedd = split[3];
					
					if (n.equals(savedn)) {
						if (id.equals(savedid)) {
							if(p.equals(savedp))
								return savedd;
						}
					} 
				}
			}
			return "error";
		} catch(IOException e) {
			e.printStackTrace();
			return "error";
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String getTime(String b, String c, String n, String id, String p) {
		ArrayList<String> saved = new ArrayList<String>();
		String savedn = null, savedid = null, savedp = null, savedd = null, savedstime = null, savedetime = null;
		File f = new File("C:/Users/user/2018116790_workspace/TeamProject/reserve_" + b + "_" + c + ".dat");
		
		try {
			if (f.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				saved = (ArrayList<String>)ois.readObject();
				
				for (String s : saved) {
					String[] split = s.split(" ");
					savedn = split[0];
					savedid = split[1];
					savedp = split[2];
					savedd = split[3];
					savedstime = split[4];
					savedetime = split[5];
			
					if (n.equals(savedn)) {
						if (id.equals(savedid)) {
							if(p.equals(savedp))
								return (savedstime + "~" + savedetime);
						}
					} 	
				}
			}
			return "error";
		} catch(IOException e) {
			e.printStackTrace();
			return "error";
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	public void ButtonOff() {
		  classButton11.setVisible(false);
		  classButton12.setVisible(false);
		  classButton13.setVisible(false);
		  classButton14.setVisible(false);
		  classButton15.setVisible(false);
		  
		  classButton21.setVisible(false);
		  classButton22.setVisible(false);
		  classButton23.setVisible(false);
		  classButton24.setVisible(false);
		  classButton25.setVisible(false);
		  
		  classButton31.setVisible(false);
		  classButton32.setVisible(false);
		  classButton33.setVisible(false);
		  classButton34.setVisible(false);
		  classButton35.setVisible(false);
		  
		  classButton41.setVisible(false);
		  classButton42.setVisible(false);
		  classButton43.setVisible(false);
		  classButton44.setVisible(false);
		  classButton45.setVisible(false);
		  
		  classButton51.setVisible(false);
		  classButton52.setVisible(false);
		  classButton53.setVisible(false);
		  classButton54.setVisible(false);
		  classButton55.setVisible(false);
		  
		  classButton61.setVisible(false);
		  classButton62.setVisible(false);
		  classButton63.setVisible(false);
		  classButton64.setVisible(false);
		  classButton65.setVisible(false);
		  
		  classButton71.setVisible(false);
		  classButton72.setVisible(false);
		  classButton73.setVisible(false);
		  classButton74.setVisible(false);
		  classButton75.setVisible(false);
		  
		  classButton81.setVisible(false);
		  classButton82.setVisible(false);
		  classButton83.setVisible(false);
		  classButton84.setVisible(false);
		  classButton85.setVisible(false);
		  
		  classButton91.setVisible(false);
		  classButton92.setVisible(false);
		  classButton93.setVisible(false);
		  classButton94.setVisible(false);
		  classButton95.setVisible(false);
		  
		  
		  noreserveButton11.setVisible(false);
		  noreserveButton12.setVisible(false);
		  noreserveButton13.setVisible(false);
		  noreserveButton14.setVisible(false);
		  noreserveButton15.setVisible(false);
		  
		  noreserveButton21.setVisible(false);
		  noreserveButton22.setVisible(false);
		  noreserveButton23.setVisible(false);
		  noreserveButton24.setVisible(false);
		  noreserveButton25.setVisible(false);
		  
		  noreserveButton31.setVisible(false);
		  noreserveButton32.setVisible(false);
		  noreserveButton33.setVisible(false);
		  noreserveButton34.setVisible(false);
		  noreserveButton35.setVisible(false);
		  
		  noreserveButton41.setVisible(false);
		  noreserveButton42.setVisible(false);
		  noreserveButton43.setVisible(false);
		  noreserveButton44.setVisible(false);
		  noreserveButton45.setVisible(false);
		  
		  noreserveButton51.setVisible(false);
		  noreserveButton52.setVisible(false);
		  noreserveButton53.setVisible(false);
		  noreserveButton54.setVisible(false);
		  noreserveButton55.setVisible(false);
		  
		  noreserveButton61.setVisible(false);
		  noreserveButton62.setVisible(false);
		  noreserveButton63.setVisible(false);
		  noreserveButton64.setVisible(false);
		  noreserveButton65.setVisible(false);
		  
		  noreserveButton71.setVisible(false);
		  noreserveButton72.setVisible(false);
		  noreserveButton73.setVisible(false);
		  noreserveButton74.setVisible(false);
		  noreserveButton75.setVisible(false);
		  
		  noreserveButton81.setVisible(false);
		  noreserveButton82.setVisible(false);
		  noreserveButton83.setVisible(false);
		  noreserveButton84.setVisible(false);
		  noreserveButton85.setVisible(false);
		  
		  noreserveButton91.setVisible(false);
		  noreserveButton92.setVisible(false);
		  noreserveButton93.setVisible(false);
		  noreserveButton94.setVisible(false);
		  noreserveButton95.setVisible(false);
	}

	
	public void settext() {
		String []bsplit = building.split("호");
		String []csplit = classroom.split("호");
		
		String b = bsplit[0];
		String c = csplit[0];
		
		nowinfo_textArea.setText("NOW SELECTED\nBuliding: " + b + "\nClassroom: " + c);
	}
}