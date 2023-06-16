package tech.leonam.view;

import tech.leonam.entidade.Dolar;
import tech.leonam.entidade.Euro;
import tech.leonam.entidade.Moeda;
import tech.leonam.entidade.Real;
import tech.leonam.util.Cofrinho;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal {
    private JTabbedPane abas;
    private JPanel root;
    private JPanel removerMoedaTab;
    private JPanel adicionarMoedaTab;
    private JPanel listarMoedasTab;
    private JRadioButton realRadioButton;
    private JRadioButton dolarRadioButton;
    private JRadioButton euroRadioButton;
    private JTextField valorTxt;
    private JButton adicionarButton;
    private JTable tabela;
    private JTextField idARemover;
    private JButton removerBotao;
    private JTable tabelaListar;
    private JLabel total;
    private final ButtonGroup group;
    private final Cofrinho cofre = new Cofrinho();
    private Moeda moeda = null;
    private DefaultTableModel tabelaMoedas;

    public JPanel getRoot() {
        return root;
    }

    public Principal() {

        //Cria um grupo de botoes, assim quando selecionar qualquer um, os outros são automaticamente desmarcados.
        group = new ButtonGroup();
        group.add(realRadioButton);
        group.add(dolarRadioButton);
        group.add(euroRadioButton);

        //Caso clique no botão de adicionar
        //"e" é uma função lambda, já que o addActionListener implementa somente 1 método.
        adicionarButton.addActionListener(e -> {
            //Lógica do Botão
            try {
                if (realRadioButton.isSelected()) {
                    moeda = new Real();
                } else if (dolarRadioButton.isSelected()) {
                    moeda = new Dolar();
                } else if (euroRadioButton.isSelected()) {
                    moeda = new Euro();
                }

                if (moeda != null) {
                    double valor = Double.parseDouble(valorTxt.getText()); //Pega de String para Double

                    if (moeda.moedaExiste(valor)) {//Se a moeda exister então insira no cofrinho
                        moeda.setValor(valor);
                        cofre.adicionarMoeda(moeda);
                        JOptionPane.showMessageDialog(getRoot(), "Adicionado com Sucesso", "Sucesso Total", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(getRoot(), "Essa moeda não existe", "Moeda inexistente", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(getRoot(), "Selecione uma moeda", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ignored) {//Se não der certo
                valorTxt.setText("");
                JOptionPane.showMessageDialog(getRoot(), "Verifique o que você inseriu no campo de valor.", "Falhou miseravelmente", JOptionPane.ERROR_MESSAGE);
            }
        });
        //Evento do botão de remover
        removerBotao.addActionListener(e -> {
            try {
                int posicao = Integer.parseInt(idARemover.getText());
                cofre.removerMoeda(posicao);
                createTableRemoverMoeda();
                atualizarTabelaRemoverMoeda();
                JOptionPane.showMessageDialog(getRoot(),"Removido com sucesso","Sucesso total",JOptionPane.INFORMATION_MESSAGE);
            }catch (IndexOutOfBoundsException exception){
                JOptionPane.showMessageDialog(getRoot(),"Insira uma posição válida","Falhou miseravelmente",JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(getRoot(),"Verifique o que digitou no campo de ID","Falhou miseravelmente",JOptionPane.ERROR_MESSAGE);
            }
        });
        abas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                createTableRemoverMoeda();
                atualizarTabelaRemoverMoeda();

                createTableListarMoeda();
                atualizarTabelaListagem();
            }
        });
    }
    /*
     * A lógica é a seguinte:
     * 1 - Insira a posição na primeira posição da linha.
     * 2 - Pegue a pegue a moeda da lista conforme a posição do iterador e escreva nome da classe.
     * 3 - Pegue a moeda conforme a posição do iterador e escreva o valor na ultima posição da linha.
     * */
    private void atualizarTabelaRemoverMoeda() {
        tabelaMoedas.setRowCount(0);//Serve para a tabela não ficar com 1 espaço.

        //Percorrendo arrayList.
        for (var i = 0; i < cofre.listagemMoedas().size();i++) {
            tabelaMoedas.addRow(new String[]{String.valueOf(i),
                    cofre.listagemMoedas().get(i).getClass().getSimpleName(),
                    cofre.listagemMoedas().get(i).info()});
        }
    }
    private void atualizarTabelaListagem() {
        tabelaMoedas.setRowCount(0);//Serve para a tabela não ficar com 1 espaço.
        var qntdConvertida = 0.0;
        //Percorrendo arrayList.
        for (var i = 0; i < cofre.listagemMoedas().size();i++) {
            tabelaMoedas.addRow(new String[]{cofre.listagemMoedas().get(i).getClass().getSimpleName(),
                    cofre.listagemMoedas().get(i).info(),
                    String.valueOf(cofre.listagemMoedas().get(i).converter())
            });
            qntdConvertida += cofre.listagemMoedas().get(i).converter();
        }
        total.setText(String.format("R$%.2f",qntdConvertida));
    }
    private void createTableRemoverMoeda() {
        Object[][] data = {{}};
        tabela.setModel(new DefaultTableModel(data, new String[]{
                "Posição",
                "Moeda",
                "Valor"}));
        tabelaMoedas = (DefaultTableModel) tabela.getModel();
    }
    private void createTableListarMoeda() {
        Object[][] data = {{}};
        tabelaListar.setModel(new DefaultTableModel(data, new String[]{
                "Moeda",
                "Valor Original",
                "Valor em Real",
        }));
        tabelaMoedas = (DefaultTableModel) tabelaListar.getModel();
    }
    public static void main(String[] args) {
        try {//Tenta
            //Pega Tema padrão do sistema.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {//Se não der certo, então tente
            e.printStackTrace();
        }

        var intancia = new Principal();

        var painelRaiz = intancia.getRoot();
        var frame = new JFrame("Cofrinho");//Instancia um novo frame
        frame.setContentPane(painelRaiz);//Seta qual o painel raiz
        frame.pack();//Faz ficar no menor tamanho de tela possível
        frame.setSize(600, 400);//Dimensões da tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Seta qual o padrão caso clique no botão de fechar
        frame.setLocationRelativeTo(null);//Faz centralizar o frame
        frame.setVisible(true);//Faz o frame ficar visivel
    }
}
