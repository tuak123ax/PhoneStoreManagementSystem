import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

public class QuanLyDienThoai {
    static Vector<HangDienThoai>listHang=new Vector<>();
    static Vector<DienThoai>listSp=new Vector<>();
    static Vector<DonHang>listDonHang=new Vector<>();
    static SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    static String today=dateFormat.format(new Date());
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        if(FileSize("DuLieu.txt")!=0)
        {
            listHang=DocFile(listHang,"DuLieu.txt");
        }
        if(FileSize("DuLieuSp.txt")!=0)
        {
            listSp=DocFile2(listSp,"DuLieuSp.txt");
        }
        if(FileSize("DuLieuDonHang.txt")!=0)
        {
            listDonHang=DocFileDonHang(listDonHang,"DuLieuDonHang.txt");
        }
        UI();
    }

    static void UI()
    {
        JFrame frame=new JFrame();
        frame.setTitle("Quản Lý Điện Thoại");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(660,400);
        BorderLayout borderLayout=new BorderLayout();
        frame.setLayout(borderLayout);
        frame.setResizable(false);

        JPanel northPanel=new JPanel();
        JLabel title=new JLabel("QUẢN LÝ CỬA HÀNG ĐIỆN THOẠI");
        title.setHorizontalAlignment(0);
        title.setForeground(Color.GREEN);
        northPanel.add(title);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.RED,3));

        JPanel centerPanel=new JPanel();
        centerPanel.setLayout(null);
        JButton buttonDM=new JButton("Quản lý danh mục");
        buttonDM.setBounds(10,100,150,70);
        JButton buttonDH=new JButton("Quản lý đơn hàng");
        buttonDH.setBounds(250,100,150,70);
        JButton buttonTK=new JButton("Thống kê báo cáo");
        buttonTK.setBounds(490,100,150,70);
        centerPanel.add(buttonDM);
        centerPanel.add(buttonDH);
        centerPanel.add(buttonTK);

        JLabel date=new JLabel("Hôm nay là: "+today);
        date.setBounds(250,20,200,50);
        centerPanel.add(date);

        buttonDM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==buttonDM)
                {
                    JFrame frame1=new JFrame();
                    frame1.setTitle("Quản lý danh mục");
                    frame1.setVisible(true);
                    frame1.setSize(600,200);
                    frame1.setLayout(null);
                    frame1.setResizable(false);

                    JButton dsHang=new JButton("Danh sách hãng điện thoại");
                    dsHang.setBounds(10,50,250,70);
                    frame1.add(dsHang);

                    dsHang.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==dsHang)
                            {
                                frame1.dispose();
                                JFrame listFrame=new JFrame();
                                listFrame.setTitle("Danh sách hãng điện thoại");
                                listFrame.setVisible(true);
                                listFrame.setSize(500,500);
                                listFrame.setLayout(null);
                                listFrame.setResizable(false);

                                JLabel label=new JLabel("DANH SÁCH HÃNG ĐIỆN THOẠI");
                                label.setBounds(100,10,300,70);
                                label.setForeground(Color.GREEN);
                                label.setHorizontalAlignment(0);
                                label.setBorder(BorderFactory.createLineBorder(Color.RED,3));

                                JTable table=new JTable();
                                Vector Header=new Vector();
                                Header.add("Tên hãng");
                                Header.add("Năm ra đời");
                                final Vector[] data = {new Vector()};
                                for(int i=0;i<listHang.size();i++)
                                {
                                    Vector newdata=new Vector();
                                    newdata.add(listHang.get(i).getName());
                                    newdata.add(listHang.get(i).getYearAppear());
                                    data[0].add(newdata);
                                }
                                table.setModel(new DefaultTableModel(data[0],Header));
                                JScrollPane scrollPane=new JScrollPane(table);
                                scrollPane.setBounds(100,100,300,300);

                                JButton them=new JButton("Thêm");
                                them.setBounds(100,410,100,50);
                                them.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==them)
                                        {
                                            JFrame addFrame=new JFrame();
                                            addFrame.setTitle("Thêm");
                                            addFrame.setVisible(true);
                                            addFrame.setSize(300,200);
                                            addFrame.setLayout(null);
                                            addFrame.setResizable(false);

                                            JLabel nameLabel=new JLabel("Tên hãng:");
                                            nameLabel.setBounds(10,10,70,30);
                                            JTextField textField=new JTextField();
                                            textField.setBounds(85,10,200,30);

                                            JLabel nameLabel2=new JLabel("Năm ra đời:");
                                            nameLabel2.setBounds(10,50,70,30);
                                            JTextField textField2=new JTextField();
                                            textField2.setBounds(85,50,200,30);

                                            JButton accept=new JButton("Thêm");
                                            accept.setBounds(50,100,200,40);
                                            accept.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if(e.getSource()==accept) {
                                                        if (textField.getText().equals("") || textField2.getText().equals("")) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "Hãy nhập đủ thông tin");
                                                        } else {
                                                            boolean check = false;
                                                            for (int i = 0; i < listHang.size(); i++) {
                                                                if (textField.getText().equals(listHang.get(i).getName())) {
                                                                    check = true;
                                                                    break;
                                                                }
                                                            }
                                                            if (check == true) {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "Hãng này đã tồn tại!");
                                                            } else {
                                                                HangDienThoai newHang = new HangDienThoai(textField.getText(), Integer.parseInt(textField2.getText()));
                                                                listHang.add(newHang);
                                                                try {
                                                                    LamTrangFile("DuLieu.txt");
                                                                    GhiFile(listHang, "DuLieu.txt");
                                                                } catch (IOException ioException) {
                                                                    ioException.printStackTrace();
                                                                }
                                                                data[0] = new Vector();
                                                                for (int i = 0; i < listHang.size(); i++) {
                                                                    Vector newdata = new Vector();
                                                                    newdata.add(listHang.get(i).getName());
                                                                    newdata.add(listHang.get(i).getYearAppear());
                                                                    data[0].add(newdata);
                                                                }
                                                                table.setModel(new DefaultTableModel(data[0], Header));
                                                            }
                                                        }
                                                    }
                                                }
                                            });

                                            addFrame.add(nameLabel);
                                            addFrame.add(textField);
                                            addFrame.add(nameLabel2);
                                            addFrame.add(textField2);
                                            addFrame.add(accept);
                                        }
                                    }
                                });
                                JButton xoa=new JButton("Xóa");
                                xoa.setBounds(200,410,100,50);
                                xoa.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==xoa)
                                        {
                                            int pos=table.getSelectedRow();
                                            if(pos<0) JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần xóa!");
                                            else
                                            {
                                                for(int i=0;i<listSp.size();i++)
                                                {
                                                    if(listSp.get(i).getHang().equals(listHang.get(pos).getName()))
                                                    {
                                                        listSp.remove(i);
                                                        i--;
                                                        System.out.println("ok");
                                                    }
                                                }
                                                listHang.remove(pos);
                                                JOptionPane.showMessageDialog(null,"Các sản phẩm của hãng đã bị xóa theo!");
                                                try {
                                                    LamTrangFile("DuLieu.txt");
                                                    GhiFile(listHang,"DuLieu.txt");
                                                    LamTrangFile("DuLieuSp.txt");
                                                    GhiFile2(listSp,"DuLieuSp.txt");
                                                } catch (IOException ioException) {
                                                    ioException.printStackTrace();
                                                }
                                                data[0] =new Vector();
                                                for(int i=0;i<listHang.size();i++)
                                                {
                                                    Vector newdata=new Vector();
                                                    newdata.add(listHang.get(i).getName());
                                                    newdata.add(listHang.get(i).getYearAppear());
                                                    data[0].add(newdata);
                                                }
                                                table.setModel(new DefaultTableModel(data[0],Header));
                                            }
                                        }
                                    }
                                });

                                JButton sua=new JButton("Sửa");
                                sua.setBounds(300,410,100,50);
                                sua.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==sua)
                                        {
                                            int pos=table.getSelectedRow();
                                            if(pos<0) JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần sửa!");
                                            else
                                            {
                                                JFrame addFrame=new JFrame();
                                                addFrame.setTitle("Sửa");
                                                addFrame.setVisible(true);
                                                addFrame.setSize(300,200);
                                                addFrame.setLayout(null);
                                                addFrame.setResizable(false);

                                                JLabel nameLabel=new JLabel("Tên hãng:");
                                                nameLabel.setBounds(10,10,70,30);
                                                JTextField textField=new JTextField();
                                                textField.setBounds(85,10,200,30);
                                                textField.setText(listHang.get(pos).getName());

                                                JLabel nameLabel2=new JLabel("Năm ra đời:");
                                                nameLabel2.setBounds(10,50,70,30);
                                                JTextField textField2=new JTextField();
                                                textField2.setBounds(85,50,200,30);
                                                textField2.setText(String.valueOf(listHang.get(pos).getYearAppear()));

                                                JButton update=new JButton("Sửa");
                                                update.setBounds(50,100,200,40);
                                                update.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if(e.getSource()==update)
                                                        {
                                                            if(textField.getText().equals("")||textField2.getText().equals(""))
                                                            {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "Hãy nhập đủ thông tin");
                                                            }
                                                            else
                                                            {
                                                                boolean check=false;
                                                                for(int i=0;i<listHang.size();i++)
                                                                {
                                                                    if(listHang.get(i).getName().equals(textField.getText()))
                                                                    {
                                                                        check=true;
                                                                        break;
                                                                    }
                                                                }
                                                                if(check==true)
                                                                {
                                                                    JOptionPane.showMessageDialog(null,"Hãng đã tồn tại!");
                                                                }
                                                                else
                                                                {
                                                                HangDienThoai newHang=new HangDienThoai(textField.getText(),Integer.parseInt(textField2.getText()));
                                                                for(int i=0;i<listSp.size();i++)
                                                                {
                                                                    if(listSp.get(i).getHang().equals(listHang.get(pos).getName()))
                                                                    {
                                                                        listSp.get(i).setHang(newHang.getName());
                                                                    }
                                                                }
                                                                listHang.remove(pos);
                                                                listHang.add(pos,newHang);
                                                                try {
                                                                    LamTrangFile("DuLieu.txt");
                                                                    GhiFile(listHang,"DuLieu.txt");
                                                                    LamTrangFile("DuLieuSp.txt");
                                                                    GhiFile2(listSp,"DuLieuSp.txt");
                                                                } catch (IOException ioException) {
                                                                    ioException.printStackTrace();
                                                                }
                                                                data[0] =new Vector();
                                                                for(int i=0;i<listHang.size();i++)
                                                                {
                                                                    Vector newdata=new Vector();
                                                                    newdata.add(listHang.get(i).getName());
                                                                    newdata.add(listHang.get(i).getYearAppear());
                                                                    data[0].add(newdata);
                                                                }
                                                                table.setModel(new DefaultTableModel(data[0],Header));
                                                            }
                                                        }}
                                                    }
                                                });

                                                addFrame.add(nameLabel);
                                                addFrame.add(textField);
                                                addFrame.add(nameLabel2);
                                                addFrame.add(textField2);
                                                addFrame.add(update);
                                            }
                                        }
                                    }
                                });

                                listFrame.add(label);
                                listFrame.add(scrollPane);
                                listFrame.add(them);
                                listFrame.add(xoa);
                                listFrame.add(sua);
                            }
                        }
                    });

                    JButton dsSp=new JButton("Danh sách sản phẩm");
                    dsSp.setBounds(300,50,250,70);
                    dsSp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==dsSp)
                            {
                                frame1.dispose();
                                JFrame listFrame=new JFrame();
                                listFrame.setTitle("Danh sách sản phẩm");
                                listFrame.setVisible(true);
                                listFrame.setSize(600,500);
                                listFrame.setLayout(null);
                                listFrame.setResizable(false);

                                JLabel label=new JLabel("DANH SÁCH SẢN PHẨM");
                                label.setBounds(100,10,400,40);
                                label.setForeground(Color.GREEN);
                                label.setHorizontalAlignment(0);
                                label.setBorder(BorderFactory.createLineBorder(Color.RED,3));

                                JLabel selectHang=new JLabel("Chọn hãng:");
                                selectHang.setBounds(100,60,100,30);

                                JTable table=new JTable();
                                Vector Header=new Vector();
                                Header.add("Tên");
                                Header.add("Hãng");
                                Header.add("Pin");
                                Header.add("Ram");
                                Header.add("Bộ nhớ trong");
                                Header.add("Giá");
                                Header.add("Số lượng");
                                final Vector[] data = {new Vector()};
                                for(int i=0;i<listSp.size();i++)
                                {
                                    Vector newdata=new Vector();
                                    newdata.add(listSp.get(i).getName());
                                    newdata.add(listSp.get(i).getHang());
                                    newdata.add(listSp.get(i).getPin());
                                    newdata.add(listSp.get(i).getRam());
                                    newdata.add(listSp.get(i).getInternalMemory());
                                    newdata.add(listSp.get(i).getPrice());
                                    newdata.add(listSp.get(i).getSoLuong());
                                    data[0].add(newdata);
                                }
                                table.setModel(new DefaultTableModel(data[0],Header));
                                JScrollPane scrollPane=new JScrollPane(table);
                                scrollPane.setBounds(100,100,400,300);

                                JComboBox<String>Hang=new JComboBox<>();
                                for(int i=0;i<listHang.size();i++)
                                {
                                    Hang.addItem(listHang.get(i).getName());
                                }
                                Hang.setSelectedItem(null);
                                Hang.setBounds(200,60,100,30);
                                Hang.addItemListener(new ItemListener() {
                                    @Override
                                    public void itemStateChanged(ItemEvent e) {
                                        if(e.getSource()==Hang)
                                        {
                                            data[0]=new Vector();
                                            for(int i=0;i<listSp.size();i++)
                                            {
                                                if(listSp.get(i).getHang().equals(Hang.getSelectedItem()))
                                                {
                                                    Vector newData=new Vector();
                                                    newData.add(listSp.get(i).getName());
                                                    newData.add(listSp.get(i).getHang());
                                                    newData.add(listSp.get(i).getPin());
                                                    newData.add(listSp.get(i).getRam());
                                                    newData.add(listSp.get(i).getInternalMemory());
                                                    newData.add(listSp.get(i).getPrice());
                                                    newData.add(listSp.get(i).getSoLuong());
                                                    data[0].add(newData);
                                                }
                                            }
                                            table.setModel(new DefaultTableModel(data[0],Header));
                                        }
                                    }
                                });

                                JButton them=new JButton("Thêm");
                                them.setBounds(150,410,100,50);
                                them.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==them)
                                        {
                                                JFrame addFrame=new JFrame();
                                                addFrame.setTitle("Thêm");
                                                addFrame.setVisible(true);
                                                addFrame.setSize(400,400);
                                                addFrame.setLayout(null);
                                                addFrame.setResizable(false);

                                                JLabel nameLabel=new JLabel("Tên:");
                                                nameLabel.setBounds(10,10,70,30);
                                                JTextField textField=new JTextField();
                                                textField.setBounds(120,10,200,30);

                                                JLabel nameLabel2=new JLabel("Hãng:");
                                                nameLabel2.setBounds(10,50,70,30);
                                                JTextField textField2=new JTextField();
                                                textField2.setBounds(120,50,200,30);

                                                JLabel nameLabel3=new JLabel("Pin:");
                                                nameLabel3.setBounds(10,90,70,30);
                                                JTextField textField3=new JTextField();
                                                textField3.setBounds(120,90,200,30);

                                                JLabel nameLabel4=new JLabel("Ram:");
                                                nameLabel4.setBounds(10,130,70,30);
                                                JTextField textField4=new JTextField();
                                                textField4.setBounds(120,130,200,30);

                                                JLabel nameLabel5=new JLabel("Bộ nhớ trong:");
                                                nameLabel5.setBounds(10,170,100,30);
                                                JTextField textField5=new JTextField();
                                                textField5.setBounds(120,170,200,30);

                                                JLabel nameLabel6=new JLabel("Giá:");
                                                nameLabel6.setBounds(10,210,100,30);
                                                JTextField textField6=new JTextField();
                                                textField6.setBounds(120,210,200,30);

                                            JLabel nameLabel7=new JLabel("Số lượng:");
                                            nameLabel7.setBounds(10,250,70,30);
                                            JTextField textField7=new JTextField();
                                            textField7.setBounds(120,250,200,30);

                                                JButton add=new JButton("Thêm");
                                                add.setBounds(100,300,200,40);
                                                add.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if(e.getSource()==add)
                                                        {
                                                            if(textField.getText().equals("")||textField2.getText().equals("")||
                                                                    textField3.getText().equals("")||textField4.getText().equals("")||
                                                                    textField5.getText().equals("")||textField6.getText().equals("")||textField7.getText().equals(""))
                                                            {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "Hãy nhập đủ thông tin");
                                                            }
                                                            else
                                                            {
                                                                boolean check=false;
                                                                for(int i=0;i<listHang.size();i++)
                                                                {
                                                                    if(listHang.get(i).getName().equals(textField2.getText()))
                                                                    {
                                                                        check=true;
                                                                        break;
                                                                    }
                                                                }
                                                                if(check==false)
                                                                {
                                                                    JOptionPane.showMessageDialog(null,"Hãng này không tồn tại!");
                                                                }
                                                                else
                                                                {
                                                                    boolean KT=false;
                                                                    DienThoai newDt=new DienThoai(textField.getText(),textField2.getText(),Integer.parseInt(textField3.getText()),
                                                                            Integer.parseInt(textField4.getText()),Integer.parseInt(textField5.getText()),Integer.parseInt(textField6.getText()),
                                                                            Integer.parseInt(textField7.getText()));
                                                                    for(int i=0;i<listSp.size();i++)
                                                                    {
                                                                        if(listSp.get(i).getName().equals(newDt.getName())&&listSp.get(i).getHang().equals(newDt.getHang())&&
                                                                                listSp.get(i).getPin()==newDt.getPin()&&listSp.get(i).getRam()==newDt.getRam()&&
                                                                                listSp.get(i).getInternalMemory()==newDt.getInternalMemory()&&listSp.get(i).getPrice()==newDt.getPrice())
                                                                        {
                                                                            KT=true;
                                                                            listSp.get(i).setSoLuong(listSp.get(i).getSoLuong()+newDt.getSoLuong());
                                                                            break;
                                                                        }
                                                                    }
                                                                    if(KT==false)
                                                                    {
                                                                        listSp.add(newDt);
                                                                    }
                                                                    try {
                                                                        LamTrangFile("DuLieuSp.txt");
                                                                        GhiFile2(listSp,"DuLieuSp.txt");
                                                                    } catch (IOException ioException) {
                                                                        ioException.printStackTrace();
                                                                    }
                                                                    data[0] =new Vector();
                                                                    if(Hang.getSelectedItem()==null)
                                                                    {
                                                                        for(int i=0;i<listSp.size();i++)
                                                                        {
                                                                            Vector newdata=new Vector();
                                                                            newdata.add(listSp.get(i).getName());
                                                                            newdata.add(listSp.get(i).getHang());
                                                                            newdata.add(listSp.get(i).getPin());
                                                                            newdata.add(listSp.get(i).getRam());
                                                                            newdata.add(listSp.get(i).getInternalMemory());
                                                                            newdata.add(listSp.get(i).getPrice());
                                                                            newdata.add(listSp.get(i).getSoLuong());
                                                                            data[0].add(newdata);
                                                                        }
                                                                    }
                                                                    else {
                                                                        String select= (String) Hang.getSelectedItem();
                                                                        for (int i = 0; i < listSp.size(); i++) {
                                                                            if (listSp.get(i).getHang().equals(select)) {
                                                                                Vector newdata = new Vector();
                                                                                newdata.add(listSp.get(i).getName());
                                                                                newdata.add(listSp.get(i).getHang());
                                                                                newdata.add(listSp.get(i).getPin());
                                                                                newdata.add(listSp.get(i).getRam());
                                                                                newdata.add(listSp.get(i).getInternalMemory());
                                                                                newdata.add(listSp.get(i).getPrice());
                                                                                newdata.add(listSp.get(i).getSoLuong());
                                                                                data[0].add(newdata);
                                                                            }
                                                                        }
                                                                    }
                                                                    table.setModel(new DefaultTableModel(data[0],Header));
                                                                }
                                                            }
                                                        }}
                                                });

                                                addFrame.add(nameLabel);
                                                addFrame.add(textField);
                                                addFrame.add(nameLabel2);
                                                addFrame.add(textField2);
                                                addFrame.add(nameLabel3);
                                                addFrame.add(textField3);
                                                addFrame.add(nameLabel4);
                                                addFrame.add(textField4);
                                                addFrame.add(nameLabel5);
                                                addFrame.add(textField5);
                                                addFrame.add(nameLabel6);
                                                addFrame.add(textField6);
                                                addFrame.add(nameLabel7);
                                                addFrame.add(textField7);
                                                addFrame.add(add);
                                            }
                                    }
                                });
                                JButton xoa=new JButton("Xóa");
                                xoa.setBounds(250,410,100,50);
                                xoa.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==xoa)
                                        {
                                            if(Hang.getSelectedItem()==null)
                                            {
                                            int pos=table.getSelectedRow();
                                            if(pos<0) JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần xóa!");
                                            else
                                            {
                                                listSp.remove(pos);
                                                try {
                                                    LamTrangFile("DuLieuSp.txt");
                                                    GhiFile2(listSp,"DuLieuSp.txt");
                                                } catch (IOException ioException) {
                                                    ioException.printStackTrace();
                                                }
                                                data[0] =new Vector();
                                                for(int i=0;i<listSp.size();i++)
                                                {
                                                    Vector newdata=new Vector();
                                                    newdata.add(listSp.get(i).getName());
                                                    newdata.add(listSp.get(i).getHang());
                                                    newdata.add(listSp.get(i).getPin());
                                                    newdata.add(listSp.get(i).getRam());
                                                    newdata.add(listSp.get(i).getInternalMemory());
                                                    newdata.add(listSp.get(i).getPrice());
                                                    newdata.add(listSp.get(i).getSoLuong());
                                                    data[0].add(newdata);
                                                }
                                                table.setModel(new DefaultTableModel(data[0],Header));
                                            }
                                        }
                                            else
                                            {
                                                Vector<DienThoai>spTheoHang=new Vector<>();
                                                String selectHang= (String) Hang.getSelectedItem();
                                                for(int i=0;i<listSp.size();i++)
                                                {
                                                    if(listSp.get(i).getHang().equals(selectHang))
                                                    {
                                                        spTheoHang.add(listSp.get(i));
                                                    }
                                                }
                                                int pos=table.getSelectedRow();
                                                if(pos<0) JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần xóa!");
                                                else
                                                {
                                                    for(int i=0;i<listSp.size();i++)
                                                    {
                                                        if(listSp.get(i).getName().equals(spTheoHang.get(pos).getName())&&
                                                                listSp.get(i).getHang().equals(spTheoHang.get(pos).getHang())&&
                                                                listSp.get(i).getPin()==spTheoHang.get(pos).getPin()&&
                                                                listSp.get(i).getRam()==spTheoHang.get(pos).getRam()&&
                                                                listSp.get(i).getInternalMemory()==spTheoHang.get(pos).getInternalMemory())
                                                        {
                                                            listSp.remove(i);
                                                            spTheoHang.remove(pos);
                                                            break;
                                                        }
                                                    }
                                                    try {
                                                        LamTrangFile("DuLieuSp.txt");
                                                        GhiFile2(listSp,"DuLieuSp.txt");
                                                    } catch (IOException ioException) {
                                                        ioException.printStackTrace();
                                                    }
                                                    data[0] =new Vector();
                                                    for(int i=0;i<spTheoHang.size();i++)
                                                    {
                                                        Vector newdata=new Vector();
                                                        newdata.add(spTheoHang.get(i).getName());
                                                        newdata.add(spTheoHang.get(i).getHang());
                                                        newdata.add(spTheoHang.get(i).getPin());
                                                        newdata.add(spTheoHang.get(i).getRam());
                                                        newdata.add(spTheoHang.get(i).getInternalMemory());
                                                        newdata.add(spTheoHang.get(i).getPrice());
                                                        newdata.add(spTheoHang.get(i).getSoLuong());
                                                        data[0].add(newdata);
                                                    }
                                                    table.setModel(new DefaultTableModel(data[0],Header));
                                                }
                                            }
                                        }
                                    }
                                });

                                JButton sua=new JButton("Sửa");
                                sua.setBounds(350,410,100,50);
                                sua.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==sua) {
                                            if (Hang.getSelectedItem() == null) {
                                                final int[] pos = {table.getSelectedRow()};
                                                if (pos[0] < 0)
                                                    JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần sửa!");
                                                else {
                                                    JFrame addFrame = new JFrame();
                                                    addFrame.setTitle("Sửa");
                                                    addFrame.setVisible(true);
                                                    addFrame.setSize(400, 400);
                                                    addFrame.setLayout(null);
                                                    addFrame.setResizable(false);
                                                    DienThoai phone = listSp.get(pos[0]);

                                                    JLabel nameLabel = new JLabel("Tên:");
                                                    nameLabel.setBounds(10, 10, 70, 30);
                                                    JTextField textField = new JTextField();
                                                    textField.setBounds(120, 10, 200, 30);
                                                    textField.setText(phone.getName());

                                                    JLabel nameLabel2 = new JLabel("Hãng:");
                                                    nameLabel2.setBounds(10, 50, 70, 30);
                                                    JTextField textField2 = new JTextField();
                                                    textField2.setBounds(120, 50, 200, 30);
                                                    textField2.setText(phone.getHang());

                                                    JLabel nameLabel3 = new JLabel("Pin:");
                                                    nameLabel3.setBounds(10, 90, 70, 30);
                                                    JTextField textField3 = new JTextField();
                                                    textField3.setBounds(120, 90, 200, 30);
                                                    textField3.setText(phone.getPin() + "");

                                                    JLabel nameLabel4 = new JLabel("Ram:");
                                                    nameLabel4.setBounds(10, 130, 70, 30);
                                                    JTextField textField4 = new JTextField();
                                                    textField4.setBounds(120, 130, 200, 30);
                                                    textField4.setText(phone.getRam() + "");

                                                    JLabel nameLabel5 = new JLabel("Bộ nhớ trong:");
                                                    nameLabel5.setBounds(10, 170, 100, 30);
                                                    JTextField textField5 = new JTextField();
                                                    textField5.setBounds(120, 170, 200, 30);
                                                    textField5.setText(phone.getInternalMemory() + "");

                                                    JLabel nameLabel6 = new JLabel("Giá:");
                                                    nameLabel6.setBounds(10, 210, 100, 30);
                                                    JTextField textField6 = new JTextField();
                                                    textField6.setBounds(120, 210, 200, 30);
                                                    textField6.setText(phone.getPrice() + "");

                                                    JLabel nameLabel7 = new JLabel("Số lượng:");
                                                    nameLabel7.setBounds(10, 250, 70, 30);
                                                    JTextField textField7 = new JTextField();
                                                    textField7.setBounds(120, 250, 200, 30);
                                                    textField7.setText(phone.getSoLuong() + "");

                                                    JButton update = new JButton("Sửa");
                                                    update.setBounds(100, 300, 200, 40);
                                                    update.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (e.getSource() == update) {
                                                                int position = table.getSelectedRow();
                                                                if (textField.getText().equals("") || textField2.getText().equals("") ||
                                                                        textField3.getText().equals("") || textField4.getText().equals("") ||
                                                                        textField5.getText().equals("") || textField6.getText().equals("") || textField7.getText().equals("")) {
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "Hãy nhập đủ thông tin");
                                                                } else {
                                                                    boolean check = false;
                                                                    for (int i = 0; i < listHang.size(); i++) {
                                                                        if (listHang.get(i).getName().equals(textField2.getText())) {
                                                                            check = true;
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (check == false) {
                                                                        JOptionPane.showMessageDialog(null, "Hãng này không tồn tại!");
                                                                    } else {
                                                                        DienThoai newDt = new DienThoai(textField.getText(), textField2.getText(), Integer.parseInt(textField3.getText()),
                                                                                Integer.parseInt(textField4.getText()), Integer.parseInt(textField5.getText()), Integer.parseInt(textField6.getText()),
                                                                                Integer.parseInt(textField7.getText()));
                                                                        listSp.remove(pos[0]);
                                                                        boolean KT = false;
                                                                        for (int i = 0; i < listSp.size(); i++) {
                                                                            if (listSp.get(i).getName().equals(newDt.getName()) && listSp.get(i).getHang().equals(newDt.getHang()) &&
                                                                                    listSp.get(i).getPin() == newDt.getPin() && listSp.get(i).getRam() == newDt.getRam() &&
                                                                                    listSp.get(i).getInternalMemory() == newDt.getInternalMemory() && listSp.get(i).getPrice() == newDt.getPrice()) {
                                                                                KT = true;
                                                                                listSp.get(i).setSoLuong(listSp.get(i).getSoLuong() + newDt.getSoLuong());
                                                                                break;
                                                                            }
                                                                        }
                                                                        if (KT == false) {
                                                                            listSp.add(pos[0], newDt);
                                                                        }
                                                                        try {
                                                                            LamTrangFile("DuLieuSp.txt");
                                                                            GhiFile2(listSp, "DuLieuSp.txt");
                                                                        } catch (IOException ioException) {
                                                                            ioException.printStackTrace();
                                                                        }
                                                                        data[0] = new Vector();
                                                                        for (int i = 0; i < listSp.size(); i++) {
                                                                            Vector newdata = new Vector();
                                                                            newdata.add(listSp.get(i).getName());
                                                                            newdata.add(listSp.get(i).getHang());
                                                                            newdata.add(listSp.get(i).getPin());
                                                                            newdata.add(listSp.get(i).getRam());
                                                                            newdata.add(listSp.get(i).getInternalMemory());
                                                                            newdata.add(listSp.get(i).getPrice());
                                                                            newdata.add(listSp.get(i).getSoLuong());
                                                                            data[0].add(newdata);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(data[0], Header));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    });

                                                    addFrame.add(nameLabel);
                                                    addFrame.add(textField);
                                                    addFrame.add(nameLabel2);
                                                    addFrame.add(textField2);
                                                    addFrame.add(nameLabel3);
                                                    addFrame.add(textField3);
                                                    addFrame.add(nameLabel4);
                                                    addFrame.add(textField4);
                                                    addFrame.add(nameLabel5);
                                                    addFrame.add(textField5);
                                                    addFrame.add(nameLabel6);
                                                    addFrame.add(textField6);
                                                    addFrame.add(nameLabel7);
                                                    addFrame.add(textField7);
                                                    addFrame.add(update);
                                                }
                                            }
                                            else
                                            {
                                                final int[] pos = {table.getSelectedRow()};
                                                Vector<DienThoai>spTheoHang=new Vector<>();
                                                String selectHang= (String) Hang.getSelectedItem();
                                                for(int i=0;i<listSp.size();i++)
                                                {
                                                    if(listSp.get(i).getHang().equals(selectHang))
                                                    {
                                                        spTheoHang.add(listSp.get(i));
                                                    }
                                                }
                                                if (pos[0] < 0)
                                                    JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần sửa!");
                                                else {
                                                    JFrame addFrame = new JFrame();
                                                    addFrame.setTitle("Sửa");
                                                    addFrame.setVisible(true);
                                                    addFrame.setSize(400, 400);
                                                    addFrame.setLayout(null);
                                                    addFrame.setResizable(false);
                                                    DienThoai phone = spTheoHang.get(pos[0]);

                                                    JLabel nameLabel = new JLabel("Tên:");
                                                    nameLabel.setBounds(10, 10, 70, 30);
                                                    JTextField textField = new JTextField();
                                                    textField.setBounds(120, 10, 200, 30);
                                                    textField.setText(phone.getName());

                                                    JLabel nameLabel2 = new JLabel("Hãng:");
                                                    nameLabel2.setBounds(10, 50, 70, 30);
                                                    JTextField textField2 = new JTextField();
                                                    textField2.setBounds(120, 50, 200, 30);
                                                    textField2.setText(phone.getHang());

                                                    JLabel nameLabel3 = new JLabel("Pin:");
                                                    nameLabel3.setBounds(10, 90, 70, 30);
                                                    JTextField textField3 = new JTextField();
                                                    textField3.setBounds(120, 90, 200, 30);
                                                    textField3.setText(phone.getPin() + "");

                                                    JLabel nameLabel4 = new JLabel("Ram:");
                                                    nameLabel4.setBounds(10, 130, 70, 30);
                                                    JTextField textField4 = new JTextField();
                                                    textField4.setBounds(120, 130, 200, 30);
                                                    textField4.setText(phone.getRam() + "");

                                                    JLabel nameLabel5 = new JLabel("Bộ nhớ trong:");
                                                    nameLabel5.setBounds(10, 170, 100, 30);
                                                    JTextField textField5 = new JTextField();
                                                    textField5.setBounds(120, 170, 200, 30);
                                                    textField5.setText(phone.getInternalMemory() + "");

                                                    JLabel nameLabel6 = new JLabel("Giá:");
                                                    nameLabel6.setBounds(10, 210, 100, 30);
                                                    JTextField textField6 = new JTextField();
                                                    textField6.setBounds(120, 210, 200, 30);
                                                    textField6.setText(phone.getPrice() + "");

                                                    JLabel nameLabel7 = new JLabel("Số lượng:");
                                                    nameLabel7.setBounds(10, 250, 70, 30);
                                                    JTextField textField7 = new JTextField();
                                                    textField7.setBounds(120, 250, 200, 30);
                                                    textField7.setText(phone.getSoLuong() + "");

                                                    JButton update = new JButton("Sửa");
                                                    update.setBounds(100, 300, 200, 40);
                                                    update.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (e.getSource() == update) {
                                                                int position = table.getSelectedRow();
                                                                if (textField.getText().equals("") || textField2.getText().equals("") ||
                                                                        textField3.getText().equals("") || textField4.getText().equals("") ||
                                                                        textField5.getText().equals("") || textField6.getText().equals("") || textField7.getText().equals("")) {
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "Hãy nhập đủ thông tin");
                                                                } else {
                                                                    boolean check = false;
                                                                    for (int i = 0; i < listHang.size(); i++) {
                                                                        if (listHang.get(i).getName().equals(textField2.getText())) {
                                                                            check = true;
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (check == false) {
                                                                        JOptionPane.showMessageDialog(null, "Hãng này không tồn tại!");
                                                                    } else {
                                                                        DienThoai newDt = new DienThoai(textField.getText(), textField2.getText(), Integer.parseInt(textField3.getText()),
                                                                                Integer.parseInt(textField4.getText()), Integer.parseInt(textField5.getText()), Integer.parseInt(textField6.getText()),
                                                                                Integer.parseInt(textField7.getText()));
                                                                        int vitri = 0;
                                                                        for(int i=0;i<listSp.size();i++)
                                                                        {
                                                                            if(listSp.get(i).getName().equals(spTheoHang.get(pos[0]).getName())&&
                                                                                    listSp.get(i).getHang().equals(spTheoHang.get(pos[0]).getHang())&&
                                                                                    listSp.get(i).getPin()==spTheoHang.get(pos[0]).getPin()&&
                                                                                    listSp.get(i).getRam()==spTheoHang.get(pos[0]).getRam()&&
                                                                                    listSp.get(i).getInternalMemory()==spTheoHang.get(pos[0]).getInternalMemory())
                                                                            {
                                                                                listSp.remove(i);
                                                                                spTheoHang.remove(pos[0]);
                                                                                vitri=i;
                                                                                break;
                                                                            }
                                                                        }
                                                                        boolean KT = false;
                                                                        for (int i = 0; i < listSp.size(); i++) {
                                                                            if (listSp.get(i).getName().equals(newDt.getName()) && listSp.get(i).getHang().equals(newDt.getHang()) &&
                                                                                    listSp.get(i).getPin() == newDt.getPin() && listSp.get(i).getRam() == newDt.getRam() &&
                                                                                    listSp.get(i).getInternalMemory() == newDt.getInternalMemory() && listSp.get(i).getPrice() == newDt.getPrice()) {
                                                                                KT = true;
                                                                                listSp.get(i).setSoLuong(listSp.get(i).getSoLuong() + newDt.getSoLuong());
                                                                                break;
                                                                            }
                                                                        }
                                                                        if (KT == false) {
                                                                            listSp.add(vitri, newDt);
                                                                            spTheoHang.add(pos[0],newDt);
                                                                        }
                                                                        try {
                                                                            LamTrangFile("DuLieuSp.txt");
                                                                            GhiFile2(listSp, "DuLieuSp.txt");
                                                                        } catch (IOException ioException) {
                                                                            ioException.printStackTrace();
                                                                        }
                                                                        data[0] = new Vector();
                                                                        for (int i = 0; i < spTheoHang.size(); i++) {
                                                                            Vector newdata = new Vector();
                                                                            newdata.add(spTheoHang.get(i).getName());
                                                                            newdata.add(spTheoHang.get(i).getHang());
                                                                            newdata.add(spTheoHang.get(i).getPin());
                                                                            newdata.add(spTheoHang.get(i).getRam());
                                                                            newdata.add(spTheoHang.get(i).getInternalMemory());
                                                                            newdata.add(spTheoHang.get(i).getPrice());
                                                                            newdata.add(spTheoHang.get(i).getSoLuong());
                                                                            data[0].add(newdata);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(data[0], Header));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    });

                                                    addFrame.add(nameLabel);
                                                    addFrame.add(textField);
                                                    addFrame.add(nameLabel2);
                                                    addFrame.add(textField2);
                                                    addFrame.add(nameLabel3);
                                                    addFrame.add(textField3);
                                                    addFrame.add(nameLabel4);
                                                    addFrame.add(textField4);
                                                    addFrame.add(nameLabel5);
                                                    addFrame.add(textField5);
                                                    addFrame.add(nameLabel6);
                                                    addFrame.add(textField6);
                                                    addFrame.add(nameLabel7);
                                                    addFrame.add(textField7);
                                                    addFrame.add(update);
                                                }
                                            }
                                        }
                                    }
                                });

                                listFrame.add(label);
                                listFrame.add(scrollPane);
                                listFrame.add(them);
                                listFrame.add(xoa);
                                listFrame.add(sua);
                                listFrame.add(Hang);
                                listFrame.add(selectHang);
                            }
                        }
                    });
                    frame1.add(dsSp);
                }
            }
        });

        buttonDH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==buttonDH)
                {
                    JFrame listFrame=new JFrame();
                    listFrame.setTitle("Quản lý đơn hàng");
                    listFrame.setVisible(true);
                    listFrame.setSize(600,500);
                    listFrame.setLayout(null);
                    listFrame.setResizable(false);

                    JLabel label=new JLabel("DANH SÁCH ĐƠN HÀNG");
                    label.setBounds(100,10,400,40);
                    label.setForeground(Color.GREEN);
                    label.setHorizontalAlignment(0);
                    label.setBorder(BorderFactory.createLineBorder(Color.RED,3));

                    JLabel selectHang=new JLabel("Nhập tháng và năm:");
                    selectHang.setBounds(100,60,120,30);

                    JTable table=new JTable();
                    Vector Header=new Vector();
                    Header.add("Ngày mua");
                    Header.add("Máy");
                    Header.add("Người mua");
                    Header.add("Giá bán");
                    final Vector[] data = {new Vector()};
                    for(int i=0;i<listDonHang.size();i++)
                    {
                        Vector newdata=new Vector();
                        newdata.add(listDonHang.get(i).getNgayMua());
                        newdata.add(listDonHang.get(i).getMayMua());
                        newdata.add(listDonHang.get(i).getNguoiMua());
                        newdata.add(listDonHang.get(i).getGiaTien());
                        data[0].add(newdata);
                    }
                    table.setModel(new DefaultTableModel(data[0],Header));
                    JScrollPane scrollPane=new JScrollPane(table);
                    scrollPane.setBounds(100,100,400,300);

                    JTextField textField=new JTextField();
                    textField.setBounds(230,60,100,30);
                    listFrame.add(textField);

                    JTextField textField2=new JTextField();
                    textField2.setBounds(330,60,100,30);
                    listFrame.add(textField2);

                    JButton confirm=new JButton("Search");
                    confirm.setBounds(440,60,100,30);
                    listFrame.add(confirm);
                    Vector<DonHang>donTheoThang=new Vector<>();
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==confirm)
                            {
                                if(textField.getText().equals("")||textField2.getText().equals(""))
                                {
                                    JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ tháng và năm!");
                                }
                                else
                                {
                                    if(Integer.parseInt(textField.getText())<0||Integer.parseInt(textField.getText())>12)
                                    {
                                        JOptionPane.showMessageDialog(null,"Không tồn tại tháng này!");
                                    }
                                    else
                                    {
                                        data[0]=new Vector();
                                        for(int i=0;i<listDonHang.size();i++)
                                        {
                                            StringTokenizer stringTokenizer=new StringTokenizer(listDonHang.get(i).getNgayMua(),"/");
                                            Vector<String>dulieu=new Vector<>();
                                            while(stringTokenizer.hasMoreTokens())
                                            {
                                                dulieu.add(stringTokenizer.nextToken());
                                            }
                                            int thang=Integer.parseInt(dulieu.get(1));
                                            int nam=Integer.parseInt(dulieu.get(2));
                                            if(thang==Integer.parseInt(textField.getText())&&nam==Integer.parseInt(textField2.getText()))
                                            {
                                                Vector newdata=new Vector();
                                                newdata.add(listDonHang.get(i).getNgayMua());
                                                newdata.add(listDonHang.get(i).getMayMua());
                                                newdata.add(listDonHang.get(i).getNguoiMua());
                                                newdata.add(listDonHang.get(i).getGiaTien());
                                                data[0].add(newdata);
                                                donTheoThang.add(listDonHang.get(i));
                                            }
                                        }
                                        table.setModel(new DefaultTableModel(data[0],Header));
                                    }
                                }
                            }
                        }
                    });

                    JButton view=new JButton("Xem chi tiết");
                    view.setBounds(100,410,100,50);
                    view.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==view)
                            {
                                int selectPos=table.getSelectedRow();
                                if(selectPos<0) JOptionPane.showMessageDialog(null,"Hãy chọn đơn hàng cần xem chi tiết!");
                                else {
                                    if (donTheoThang.size() == 0) {
                                        JFrame littleFrame = new JFrame();
                                        littleFrame.setLayout(null);
                                        littleFrame.setResizable(false);
                                        littleFrame.setVisible(true);
                                        littleFrame.setSize(300, 250);
                                        littleFrame.setTitle("Chi tiết");

                                        DonHang donHang = listDonHang.get(selectPos);
                                        JLabel nameLabel = new JLabel("Ngày mua:");
                                        nameLabel.setBounds(10, 10, 140, 30);
                                        JLabel jLabel = new JLabel(donHang.getNgayMua());
                                        jLabel.setBounds(100, 10, 200, 30);

                                        JLabel nameLabel2 = new JLabel("Máy:");
                                        nameLabel2.setBounds(10, 50, 70, 30);
                                        JLabel jLabel2 = new JLabel(donHang.getMayMua());
                                        jLabel2.setBounds(100, 50, 100, 30);
                                        JButton button = new JButton("Chi tiết máy");
                                        button.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == button) {
                                                    JFrame litFrame = new JFrame();
                                                    litFrame.setLayout(null);
                                                    litFrame.setResizable(false);
                                                    litFrame.setVisible(true);
                                                    litFrame.setSize(300, 250);
                                                    litFrame.setTitle("Chi tiết máy");

                                                    JLabel NameLabel = new JLabel("Tên máy:");
                                                    NameLabel.setBounds(10, 10, 140, 30);
                                                    JLabel jlabel = new JLabel(donHang.getMayMua());
                                                    jlabel.setBounds(100, 10, 200, 30);

                                                    JLabel NameLabel2 = new JLabel("Hãng:");
                                                    NameLabel2.setBounds(10, 50, 70, 30);
                                                    JLabel jlabel2 = new JLabel(donHang.getHang());
                                                    jlabel2.setBounds(100, 50, 100, 30);

                                                    JLabel NameLabel3 = new JLabel("Pin:");
                                                    NameLabel3.setBounds(10, 90, 70, 30);
                                                    JLabel jlabel3 = new JLabel(donHang.getPin() + "");
                                                    jlabel3.setBounds(100, 90, 200, 30);

                                                    JLabel NameLabel4 = new JLabel("Ram:");
                                                    NameLabel4.setBounds(10, 130, 100, 30);
                                                    JLabel jlabel4 = new JLabel(donHang.getRam() + "");
                                                    jlabel4.setBounds(100, 130, 200, 30);

                                                    JLabel NameLabel5 = new JLabel("Bộ nhớ trong:");
                                                    NameLabel5.setBounds(10, 170, 100, 30);
                                                    JLabel jlabel5 = new JLabel(donHang.getInternalMemory() + "");
                                                    jlabel5.setBounds(100, 170, 200, 30);

                                                    litFrame.add(NameLabel);
                                                    litFrame.add(NameLabel5);
                                                    litFrame.add(NameLabel2);
                                                    litFrame.add(NameLabel3);
                                                    litFrame.add(NameLabel4);
                                                    litFrame.add(jlabel);
                                                    litFrame.add(jlabel2);
                                                    litFrame.add(jlabel3);
                                                    litFrame.add(jlabel4);
                                                    litFrame.add(jlabel5);
                                                }
                                            }
                                        });
                                        button.setBounds(180, 50, 100, 30);

                                        JLabel nameLabel3 = new JLabel("Người mua:");
                                        nameLabel3.setBounds(10, 90, 70, 30);
                                        JLabel jLabel3 = new JLabel(donHang.getNguoiMua());
                                        jLabel3.setBounds(100, 90, 200, 30);

                                        JLabel nameLabel4 = new JLabel("Số điện thoại:");
                                        nameLabel4.setBounds(10, 130, 100, 30);
                                        JLabel jLabel4 = new JLabel(donHang.getDt());
                                        jLabel4.setBounds(100, 130, 200, 30);

                                        JLabel nameLabel5 = new JLabel("Giá tiền:");
                                        nameLabel5.setBounds(10, 170, 70, 30);
                                        JLabel jLabel5 = new JLabel(donHang.getGiaTien() + "");
                                        jLabel5.setBounds(100, 170, 200, 30);

                                        littleFrame.add(nameLabel);
                                        littleFrame.add(nameLabel5);
                                        littleFrame.add(nameLabel2);
                                        littleFrame.add(nameLabel3);
                                        littleFrame.add(nameLabel4);
                                        littleFrame.add(jLabel);
                                        littleFrame.add(jLabel2);
                                        littleFrame.add(jLabel3);
                                        littleFrame.add(jLabel4);
                                        littleFrame.add(jLabel5);
                                        littleFrame.add(button);
                                    }
                                    else
                                    {
                                        JFrame littleFrame = new JFrame();
                                        littleFrame.setLayout(null);
                                        littleFrame.setResizable(false);
                                        littleFrame.setVisible(true);
                                        littleFrame.setSize(300, 250);
                                        littleFrame.setTitle("Chi tiết");

                                        DonHang donHang = donTheoThang.get(selectPos);
                                        JLabel nameLabel = new JLabel("Ngày mua:");
                                        nameLabel.setBounds(10, 10, 140, 30);
                                        JLabel jLabel = new JLabel(donHang.getNgayMua());
                                        jLabel.setBounds(100, 10, 200, 30);

                                        JLabel nameLabel2 = new JLabel("Máy:");
                                        nameLabel2.setBounds(10, 50, 70, 30);
                                        JLabel jLabel2 = new JLabel(donHang.getMayMua());
                                        jLabel2.setBounds(100, 50, 100, 30);
                                        JButton button = new JButton("Chi tiết máy");
                                        button.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == button) {
                                                    JFrame litFrame = new JFrame();
                                                    litFrame.setLayout(null);
                                                    litFrame.setResizable(false);
                                                    litFrame.setVisible(true);
                                                    litFrame.setSize(300, 250);
                                                    litFrame.setTitle("Chi tiết máy");

                                                    JLabel NameLabel = new JLabel("Tên máy:");
                                                    NameLabel.setBounds(10, 10, 140, 30);
                                                    JLabel jlabel = new JLabel(donHang.getMayMua());
                                                    jlabel.setBounds(100, 10, 200, 30);

                                                    JLabel NameLabel2 = new JLabel("Hãng:");
                                                    NameLabel2.setBounds(10, 50, 70, 30);
                                                    JLabel jlabel2 = new JLabel(donHang.getHang());
                                                    jlabel2.setBounds(100, 50, 100, 30);

                                                    JLabel NameLabel3 = new JLabel("Pin:");
                                                    NameLabel3.setBounds(10, 90, 70, 30);
                                                    JLabel jlabel3 = new JLabel(donHang.getPin() + "");
                                                    jlabel3.setBounds(100, 90, 200, 30);

                                                    JLabel NameLabel4 = new JLabel("Ram:");
                                                    NameLabel4.setBounds(10, 130, 100, 30);
                                                    JLabel jlabel4 = new JLabel(donHang.getRam() + "");
                                                    jlabel4.setBounds(100, 130, 200, 30);

                                                    JLabel NameLabel5 = new JLabel("Bộ nhớ trong:");
                                                    NameLabel5.setBounds(10, 170, 100, 30);
                                                    JLabel jlabel5 = new JLabel(donHang.getInternalMemory() + "");
                                                    jlabel5.setBounds(100, 170, 200, 30);

                                                    litFrame.add(NameLabel);
                                                    litFrame.add(NameLabel5);
                                                    litFrame.add(NameLabel2);
                                                    litFrame.add(NameLabel3);
                                                    litFrame.add(NameLabel4);
                                                    litFrame.add(jlabel);
                                                    litFrame.add(jlabel2);
                                                    litFrame.add(jlabel3);
                                                    litFrame.add(jlabel4);
                                                    litFrame.add(jlabel5);
                                                }
                                            }
                                        });
                                        button.setBounds(180, 50, 100, 30);

                                        JLabel nameLabel3 = new JLabel("Người mua:");
                                        nameLabel3.setBounds(10, 90, 70, 30);
                                        JLabel jLabel3 = new JLabel(donHang.getNguoiMua());
                                        jLabel3.setBounds(100, 90, 200, 30);

                                        JLabel nameLabel4 = new JLabel("Số điện thoại:");
                                        nameLabel4.setBounds(10, 130, 100, 30);
                                        JLabel jLabel4 = new JLabel(donHang.getDt());
                                        jLabel4.setBounds(100, 130, 200, 30);

                                        JLabel nameLabel5 = new JLabel("Giá tiền:");
                                        nameLabel5.setBounds(10, 170, 70, 30);
                                        JLabel jLabel5 = new JLabel(donHang.getGiaTien() + "");
                                        jLabel5.setBounds(100, 170, 200, 30);

                                        littleFrame.add(nameLabel);
                                        littleFrame.add(nameLabel5);
                                        littleFrame.add(nameLabel2);
                                        littleFrame.add(nameLabel3);
                                        littleFrame.add(nameLabel4);
                                        littleFrame.add(jLabel);
                                        littleFrame.add(jLabel2);
                                        littleFrame.add(jLabel3);
                                        littleFrame.add(jLabel4);
                                        littleFrame.add(jLabel5);
                                        littleFrame.add(button);
                                    }
                                }
                            }
                        }
                    });
                    listFrame.add(view);

                    JButton them=new JButton("Thêm");
                    them.setBounds(200,410,100,50);
                    them.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==them)
                            {
                                JFrame addFrame=new JFrame();
                                addFrame.setTitle("Thêm");
                                addFrame.setVisible(true);
                                addFrame.setSize(400,470);
                                addFrame.setLayout(null);
                                addFrame.setResizable(false);

                                JLabel nameLabel=new JLabel("Ngày mua(dd/mm/yyyy):");
                                nameLabel.setBounds(10,10,140,30);
                                JTextField textField=new JTextField();
                                textField.setBounds(150,10,200,30);

                                JLabel nameLabel2=new JLabel("Tên máy:");
                                nameLabel2.setBounds(10,50,70,30);
                                JTextField textField2=new JTextField();
                                textField2.setBounds(150,50,200,30);

                                JLabel nameLabel3=new JLabel("Hãng:");
                                nameLabel3.setBounds(10,90,70,30);
                                JTextField textField3=new JTextField();
                                textField3.setBounds(150,90,200,30);

                                JLabel nameLabel4=new JLabel("Pin:");
                                nameLabel4.setBounds(10,130,100,30);
                                JTextField textField4=new JTextField();
                                textField4.setBounds(150,130,200,30);

                                JLabel nameLabel5=new JLabel("Ram:");
                                nameLabel5.setBounds(10,170,70,30);
                                JTextField textField5=new JTextField();
                                textField5.setBounds(150,170,200,30);

                                JLabel nameLabel6=new JLabel("Bộ nhớ trong:");
                                nameLabel6.setBounds(10,210,100,30);
                                JTextField textField6=new JTextField();
                                textField6.setBounds(150,210,200,30);

                                JLabel nameLabel7=new JLabel("Người mua:");
                                nameLabel7.setBounds(10,250,70,30);
                                JTextField textField7=new JTextField();
                                textField7.setBounds(150,250,200,30);

                                JLabel nameLabel8=new JLabel("Số điện thoại:");
                                nameLabel8.setBounds(10,290,100,30);
                                JTextField textField8=new JTextField();
                                textField8.setBounds(150,290,200,30);

                                JLabel nameLabel9=new JLabel("Giá tiền:");
                                nameLabel9.setBounds(10,330,70,30);
                                JTextField textField9=new JTextField();
                                textField9.setBounds(150,330,200,30);

                                JButton add=new JButton("Thêm");
                                add.setBounds(100,380,200,40);
                                add.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==add)
                                        {
                                            if(textField.getText().equals("")||textField2.getText().equals("")||
                                                    textField3.getText().equals("")||textField4.getText().equals("")||
                                                    textField5.getText().equals("")||textField6.getText().equals("")||textField7.getText().equals("")||
                                                    textField8.getText().equals("")||textField9.getText().equals(""))
                                            {
                                                JOptionPane.showMessageDialog(null,
                                                        "Hãy nhập đủ thông tin");
                                            }
                                            else
                                            {
                                                boolean check=false;
                                                for(int i=0;i<listSp.size();i++)
                                                {
                                                    if(listSp.get(i).getName().equals(textField2.getText()))
                                                    {
                                                        check=true;
                                                        break;
                                                    }
                                                }
                                                if(check==false)
                                                {
                                                    JOptionPane.showMessageDialog(null,"Máy này đã hết!");
                                                }
                                                else
                                                {
                                                    int price=0;
                                                    boolean anocheck=false;
                                                    for(int i=0;i<listSp.size();i++)
                                                    {
                                                        if(listSp.get(i).getName().equals(textField2.getText())&&listSp.get(i).getHang().equals(textField3.getText())&&
                                                                listSp.get(i).getPin()==Integer.parseInt(textField4.getText())&&listSp.get(i).getRam()==Integer.parseInt(textField5.getText())&&
                                                                listSp.get(i).getInternalMemory()==Integer.parseInt(textField6.getText()))
                                                        {
                                                            anocheck=true;
                                                            price=listSp.get(i).getPrice();
                                                            break;
                                                        }
                                                    }
                                                    if(anocheck==false)
                                                    {
                                                        JOptionPane.showMessageDialog(null,"Hãy nhập đúng thông số!");
                                                    }
                                                    else
                                                    {
                                                        if(Integer.parseInt(textField9.getText())<price)
                                                        {
                                                            JOptionPane.showMessageDialog(null,"Giá bán thấp hơn giá gốc! Hãy bán giá cao hơn để không bị lỗ bạn nhé!");
                                                        }
                                                        else
                                                        {
                                                    DonHang newDon=new DonHang(textField.getText(),textField2.getText(),textField3.getText(),Integer.parseInt(textField4.getText()),
                                                            Integer.parseInt(textField5.getText()),Integer.parseInt(textField6.getText()),textField7.getText(),textField8.getText(),
                                                            Integer.parseInt(textField9.getText()));
                                                    for(int i=0;i<listSp.size();i++)
                                                    {
                                                        if(listSp.get(i).getName().equals(newDon.getMayMua())&&listSp.get(i).getHang().equals(newDon.getHang())&&
                                                                listSp.get(i).getPin()==newDon.getPin()&&listSp.get(i).getRam()==newDon.getRam()&&
                                                                listSp.get(i).getInternalMemory()==newDon.getInternalMemory())
                                                        {
                                                            if(listSp.get(i).getSoLuong()<=0)
                                                            {
                                                                JOptionPane.showMessageDialog(null,"Máy này đã hết!");
                                                            }
                                                            else {
                                                                listSp.get(i).setSoLuong(listSp.get(i).getSoLuong() - 1);
                                                                listDonHang.add(newDon);
                                                            }
                                                            break;
                                                        }
                                                    }
                                                    try {
                                                        LamTrangFile("DuLieuDonHang.txt");
                                                        GhiFileDonHang(listDonHang,"DuLieuDonHang.txt");
                                                    } catch (IOException ioException) {
                                                        ioException.printStackTrace();
                                                    }
                                                    data[0] =new Vector();
                                                    if(donTheoThang.size()==0)
                                                    {
                                                    for(int i=0;i<listDonHang.size();i++)
                                                    {
                                                        Vector newdata=new Vector();
                                                        newdata.add(listDonHang.get(i).getNgayMua());
                                                        newdata.add(listDonHang.get(i).getMayMua());
                                                        newdata.add(listDonHang.get(i).getNguoiMua());
                                                        newdata.add(listDonHang.get(i).getGiaTien());
                                                        data[0].add(newdata);
                                                    }
                                                    }
                                                    else
                                                    {
                                                        for(int i=0;i<donTheoThang.size();i++)
                                                        {
                                                            Vector newdata=new Vector();
                                                            newdata.add(donTheoThang.get(i).getNgayMua());
                                                            newdata.add(donTheoThang.get(i).getMayMua());
                                                            newdata.add(donTheoThang.get(i).getNguoiMua());
                                                            newdata.add(donTheoThang.get(i).getGiaTien());
                                                            data[0].add(newdata);
                                                        }
                                                    }
                                                    table.setModel(new DefaultTableModel(data[0],Header));
                                                }
                                            }
                                        }}}}
                                });

                                addFrame.add(nameLabel);
                                addFrame.add(textField);
                                addFrame.add(nameLabel2);
                                addFrame.add(textField2);
                                addFrame.add(nameLabel3);
                                addFrame.add(textField3);
                                addFrame.add(nameLabel4);
                                addFrame.add(textField4);
                                addFrame.add(nameLabel5);
                                addFrame.add(textField5);
                                addFrame.add(nameLabel6);
                                addFrame.add(textField6);
                                addFrame.add(nameLabel7);
                                addFrame.add(textField7);
                                addFrame.add(nameLabel8);
                                addFrame.add(textField8);
                                addFrame.add(nameLabel9);
                                addFrame.add(textField9);
                                addFrame.add(add);
                            }
                        }
                    });
                    JButton xoa=new JButton("Xóa");
                    xoa.setBounds(300,410,100,50);
                    xoa.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==xoa)
                            {
                                int pos=table.getSelectedRow();
                                if(pos<0) JOptionPane.showMessageDialog(null,"Hãy chọn đơn hàng cần xóa!");
                                else {
                                    if (donTheoThang.size() == 0) {
                                        for (int i = 0; i < listSp.size(); i++) {
                                            if (listSp.get(i).getName().equals(listDonHang.get(pos).getMayMua()) &&
                                                    listSp.get(i).getHang().equals(listDonHang.get(pos).getHang()) &&
                                                    listSp.get(i).getPin() == listDonHang.get(pos).getPin() &&
                                                    listSp.get(i).getRam() == listDonHang.get(pos).getRam() &&
                                                    listSp.get(i).getInternalMemory() == listDonHang.get(pos).getInternalMemory()) {
                                                listSp.get(i).setSoLuong(listSp.get(i).getSoLuong() + 1);
                                            }
                                        }
                                        listDonHang.remove(pos);
                                        try {
                                            LamTrangFile("DuLieuDonHang.txt");
                                            GhiFileDonHang(listDonHang, "DuLieuDonHang.txt");
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        }
                                        data[0] = new Vector();
                                        for(int i=0;i<listDonHang.size();i++)
                                        {
                                            Vector newdata=new Vector();
                                            newdata.add(listDonHang.get(i).getNgayMua());
                                            newdata.add(listDonHang.get(i).getMayMua());
                                            newdata.add(listDonHang.get(i).getNguoiMua());
                                            newdata.add(listDonHang.get(i).getGiaTien());
                                            data[0].add(newdata);
                                        }
                                        table.setModel(new DefaultTableModel(data[0], Header));
                                    }
                                    else
                                    {
                                        for (int i = 0; i < listSp.size(); i++) {
                                            if (listSp.get(i).getName().equals(donTheoThang.get(pos).getMayMua()) &&
                                                    listSp.get(i).getHang().equals(donTheoThang.get(pos).getHang()) &&
                                                    listSp.get(i).getPin() == donTheoThang.get(pos).getPin() &&
                                                    listSp.get(i).getRam() == donTheoThang.get(pos).getRam() &&
                                                    listSp.get(i).getInternalMemory() == donTheoThang.get(pos).getInternalMemory()) {
                                                listSp.get(i).setSoLuong(listSp.get(i).getSoLuong() + 1);
                                            }
                                        }
                                        for(int i=0;i<listDonHang.size();i++)
                                        {
                                            if(listDonHang.get(i).getNgayMua().equals(donTheoThang.get(pos).getNgayMua())&&
                                                    listDonHang.get(i).getMayMua().equals(donTheoThang.get(pos).getMayMua())&&
                                                    listDonHang.get(i).getHang().equals(donTheoThang.get(pos).getHang())&&
                                                    listDonHang.get(i).getPin()==donTheoThang.get(pos).getPin()&&
                                                    listDonHang.get(i).getRam()==donTheoThang.get(pos).getRam()&&
                                                    listDonHang.get(i).getInternalMemory()==donTheoThang.get(pos).getInternalMemory()&&
                                                    listDonHang.get(i).getGiaTien()==donTheoThang.get(pos).getGiaTien()&&
                                                    listDonHang.get(i).getNguoiMua().equals(donTheoThang.get(pos).getNguoiMua())&&
                                                    listDonHang.get(i).getDt().equals(donTheoThang.get(pos).getDt()))
                                            {
                                                listDonHang.remove(i);
                                                break;
                                            }
                                        }
                                        donTheoThang.remove(pos);
                                        try {
                                            LamTrangFile("DuLieuDonHang.txt");
                                            GhiFileDonHang(listDonHang, "DuLieuDonHang.txt");
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        }
                                        data[0] = new Vector();
                                        for(int i=0;i<donTheoThang.size();i++)
                                        {
                                            Vector newdata=new Vector();
                                            newdata.add(donTheoThang.get(i).getNgayMua());
                                            newdata.add(donTheoThang.get(i).getMayMua());
                                            newdata.add(donTheoThang.get(i).getNguoiMua());
                                            newdata.add(donTheoThang.get(i).getGiaTien());
                                            data[0].add(newdata);
                                        }
                                        table.setModel(new DefaultTableModel(data[0], Header));
                                    }
                                }
                            }
                        }
                    });

                    JButton sua=new JButton("Sửa");
                    sua.setBounds(400,410,100,50);
                    sua.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==sua)
                            {
                                int pos=table.getSelectedRow();
                                if(pos<0) JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần sửa!");
                                else {
                                    if (donTheoThang.size() == 0) {
                                        JFrame addFrame = new JFrame();
                                        addFrame.setTitle("Sửa");
                                        addFrame.setVisible(true);
                                        addFrame.setSize(400, 470);
                                        addFrame.setLayout(null);
                                        addFrame.setResizable(false);
                                        DonHang don = listDonHang.get(pos);

                                        JLabel nameLabel = new JLabel("Ngày mua(dd/mm/yyyy):");
                                        nameLabel.setBounds(10, 10, 140, 30);
                                        JTextField textField = new JTextField();
                                        textField.setBounds(150, 10, 200, 30);
                                        textField.setText(don.getNgayMua());

                                        JLabel nameLabel2 = new JLabel("Tên máy:");
                                        nameLabel2.setBounds(10, 50, 70, 30);
                                        JTextField textField2 = new JTextField();
                                        textField2.setBounds(150, 50, 200, 30);
                                        textField2.setText(don.getMayMua());

                                        JLabel nameLabel3 = new JLabel("Hãng:");
                                        nameLabel3.setBounds(10, 90, 70, 30);
                                        JTextField textField3 = new JTextField();
                                        textField3.setBounds(150, 90, 200, 30);
                                        textField3.setText(don.getHang());

                                        JLabel nameLabel4 = new JLabel("Pin:");
                                        nameLabel4.setBounds(10, 130, 100, 30);
                                        JTextField textField4 = new JTextField();
                                        textField4.setBounds(150, 130, 200, 30);
                                        textField4.setText(don.getPin() + "");

                                        JLabel nameLabel5 = new JLabel("Ram:");
                                        nameLabel5.setBounds(10, 170, 70, 30);
                                        JTextField textField5 = new JTextField();
                                        textField5.setBounds(150, 170, 200, 30);
                                        textField5.setText(don.getRam() + "");

                                        JLabel nameLabel6 = new JLabel("Bộ nhớ trong:");
                                        nameLabel6.setBounds(10, 210, 100, 30);
                                        JTextField textField6 = new JTextField();
                                        textField6.setBounds(150, 210, 200, 30);
                                        textField6.setText(don.getInternalMemory() + "");

                                        JLabel nameLabel7 = new JLabel("Người mua:");
                                        nameLabel7.setBounds(10, 250, 70, 30);
                                        JTextField textField7 = new JTextField();
                                        textField7.setBounds(150, 250, 200, 30);
                                        textField7.setText(don.getNguoiMua());

                                        JLabel nameLabel8 = new JLabel("Số điện thoại:");
                                        nameLabel8.setBounds(10, 290, 100, 30);
                                        JTextField textField8 = new JTextField();
                                        textField8.setBounds(150, 290, 200, 30);
                                        textField8.setText(don.getDt());

                                        JLabel nameLabel9 = new JLabel("Giá tiền:");
                                        nameLabel9.setBounds(10, 330, 70, 30);
                                        JTextField textField9 = new JTextField();
                                        textField9.setBounds(150, 330, 200, 30);
                                        textField9.setText(don.getGiaTien() + "");

                                        JButton update = new JButton("Sửa");
                                        update.setBounds(100, 380, 200, 40);
                                        update.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == update) {
                                                    int position = table.getSelectedRow();
                                                    if (position < 0)
                                                        JOptionPane.showMessageDialog(null, "Hãy chọn đơn hàng cần sửa!");
                                                    else {
                                                        if  (textField.getText().equals("") || textField2.getText().equals("") ||
                                                                textField3.getText().equals("") || textField4.getText().equals("") ||
                                                                textField5.getText().equals("")||textField6.getText().equals("")||
                                                                textField7.getText().equals("")||textField8.getText().equals("")||
                                                                textField9.getText().equals("")) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "Hãy nhập đủ thông tin");
                                                        } else {
                                                            boolean check = false;
                                                            for (int i = 0; i < listSp.size(); i++) {
                                                                if (listSp.get(i).getName().equals(textField2.getText())) {
                                                                    check = true;
                                                                    break;
                                                                }
                                                            }
                                                            if (check == false) {
                                                                JOptionPane.showMessageDialog(null, "Máy này đã hết!");
                                                            } else {
                                                                boolean KT = false;
                                                                int Price=0;
                                                                for (int i = 0; i < listSp.size(); i++) {
                                                                    if (listSp.get(i).getName().equals(textField2.getText()) &&
                                                                            listSp.get(i).getHang().equals(textField3.getText()) &&
                                                                            listSp.get(i).getPin() == Integer.parseInt(textField4.getText()) &&
                                                                            listSp.get(i).getRam() == Integer.parseInt(textField5.getText()) &&
                                                                            listSp.get(i).getInternalMemory() == Integer.parseInt(textField6.getText())) {
                                                                        KT = true;
                                                                        Price=listSp.get(i).getPrice();
                                                                        break;
                                                                    }
                                                                }
                                                                if (KT == false) {
                                                                    JOptionPane.showMessageDialog(null, "Hãy nhập đúng thông số!");
                                                                } else {
                                                                    if (Integer.parseInt(textField9.getText()) < Price) {
                                                                        JOptionPane.showMessageDialog(null, "Giá bán thấp hơn giá gốc! Hãy bán giá cao hơn để không bị lỗ bạn nhé!");
                                                                    } else {
                                                                        DonHang newDon = new DonHang(textField.getText(), textField2.getText(), textField3.getText(), Integer.parseInt(textField4.getText()),
                                                                                Integer.parseInt(textField5.getText()), Integer.parseInt(textField6.getText()), textField7.getText(), textField8.getText(),
                                                                                Integer.parseInt(textField9.getText()));
                                                                        boolean ck=false;
                                                                        for (int i = 0; i < listSp.size(); i++) {
                                                                            if (listSp.get(i).getName().equals(newDon.getMayMua()) &&
                                                                                    listSp.get(i).getHang().equals(newDon.getHang()) &&
                                                                                    listSp.get(i).getPin() == newDon.getPin() &&
                                                                                    listSp.get(i).getRam() == newDon.getRam() &&
                                                                                    listSp.get(i).getInternalMemory() == newDon.getInternalMemory()) {
                                                                                if(listSp.get(i).getSoLuong()<=0)
                                                                                {
                                                                                    JOptionPane.showMessageDialog(null,"Máy này đã hết!");
                                                                                }
                                                                                else {
                                                                                    listSp.get(i).setSoLuong(listSp.get(i).getSoLuong() - 1);
                                                                                    ck=true;
                                                                                }
                                                                            }
                                                                        }
                                                                        if(ck==true) {
                                                                            for (int j = 0; j < listSp.size(); j++) {
                                                                                if (listSp.get(j).getName().equals(listDonHang.get(pos).getMayMua()) &&
                                                                                        listSp.get(j).getHang().equals(listDonHang.get(pos).getHang()) &&
                                                                                        listSp.get(j).getPin() == listDonHang.get(pos).getPin() &&
                                                                                        listSp.get(j).getRam() == listDonHang.get(pos).getRam() &&
                                                                                        listSp.get(j).getInternalMemory() == listDonHang.get(pos).getInternalMemory()) {
                                                                                    listSp.get(j).setSoLuong(listSp.get(j).getSoLuong() + 1);
                                                                                }
                                                                            }
                                                                            listDonHang.remove(pos);
                                                                            listDonHang.add(pos, newDon);
                                                                        }
                                                                        try {
                                                                            LamTrangFile("DuLieuDonHang.txt");
                                                                            GhiFileDonHang(listDonHang, "DuLieuDonHang.txt");
                                                                        } catch (IOException ioException) {
                                                                            ioException.printStackTrace();
                                                                        }
                                                                        data[0] = new Vector();
                                                                        for(int i=0;i<listDonHang.size();i++)
                                                                        {
                                                                            Vector newdata=new Vector();
                                                                            newdata.add(listDonHang.get(i).getNgayMua());
                                                                            newdata.add(listDonHang.get(i).getMayMua());
                                                                            newdata.add(listDonHang.get(i).getNguoiMua());
                                                                            newdata.add(listDonHang.get(i).getGiaTien());
                                                                            data[0].add(newdata);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(data[0], Header));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        });

                                        addFrame.add(nameLabel);
                                        addFrame.add(textField);
                                        addFrame.add(nameLabel2);
                                        addFrame.add(textField2);
                                        addFrame.add(nameLabel3);
                                        addFrame.add(textField3);
                                        addFrame.add(nameLabel4);
                                        addFrame.add(textField4);
                                        addFrame.add(nameLabel5);
                                        addFrame.add(textField5);
                                        addFrame.add(nameLabel6);
                                        addFrame.add(textField6);
                                        addFrame.add(nameLabel7);
                                        addFrame.add(textField7);
                                        addFrame.add(nameLabel8);
                                        addFrame.add(textField8);
                                        addFrame.add(nameLabel9);
                                        addFrame.add(textField9);
                                        addFrame.add(update);
                                    }
                                    else
                                    {
                                        JFrame addFrame = new JFrame();
                                        addFrame.setTitle("Sửa");
                                        addFrame.setVisible(true);
                                        addFrame.setSize(400, 470);
                                        addFrame.setLayout(null);
                                        addFrame.setResizable(false);
                                        DonHang don = donTheoThang.get(pos);

                                        JLabel nameLabel = new JLabel("Ngày mua(dd/mm/yyyy):");
                                        nameLabel.setBounds(10, 10, 140, 30);
                                        JTextField textField = new JTextField();
                                        textField.setBounds(150, 10, 200, 30);
                                        textField.setText(don.getNgayMua());

                                        JLabel nameLabel2 = new JLabel("Tên máy:");
                                        nameLabel2.setBounds(10, 50, 70, 30);
                                        JTextField textField2 = new JTextField();
                                        textField2.setBounds(150, 50, 200, 30);
                                        textField2.setText(don.getMayMua());

                                        JLabel nameLabel3 = new JLabel("Hãng:");
                                        nameLabel3.setBounds(10, 90, 70, 30);
                                        JTextField textField3 = new JTextField();
                                        textField3.setBounds(150, 90, 200, 30);
                                        textField3.setText(don.getHang());

                                        JLabel nameLabel4 = new JLabel("Pin:");
                                        nameLabel4.setBounds(10, 130, 100, 30);
                                        JTextField textField4 = new JTextField();
                                        textField4.setBounds(150, 130, 200, 30);
                                        textField4.setText(don.getPin() + "");

                                        JLabel nameLabel5 = new JLabel("Ram:");
                                        nameLabel5.setBounds(10, 170, 70, 30);
                                        JTextField textField5 = new JTextField();
                                        textField5.setBounds(150, 170, 200, 30);
                                        textField5.setText(don.getRam() + "");

                                        JLabel nameLabel6 = new JLabel("Bộ nhớ trong:");
                                        nameLabel6.setBounds(10, 210, 100, 30);
                                        JTextField textField6 = new JTextField();
                                        textField6.setBounds(150, 210, 200, 30);
                                        textField6.setText(don.getInternalMemory() + "");

                                        JLabel nameLabel7 = new JLabel("Người mua:");
                                        nameLabel7.setBounds(10, 250, 70, 30);
                                        JTextField textField7 = new JTextField();
                                        textField7.setBounds(150, 250, 200, 30);
                                        textField7.setText(don.getNguoiMua());

                                        JLabel nameLabel8 = new JLabel("Số điện thoại:");
                                        nameLabel8.setBounds(10, 290, 100, 30);
                                        JTextField textField8 = new JTextField();
                                        textField8.setBounds(150, 290, 200, 30);
                                        textField8.setText(don.getDt());

                                        JLabel nameLabel9 = new JLabel("Giá tiền:");
                                        nameLabel9.setBounds(10, 330, 70, 30);
                                        JTextField textField9 = new JTextField();
                                        textField9.setBounds(150, 330, 200, 30);
                                        textField9.setText(don.getGiaTien() + "");

                                        JButton update = new JButton("Sửa");
                                        update.setBounds(100, 380, 200, 40);
                                        update.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == update) {
                                                    int position = table.getSelectedRow();
                                                    if (position < 0)
                                                        JOptionPane.showMessageDialog(null, "Hãy chọn đơn hàng cần sửa!");
                                                    else {
                                                        if (textField.getText().equals("") || textField2.getText().equals("") ||
                                                                textField3.getText().equals("") || textField4.getText().equals("") ||
                                                                textField5.getText().equals("")||textField6.getText().equals("")||
                                                        textField7.getText().equals("")||textField8.getText().equals("")||
                                                        textField9.getText().equals("")) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "Hãy nhập đủ thông tin");
                                                        } else {
                                                            boolean check = false;
                                                            for (int i = 0; i < listSp.size(); i++) {
                                                                if (listSp.get(i).getName().equals(textField2.getText())) {
                                                                    check = true;
                                                                    break;
                                                                }
                                                            }
                                                            if (check == false) {
                                                                JOptionPane.showMessageDialog(null, "Máy này đã hết!");
                                                            } else {
                                                                int Price=0;
                                                                boolean KT = false;
                                                                for (int i = 0; i < listSp.size(); i++) {
                                                                    if (listSp.get(i).getName().equals(textField2.getText()) &&
                                                                            listSp.get(i).getHang().equals(textField3.getText()) &&
                                                                            listSp.get(i).getPin() == Integer.parseInt(textField4.getText()) &&
                                                                            listSp.get(i).getRam() == Integer.parseInt(textField5.getText()) &&
                                                                            listSp.get(i).getInternalMemory() == Integer.parseInt(textField6.getText())) {
                                                                        KT = true;
                                                                        Price=listSp.get(i).getPrice();
                                                                        break;
                                                                    }
                                                                }
                                                                if (KT == false) {
                                                                    JOptionPane.showMessageDialog(null, "Hãy nhập đúng thông số!");
                                                                } else {
                                                                    if (Integer.parseInt(textField9.getText()) < Price) {
                                                                        JOptionPane.showMessageDialog(null, "Giá bán thấp hơn giá gốc! Hãy bán giá cao hơn để không bị lỗ bạn nhé!");
                                                                    } else {
                                                                        DonHang newDon = new DonHang(textField.getText(), textField2.getText(), textField3.getText(), Integer.parseInt(textField4.getText()),
                                                                                Integer.parseInt(textField5.getText()), Integer.parseInt(textField6.getText()), textField7.getText(), textField8.getText(),
                                                                                Integer.parseInt(textField9.getText()));
                                                                        boolean ck=false;
                                                                        for (int i = 0; i < listSp.size(); i++) {
                                                                            if (listSp.get(i).getName().equals(newDon.getMayMua()) &&
                                                                                    listSp.get(i).getHang().equals(newDon.getHang()) &&
                                                                                    listSp.get(i).getPin() == newDon.getPin() &&
                                                                                    listSp.get(i).getRam() == newDon.getRam() &&
                                                                                    listSp.get(i).getInternalMemory() == newDon.getInternalMemory()) {
                                                                                if(listSp.get(i).getSoLuong()<=0)
                                                                                {
                                                                                    JOptionPane.showMessageDialog(null,"Máy này đã hết!");
                                                                                }
                                                                                else {
                                                                                    listSp.get(i).setSoLuong(listSp.get(i).getSoLuong() - 1);
                                                                                    ck=true;
                                                                                }
                                                                            }
                                                                        }
                                                                        if(ck==true) {
                                                                            for (int j = 0; j < listSp.size(); j++) {
                                                                                if (listSp.get(j).getName().equals(donTheoThang.get(pos).getMayMua()) &&
                                                                                        listSp.get(j).getHang().equals(donTheoThang.get(pos).getHang()) &&
                                                                                        listSp.get(j).getPin() == donTheoThang.get(pos).getPin() &&
                                                                                        listSp.get(j).getRam() == donTheoThang.get(pos).getRam() &&
                                                                                        listSp.get(j).getInternalMemory() == donTheoThang.get(pos).getInternalMemory()) {
                                                                                    listSp.get(j).setSoLuong(listSp.get(j).getSoLuong() + 1);
                                                                                }
                                                                            }
                                                                            for (int i = 0; i < listDonHang.size(); i++) {
                                                                                if (listDonHang.get(i).getNgayMua().equals(donTheoThang.get(pos).getNgayMua()) &&
                                                                                        listDonHang.get(i).getMayMua().equals(donTheoThang.get(pos).getMayMua()) &&
                                                                                        listDonHang.get(i).getHang().equals(donTheoThang.get(pos).getHang()) &&
                                                                                        listDonHang.get(i).getPin() == donTheoThang.get(pos).getPin() &&
                                                                                        listDonHang.get(i).getRam() == donTheoThang.get(pos).getRam() &&
                                                                                        listDonHang.get(i).getInternalMemory() == donTheoThang.get(pos).getInternalMemory() &&
                                                                                        listDonHang.get(i).getGiaTien() == donTheoThang.get(pos).getGiaTien() &&
                                                                                        listDonHang.get(i).getNguoiMua().equals(donTheoThang.get(pos).getNguoiMua()) &&
                                                                                        listDonHang.get(i).getDt().equals(donTheoThang.get(pos).getDt())) {
                                                                                    listDonHang.remove(i);
                                                                                    listDonHang.add(i, newDon);
                                                                                    break;
                                                                                }
                                                                            }
                                                                            donTheoThang.remove(pos);
                                                                            donTheoThang.add(pos, newDon);
                                                                        }
                                                                        try {
                                                                            LamTrangFile("DuLieuDonHang.txt");
                                                                            GhiFileDonHang(listDonHang, "DuLieuDonHang.txt");
                                                                        } catch (IOException ioException) {
                                                                            ioException.printStackTrace();
                                                                        }
                                                                        data[0] = new Vector();
                                                                        for(int i=0;i<donTheoThang.size();i++)
                                                                        {
                                                                            Vector newdata=new Vector();
                                                                            newdata.add(donTheoThang.get(i).getNgayMua());
                                                                            newdata.add(donTheoThang.get(i).getMayMua());
                                                                            newdata.add(donTheoThang.get(i).getNguoiMua());
                                                                            newdata.add(donTheoThang.get(i).getGiaTien());
                                                                            data[0].add(newdata);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(data[0], Header));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        });

                                        addFrame.add(nameLabel);
                                        addFrame.add(textField);
                                        addFrame.add(nameLabel2);
                                        addFrame.add(textField2);
                                        addFrame.add(nameLabel3);
                                        addFrame.add(textField3);
                                        addFrame.add(nameLabel4);
                                        addFrame.add(textField4);
                                        addFrame.add(nameLabel5);
                                        addFrame.add(textField5);
                                        addFrame.add(nameLabel6);
                                        addFrame.add(textField6);
                                        addFrame.add(nameLabel7);
                                        addFrame.add(textField7);
                                        addFrame.add(nameLabel8);
                                        addFrame.add(textField8);
                                        addFrame.add(nameLabel9);
                                        addFrame.add(textField9);
                                        addFrame.add(update);
                                    }
                                }
                            }
                        }
                    });

                    listFrame.add(label);
                    listFrame.add(scrollPane);
                    listFrame.add(them);
                    listFrame.add(xoa);
                    listFrame.add(sua);
                    listFrame.add(selectHang);
                }
            }
        });

        buttonTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==buttonTK)
                {
                    JFrame frame3=new JFrame();
                    frame3.setTitle("Thống kê báo cáo");
                    frame3.setVisible(true);
                    frame3.setSize(570,200);
                    frame3.setLayout(null);
                    frame3.setResizable(false);

                    JButton button1=new JButton("Mặt hàng sắp hết");
                    button1.setBounds(10,50,150,70);
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==button1) {
                                final Vector[] data = {new Vector()};
                                for (int i = 0; i < listSp.size(); i++) {
                                    if (listSp.get(i).getSoLuong() < 10) {
                                        Vector dataMoi = new Vector();
                                        dataMoi.add(listSp.get(i).getName());
                                        dataMoi.add(listSp.get(i).getHang());
                                        dataMoi.add(listSp.get(i).getPin());
                                        dataMoi.add(listSp.get(i).getRam());
                                        dataMoi.add(listSp.get(i).getInternalMemory());
                                        dataMoi.add(listSp.get(i).getPrice());
                                        dataMoi.add(listSp.get(i).getSoLuong());
                                        data[0].add(dataMoi);
                                    }
                                }
                                if (data[0].size() == 0) {
                                    JOptionPane.showMessageDialog(null, "Không có mặt hàng nào sắp hết!");
                                } else {
                                    frame3.dispose();
                                    JFrame jFrame = new JFrame();
                                    jFrame.setResizable(false);
                                    jFrame.setSize(700, 500);
                                    jFrame.setTitle("Mặt hàng sắp hết");
                                    jFrame.setLayout(null);
                                    jFrame.setVisible(true);

                                    JLabel label = new JLabel("MẶT HÀNG SẮP HẾT");
                                    label.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                                    label.setBounds(250, 0, 200, 50);
                                    label.setHorizontalAlignment(0);

                                    JTable table = new JTable();
                                    Vector Header = new Vector();
                                    Header.add("Tên");
                                    Header.add("Hãng");
                                    Header.add("Pin");
                                    Header.add("Ram");
                                    Header.add("Bộ nhớ trong");
                                    Header.add("Giá");
                                    Header.add("Còn lại");
                                    table.setModel(new DefaultTableModel(data[0], Header));
                                    JScrollPane scrollPane = new JScrollPane(table);
                                    scrollPane.setBounds(100, 50, 500, 400);

                                    jFrame.add(label);
                                    jFrame.add(scrollPane);
                                }
                            }
                        }
                    });
                    frame3.add(button1);

                    JButton button2=new JButton("Mặt hàng bán chạy");
                    button2.setBounds(200,50,160,70);
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==button2)
                            {
                                frame3.dispose();
                                JFrame Frame=new JFrame();
                                Frame.setResizable(false);
                                Frame.setSize(300,200);
                                Frame.setTitle("Mặt hàng bán chạy");
                                Frame.setLayout(null);
                                Frame.setVisible(true);

                                JLabel label=new JLabel("Nhập thời gian");
                                label.setBounds(100,0,100,50);
                                JLabel thangLabel=new JLabel("Tháng:");
                                thangLabel.setBounds(10,50,50,30);
                                JTextField thang=new JTextField();
                                thang.setBounds(60,50,50,30);
                                JLabel namLabel=new JLabel("Năm:");
                                namLabel.setBounds(130,50,50,30);
                                JTextField nam=new JTextField();
                                nam.setBounds(180,50,50,30);

                                JButton button=new JButton("Xác nhận");
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==button) {
                                            Vector<DonHang> listThangTrc = new Vector<>();
                                            for (int i = 0; i < listDonHang.size(); i++) {
                                                StringTokenizer tokenizer = new StringTokenizer(listDonHang.get(i).getNgayMua(), "/");
                                                Vector<String> ngayMua = new Vector<>();
                                                while (tokenizer.hasMoreTokens()) {
                                                    ngayMua.add(tokenizer.nextToken());
                                                }
                                                int thangmua = Integer.parseInt(ngayMua.get(1));
                                                int nammua = Integer.parseInt(ngayMua.get(2));
                                                if (Integer.parseInt(thang.getText()) == thangmua && Integer.parseInt(nam.getText()) == nammua) {
                                                    listThangTrc.add(listDonHang.get(i));
                                                }
                                            }

                                            if (listThangTrc.size() == 0) {
                                                JOptionPane.showMessageDialog(null, "Không có sản phẩm nào được bán trong tháng này!");
                                            } else {
                                                Vector<DonHang> duyet = new Vector<>();
                                                final Vector[] data = {new Vector()};
                                                for (int i = 0; i < listThangTrc.size(); i++) {
                                                    boolean check = false;
                                                    for (int k = 0; k < duyet.size(); k++) {
                                                        if (listThangTrc.get(i).getMayMua().equals(duyet.get(k).getMayMua()) &&
                                                                listThangTrc.get(i).getHang().equals(duyet.get(k).getHang()) &&
                                                                listThangTrc.get(i).getPin() == duyet.get(k).getPin() &&
                                                                listThangTrc.get(i).getRam() == duyet.get(k).getRam() &&
                                                                listThangTrc.get(i).getInternalMemory() == duyet.get(k).getInternalMemory()
                                                                && listThangTrc.get(i).getGiaTien() == duyet.get(k).getGiaTien()) {
                                                            check = true;
                                                            break;
                                                        }
                                                    }
                                                    if (check == true) {
                                                        continue;
                                                    }
                                                    int count = 1;
                                                    for (int j = i + 1; j < listThangTrc.size(); j++) {
                                                        if (listThangTrc.get(i).getMayMua().equals(listThangTrc.get(j).getMayMua()) &&
                                                                listThangTrc.get(i).getHang().equals(listThangTrc.get(j).getHang()) &&
                                                                listThangTrc.get(i).getPin() == listThangTrc.get(j).getPin() &&
                                                                listThangTrc.get(i).getRam() == listThangTrc.get(j).getRam() &&
                                                                listThangTrc.get(i).getInternalMemory() == listThangTrc.get(j).getInternalMemory()) {
                                                            count++;
                                                        }
                                                    }
                                                    duyet.add(listThangTrc.get(i));
                                                    if (count >= 10) {
                                                        Vector dataMoi = new Vector();
                                                        dataMoi.add(listThangTrc.get(i).getMayMua());
                                                        dataMoi.add(listThangTrc.get(i).getHang());
                                                        dataMoi.add(listThangTrc.get(i).getPin());
                                                        dataMoi.add(listThangTrc.get(i).getRam());
                                                        dataMoi.add(listThangTrc.get(i).getInternalMemory());
                                                        dataMoi.add(count);
                                                        data[0].add(dataMoi);
                                                    }
                                                }
                                                if (data[0].size() == 0) {
                                                    JOptionPane.showMessageDialog(null, "Số lượng các mặt hàng đã bán khá ít nên không có mặt hàng nào bán chạy trong tháng này!");
                                                } else {
                                                    Frame.dispose();
                                                    JFrame jFrame = new JFrame();
                                                    jFrame.setResizable(false);
                                                    jFrame.setSize(700, 500);
                                                    jFrame.setTitle("Mặt hàng bán chạy");
                                                    jFrame.setLayout(null);
                                                    jFrame.setVisible(true);

                                                    JLabel label = new JLabel("MẶT HÀNG BÁN CHẠY");
                                                    label.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                                                    label.setBounds(250, 0, 250, 50);
                                                    label.setHorizontalAlignment(0);

                                                    JTable table = new JTable();
                                                    Vector Header = new Vector();
                                                    Header.add("Tên");
                                                    Header.add("Hãng");
                                                    Header.add("Pin");
                                                    Header.add("Ram");
                                                    Header.add("Bộ nhớ trong");
                                                    Header.add("Đã bán");
                                                    table.setModel(new DefaultTableModel(data[0], Header));
                                                    JScrollPane scrollPane = new JScrollPane(table);
                                                    scrollPane.setBounds(100, 50, 500, 400);
                                                    jFrame.add(label);
                                                    jFrame.add(scrollPane);
                                                }
                                            }
                                        }
                                    }
                                });
                                button.setBounds(100,100,100,30);

                                Frame.add(label);
                                Frame.add(thangLabel);
                                Frame.add(thang);
                                Frame.add(namLabel);
                                Frame.add(nam);
                                Frame.add(button);

                            }
                        }
                    });
                    frame3.add(button2);

                    JButton button3=new JButton("Doanh thu");
                    button3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==button3)
                            {
                                frame3.dispose();
                                JFrame jFrame=new JFrame();
                                jFrame.setResizable(false);
                                jFrame.setSize(300,200);
                                jFrame.setTitle("Doanh thu");
                                jFrame.setLayout(null);
                                jFrame.setVisible(true);

                                JLabel label=new JLabel("Nhập thời gian");
                                label.setBounds(100,0,100,50);
                                JLabel thangLabel=new JLabel("Tháng:");
                                thangLabel.setBounds(10,50,50,30);
                                JTextField thang=new JTextField();
                                thang.setBounds(60,50,50,30);
                                JLabel namLabel=new JLabel("Năm:");
                                namLabel.setBounds(130,50,50,30);
                                JTextField nam=new JTextField();
                                nam.setBounds(180,50,50,30);

                                JButton button=new JButton("Xác nhận");
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(e.getSource()==button)
                                        {
                                            if(thang.getText().equals("")||nam.getText().equals(""))
                                            {
                                                JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin!");
                                            }
                                            else
                                            {
                                                jFrame.dispose();
                                                JFrame childFrame=new JFrame();
                                                childFrame.setResizable(false);
                                                childFrame.setSize(400,400);
                                                childFrame.setTitle("Doanh thu "+thang.getText()+"/"+nam.getText());
                                                childFrame.setLayout(null);
                                                childFrame.setVisible(true);

                                                JLabel title=new JLabel("Doanh thu "+thang.getText()+"/"+nam.getText());
                                                title.setBounds(100,0,200,50);
                                                title.setBorder(BorderFactory.createLineBorder(Color.RED,3));
                                                title.setHorizontalAlignment(0);
                                                JLabel SL=new JLabel("Số lượng máy đã bán: "+SoLuongDaBan(Integer.parseInt(thang.getText()),
                                                        Integer.parseInt(nam.getText())));
                                                SL.setBounds(100,100,300,50);
                                                JLabel doanhthu=new JLabel("Doanh thu: "+DoanhThu(Integer.parseInt(thang.getText()),
                                                        Integer.parseInt(nam.getText())));
                                                doanhthu.setBounds(100,150,300,50);
                                                JLabel loinhuan=new JLabel("Lợi nhuận: "+LoiNhuan(Integer.parseInt(thang.getText()),
                                                        Integer.parseInt(nam.getText())));
                                                loinhuan.setBounds(100,200,300,50);

                                                childFrame.add(title);
                                                childFrame.add(SL);
                                                childFrame.add(doanhthu);
                                                childFrame.add(loinhuan);
                                            }
                                        }
                                    }
                                });
                                button.setBounds(100,100,100,30);

                                jFrame.add(label);
                                jFrame.add(thangLabel);
                                jFrame.add(thang);
                                jFrame.add(namLabel);
                                jFrame.add(nam);
                                jFrame.add(button);

                            }
                        }
                    });
                    button3.setBounds(390,50,150,70);
                    frame3.add(button3);
                }
            }
        });

        frame.add(northPanel,BorderLayout.NORTH);
        frame.add(centerPanel,BorderLayout.CENTER);
    }
    static double FileSize(String filename)
    {
        File file=new File(filename);
        if(!file.exists()||!file.isFile()) return 0;
        else
        {
            return file.length();
        }
    }
    static void GhiFile(Vector a,String Filename) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(Filename));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            HangDienThoai object= (HangDienThoai) a.get(i);
            out.writeObject(object);
        }
        out.flush();
        out.close();
    }
    static void GhiFile2(Vector a,String Filename) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(Filename));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            DienThoai object= (DienThoai) a.get(i);
            out.writeObject(object);
        }
        out.flush();
        out.close();
    }
    static Vector DocFile(Vector a,String Filename) throws IOException, ClassNotFoundException {
        ObjectInputStream is=new ObjectInputStream(new FileInputStream(Filename));
        int count=is.readInt();
        for(int i=0;i<count;i++)
        {
            HangDienThoai object= (HangDienThoai) is.readObject();
            a.add(object);
        }
        is.close();
        return a;
    }
    static Vector DocFile2(Vector a,String Filename) throws IOException, ClassNotFoundException {
        ObjectInputStream is=new ObjectInputStream(new FileInputStream(Filename));
        int count=is.readInt();
        for(int i=0;i<count;i++)
        {
            DienThoai object= (DienThoai) is.readObject();
            a.add(object);
        }
        is.close();
        return a;
    }
    static void GhiFileDonHang(Vector a,String Filename) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(Filename));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            DonHang object= (DonHang) a.get(i);
            out.writeObject(object);
        }
        out.flush();
        out.close();
    }
    static Vector DocFileDonHang(Vector a,String Filename) throws IOException, ClassNotFoundException {
        ObjectInputStream is=new ObjectInputStream(new FileInputStream(Filename));
        int count=is.readInt();
        for(int i=0;i<count;i++)
        {
            DonHang object= (DonHang) is.readObject();
            a.add(object);
        }
        is.close();
        return a;
    }
    static void LamTrangFile(String filename) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(filename));
        out.writeBytes("");
        out.flush();
        out.close();
    }
    static int SoLuongDaBan(int thang,int nam)
    {
        int count=0;
        for(int i=0;i<listDonHang.size();i++)
        {
            StringTokenizer tokenizer=new StringTokenizer(listDonHang.get(i).getNgayMua(),"/");
            Vector<String>data=new Vector<>();
            while(tokenizer.hasMoreTokens())
            {
                data.add(tokenizer.nextToken());
            }
            int Thang=Integer.parseInt(data.get(1));
            int Nam=Integer.parseInt(data.get(2));
            if(Thang==thang&&Nam==nam)
            {
                count++;
            }
        }
        return count;
    }
    static int DoanhThu(int thang,int nam)
    {
        int doanhthu=0;
        for(int i=0;i<listDonHang.size();i++)
        {
            StringTokenizer tokenizer=new StringTokenizer(listDonHang.get(i).getNgayMua(),"/");
            Vector<String>data=new Vector<>();
            while(tokenizer.hasMoreTokens())
            {
                data.add(tokenizer.nextToken());
            }
            int Thang=Integer.parseInt(data.get(1));
            int Nam=Integer.parseInt(data.get(2));
            if(Thang==thang&&Nam==nam)
            {
                doanhthu+=listDonHang.get(i).getGiaTien();
            }
        }
        return doanhthu;
    }
    static int LoiNhuan(int thang,int nam)
    {
        int loinhuan=0;
        for(int i=0;i<listDonHang.size();i++)
        {
            StringTokenizer tokenizer=new StringTokenizer(listDonHang.get(i).getNgayMua(),"/");
            Vector<String>data=new Vector<>();
            while(tokenizer.hasMoreTokens())
            {
                data.add(tokenizer.nextToken());
            }
            int Thang=Integer.parseInt(data.get(1));
            int Nam=Integer.parseInt(data.get(2));
            if(Thang==thang&&Nam==nam)
            {
                for(int j=0;j<listSp.size();j++)
                {
                    if(listDonHang.get(i).getMayMua().equals(listSp.get(j).getName())&&
                            listDonHang.get(i).getHang().equals(listSp.get(j).getHang())&&
                            listDonHang.get(i).getPin()==listSp.get(j).getPin()&&
                            listDonHang.get(i).getRam()==listSp.get(j).getRam()&&
                            listDonHang.get(i).getInternalMemory()==listSp.get(j).getInternalMemory())
                    {
                        loinhuan+=listDonHang.get(i).getGiaTien()-listSp.get(j).getPrice();
                        break;
                    }
                }
            }
        }
        return loinhuan;
    }
}
