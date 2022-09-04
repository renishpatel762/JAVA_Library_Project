import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class ForAdmin extends JFrame implements ActionListener
{
	JPanel jpt,jpb;
	JLabel jltl,jlta,jleb;
	Font f,fb;
	JList jl;
	JButton jba,jadd,jview,jbr,jback;
	JEditorPane jep;
	JTextField jfa;
	JScrollPane jspb,jsta;
	Color ct,cb;
	Vector<String> vb=new Vector<String>();
	
	ForAdmin(String s)
	{
		super(s);
		
		setLayout(null);
		
		jpt=new JPanel();
		jpb=new JPanel();
		jltl=new JLabel("MS LIBRARY");
		jlta=new JLabel("FOR ADMIN");
		jba=new JButton("Add Book");
		jadd=new JButton("Add");
		jfa=new JTextField();
		jleb=new JLabel("Enter Book Name:");
		jview=new JButton("View Books");
		jbr=new JButton("Record");
		jback=new JButton("Back");
		jep=new JEditorPane();
		
		ct=new Color(217,217,217);
		cb=new Color(0,184,230);
		
		try
		{
			FileReader fr=new FileReader("BookList.txt");
			BufferedReader br=new BufferedReader(fr);
			
			String line=" ";
			
			while(line!=null)
			{
				line=br.readLine();
				if(line!=null)
				{
					vb.add(line);
				}
			}
			fr.close();
		}
		catch(Exception e) {}
		
		Collections.sort(vb);
		
		jl=new JList(vb);
		//jl.setVisible(false);
		
		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jspb=new JScrollPane(jl);
		jspb.setVisible(false);
		
		jsta=new JScrollPane(jep);
		jsta.setVisible(false);
		
		f=new Font("Arial",Font.BOLD,30);
		jltl.setFont(f);
		jlta.setFont(f);
		
		f=new Font("Arial",Font.BOLD,25);
		jl.setFont(f);
		jfa.setFont(f);
		jleb.setFont(f);
		jep.setFont(f);
		
	        fb=new Font("Dialog",Font.BOLD,20);
	        jba.setFont(fb);
		jadd.setFont(fb);
		jview.setFont(fb);
		jbr.setFont(fb);
		jback.setFont(fb);
		
		jpt.setLayout(null);
		jpb.setLayout(null);
		
		jleb.setVisible(false);
		jadd.setVisible(false);
		jfa.setVisible(false);
		jsta.setVisible(false);
		
		jpt.setBounds(0,0,1200,150);
		jpb.setBounds(0,150,1200,550);
		jltl.setBounds(500,60,500,30);
		jlta.setBounds(0,120,500,30);
		jspb.setBounds(300,0,885,512);
		//jl.setBounds(300,50,700,450);
		jba.setBounds(40,50,150,40);
		jfa.setBounds(300,100,300,40);
		jadd.setBounds(300,170,120,40);
		jleb.setBounds(300,50,300,40);
		jview.setBounds(40,120,150,40);
		jbr.setBounds(40,190,150,40);
		jback.setBounds(40,260,150,40);
		jsta.setBounds(300,0,885,512);
		
		jpt.setBackground(ct);
		jpb.setBackground(cb);
		
		jba.addActionListener(this);
		jadd.addActionListener(this);
		jfa.addActionListener(this);
		jview.addActionListener(this);
		jbr.addActionListener(this);
		jback.addActionListener(this);
		
		
		jpt.add(jltl);
		jpt.add(jlta);
		//jpb.add(jl);
		jpb.add(jspb);
		jpb.add(jba);
		jpb.add(jadd);
		jpb.add(jfa);
		jpb.add(jleb);
		jpb.add(jview);
		jpb.add(jbr);
		jpb.add(jback);
		jpb.add(jsta);
		
		add(jpt);
		add(jpb);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		String sac=e.getActionCommand();
		
		if(sac.equals("Add Book"))
		{
			jleb.setVisible(true);
			jadd.setVisible(true);
			jfa.setVisible(true);	
			jsta.setVisible(false);
			jspb.setVisible(false);
		}
		else if(sac.equals("Add"))
		{
			String bname=jfa.getText();
			vb.add(bname);
			{
				try
				{
					String l="";
					FileWriter fw=new FileWriter("BookList.txt");
					Enumeration e1=vb.elements();
					while(e1.hasMoreElements())
					{
						l=l+e1.nextElement()+"\n";
					}
					fw.write(l);
					
					fw.close();
				}
				catch(Exception ex) {}
				Collections.sort(vb);
				jfa.setText("");
				
				jl.setListData(vb);
			}
		}
		else if(sac.equals("View Books"))
		{
			jleb.setVisible(false);
			jadd.setVisible(false);
			jfa.setVisible(false);
			jsta.setVisible(false);
			jspb.setVisible(true);
			vb.removeAllElements();
			try
			{
				FileReader fr=new FileReader("BookList.txt");
				BufferedReader br=new BufferedReader(fr);
				
				String line=" ";
				
				while(line!=null)
				{
					line=br.readLine();
					if(line!=null)
					{
						vb.add(line);
					}
				}
				fr.close();
			}
			catch(Exception ex) {}
			
			Collections.sort(vb);
			jl.setListData(vb);
		}
		else if(sac.equals("Record"))
		{
			jleb.setVisible(false);
			jadd.setVisible(false);
			jfa.setVisible(false);
			jspb.setVisible(false);
			jsta.setVisible(true);
			
		
			String data="";
			try
			{
			 //int n=0;
			 
			 /*FileInputStream fis=new FileInputStream("Record.txt");
			 
			 while(n!=-1)
			 {
				 n=fis.read();
				 if(n!=-1)
				 {
					 data=data+(char)n;
				 }
			 }
			 fis.close();*/
			 String line=" ";
			 FileReader fr=new FileReader("Record.txt");
			 BufferedReader br=new BufferedReader(fr);
			 
			 while(line!=null)
			 {
				 line=br.readLine();
				 if(line!=null)
				 {
					 data=data+line+"\n";
				 }
			 }
			 br.close();
			 fr.close();
			 //System.out.print(data);
			
			}
			catch(Exception ex) {}
			 jep.setText(data);
		}
		else if(sac.equals("Back"))
		{
			jleb.setVisible(false);
			jadd.setVisible(false);
			jfa.setVisible(false);
			jspb.setVisible(false);
			jsta.setVisible(false);
			setVisible(false);
		}
		
	}
}

