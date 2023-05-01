import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Maze {
    public static void main(String[] args) {
        JFrame j = new JFrame();
        MyPanela m = new MyPanela();
        j.setSize(m.getSize());
        j.add(m);

        j.addKeyListener(m);
        j.addMouseListener(m);
        j.setVisible(true);

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MyPanela extends JPanel implements ActionListener, KeyListener, MouseListener {
    private Timer time;
    
    private int[] direction;
    private boolean[][] maze;
    private int[] position;
    private boolean door;
    private int x;
    private int a;
    private boolean opening;
    private int alpha;
    private String dir;
    private int turn;
    private boolean firstMessage;
    private boolean secondMessage;
    private boolean thirdMessage;
    private int message1;
    private int message2;
    private int message3;
    private boolean temp;
    private boolean temp1;
    private boolean done;
    
    MyPanela() {
        x=0;
        a=300;
        alpha=1;
        turn=0;
        message1=0;
        message2=0;
        message3=0;
        position = new int[]{0, 0};
        direction = new int[]{0, 1};
        dir = "RIGHT";
        door=false;
        opening=false;
        firstMessage=true;
        secondMessage=true;
        thirdMessage=true;
        done=false;
        temp=false;
        temp1=false;
        maze = new boolean[][]  {{false, false, false, false, false, false, false, false, false, false, false},
                                 {false, true,  true,  true,  true,  false, false, false, false, false, false},
                                 {false, false, true,  false, false, false, false, true,  true,  false, false},
                                 {false, false, true,  true,  true,  false, false, true,  false, false, false},
                                 {false, false, true,  false, true,  false, false, true,  true,  true,  false},
                                 {false, true,  true,  false, true,  false, false, true,  false, false, false},
                                 {false, false, false, true,  true,  true,  false, true,  true,  false, false},
                                 {false, false, false, false, true,  false, true,  false, true,  false, false},
                                 {false, false, false, true,  true,  true,  true,  false, true,  true,  false},
                                 {false, false, true,  true,  true,  true,  false, false, false, true,  false},
                                 {false, false, true,  false, false, true,  false, true,  true,  true,  false},
                                 {false, false, false, false, false, true,  false, true,  false, false, false},
                                 {false, false, false, true,  true,  true,  true,  true,  true,  false, false},
                                 {false, false, true,  true,  false, false, true,  false, false, false, false}};
        time = new Timer(10, this);
        setSize(800, 600);
        setVisible(true);
        time.start();
    }

    public void paintComponent(Graphics g) {
        if(done) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 800, 600);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.drawString("You did it!", 300, 300);
        } else {
            if(door) {
                drawRoom(door, g);
            } else {
                drawRoom(g);
            }
    
            if(position[0]==0 && position[1]==0) {
                displayInstructions(g);
            } else {
                drawSmoke(g);
            }

            if(turn>0 && turn <5) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 800, 600);
                turn++;
                opening=false;
            } else if(turn==3) {
                turn=0;
            }

            if(opening) {
                if(a>150) {
                    a-=2;
                    drawRoom(true, g);
                } else if(alpha<225){
                    g.setColor(new Color(0, 0, 0, alpha));
                    g.fillRect(0, 0, 800, 600);
                    alpha+=4;
                } else {
                    if(maze[position[0]+direction[0]][position[1]+direction[1]]) {
                        door=true;
                    } else {
                        door=false;
                    }
                    opening=false;
                    a=300;
                    alpha=1;
                    if(position[0]==4 && position[1]==9) {
                        done=true;
                        g.setColor(Color.WHITE);
                        g.fillRect(0, 0, 800, 600);
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial", Font.PLAIN, 40));
                        g.drawString("You did it!", 300, 300);
                    } else if(door) {
                        drawRoom(door, g);
                    } else {
                        drawRoom(g);
                    }
                }
            }
            if(message1>0) {
                temp1=true;
                if(door) {
                    temp=true;
                    door=false;
                }
                if(message1==140) {
                    message1=0;
                    firstMessage=false;
                    temp1=false;
                    if(temp) {
                        door=true;
                        temp=false;
                    }
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, 800, 600);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.ITALIC, 36));
                    g.drawString("ARE YOU LOST?", 250, 300);
                    message1++;
                }
            }
            if(message2>0) {
                temp1=true;
                if(door) {
                    temp=true;
                    door=false;
                }
                if(message2==140) {
                    message2=0;
                    firstMessage=false;
                    temp1=false;
                    if(temp) {
                        door=true;
                        temp=false;
                    }
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, 800, 600);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.ITALIC, 36));
                    g.drawString("DO YOU KNOW WHERE YOU ARE?", 97, 300);
                    message2++;
                }
            }if(message3>0) {
                temp1=true;
                if(door) {
                    temp=true;
                    door=false;
                }
                if(message3==140) {
                    message3=0;
                    firstMessage=false;
                    temp1=false;
                    if(temp) {
                        door=true;
                        temp=false;
                    }
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, 800, 600);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.ITALIC, 36));
                    g.drawString("WHERE ARE YOU GOING?", 170, 300);
                    message3++;
                }
            }  
        }
    }

    private void drawRoom(boolean isDoor, Graphics g) {
        drawRoom(g);
        g.setColor(new Color(0, 55, 0));
        g.fillRect(a, 220, 200, 300);
        g.setColor(new Color(0, 35, 0));
        g.fillRect(a+29, 247, 58, 108);
        g.fillRect(a+29, 385, 58, 108);
        g.fillRect(a+113, 247, 58, 108);
        g.fillRect(a+113, 385, 58, 108);
        g.setColor(new Color(50, 45, 20));
        g.fillOval(a+165, 357, 25, 25);
        g.setColor(new Color(0, 0, 0));
        g.drawRect(a, 220, 200, 300);
        door=true;
        if(a<300) {
            g.fillRect(a+200, 220, 300-a, 300);
        }
    }
    private void drawRoom(Graphics g) {
        g.setColor(new Color(0, 0, 110));
        Polygon leftWall = new Polygon();
        leftWall.addPoint(0, 0);
        leftWall.addPoint(100, 60);
        leftWall.addPoint(100, 520);
        leftWall.addPoint(0, 600);
        g.fillPolygon(leftWall);

        Polygon rightWall = new Polygon();
        rightWall.addPoint(800, 0);
        rightWall.addPoint(700, 60);
        rightWall.addPoint(700, 520);
        rightWall.addPoint(800, 600);
        g.fillPolygon(rightWall);

        g.setColor(new Color(0, 0, 90));
        Polygon floor = new Polygon();
        floor.addPoint(0, 600);
        floor.addPoint(100, 520);
        floor.addPoint(700, 520);
        floor.addPoint(800, 600);
        g.fillPolygon(floor);
        
        g.setColor(new Color(0, 0, 135));
        Polygon ceiling = new Polygon();
        ceiling.addPoint(0, 0);
        ceiling.addPoint(100, 60);
        ceiling.addPoint(700, 60);
        ceiling.addPoint(800, 0);
        g.fillPolygon(ceiling);

        g.setColor(new Color(0, 0, 70));
        Polygon backWall = new Polygon();
        backWall.addPoint(100, 60);
        backWall.addPoint(100, 520);
        backWall.addPoint(700, 520);
        backWall.addPoint(700, 60);
        g.fillPolygon(backWall);
        door=false;
    }
    private void drawSmoke(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 560, 800, 40);
        for(int a=0; a<7; a++) {
            g.fillArc((x+160*a)%960-160, 540, 320, 110, 90, 90);
        }
    }
    private void displayInstructions(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.WHITE);
        g.drawString("Left arrow to turn left", 342, 225);
        g.drawString("Right arrow to turn right", 335, 250);
        g.drawString("Click to go through doors", 330, 275);
        g.drawString("Click to start!", 360, 325);
    }

    public void mouseClicked(MouseEvent e) {
        if(position[0]==0) {
            position[0]=1;
            position[1]=1;
            door=true;
        } else if(!temp1 && door && !opening &&  e.getX()>=300 && e.getX()<=500 && e.getY()>=220 && e.getY()<=520) {
            opening=true;
            position[0]+=direction[0];
            position[1]+=direction[1];
            if(position[0]==1 && position[1]==4 && firstMessage) {
                message1=1;
            } else if(position[0]==6 && position[1]==4 && secondMessage) {
                message2=1;
            } else if(position[0]==12 && position[1]==8 && thirdMessage) {
                message3=1;
            }
        }
    }
    public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
    
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(!temp1) {
            if(code==KeyEvent.VK_LEFT) {
                if(dir.equals("RIGHT")) {
                    dir="UP";
                    direction[0]=-1;
                    direction[1]=0;
                } else if(dir.equals("UP")) {
                    dir="LEFT";
                    direction[0]=0;
                    direction[1]=-1;
                } else if(dir.equals("LEFT")) {
                    dir="DOWN";
                    direction[0]=1;
                    direction[1]=0;
                } else if(dir.equals("DOWN")) {
                    dir="RIGHT";
                    direction[0]=0;
                    direction[1]=1;
                }
                turn=1;
            } else if(code==KeyEvent.VK_RIGHT) {
                if(dir.equals("LEFT")) {
                    dir="UP";
                    direction[0]=-1;
                    direction[1]=0;
                } else if(dir.equals("DOWN")) {
                    dir="LEFT";
                    direction[0]=0;
                    direction[1]=-1;
                } else if(dir.equals("RIGHT")) {
                    dir="DOWN";
                    direction[0]=1;
                    direction[1]=0;
                } else if(dir.equals("UP")) {
                    dir="RIGHT";
                    direction[0]=0;
                    direction[1]=1;
                }
                turn=1;
            }
            if(maze[position[0]+direction[0]][position[1]+direction[1]]) {
                door=true;
            } else {
                door=false;
            }
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void actionPerformed(ActionEvent e) {
        x+=2;
        x%=800;
        repaint();
    }
}
