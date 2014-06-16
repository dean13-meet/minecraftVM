package minecraft;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.sun.jdi.Field;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.Location;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.Value;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.event.EventSet;

import exceptions.alreadyConnectedToVM;
import exceptions.breakPointNotHitException;

public class initialDisplay extends Display {
	private final JLabel lblRunningThreads = new JLabel("Running Threads");
	private final JTree tree = new JTree();
	//private TreeModel tempTree = null; //Holds the tree temporarily while displaying search info
	private boolean realThreads = true;
	private final Button refreshButton = new Button("refreshButton", new refreshThreadCommand(this), new String[] {"Refresh"},width/2-50, 625, 100, 50);
	private final Button expandAllButton = new Button("expandAllButton", new expandAllCommand(this), new String[]{"Expand All"}, width/2-50, 680, 100, 50);
	private final Button searchButton = new Button("searchButton", new searchButtonCommand(this), new String[]{"Search"}, width/3 + 175, height/8 -25,100,50);

	private final JLabel lblSearchBox = new JLabel("Search: ");
	private JTextField searchBox;
	private String prevText = "";//Used to check for change in search text

	public initialDisplay(int w, int h, JFrame f, GUI program) {
		super(w, h, f, program);
		init();
	}

	@Override
	protected void init() {

		try {
			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("outputMainDetailed.txt")),true));
			System.setErr(new PrintStream(new BufferedOutputStream(new FileOutputStream("outputMainDetailed.txt")),true));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		add(lblRunningThreads);
		lblRunningThreads.setBounds(30, 50, 200, 30);
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setBounds(30, 100, (int)(this.width/1.2), 500);
		add(treeView);
		tree.getSelectionModel().setSelectionMode
		(TreeSelectionModel.SINGLE_TREE_SELECTION);
		add(refreshButton);
		add(expandAllButton);
		add(searchButton);


		lblSearchBox.setBounds(width/3-50, height/8 -25, 100, 25);
		add(lblSearchBox);
		lblSearchBox.setVisible(true);

		searchBox = new JTextField("");
		searchBox.setBounds(width/3, height/8 -25, 150, 25);
		add(searchBox);
		searchBox.setVisible(true);


		refreshThreads();
		repaint();


	}

	@Override
	protected void paintComponent(Graphics g) {


		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		repaint();

	}

	public void refreshSearch() {
		
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.collapseRow(i);
		}
		if(searchBox.getText().length()==0){tree.expandRow(0);return;}
		DefaultMutableTreeNode node_1 = (DefaultMutableTreeNode) tree.getModel().getRoot();
		ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
		Enumeration enumerator = node_1.breadthFirstEnumeration();
		while(enumerator.hasMoreElements()){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumerator.nextElement();
			if(node.getUserObject().toString().contains(searchBox.getText())){
				nodes.add(node);
				System.out.println(searchBox.getText() + " " + node.getUserObject());
			}
		}
		for(DefaultMutableTreeNode node : nodes){
			TreeNode[] path = node.getPath();
			TreePath pathA = new TreePath(path);
			tree.scrollPathToVisible(pathA);
		}


	}


	public JTree getTree(){
		return tree;
	}
	
	public boolean checkString(String str){
		String[] strings = new String[]{"java", "sun", "apache", "joptsimple", "apple", "char", "boolean", "byte", "long", "int", "short", "float", "double","google", "netty", "opengl"};
		for(String s : strings){
			if(str.contains(s))return false;
		}
		return true;
	}
	public void refreshThreads() {
		try {
			connectToMC.createConnection(Main.getPort());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalConnectorArgumentsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (alreadyConnectedToVM e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(realThreads ){
			EventSet evtSet = null;
			try {
				evtSet = connectToMC.waitUntilBreakPointIsReached(connectToMC.breakPointInThread(null));
				connectToMC.analyzed = new ArrayList<Value>();

				tree.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Minecraft") {
							DefaultMutableTreeNode node_0 = new DefaultMutableTreeNode ("Threads");
							DefaultMutableTreeNode node_00 = new DefaultMutableTreeNode ("Loaded Classes");
							{for(ThreadReference thread : connectToMC.getVM().allThreads()){


								DefaultMutableTreeNode node_1;
								node_1 = new DefaultMutableTreeNode(thread.name());
								try{
									for(StackFrame f : thread.frames()){

										DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode(f.location().toString());
										try{
											for(LocalVariable v : f.visibleVariables()){
												if(f.getValue(v) instanceof ObjectReference){
													System.out.println("IS OBJECT REF: " + v);
													DefaultMutableTreeNode node_3 = new DefaultMutableTreeNode(v.toString());
													DefaultMutableTreeNode node_4 = connectToMC.getTreeOfAnyValue(f.getValue(v));
													node_3.add(node_4);
													node_2.add(node_3);
													System.out.println("IS NODE: " + node_2);
												}
												else{
													DefaultMutableTreeNode node_3 = new DefaultMutableTreeNode(v.toString());
													node_2.add(node_3);}
											}
											if(node_2.isLeaf())node_2.add(new DefaultMutableTreeNode("(empty)"));
										}catch(Exception e){System.out.println("Can't access frame:" +f);e.printStackTrace();}
										node_1.add(node_2);
									}
								}catch (Exception e){
									e.printStackTrace();
								}
								node_0.add(node_1);
							}

							}
							
							{for(ReferenceType n : connectToMC.getVM().allClasses()){
								//System.out.println(n + ": " + n.fields() ); if(true)continue;
								//if(!(n.name().contains("mojang")||n.name().contains("minecraft")))continue;
								try{DefaultMutableTreeNode node_1 = new DefaultMutableTreeNode(n.toString());
								
								if(checkString(n.name())){
									System.out.println("REF: " + node_1.getUserObject());
								
								for(ObjectReference nn : n.instances(0)){
									
									DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode(nn.toString());
									System.out.println("OBJ: " + node_2.getUserObject());
									try{
									Map<Field,Value> nnn = nn.getValues(n.allFields());
									
										try{
										DefaultMutableTreeNode node_3 = connectToMC.getTreeOfField(nnn);
										//System.out.println(node_3.getUserObject());
										node_2.add(node_3);
										}catch(Exception e){e.printStackTrace();}
										
									
									}catch(Exception e){e.printStackTrace();}
									node_1.add(node_2);
								}}
								node_00.add(node_1);
							}catch(IllegalArgumentException e){
								e.printStackTrace();
							}catch(UnsupportedOperationException e){
								e.printStackTrace();
							}
							
							}add(node_0);
							add(node_00);
							}
							
						}
						));
			}
			catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (breakPointNotHitException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IncompatibleThreadStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				if(evtSet!=null)evtSet.resume();
			}
		}
		else{
			tree.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("Threads") {
						{

							Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
							for(Thread thread : threads.keySet()){
								DefaultMutableTreeNode node_1;
								node_1 = new DefaultMutableTreeNode(thread.getName());
								try{
									for(StackTraceElement t : threads.get(thread)){
										node_1.add(new DefaultMutableTreeNode(t.getMethodName() + ". at: " + t.getClassName() + " , " + t.getLineNumber()));
									}
								}catch (Exception e){
									e.printStackTrace();
								}
								add(node_1);
							}

						}
					}
					));
		}
		connectToMC.releaseConnection();
	}



}