class ForStudent extends JFrame implements ActionListener,ListSelectionListener
{
	JPanel jpst,jpsb;
	JLabel jlsn,jlss,jlname,jlprn,jlbname,jlmob,jlwar;
	JButton jbsi,jbsu,jsback,jbladd,jblcan,jbliss;
	JTextField jtname,jtprn,jtbname,jtmob;
	JList jls;
	JScrollPane jss;
	Vector<String> vb=new Vector<String>();
	Font f,fb;
	Color ct,cb;
	
	ForStudent(String s)
	{
		super(s);
		setLayout(null);
		
		jpst=new JPanel();
		jpsb=new JPanel();
		jlsn=new JLabel("MS Library");
		jlss=new JLabel("For Student");
		jbsi=new JButton("Issue Book");
		jbsu=new JButton("Unissue Book");
		jsback=new JButton("Back");
		jlname=new JLabel("Enter Your Name:");
		jlprn=new JLabel("Enter PRN No. :");
		jlbname=new JLabel("Book Name:");
		jbladd=new JButton("Add");
		jbliss=new JButton("Issue");
		jblcan=new JButton("Cancel");
		jlmob=new JLabel("Enter Mobile No.:");
		jlwar=new JLabel("");
		jtmob=new JTextField();
		jtname=new JTextField();
		jtprn=new JTextField();
		jtbname=new JTextField();
		
		ct=new Color(217,217,217);
		cb=new Color(0,184,230);
		
		try
		{
			FileReader fr=new FileReader("BookList.txt");
			BufferedReader br=new BufferedReader(fr);
			
			String line=" ";
			
			while(line!=null)
			{
				line=br.readLine();
				if(line!=null)
				{
					vb.add(line);
				}
			}
			fr.close();
		}
		catch(Exception e) {}
		
		Collections.sort(vb);
		
		
		
		jls=new JList(vb);
		jls.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jss=new JScrollPane(jls);
		jss.setVisible(false);
		
		f=new Font("Arial",Font.BOLD,30);
		jlsn.setFont(f);
		jlss.setFont(f);
		
		f=new Font("Arial",Font.BOLD,25);
		jls.setFont(f);
		jlname.setFont(f);
		jlprn.setFont(f);
		jlbname.setFont(f);
		jtname.setFont(f);
		jtprn.setFont(f);
		jtbname.setFont(f);
		jlmob.setFont(f);
		jtmob.setFont(f);
		jlwar.setFont(f);
		
		fb=new Font("Dialog",Font.BOLD,20);
		jbsi.setFont(fb);
		jbsu.setFont(fb);
		jsback.setFont(fb);
		jbladd.setFont(fb);
		jblcan.setFont(fb);
		jbliss.setFont(fb);
		
		jpst.setBounds(0,0,1200,150);
		jpsb.setBounds(0,150,1200,550);
		jlsn.setBounds(500,60,500,35);
		jlss.setBounds(0,120,500,30);
		jbsi.setBounds(40,100,200,40);
		jbsu.setBounds(40,200,200,40);
		jss.setBounds(300,0,885,512);
		jsback.setBounds(40,300,200,40);
		jlname.setBounds(400,30,400,40);
		jtname.setBounds(400,70,400,40);
		jlprn.setBounds(400,110,400,40);
		jtprn.setBounds(400,150,400,40);
		jlbname.setBounds(400,190,400,40);
		jtbname.setBounds(400,230,400,40);
		jlmob.setBounds(400,270,400,40);
		jtmob.setBounds(400,310,400,40);
		jbladd.setBounds(430,400,150,40);
		jbliss.setBounds(430,400,150,40);
		jblcan.setBounds(630,400,150,40);
		jlwar.setBounds(400,360,400,40);
		
		jlname.setVisible(false);
		jlprn.setVisible(false);
		jlbname.setVisible(false);
		jlmob.setVisible(false);
		jbladd.setVisible(false);
		jblcan.setVisible(false);
		jtname.setVisible(false);
		jtprn.setVisible(false);
		jtbname.setVisible(false);
		jtmob.setVisible(false);
		jbliss.setVisible(false);
		
		jpst.setLayout(null);
		jpsb.setLayout(null);
		
		jpst.setBackground(ct);
		jpsb.setBackground(cb);
		
		jbsi.addActionListener(this);
		jbsu.addActionListener(this);
		jsback.addActionListener(this);
		jbladd.addActionListener(this);
		jblcan.addActionListener(this);
		jls.addListSelectionListener(this);
		jbliss.addActionListener(this);
		
		jpst.add(jlsn);
		jpst.add(jlss);
		jpsb.add(jbsi);
		jpsb.add(jbsu);
		jpsb.add(jss);
		jpsb.add(jsback);
		jpsb.add(jlname);
		jpsb.add(jtname);
		jpsb.add(jlprn);
		jpsb.add(jtprn);
		jpsb.add(jlbname);
		jpsb.add(jtbname);
		jpsb.add(jlmob);
		jpsb.add(jtmob);
		jpsb.add(jbladd);
		jpsb.add(jblcan);
		jpsb.add(jbliss);
		jpsb.add(jlwar);
		
		add(jpst);
		add(jpsb);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String ssa=e.getActionCommand();
		
		if(ssa.equals("Issue Book"))
		{
			
			jlname.setVisible(false);
			jlprn.setVisible(false);
			jlbname.setVisible(false);
			jlmob.setVisible(false);
			jbladd.setVisible(false);
			jblcan.setVisible(false);
			jtname.setVisible(false);
			jtprn.setVisible(false);
			jtbname.setVisible(false);
			jtmob.setVisible(false);
			jbliss.setVisible(false);
			jss.setVisible(true);
			
			vb.removeAllElements();
			try
			{
				FileReader fr=new FileReader("BookList.txt");
				BufferedReader br=new BufferedReader(fr);
				
				String line=" ";
				
				while(line!=null)
				{
					line=br.readLine();
					if(line!=null)
					{
						vb.add(line);
					}
				}
				fr.close();
			}
			catch(Exception ex) {}
			
			Collections.sort(vb);
			jls.setListData(vb);
		}
		else if(ssa.equals("Unissue Book"))
		{
			jtname.setText("");
			jtprn.setText("");
			jtbname.setText("");
			jss.setVisible(false);
			jlname.setVisible(true);
			jlprn.setVisible(true);
			jlbname.setVisible(true);
			jlmob.setVisible(false);
			jbladd.setVisible(true);
			jblcan.setVisible(true);
			jtname.setVisible(true);
			jtprn.setVisible(true);
			jtbname.setVisible(true);
			jbliss.setVisible(false);
			jtmob.setVisible(false);
			
		}
		else if(ssa.equals("Issue"))
		{
			String sname,sprn,sbname,smob;
			String data="";
			int index=jls.getSelectedIndex();
			
			sname=jtname.getText();
			data=data+"Name:"+sname+"\n";
			sprn=jtprn.getText();
			data=data+"PRN No.:"+sprn+"\n";
			sbname=jtbname.getText();
			data=data+"Book Name:"+sbname+"\n";
			smob=jtmob.getText();
			data=data+"Mobile No.:"+smob+"\n";
			data=data+sname+" has taken the book.\n\n";
			
			if(sname.equals("") || sprn.equals("") || sbname.equals("") || smob.equals(""))
			{
				if(sname.equals(""))
				{
					jlwar.setText("Please Enter Name ");
				}
				else if(sprn.equals(""))
				{
					jlwar.setText("Please Enter PRN No.");
				}
				else if(sbname.equals(""))
				{
					jlwar.setText("Please Enter Book Name ");
				}
				else if(smob.equals(""))
				{
					jlwar.setText("Please Enter Mobile No. ");
				}
			}
			else
			{
				vb.remove(index);
				jls.setListData(vb);
				
				jlname.setVisible(false);
				jlprn.setVisible(false);
				jlbname.setVisible(false);
				jlmob.setVisible(false);
				jbladd.setVisible(false);
				jblcan.setVisible(false);
				jtname.setVisible(false);
				jtprn.setVisible(false);
				jtbname.setVisible(false);
				jtmob.setVisible(false);
				jbliss.setVisible(false);
				jss.setVisible(true);
				
				try
				{
					FileWriter fw=new FileWriter("Record.txt",true);
					fw.write(data);
					
					fw.close();
				}
				catch(Exception ex) {}
				
				try
				{
					String l="";
					FileWriter fw=new FileWriter("BookList.txt");
					Enumeration e1=vb.elements();
					while(e1.hasMoreElements())
					{
						l=l+e1.nextElement()+"\n";
					}
					fw.write(l);
					
					fw.close();
				}
				catch(Exception ex) {}
				
				
				Collections.sort(vb);
				jlwar.setText("");
				jtname.setText("");
				jtprn.setText("");
				jtbname.setText("");
				jtmob.setText("");
			}
		}
		else if(ssa.equals("Add"))
		{
			String sname,sprn,sbname;
			String data="";
			
			sname=jtname.getText();
			data=data+"Name:"+sname+"\n";
			sprn=jtprn.getText();
			data=data+"PRN No.:"+sprn+"\n";
			sbname=jtbname.getText();
			data=data+"Book Name:"+sbname+"\n";
			data=data+sname+" returned the book.\n\n";
			
			if(sname.equals("") || sprn.equals("") || sbname.equals(""))
			{
				if(sname.equals(""))
				{
					jlwar.setText("Please Enter Name ");
				}
				else if(sprn.equals(""))
				{
					jlwar.setText("Please Enter PRN No.");
				}
				else if(sbname.equals(""))
				{
					jlwar.setText("Please Enter Book Name ");
				}
			}
			else
			{
				vb.add(sbname);
				Collections.sort(vb);
				jls.setListData(vb);
				
				jlname.setVisible(false);
				jlprn.setVisible(false);
				jlbname.setVisible(false);
				jlmob.setVisible(false);
				jbladd.setVisible(false);
				jblcan.setVisible(false);
				jtname.setVisible(false);
				jtprn.setVisible(false);
				jtbname.setVisible(false);
				jtmob.setVisible(false);
				jbliss.setVisible(false);
				jss.setVisible(true);
				
				try
				{
					FileWriter fw=new FileWriter("Record.txt",true);
					fw.write(data);
					
					fw.close();
				}
				catch(Exception ex) {}
				
				try
				{
					String l="";
					FileWriter fw=new FileWriter("BookList.txt");
					Enumeration e1=vb.elements();
					while(e1.hasMoreElements())
					{
						l=l+e1.nextElement()+"\n";
					}
					fw.write(l);
					
					fw.close();
				}
				catch(Exception ex) {}
				
				jlwar.setText("");
				jtname.setText("");
				jtprn.setText("");
				jtbname.setText("");
			}
		}
		else if(ssa.equals("Cancel"))
		{
			jss.setVisible(true);
			jtname.setText("");
			jtprn.setText("");
			jtbname.setText("");
			jtmob.setText("");
			jlwar.setText("");
			jlname.setVisible(false);
			jlprn.setVisible(false);
			jlbname.setVisible(false);
			jlmob.setVisible(false);
			jbladd.setVisible(false);
			jblcan.setVisible(false);
			jtname.setVisible(false);
			jtprn.setVisible(false);
			jtbname.setVisible(false);
			jtmob.setVisible(false);
			jbliss.setVisible(false);
		}
		else if(ssa.equals("Back"))
		{
			jlname.setVisible(false);
			jlprn.setVisible(false);
			jlbname.setVisible(false);
			jlmob.setVisible(false);
			jbladd.setVisible(false);
			jblcan.setVisible(false);
			jtname.setVisible(false);
			jtprn.setVisible(false);
			jtbname.setVisible(false);
			jtmob.setVisible(false);
			jbliss.setVisible(false);
			jss.setVisible(false);
			setVisible(false);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int index=jls.getSelectedIndex();
		
		String st=(String)jls.getSelectedValue();
		
		jtbname.setText(st);
		jss.setVisible(false);
		
		jlname.setVisible(true);
		jlprn.setVisible(true);
		jlbname.setVisible(true);
		jlmob.setVisible(true);
		jbladd.setVisible(false);
		jblcan.setVisible(true);
		jtname.setVisible(true);
		jtprn.setVisible(true);
		jtbname.setVisible(true);
		jtmob.setVisible(true);
		jbliss.setVisible(true);
	}
}


class Lib extends JFrame implements ActionListener
{
	JPanel jpt,jpb;
	JLabel jll,jlw,jlp,jwp,jpic;
	JButton jba,jbs,jbl;
	JPasswordField jfp;
	Font f,f1;
	ForAdmin fa;
	ForStudent fs;
	Color ct,cb,cr;
	
