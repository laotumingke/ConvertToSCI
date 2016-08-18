package main;
/**
 * 
 * @Author wang
 * @Date 2016/03/15
 * 
 * **/

import java.awt.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.File;  
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JTabbedPane;  
import javax.swing.JTextField; 

public class mianJFrame implements ActionListener {
	JFrame mainFrame = new JFrame("NSF格式文件转换成SCI文本格式");
	Panel toppan = new Panel();
	Panel cenpan = new Panel();
	Panel botpan = new Panel();
	JLabel filelabel = new JLabel("选择NSF文件");
	JTextField filetext = new JTextField("");
	JFileChooser jfc = new JFileChooser();
	JButton filechoo = new JButton("选择文件");
	JLabel txtlabel = new JLabel("选择保存文件夹");
	JTextField txttext = new JTextField("");
//	JFileChooser jfc1 = new JFileChooser();
	JButton txtchoo = new JButton("选择文件夹");
	JButton fileyes = new JButton("转换");
//	JButton lineyes = new JButton("显示折线图");
//	JButton rectyes = new JButton("显示直方图");
	JLabel graphics = new JLabel("提示：请按要求选择excel文件，以及用于保存生成txt文件的文件夹后，再点击转换按钮！");
	JLabel email = new JLabel("有问题请发邮件至 wangwenjuan@mail.las.ac.cn~");
	
	mianJFrame(){
		jfc.setCurrentDirectory(new File("d://"));
//		jfc1.setCurrentDirectory(new File("d://"));
		mainFrame.setSize(720, 600);
		mainFrame.setLayout(new BorderLayout());
		toppan.setLayout(new GridLayout());
//		filelabel.setBounds(5, 5, 60, 30);
		toppan.setSize(720, 100);
		toppan.add(filelabel);
		toppan.add(filetext);
		toppan.add(filechoo);
		toppan.add(txtlabel);
		toppan.add(txttext);
		toppan.add(txtchoo);
		toppan.add(fileyes);
		
		cenpan.setLayout(new CardLayout());
		cenpan.add(graphics);
		
		botpan.setSize(720, 100);
		botpan.setLayout(new GridLayout());
		botpan.add(email);
		
		mainFrame.getContentPane().add("North", toppan);
		mainFrame.getContentPane().add("Center", cenpan);
		mainFrame.getContentPane().add("South", botpan);
//		mainFrame.getContentPane().add(botpan);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null); 
		mainFrame.setVisible(true);
		
		filechoo.addActionListener(this);
		txtchoo.addActionListener(this);
		fileyes.addActionListener(this);
//		lineyes.addActionListener(this);
//		rectyes.addActionListener(this);
		
		
		
		
	}
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource().equals(filechoo)){
			jfc.setFileSelectionMode(0);
            int state = jfc.showOpenDialog(null); 
            if (state == 1) {  
                return;
            } else {  
                File f = jfc.getSelectedFile();
                filetext.setText(f.getAbsolutePath());
            }
		}
		if(e.getSource().equals(txtchoo)){
			jfc.setFileSelectionMode(1);
            int state = jfc.showOpenDialog(null); 
            if (state == 1) {  
                return;
            } else {  
                File f = jfc.getSelectedFile();
                txttext.setText(f.getAbsolutePath());
            }
		}
		/*if(e.getSource().equals(txtchoo)){
			
//			jfc1.setCurrentDirectory(new java.io.File("."));
			jfc1.setDialogTitle("选择保存的文件夹");
			jfc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfc1.setAcceptAllFileFilterUsed(false);
            int state = jfc1.showOpenDialog(null); 
            if (state == 1) {  
                return;
            } else {  
                File f = jfc1.getSelectedFile();
                txttext.setText(f.getAbsolutePath());
            }
		}*/
		if(e.getSource().equals(fileyes)){
			Convert dg = new Convert();
			dg.setfilepath(filetext.getText().replace("\\","\\\\"));
			dg.setOutPath(txttext.getText().replace("\\","\\\\"));
			
/*	dg.drawLine();
			dg.drawRect();*/
			graphics.removeAll();
			ImageIcon icon = new ImageIcon();
//			icon.setImage();
			graphics.setIcon(icon);			
			graphics.setText(dg.convert());
		}
		/*if(e.getSource().equals(lineyes)){
			ImageIcon icon = new ImageIcon("d:\\Line.png");  
	        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
	                icon.getIconHeight(), Image.SCALE_DEFAULT));  
//	        System.out.println(icon.getIconHeight() + "" + icon.getIconWidth());
	        graphics.setHorizontalAlignment(0);  
	        graphics.setIcon(icon);
		}
		if(e.getSource().equals(rectyes)){
			ImageIcon icon = new ImageIcon("d:\\Rect.png");  
	        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
	                icon.getIconHeight(), Image.SCALE_DEFAULT));  
//	        System.out.println(icon.getIconHeight() + "" + icon.getIconWidth());
	        graphics.setHorizontalAlignment(0);  
	        graphics.setIcon(icon);
		}*/
	}
	
	public static void main(String[] args) {  
        new mianJFrame();  
    } 

}
