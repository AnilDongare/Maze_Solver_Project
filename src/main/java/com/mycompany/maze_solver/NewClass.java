package com.mycompany.maze_solver;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;

public class NewClass extends JFrame {
    private int[][] maze = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    public List<Integer> path = new ArrayList<>();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public NewClass() {
        setTitle("Maze Solver");
        setSize(640, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DepthFirst.searchpath(maze, 1, 1, path);
        System.out.println(path);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Create a gradient paint for the background
        Color startColor = new Color(255, 255, 255); // Define your desired start color
        Color endColor = new Color(200, 200, 255); // Define your desired end color
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);

        // Set the gradient paint as the background
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the maze
        g2d.translate(50, 50);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                Color color;
                switch (maze[i][j]) {
                    case 1:
                        color = Color.BLACK;
                        break;
                    case 9:
                        color = Color.RED;
                        break;
                    default:
                        color = Color.WHITE;
                        break;
                }
                g2d.setColor(color);
                g2d.fillRect(30 * j, 30 * i, 30, 30);
                g2d.setColor(Color.RED);
                g2d.drawRect(30 * j, 30 * i, 30, 30);
            }
        }

        // Draw the path
        for (int i = 0; i < path.size(); i += 2) {
            int pathx = path.get(i);
            int pathy = path.get(i + 1);

            g2d.setColor(Color.GREEN);
            g2d.fillOval(30 * pathx + 5, 30 * pathy + 5, 20, 20);
        }

        // Add the watermark
        g2d.setColor(Color.PINK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        String watermark = "Developed by Anil Dongare";
        int watermarkWidth = g2d.getFontMetrics().stringWidth(watermark);
        int x = (getWidth() - watermarkWidth) / 2;
        int y = 450;
        g2d.drawString(watermark, x, y);

        // Display real-time timing
        g2d.setColor(Color.PINK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        String currentTime = timeFormat.format(new Date());
        int timeWidth = g2d.getFontMetrics().stringWidth(currentTime);
        int timeX = 100;
        int timeY = 450;
        g2d.drawString(currentTime, timeX, timeY);

        // Display current date
        g2d.setColor(Color.PINK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        String currentDate = dateFormat.format(new Date());
        int dateWidth = g2d.getFontMetrics().stringWidth(currentDate);
        int dateX = 10;
        int dateY = 450;
        g2d.drawString(currentDate, dateX, dateY);
    }

    public static void main(String[] args) {
        NewClass view = new NewClass();
        view.setVisible(true);
    }
}
