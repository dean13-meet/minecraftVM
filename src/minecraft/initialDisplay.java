package minecraft;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.Location;
import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.event.EventSet;

import exceptions.alreadyConnectedToVM;
import exceptions.breakPointNotHitException;

public class initialDisplay extends Display {
	private final JLabel lblRunningThreads = new JLabel("Running Threads");
	private final JTree tree = new JTree();
	private boolean realThreads = true;
	private final Button refreshButton = new Button("refreshButton", new refreshThreadCommand(this), new String[] {"Refresh Threads"},130, 650, 100, 50);

	public initialDisplay(int w, int h, JFrame f, GUI program) {
		super(w, h, f, program);
		setPanel();
	}

	@Override
	protected void setPanel() {

		add(lblRunningThreads);
		lblRunningThreads.setBounds(30, 50, 200, 30);
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setBounds(30, 100, 300, 500);
		add(treeView);
		tree.getSelectionModel().setSelectionMode
		(TreeSelectionModel.SINGLE_TREE_SELECTION);
		add(refreshButton);



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
				evtSet = connectToMC.waitUntilBreakPointIsReached(connectToMC.breakPoint(null));
			
			tree.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("Threads") {
						{for(ThreadReference thread : connectToMC.getVM().allThreads()){
								
								
								DefaultMutableTreeNode node_1;
								node_1 = new DefaultMutableTreeNode(thread.name());
								try{
									for(StackFrame f : thread.frames()){
										node_1.add(new DefaultMutableTreeNode(f.location().toString()));
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
