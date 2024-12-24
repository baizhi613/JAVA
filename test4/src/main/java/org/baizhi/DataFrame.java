package org.baizhi;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, additionalField; // 额外的字段用于不同表
    private JComboBox<String> tableSelector;
    private String[] tableNames = {"班级", "学生", "课程", "选课"};

    public DataFrame() {
        // 设置窗口标题
        setTitle("数据管理");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建表格
        tableModel = new DefaultTableModel(new String[]{"ID", "名称"}, 0); // 默认表结构
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 创建输入框和按钮
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("名称:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        additionalField = new JTextField(); // 用于课程表的额外字段（如学分）
        inputPanel.add(new JLabel("额外信息:"));
        inputPanel.add(additionalField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("添加");
        JButton loadButton = new JButton("加载");

        // 按钮事件处理
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadRecords();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(loadButton);

        // Table选择器
        tableSelector = new JComboBox<>(tableNames);
        tableSelector.addActionListener(e -> loadRecords()); // 当选择表时加载数据

        JPanel selectorPanel = new JPanel();
        selectorPanel.add(new JLabel("选择表:"));
        selectorPanel.add(tableSelector);

        // 将组件添加到窗口
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(selectorPanel, BorderLayout.WEST);

        // 初始化数据
        loadRecords();
    }

    // 根据选中的表加载数据
    private void loadRecords() {
        String selectedTable = (String) tableSelector.getSelectedItem();
        switch (selectedTable) {
            case "班级":
                loadClasses();
                break;
            case "学生":
                loadStudents();
                break;
            case "课程":
                loadSubjects();
                break;
            case "选课":
                loadElectives();
                break;
        }
    }

    // 发送POST请求，添加数据
    private void addRecord() {
        String id = idField.getText();
        String name = nameField.getText();
        String additional = additionalField.getText(); // 获取额外信息

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID和名称不能为空！");
            return;
        }

        try {
            String selectedTable = (String) tableSelector.getSelectedItem();
            URL url = new URL("http://localhost:8080/" + selectedTable.toLowerCase());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // 构建JSON对象
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("name", name);
            if (!additional.isEmpty()) {
                jsonObject.put("additional", additional); // 对于课程，加入学分字段
            }

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObject.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, selectedTable + "添加成功！");
                loadRecords(); // 刷新列表
            } else {
                JOptionPane.showMessageDialog(this, selectedTable + "添加失败！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "发生错误：" + ex.getMessage());
        }
    }

    // 发送GET请求，加载班级数据
    private void loadClasses() {
        try {
            URL url = new URL("http://localhost:8080/classes");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 解析JSON并更新表格
                JSONArray classes = new JSONArray(response.toString());
                tableModel.setRowCount(0); // 清空表格
                for (int i = 0; i < classes.length(); i++) {
                    JSONObject classObj = classes.getJSONObject(i);
                    String id = classObj.getString("classid");
                    String name = classObj.getString("classname");
                    tableModel.addRow(new Object[]{id, name});
                }
            } else {
                JOptionPane.showMessageDialog(this, "加载班级列表失败！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "发生错误：" + ex.getMessage());
        }
    }

    // 发送GET请求，加载学生数据
    private void loadStudents() {
        try {
            URL url = new URL("http://localhost:8080/students");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 解析JSON并更新表格
                JSONArray students = new JSONArray(response.toString());
                tableModel.setRowCount(0); // 清空表格
                for (int i = 0; i < students.length(); i++) {
                    JSONObject studentObj = students.getJSONObject(i);
                    String id = studentObj.getString("studentid");
                    String name = studentObj.getString("studentname");
                    tableModel.addRow(new Object[]{id, name});
                }
            } else {
                JOptionPane.showMessageDialog(this, "加载学生列表失败！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "发生错误：" + ex.getMessage());
        }
    }

    // 发送GET请求，加载课程数据
    private void loadSubjects() {
        try {
            URL url = new URL("http://localhost:8080/subjects");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 解析JSON并更新表格
                JSONArray subjects = new JSONArray(response.toString());
                tableModel.setRowCount(0); // 清空表格
                for (int i = 0; i < subjects.length(); i++) {
                    JSONObject subjectObj = subjects.getJSONObject(i);
                    String id = subjectObj.getString("subjectid");
                    String name = subjectObj.getString("subjectname");
                    tableModel.addRow(new Object[]{id, name});
                }
            } else {
                JOptionPane.showMessageDialog(this, "加载课程列表失败！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "发生错误：" + ex.getMessage());
        }
    }

    // 发送GET请求，加载选课数据
    private void loadElectives() {
        try {
            URL url = new URL("http://localhost:8080/electives");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 解析JSON并更新表格
                JSONArray electives = new JSONArray(response.toString());
                tableModel.setRowCount(0); // 清空表格
                for (int i = 0; i < electives.length(); i++) {
                    JSONObject electiveObj = electives.getJSONObject(i);
                    String id = electiveObj.getString("studentid");
                    String name = electiveObj.getString("subjectid");
                    tableModel.addRow(new Object[]{id, name});
                }
            } else {
                JOptionPane.showMessageDialog(this, "加载选课列表失败！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "发生错误：" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DataFrame().setVisible(true);
        });
    }
}
