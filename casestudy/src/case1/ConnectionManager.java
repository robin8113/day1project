package case1;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

public class ConnectionManager {
	public void display() throws ClassNotFoundException, SQLException
	{
	Class.forName("com.mysql.jdbc.Driver");  
	Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");  
	
	if (con != null)
	{
		System.out.println("Connected");
	}
	else
	{
		System.out.println("not Connected");
	}
	Statement statement=(Statement) con.createStatement();
	JFrame obj=new  JFrame();
	JButton ad=new JButton("Admin");
	ad.setBounds(150, 50, 100, 30);
	 ad.setBackground(Color.GREEN);
     ad.setContentAreaFilled(false);
     ad.setOpaque(true);
	JButton ag=new JButton("Agent");
	ag.setBounds(150, 100, 100, 30);
	ag.setBackground(Color.blue);
    ag.setContentAreaFilled(false);
    ag.setOpaque(true);
	JButton ex=new JButton("Exit");
	ex.setBounds(150, 150, 100, 30);
	ex.setBackground(Color.red);
    ex.setContentAreaFilled(false);
    ex.setOpaque(true);
	
	ex.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(ex, "Thank you for using our service!!!");
			obj.dispose();
		}
	});
	ag.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFrame obj4=new  JFrame();
			final JLabel aun,apass;
			final JTextField auname;
			final JPasswordField apas;
			aun=new JLabel("Username :");
			aun.setBounds(150, 50, 100, 30);
			auname=new JTextField();
			auname.setBounds(250, 50, 100, 30);
			apass=new JLabel("Password :");
			apass.setBounds(150, 100, 100, 30);
			apas=new JPasswordField();
			apas.setBounds(250, 100, 100, 30);
			JButton aex1=new JButton("Login");
			aex1.setBounds(150, 150, 100, 30);
			aex1.setBackground(Color.GREEN);
			
			aex1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String aunam=auname.getText();
					String apassw=apas.getText();
                  String s="Select username,password from agent where username='"+aunam+"' and password='"+apassw+"'";
					try {
						ResultSet resul=statement.executeQuery(s);
						if(resul.next()==false)
						{
							JOptionPane.showMessageDialog(aex1, "Incorrect password");
							auname.setText(null);
							apas.setText(null);
							
						}
						else
						{

							JOptionPane.showMessageDialog(aex1, " Login Successfull");
							auname.setText(null);
							apas.setText(null);
							JFrame obj5=new  JFrame();
							JButton aadd,adis,aexit;
							aadd=new JButton("Add product") ;
							aadd.setBounds(150, 50, 150, 30);
							adis=new JButton("order History");
							adis.setBounds(150, 100, 150, 30);
							aexit=new JButton("Logout") ;
							aexit.setBounds(150, 150, 150, 30);
							adis.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
                                    JFrame f1=new JFrame("Order History...");
							        
								    
							        String[] columnNames = {"Id","Product","Transcation Type", "qty", "price","Total"};
							        DefaultTableModel model = new DefaultTableModel();
							        model.setColumnIdentifiers(columnNames);
							        //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
							        //table = new JTable(model);
							        JTable table = new JTable();
							        table.setModel(model);
							        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							        table.setFillsViewportHeight(true);
							        JScrollPane scroll = new JScrollPane(table);
							        scroll.setHorizontalScrollBarPolicy(
							        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							        scroll.setVerticalScrollBarPolicy(
							        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
									
									// TODO Auto-generated method stub
									String s="select * from inventory as i,product as p,swing as s where i.id=p.id and s.id= i.uid";
									System.out.println(s);
									 ResultSet rs1 = null;
									 try {
										rs1 = statement.executeQuery(s);
										int i=0;
			                            while(rs1.next())
			                            {
			                               int pid = rs1.getInt("id");
			                                String name = rs1.getString("productname");
			                                String trtype=rs1.getString("option");
			                                int qty = rs1.getInt("quantity");
			                                int price = rs1.getInt("price");
			                                int total = rs1.getInt("total");
			                              
			                                model.addRow(new Object[]{pid, name,trtype, qty, price,total});
			                                i++;
			                                
			                    
			        
			        
			    }
			    if(i <1)
			    {
			    JOptionPane.showMessageDialog(null, "No Record Found","Error",
			    JOptionPane.ERROR_MESSAGE);
			    }
			    if(i ==1)
			    {
			    System.out.println(i+" Record Found");
			    }
			    else
			    {
			    System.out.println(i+" Records Found");
			    }
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									 f1.add(scroll);
									 f1.setVisible(true);
									 f1.setSize(400, 500);
								}
							});
							aadd.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									JFrame f1=new JFrame("Add product");
									JLabel comb=new JLabel("Product Name:");
									comb.setBounds(100, 50, 150, 30);
									JComboBox t1,t2;
									t1=new JComboBox();
									t1.addItem("select Item");
									t1.setBounds(190, 50, 150, 30);
									try {
										ResultSet r=statement.executeQuery("select * from product");
										while(r.next())
										{
											String st=(r.getString("productname"));
											t1.addItem(st);
										}
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									JLabel comb1=new JLabel("Option :");
									comb1.setBounds(100, 100, 150, 30);
									t2=new JComboBox();
									t2.setBounds(190, 100, 150, 30);
									t2.addItem("select");
									t2.addItem("Buy");
									t2.addItem("Sell");
									JLabel comb2=new JLabel("Quantity :");
									comb2.setBounds(100, 150, 150, 30);
									JTextField q=new JTextField();
									q.setBounds(190, 150, 150, 30);
									JLabel comb3=new JLabel("price :");
									comb3.setBounds(100, 200, 150, 30);
									JTextField pr=new JTextField();
									pr.setBounds(190, 200, 150, 30);
									JTextField pid=new JTextField();
									JTextField userid=new JTextField();
									JTextField qnt=new JTextField();
									q.addFocusListener(new FocusListener() {
									
										@Override
										public void focusLost(FocusEvent e) {
											// TODO Auto-generated method stub
											try {
												String p=(String) t1.getSelectedItem();
													ResultSet obj=statement.executeQuery("select * from product where productname='"+p+"'");
													
													//System.out.println();
													while(obj.next())
													{
														int qs=obj.getInt("price");
														pr.setText(""+qs);
														int id=obj.getInt("id");
														pid.setText(""+id);
														int qn=obj.getInt("quantity");
														qnt.setText(""+qn);
													}
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
										}
										
										@Override
										public void focusGained(FocusEvent e) {
											// TODO Auto-generated method stub
											try {
												ResultSet obj1=statement.executeQuery("select * from swing");
												while(obj1.next())
												{
													int id=obj1.getInt("id");
													userid.setText(""+id);
												}
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										}
									});
									
									JLabel comb4=new JLabel("Total Price :");
									comb4.setBounds(100, 250, 150, 30);
									JTextField tp=new JTextField();
									tp.setBounds(190, 250, 150, 30);
									pr.addFocusListener(new FocusListener() {
										
										@Override
										public void focusLost(FocusEvent e) {
											// TODO Auto-generated method stub
											int tot=Integer.parseInt(q.getText())*Integer.parseInt(pr.getText());
											tp.setText(""+tot);
										}
										
										@Override
										public void focusGained(FocusEvent e) {
											// TODO Auto-generated method stub
											
										}
									});
									
									JButton p1=new JButton("Purchase");	
									p1.setBounds(190, 300, 120, 30);
									JButton ext=new JButton("Back");	
									ext.setBounds(350, 300, 80, 30);
									
									
									f1.add(comb4);f1.add(tp);
									f1.add(comb3);f1.add(pr);
									f1.add(comb2);f1.add(q);
									f1.add(comb);f1.add(t1);
									f1.add(t2);f1.add(comb1);
									f1.add(p1);f1.add(ext);
									p1.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent e) {
											// TODO Auto-generated method stub
											
											String st1=(String) t2.getSelectedItem();
											int prid=Integer.parseInt(pid.getText());
											int urid=Integer.parseInt(userid.getText());
											int qnty=Integer.parseInt(q.getText());
											int pri=Integer.parseInt(pr.getText());
											int tpr=Integer.parseInt(tp.getText());
							                 int qnti=Integer.parseInt(qnt.getText());
											
											System.out.println(st1);
											if(st1.equalsIgnoreCase("buy"))
											{
												int quant=qnti-qnty;
											
											if(quant<=0)
											{
												JOptionPane.showMessageDialog(p1, "Quantity not available");
											}
											else
											{
												String s="insert into inventory (pid,uid,option,total)  values('"+prid+"','"+urid+"','"+st1+"','"+tpr+"')";
												System.out.println(s);
												try {
													statement.executeUpdate(s);
													JOptionPane.showMessageDialog(p1, "purchase completed");
													statement.executeUpdate("update product set quantity=quantity-'"+qnty+"'where pid ='"+prid+"' ");
													t2.setSelectedItem(null);
													t1.setSelectedItem(null);
													q.setText(null);
													pr.setText(null);
													tp.setText(null);
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
											}
											else if(st1.equalsIgnoreCase("sell"))
											{
												String s="insert into inventory (pid,uid,option,total)  values('"+prid+"','"+urid+"','"+st1+"','"+tpr+"')";
												System.out.println(s);
												try {
													statement.executeUpdate(s);
													JOptionPane.showMessageDialog(p1, "purchase completed");
													statement.executeUpdate("update product set quantity=quantity +'"+qnty+"'where id='"+prid+"' ");
													t2.setSelectedItem(null);
													t1.setSelectedItem(null);
													q.setText(null);
													pr.setText(null);
													tp.setText(null);
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
											
										}
									});
									ext.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent e) {
											// TODO Auto-generated method stub
											obj5.setVisible(true);
											f1.dispose();
										}
									});
									f1.setSize(500, 500);
									f1.setLayout(null);
									f1.setVisible(true);
								}
							});
							aexit.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									obj.setVisible(true);
									obj5.dispose();
								}
							});
							obj5.add(aadd);obj5.add(adis);
							obj5.add(aexit);
							obj5.setSize(500, 500);
							obj5.setLayout(null);
							obj4.dispose();
							obj5.setVisible(true);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			obj4.setContentPane(new JLabel(new ImageIcon("C:\\login.jpg")));
			obj4.add(aun);obj4.add(auname);
			obj4.add(apas);obj4.add(apass);
			obj4.add(aex1);
			obj4.setSize(500, 500);
			obj4.setLayout(null);
			obj4.setVisible(true);
		}
	});
	ad.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFrame obj1=new  JFrame();
			final JLabel un,pass;
			final JTextField uname;
			final JPasswordField pas;
			un=new JLabel("Username :");
			un.setBounds(150, 50, 100, 30);
			uname=new JTextField();
			uname.setBounds(250, 50, 100, 30);
			pass=new JLabel("Password :");
			pass.setBounds(150, 100, 100, 30);
			pas=new JPasswordField();
			pas.setBounds(250, 100, 100, 30);
			JButton ex1=new JButton("Login");
			ex1.setBounds(150, 150, 100, 30);
			ex1.setBackground(Color.YELLOW);
			
			ex1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String unam=uname.getText();
					String passw=pas.getText();
                  String s="Select username,password from swing where username='"+unam+"' and password='"+passw+"'";
					
					ResultSet result;
					try {
						result = statement.executeQuery(s);
						if(result.next()==false)
						{
							JOptionPane.showMessageDialog(ex1, "Incorrect password");
							uname.setText(null);
							pas.setText(null);
							
						}
						else
						{
							JOptionPane.showMessageDialog(ex1, " Login Successfull");
							uname.setText(null);
							pas.setText(null);
							JFrame obj2=new  JFrame();
							JButton add,dis,exit;
							add=new JButton("Add product") ;
							add.setBounds(150, 50, 150, 30);
							dis=new JButton("Display products") ;
							dis.setBounds(150, 100, 150, 30);
							exit=new JButton("Exit") ;
							exit.setBounds(150, 150, 150, 30);
							dis.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									
									JFrame f=new JFrame("Purchase Details...");
							        
								    
							        String[] columnNames = {"Id", "Agent Name","Product","Transcation Type", "qty", "price","Total"};
							        DefaultTableModel model = new DefaultTableModel();
							        model.setColumnIdentifiers(columnNames);
							        //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
							        //table = new JTable(model);
							        JTable table = new JTable();
							        table.setModel(model);
							        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							        table.setFillsViewportHeight(true);
							        JScrollPane scroll = new JScrollPane(table);
							        scroll.setHorizontalScrollBarPolicy(
							        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							        scroll.setVerticalScrollBarPolicy(
							        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
									
									// TODO Auto-generated method stub
									String s="select * from inventory as i,product as p,swing as s where i.id=p.id and s.id= i.uid";
									System.out.println(s);
									 ResultSet rs1 = null;
									 try {
										rs1 = statement.executeQuery(s);
										int i=0;
			                            while(rs1.next())
			                            {
			                               int pid = rs1.getInt("id");
			                               String uname=rs1.getString("username");
			                                String name = rs1.getString("productname");
			                                String trtype=rs1.getString("option");
			                                int qty = rs1.getInt("quantity");
			                                int price = rs1.getInt("price");
			                                int total = rs1.getInt("total");
			                              
			                                model.addRow(new Object[]{pid, uname,name,trtype, qty, price,total});
			                                i++;
			                                
			                    
			        
			        
			    }
			    if(i <1)
			    {
			    JOptionPane.showMessageDialog(null, "No Record Found","Error",
			    JOptionPane.ERROR_MESSAGE);
			    }
			    if(i ==1)
			    {
			    System.out.println(i+" Record Found");
			    }
			    else
			    {
			    System.out.println(i+" Records Found");
			    }
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									 f.add(scroll);
									 f.setVisible(true);
									 f.setSize(400, 500);
								}
							});
							exit.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									obj.setVisible(true);
									obj2.dispose();
								}
							});
							add.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									JFrame obj3=new  JFrame();
									final JLabel pid,pname,quant;
									final JTextField npid,npname,nquant;
									
									pname=new JLabel("ProductName:");
									pname.setBounds(150, 100, 100, 30);
									npname=new JTextField();
									npname.setBounds(250, 100, 100, 30);
									pid=new JLabel("product price :");
									pid.setBounds(150, 50, 100, 30);
									npid=new JTextField();
									npid.setBounds(250, 50, 100, 30);
									quant=new JLabel("Quantity :");
									quant.setBounds(150, 150, 100, 30);
									nquant=new JTextField();
									nquant.setBounds(250, 150, 100, 30);
									
									JButton add1=new JButton("ADD");
									add1.setBounds(150, 350, 100, 30);
									
									JButton add2=new JButton("Back");
									add2.setBounds(350, 350, 100, 30);
									add2.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent e) {
											// TODO Auto-generated method stub
											obj2.setVisible(true);
											obj3.dispose();
										}
									});
									
									add1.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent e) {
											// TODO Auto-generated method stub
											int pd=Integer.parseInt(npid.getText());
											String pnam=npname.getText();
											int qn=Integer.parseInt(nquant.getText());
											try {
												String s="insert into product (price,productname,quantity)  values('"+pd+"','"+pnam+"','"+qn+"')";
												System.out.println(s);
												statement.executeUpdate(s);
												
												JOptionPane.showMessageDialog(add1, "Inserted Successfully");
												npid.setText(null);
												npname.setText(null);
												nquant.setText(null);
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										}
									});
									obj3.add(pid);obj3.add(npid);
									obj3.add(pname);obj3.add(npname);
									obj3.add(quant);obj3.add(nquant);
									obj3.add(add1);obj3.add(add2);
									obj3.setSize(500, 500);
									obj3.setLayout(null);
									obj3.setVisible(true);
									
								}
							});
							obj2.add(add);obj2.add(dis);obj2.add(exit);
							obj2.setSize(500, 500);
							obj2.setLayout(null);
							obj2.setVisible(true);
							obj1.dispose();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			obj1.setContentPane(new JLabel(new ImageIcon("C:\\login.jpg")));
			
			obj1.add(un);obj1.add(uname);obj1.add(pass);obj1.add(pas);obj1.add(ex1);
			obj1.setSize(500, 500);
			obj1.setLayout(null);
			obj1.setVisible(true);
			obj.dispose();
		}
	});
	obj.setContentPane(new JLabel(new ImageIcon("C:\\icon.jpg")));
	obj.setSize(500, 500);
	obj.add(ad);obj.add(ag);obj.add(ex);
	
	obj.setLayout(null);
	obj.setVisible(true);
	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
