import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextPane;
import javax.swing.JList;

import FrmModelo.FrmCadastroPadrao01.StatusCadastro;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class CadastroProd extends JFrame {

	private JPanel panel; 
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JButton btnFechar;
	private JPanel contentPane;
	private JTextField textCod;
	private JTextField textProd;
	private JTextField textQuant;
	private JTextField textPreço;
	private JComboBox cBoxCampo;
	private JLabel lblQuantidade;
	private JLabel lblPreco;
	private JLabel lblProduto;
	private JLabel lblCadastroProduto;
	private JLabel lblCodigo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private StatusCadastro sStatus;
	private enum StatusCadastro{
		scInserindo,
		scNavegando,
		scEditando,
		scVisualizando
	}

	public CadastroProd() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				sStatus = StatusCadastro.scNavegando;
				HabilitaDesabilitaBotoes();	
				HabilitaDesabilitaComponentes(false);

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCadastroProduto = new JLabel("Cadastro Produto");
		lblCadastroProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCadastroProduto.setForeground(new Color(0, 0, 0));
		lblCadastroProduto.setBounds(214, 11, 143, 20);
		contentPane.add(lblCadastroProduto);

		panel = new JPanel();
		panel.setBounds(10, 42, 533, 346);
		contentPane.add(panel);
		panel.setLayout(null);

		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(10, 11, 46, 14);
		panel.add(lblCodigo);

		textCod = new JTextField();
		textCod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if((car<'a' || car>'z')&&(car<'A' || car>'Z') &&
						(car !=(char)KeyEvent.VK_BACK_SPACE)&&(car !=(char)KeyEvent.VK_SPACE)){
					evt.consume();
					JOptionPane.showMessageDialog(null, "Só aceita letras", "Digitar letras", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		textCod.setBounds(91, 8, 432, 20);
		panel.add(textCod);
		textCod.setColumns(10);

		lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(10, 48, 71, 14);
		panel.add(lblProduto);

		textProd = new JTextField();
		textProd.setColumns(10);
		textProd.setBounds(91, 45, 432, 20);
		panel.add(textProd);

		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(10, 84, 78, 14);
		panel.add(lblQuantidade);

		textQuant = new JTextField();
		textQuant.setBounds(91, 81, 432, 20);
		panel.add(textQuant);
		textQuant.setColumns(10);

		lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setBounds(10, 115, 46, 14);
		panel.add(lblPreco);

		textPreço = new JTextField();
		textPreço.setColumns(10);
		textPreço.setBounds(91, 112, 432, 20);
		panel.add(textPreço);

		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(10, 144, 78, 14);
		panel.add(lblFornecedor);

		cBoxCampo = new JComboBox();
		cBoxCampo.setBounds(91, 143, 432, 20);
		panel.add(cBoxCampo);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					if (BotaoNovo())
					{
						sStatus = StatusCadastro.scInserindo;
						HabilitaDesabilitaBotoes();
						HabilitaDesabilitaComponentes(true);
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}


			}

		});
		btnNovo.setBounds(10, 208, 89, 23);
		panel.add(btnNovo);

		JButton btnEditar = new JButton("Editar\r\n");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					sStatus = StatusCadastro.scEditando;
					HabilitaDesabilitaBotoes();
					HabilitaDesabilitaComponentes(true);
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}				
			}
		});
		btnEditar.setBounds(109, 208, 89, 23);
		panel.add(btnEditar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(BotaoSalvar())
					{
						sStatus = StatusCadastro.scNavegando;
						HabilitaDesabilitaBotoes();
						HabilitaDesabilitaComponentes(false);
						JOptionPane.showMessageDialog(null,"Registro salvo com sucesso");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"O registro não foi salvo, por favor verifique os erros!");
					}                
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}				

			}


		});
		btnSalvar.setBounds(208, 208, 89, 23);
		panel.add(btnSalvar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int temp = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?.", "Alerta do Sistema", JOptionPane.YES_NO_OPTION);

					if (temp == JOptionPane.YES_OPTION)
					{
						if (BotaoExcluir())
						{
							sStatus = StatusCadastro.scNavegando;
							HabilitaDesabilitaBotoes();
							HabilitaDesabilitaComponentes(false);
							JOptionPane.showMessageDialog(null,"Registro excluído com sucesso");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"O registro não foi excluído, por favor verifique os erros!");
						}
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnExcluir.setBounds(307, 208, 89, 23);
		panel.add(btnExcluir);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(406, 208, 89, 23);
		panel.add(btnCancelar);

		JList list = new JList();
		list.setBounds(10, 242, 513, 104);
		panel.add(list);

	}
	private void HabilitaDesabilitaComponentes(boolean condicao) {
		for (Component comp : contentPane.getComponents()) {

			comp.setEnabled(condicao);

			if ((comp.getClass().getSimpleName().equals("JTextField")) && (!condicao) ) {
				((JTextField) comp).setText("");
			}

			if ((comp.getClass().getSimpleName().equals("JComboBox")) && (!condicao) ) {
				((JComboBox) comp).setSelectedIndex(-1);
			}
		}

	}
	private void HabilitaDesabilitaBotoes() {
		btnNovo.setEnabled(sStatus == StatusCadastro.scNavegando ||
				sStatus == StatusCadastro.scVisualizando);

		btnSalvar.setEnabled(sStatus == StatusCadastro.scEditando ||
				sStatus == StatusCadastro.scInserindo);

		btnEditar.setEnabled(sStatus == StatusCadastro.scVisualizando);

		btnCancelar.setEnabled(sStatus == StatusCadastro.scEditando ||
				sStatus == StatusCadastro.scInserindo);

		btnExcluir.setEnabled(sStatus == StatusCadastro.scVisualizando);

		btnFechar.setEnabled(true);	

	}
	protected abstract boolean BotaoExcluir();
	protected abstract boolean BotaoSalvar();
	protected abstract boolean BotaoNovo();
}
