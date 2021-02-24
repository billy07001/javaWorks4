import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionEvent;

public class Aquarium extends JFrame {

	private JPanel contentPane;
	private Graphics g;
	int fishcount;


	public Aquarium() {
		super("Fish Bowl");
		
		//建立視窗
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Bowl bowl = new Bowl();
        contentPane.add(bowl);
		
		JPanel buttonpanel = new JPanel();
		contentPane.add(buttonpanel, BorderLayout.NORTH);
		buttonpanel.setLayout(new GridLayout(0, 2));
		
		JButton newfish = new JButton("\u65B0\u589E\u9B5A");		
		buttonpanel.add(newfish);
		
		JButton rmSelect = new JButton("\u79FB\u9664\u9078\u53D6");
		buttonpanel.add(rmSelect);
		
		JButton newturtle = new JButton("\u65B0\u589E\u70CF\u9F9C");
		buttonpanel.add(newturtle);
		
		JButton rmAll = new JButton("\u79FB\u9664\u5168\u90E8");
		buttonpanel.add(rmAll);
		
		JPanel status = new JPanel();
		buttonpanel.add(status);
		GridBagLayout gbl_status = new GridBagLayout();
		gbl_status.columnWidths = new int[]{97, 69, 69, 0};
		gbl_status.rowHeights = new int[]{23, 0};
		gbl_status.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_status.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		status.setLayout(gbl_status);
		
		JLabel current = new JLabel("目前功能 : 新增魚  ");
		GridBagConstraints gbc_current = new GridBagConstraints();
		gbc_current.anchor = GridBagConstraints.NORTHWEST;
		gbc_current.insets = new Insets(0, 0, 0, 5);
		gbc_current.gridx = 0;
		gbc_current.gridy = 0;
		status.add(current, gbc_current);
		
		JLabel amount = bowl.getLabel();
		GridBagConstraints gbc_amount = new GridBagConstraints();
		gbc_amount.gridwidth = 2;
		gbc_amount.anchor = GridBagConstraints.NORTHWEST;
		gbc_amount.gridx = 1;
		gbc_amount.gridy = 0;
		status.add(amount, gbc_amount);
		
		
		newfish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bowl.setFunction(0);
				current.setText("目前功能 : 新增魚  ");
			}
		});
		
		newturtle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bowl.setFunction(1);
				current.setText("目前功能 : 新增烏龜 ");
			}
		});
		
		rmSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setText("目前功能 : 移除選取 ");
			}
		});
		
		rmAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bowl.setFunction(3);
				current.setText("目前功能 : 移除全部 ");
				bowl.totalGueiGuei = new GueiGuei[1000];
				bowl.totalfish = new Fish[1000];
				bowl.fishcount = 0;
				bowl.GueiGueicount = 0;
				bowl.repaint();
				amount.setText("魚數量: 0 烏龜數量: 0 ");
			}
		});
		
		

	}
}
class Bowl extends JPanel{
	private int xpos;
	private int ypos;
	private int maxh;
	private int maxw;
	private Image fish1;
	private int FishSize;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	Fish[] totalfish = new Fish[1000];
	private fishthread[] fishThread = new fishthread[1000];
	GueiGuei[] totalGueiGuei = new GueiGuei[1000];
	private GueiGueithread[] GueiGueiThread = new GueiGueithread[1000];
	
	JLabel current = new JLabel("魚數量: 0 烏龜數量: 0 ");;
	
	int fishcount;
	int GueiGueicount;
	private int function;
	String info ;

	public void setFunction(int function) {
		this.function = function;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	
	
	public Bowl() {
		setBackground(Color.CYAN);
        addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				xpos = e.getX();
				ypos = e.getY();
				
				if (function == 0) {
				totalfish[fishcount] = new Fish(xpos,ypos);
				fishThread[fishcount] = new fishthread(totalfish[fishcount],xpos,ypos);
				executorService.execute(fishThread[fishcount]);
				fishcount++;
				info = String.format("魚數量: %d 烏龜數量: %d " , fishcount, GueiGueicount);
				current.setText(info);
				}
				
				else if (function == 1) {
					totalGueiGuei[GueiGueicount] = new GueiGuei(xpos,ypos);
					GueiGueiThread[GueiGueicount] = new GueiGueithread(totalGueiGuei[GueiGueicount],xpos,ypos);
					executorService.execute(GueiGueiThread[GueiGueicount]);
					GueiGueicount++;
					info = String.format("魚數量: %d 烏龜數量: %d " , fishcount, GueiGueicount);
					current.setText(info);
				}
				}
	});
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (totalfish != null) {
		for (int i = 0 ; i < fishcount ; i++) {
			totalfish[i].draw(g);
		}
		}
		if (totalGueiGuei != null) {
			for (int j = 0 ; j < GueiGueicount ; j++) {
				totalGueiGuei[j].draw(g);
			}
		}
		repaint();
	}
	
	public JLabel getLabel() {
		return current;
	}
}