	Lib(String s)
	{
		super(s);
		setLayout(null);
		
		jpt=new JPanel();
		jpb=new JPanel();
		
		ct=new Color(217,217,217);
		cb=new Color(0,184,230);
		cr=new Color(255,0,0);
		
		jpt.setBounds(0,0,1200,100);
		jpb.setBounds(0,100,1200,600);
		
		fa=new ForAdmin("ADMIN");
		fa.setSize(1200,700);
		fa.setLocation(10,10);
		
		fs=new ForStudent("Student");
		fs.setSize(1200,700);
		fs.setLocation(10,10);
		
		jpt.setLayout(null);
		jpb.setLayout(null);
		
		f=new Font("Arial",Font.BOLD,30);
		
		jll=new JLabel("MS LIBRARY");
		jlw=new JLabel("-----WELCOME TO LIBRARY-----");
		jba=new JButton("Admin");
		jbs=new JButton("Student");
		jlp=new JLabel("Enter Password : ");
		jfp=new JPasswordField(10);
		jbl=new JButton("Enter");
		jwp=new JLabel("Incorrect Password!!");
		jpic=new JLabel();
		
		jll.setFont(f);
		jlw.setFont(f);
		jba.setFont(f);
		jbs.setFont(f);
		jbl.setFont(f);
		jll.setBounds(490,25,500,30);
		jlw.setBounds(370,55,500,30);
		
		
		jba.setBounds(370,220,160,40);
		jbs.setBounds(570,220,160,40);
		jlp.setBounds(370,300,200,40);
		jfp.setBounds(370,350,200,40);
		jbl.setBounds(390,420,160,40);
		jwp.setBounds(370,385,200,40);
		jpic.setBounds(0,0,200,100);
		
		jpic.setIcon(new ImageIcon("src//raw.jpg"));
		
		f1=new Font("Arial",Font.BOLD,20);
		jlp.setFont(f1);
		jfp.setFont(f1);
		jwp.setFont(f1);
		
		jlp.setVisible(false);
		jfp.setVisible(false);
		jbl.setVisible(false);
		jwp.setVisible(false);
		
		jpt.add(jll);
		jpt.add(jlw);
		
		jpb.add(jba);
		jpb.add(jbs);
		jpb.add(jlp);
		jpb.add(jfp);
		jpb.add(jbl);
		jpb.add(jwp);
		jpt.add(jpic);
		jba.addActionListener(this);
		jbs.addActionListener(this);
		jbl.addActionListener(this);
		jfp.addActionListener(this);
		
		jpt.setBackground(ct);
		jpb.setBackground(cb);
		jwp.setForeground(cr);
		add(jpt);
		add(jpb);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String sa=e.getActionCommand();
		String sp=jfp.getText();
		
		if(sa.equals("Admin"))
		{
			jlp.setVisible(true);
			jfp.setVisible(true);
			jbl.setVisible(true);
		}
		else if(sa.equals("Enter"))
		{
			if(sp.equals("Admin-20"))
			{
				jfp.setText("");
				jwp.setVisible(false);
				fa.setVisible(true);
			}
			else
			{
				jwp.setVisible(true);
			}
		}
		else if(sa.equals("Student"))
		{
			jlp.setVisible(false);
			jfp.setVisible(false);
			jbl.setVisible(false);
			jwp.setVisible(false);
			
			fs.setVisible(true);
		}
	}
}

public class MyLibrary
{
	public static void main(String[] args) 
	{
		Lib l1=new Lib("LIBRARY");
		l1.setVisible(true);
		l1.setSize(1200,700);
		l1.setLocation(10,10);
		
	}
}
