import org.w3c.dom.Text;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Timer;
public class Main {
    static int count=0,button1row=0,button1col=0,button2row=0,button2col=0,HRow=0,HCol=0,VRow=0,VCol=0;
    static int Hcount=0,Vcount=0,FindV=0,FindH=0,HEXRow=0,HEXCol=0,VEXRow=0,VEXCol=0,Score=0;
    static boolean flag=false,Customplay=false;
    static JLabel ScoreLbL= new JLabel(String.valueOf(Score));
    static boolean Hcheck=false,Vcheck=false;
    static int[] LeaderBoard = new int[5];
    public static void main(String[] args) {
        Candy [][] candies =new Candy[10][10];
        MainMenu(candies);
    }

    public static void MainMenu(Candy[][] candies){  //Main Menu Panel
        JFrame Menu = new JFrame("Main-Menu");
        JPanel menu = new JPanel();
        JLabel lbl = new JLabel();
        lbl.setIcon(new ImageIcon("resources/MainMenu.jpg"));
        Menu.setContentPane(lbl);
        JButton Play=new JButton("Play");
        Play.setFont(new Font("Serif",Font.PLAIN,30));
        Play.setBackground(Color.ORANGE);
        Play.setFocusable(false);
        JButton ScoreBoard=new JButton("ScoreBoard");
        ScoreBoard.setBackground(Color.orange);
        ScoreBoard.setFocusable(false);
        ScoreBoard.setFont(new Font("Serif",Font.PLAIN,30));
        JButton CustomPlay=new JButton("CustomPlay");
        CustomPlay.setBackground(Color.orange);
        CustomPlay.setFocusable(false);
        CustomPlay.setFont(new Font("Serif",Font.PLAIN,30));
        Menu.setPreferredSize(new Dimension(600,600));
        menu.setPreferredSize(new Dimension(250,180));
        menu.setBackground(Color.pink);
        menu.add(Play);
        menu.add(CustomPlay);
        menu.add(ScoreBoard);
        menu.setLayout(new GridLayout(3,1,5,10));
        Menu.add(menu);
        menu.setBackground(new Color(255,105,180,150));
        Menu.setLayout(new FlowLayout());
        Menu.setResizable(false);
        Menu.setVisible(true);
        Menu.pack();
        Play.addActionListener(new ActionListener() { // Play Button
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(false);
                Play(candies,false);
            }
        });
        ScoreBoard.addActionListener(new ActionListener() { //ScoreBoard Button
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(false);
                ScoreBoard(candies);
            }
        });
        CustomPlay.addActionListener(new ActionListener() {  // CuastomPlay Button
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(false);
                Play(candies,true);
            }
        });
        Menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void ScoreBoard(Candy[][] candies){ // ScoreBoard Panel
        JFrame ScoreFrame = new JFrame("ScoreBoard");
        JPanel ScorePanel = new JPanel();
        ScoreFrame.setPreferredSize(new Dimension(600,700));
        ScorePanel.setLayout(new GridLayout(8,1,0,40));
        ScoreFrame.setContentPane(new JLabel(new ImageIcon("resources/ScoreBoard(1).jpg")));
        ScoreFrame.setVisible(true);
        ScoreFrame.setResizable(false);
        ScoreFrame.setLayout(new GridBagLayout());
        ScorePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        ScorePanel.setPreferredSize(new Dimension(250,640));
        ScorePanel.setOpaque(false);
        ScoreFrame.add(ScorePanel);
        ScoreFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ScoreFrame.pack();
        JButton Menu = new JButton();
        ScorePanel.add(new JLabel());
        ScorePanel.add(Menu);
        JPanel temp = new JPanel();
        temp.setPreferredSize(new Dimension(5,5));
        for (int i=0;i<5;++i) // Displaying Top 5 Scores
        {
            JLabel lbl = new JLabel();
            lbl.setText(LeaderBoard[i]+"\n");
            lbl.setFont(new Font("Serif",Font.PLAIN,50));
            lbl.setForeground(Color.red);
            ScorePanel.add(lbl);
        }
        ScorePanel.add(new JLabel());
        Menu.setText("Menu");
        Menu.setFont(new Font("Serif",Font.PLAIN,30));
        Menu.setBackground(Color.orange);
        Menu.setFocusable(false);
        Menu.addActionListener(new ActionListener() { // Main Menu Button
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreFrame.setVisible(false);
                MainMenu(candies);
            }
        });
    }

    public static void Play(Candy[][] candies,boolean customplay){ // GameBoard Panel
        flag=false;
        Score=0;
        JFrame frame =new JFrame("Candy-Crush");
        JPanel panel = new JPanel();
        JPanel ScorePanel = new JPanel();
        JPanel temp = new JPanel();
        JPanel temp1 = new JPanel();
        temp.setOpaque(false);
        temp.setPreferredSize(new Dimension(10,20));
        temp1.setOpaque(false);
        temp1.setPreferredSize(new Dimension(10,20));
        ScorePanel.setPreferredSize(new Dimension(180,50));
        JPanel MenuPanel = new JPanel();
        MenuPanel.setPreferredSize(new Dimension(170,50));
        MenuPanel.setLayout(new FlowLayout());
        ImageIcon img = new ImageIcon("resources/Background.jpg");
        JButton Menu = new JButton("Menu");
        Menu.setBackground(Color.orange);
        Menu.setFocusable(false);
        ScorePanel.setOpaque(false);
        ScoreLbL.setFont(new Font("Serif",Font.PLAIN,30));
        ScoreLbL.setForeground(Color.BLUE);
        JLabel Scorelable = new JLabel();
        Scorelable.setText("Score:");
        Scorelable.setForeground(Color.BLUE);
        Scorelable.setFont(new Font("Serif",Font.PLAIN,30));
        JLabel lbl = new JLabel(img);
        MenuPanel.setOpaque(false);
        MenuPanel.add(Menu,BorderLayout.PAGE_START);
        ScorePanel.add(Scorelable);
        ScorePanel.add(ScoreLbL);
        if (!customplay) //This statement is true when you choose play then you start randomly
            for (int row=0;row< candies.length;++row)
            {
                for(int col=0;col<candies[row].length;++col)
                {
                    RandomStart(row,col,candies);
                    candies[row][col].setFocusable(false);
                    candies[row][col].setContentAreaFilled(false);
                    candies[row][col].setBorderPainted(true);
                    candies[row][col].setBackground(Color.pink);
                    candies[row][col].setOpaque(true);
                    panel.add(candies[row][col]);
                }
            }
        else{ // if you choose "Custom Play" the game panel will be share to start with your candies
            customplay=false;
            CustomPlay(candies,panel);
        }
        DeleteCandy(candies);
        panel.setBackground(Color.darkGray);
        frame.setContentPane(lbl);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(1200,700));
        panel.setLayout(new GridLayout(0,10,3,3));
        panel.setPreferredSize(new Dimension(670,700));
        frame.add(temp,BorderLayout.PAGE_START);
        frame.add(ScorePanel,BorderLayout.LINE_START);
        frame.add(panel,BorderLayout.CENTER);
        frame.add(MenuPanel,BorderLayout.LINE_END);
        frame.add(temp1,BorderLayout.PAGE_END);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        flag=true;
        for (int row=0;row< candies.length;++row)
            for (int col = 0; col < candies[row].length; ++col) {
                candies[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (count == 0) {
                            findCandy(e.getSource(), candies);
                            candies[button1row][button1col].setBackground(Color.orange);
                            ++count;
                        } else {
                            findCandy(e.getSource(), candies);
                            candies[button1row][button1col].setBackground(Color.pink);
                            count = 0;
                            swap(button1row, button1col, button2row, button2col, candies, panel);
                            if (Score >= 1500) {
                                JOptionPane.showMessageDialog(null, "You Won!");
                                if (Score > LeaderBoard[4])
                                    AddScore(Score);
                                frame.setVisible(false);
                                MainMenu(candies);
                            }
                        }
                    }
                });
            }
        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Score > LeaderBoard[4])
                    AddScore(Score);
                frame.setVisible(false);
                MainMenu(candies);
            }
        });
    }
    public static void AddScore(int Score){
        int index=4;
        for (;index>0;--index)
            if (Score>LeaderBoard[index-1]) {
                    LeaderBoard[index]=LeaderBoard[index-1];
            }
            else
                break;
                LeaderBoard[index]=Score;
    }
    public static void CustomPlay(Candy[][] candies,JPanel panel){
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try {
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
                String line = reader.readLine();
                for (int Row=0;Row<10;++Row){
                    Scanner console = new Scanner(line);
                    for (int Col=0;Col<10;++Col){
                        candies[Row][Col]=CustomCandy(console.next());
                        candies[Row][Col].setFocusable(false);
                        candies[Row][Col].setContentAreaFilled(false);
                        candies[Row][Col].setBorderPainted(true);
                        candies[Row][Col].setBackground(Color.pink);
                        candies[Row][Col].setOpaque(true);
                        panel.add(candies[Row][Col]);
                        if(console.hasNext())
                            console.next();
                    }
                    line= reader.readLine();
                }
            }catch (Exception e){
                System.out.println("");
            }
        }
    }
    public static void findCandy(Object button,Candy[][] candies) {
        for (int row = 0; row < candies.length; ++row) {
            for (int col=0;col<candies[row].length;++col)
                if (button.equals(candies[row][col])) {
                    if(count==0)
                    {
                        button1row=row;
                        button1col=col;
                    }
                    else {
                        button2row=row;
                        button2col=col;
                    }

                }
        }
    }
    public static void swap(int button1row,int button1col,int button2row,int button2col,Candy[][] candies,JPanel panel)
    {
        if(!canswap(candies))
            return;
        String Color = candies[button1row][button1col].getColor();
        Icon icon = candies[button1row][button1col].getIcon();
        String Behavior = candies[button1row][button1col].getBehavior();
        String type = candies[button1row][button1col].getType();

        candies[button1row][button1col].setColor(candies[button2row][button2col].getColor());
        candies[button1row][button1col].setIcon(candies[button2row][button2col].getIcon());
        candies[button1row][button1col].setBehavior(candies[button2row][button2col].getColor());
        candies[button1row][button1col].setType(candies[button2row][button2col].getType());

        candies[button2row][button2col].setColor(Color);
        candies[button2row][button2col].setIcon(icon);
        candies[button2row][button2col].setBehavior(Behavior);
        candies[button2row][button2col].setType(type);
    }
    public static boolean canswap(Candy [][] candies){
        if(Math.abs(button1row-button2row)>1||Math.abs(button1col-button2col)>1)
            return false;
        if(button1row!=button2row&&button1col!=button2col)
            return false;
        String Color = candies[button1row][button1col].getColor();
        Icon icon = candies[button1row][button1col].getIcon();
        String Behavior = candies[button1row][button1col].getBehavior();
        String type = candies[button1row][button1col].getType();

        candies[button1row][button1col].setColor(candies[button2row][button2col].getColor());
        candies[button1row][button1col].setIcon(candies[button2row][button2col].getIcon());
        candies[button1row][button1col].setBehavior(candies[button2row][button2col].getColor());
        candies[button1row][button1col].setType(candies[button2row][button2col].getType());

        candies[button2row][button2col].setColor(Color);
        candies[button2row][button2col].setIcon(icon);
        candies[button2row][button2col].setBehavior(Behavior);
        candies[button2row][button2col].setType(type);
        Hcheck(candies);
        Vcheck(candies);
        if (Hcheck||Vcheck){
            Hcheck=false;
            Vcheck=false;
            DeleteCandy(candies);
            return false;
        }
        Hcheck=false;
        Vcheck=false;
        return true;
    }
    public static void DeleteCandy(Candy [][] candies){
        do {
            Hcheck(candies);
            if(Hcheck)
                DeleteRow(candies);
            Vcheck(candies);
            if (Vcheck)
                DeleteCol(candies);
            Hcheck(candies);
            Vcheck(candies);
        }while (Hcheck||Vcheck);
    }
    public static void Hcheck(Candy [][] candies)
    {
        int counter=1;
        for(int row=0;row< candies.length;++row)//Check Horizontal
        {
            for (int col=0;col< candies.length-1;++col)
            {
                for (int j=col+1;j<candies.length;++j)
                {
                    if (candies[row][col].getColor().equals(candies[row][j].getColor()))
                    {
                        ++counter;
                        if(counter>=3)
                        {
                            Hcheck=true;
                            HRow=row;
                            HCol=col;
                            Hcount=counter;
                        }
                    }
                    else break;
                }
                counter=1;
                if(Hcheck)
                    return;
            }
            counter=1;
        }
    }
    public static void Vcheck(Candy [][] candies){
        int counter=1;
        for(int col=0;col< candies.length;++col)//Check Vertical
        {
            for (int row=0;row<candies.length-1;++row)
            {
                for (int k=row+1;k<candies.length;++k)
                {
                    if(candies[row][col].getColor().equals(candies[k][col].getColor()))
                    {
                        ++counter;
                        if(counter>=3)
                        {
                            Vcheck = true;
                            VRow=row;
                            VCol=col;
                            Vcount=counter;
                        }
                    }
                    else break;
                }
                counter=1;
                if(Vcheck)
                    return;
            }
            counter=1;
        }
    }


    public static void DeleteRow(Candy [][]candies){
        if(Hcount==4&&FindSpecialInRow(candies))//Special Deletion
        {
            if (flag)
                Score+=10;
            ScoreLbL.setText(String.valueOf(Score));
            if(FindVInRow(candies)){
                AddSpecialInRow(candies);
                DefaultDeletionRow(candies,HCol+1);
                SpecialDelCol(candies,FindV,false);
            }
            else {//Horizontal Special Candy
                SpecialDelRow(candies,HRow,false);
            }
        }
        else if (FindExplosiveInRow(candies))
        {
            if (flag)
                Score+=15;
            ScoreLbL.setText(String.valueOf(Score));
            DeleteExplosive(candies,HEXRow,HEXCol);
        }
        else if (Hcount==5)
        {
            if (flag)
                Score+=5;
            ScoreLbL.setText(String.valueOf(Score));
            AddExplosiveCandy(candies);
            DefaultDeletionRow(candies,HCol+1);
        }
        else if(Hcount==4)
        {
            if (flag)
                Score+=5;
            ScoreLbL.setText(String.valueOf(Score));
            AddSpecialInRow(candies);
            DefaultDeletionRow(candies,HCol+1);
        }
        else if(FindSpecialInRow(candies))
        {//Special Deletion
            if (flag)
                Score+=10;
            ScoreLbL.setText(String.valueOf(Score));
            if(FindVInRow(candies)){
                DefaultDeletionRow(candies,HCol);
                SpecialDelCol(candies,FindV,false);
            }
            else {//Horizontal Special Candy
                SpecialDelRow(candies,HRow,false);
            }
        }
        else
        {
            if (flag)
                Score+=5;
            ScoreLbL.setText(String.valueOf(Score));
            DefaultDeletionRow(candies,HCol);
        }
        Hcheck=false;
    }

    public static boolean FindExplosiveInRow(Candy[][] candies){
        for (int j=HCol;j<Hcount+HCol;++j)
            if(candies[HRow][j].getBehavior().equals("Explosive"))
            {
                HEXRow=HRow;
                HEXCol=j;
                return true;
            }
            return false;
    }


    public static void AddExplosiveCandy(Candy[][] candies)
    {
        ExplosiveCandy newCandy=Explosive(candies[HRow][HCol].getColor());
        candies[HRow][HCol].setColor(newCandy.getColor());
        candies[HRow][HCol].setIcon(newCandy.getIcon());
        candies[HRow][HCol].setBehavior("Explosive");
        candies[HRow][HCol].setType("");
    }

    public static boolean FindVInRow(Candy[][] candies)//Special Vertical Candy in Row
    {
        for(int y=HCol;y<HCol+Hcount;++y)
            if (candies[HRow][y].getType().equals("v"))
            {
                FindV=y;
                return true;
            }
            return false;
    }

    public static boolean FindSpecialInRow(Candy[][] candies){
        for (int j=HCol;j<Hcount+HCol;++j)
            if (candies[HRow][j].getBehavior().equals("Special"))
                return true;
        return false;
    }


    public static void AddSpecialInRow(Candy[][] candies){
        Candy newCandy;
        newCandy=Special(candies[HRow][HCol].getColor(),"h");
        candies[HRow][HCol].setColor(newCandy.getColor());
        candies[HRow][HCol].setIcon(newCandy.getIcon());
        candies[HRow][HCol].setBehavior("Special");
        candies[HRow][HCol].setType("h");
    }


    public static void DefaultDeletionRow(Candy[][] candies,int FHCol){
        Candy newCandy;
        for (int x=HRow;x>0;--x)//Defaul Deletion
        {
            for (int j=FHCol;j<Hcount+HCol;++j)
            {
                candies[x][j].setColor(candies[x-1][j].getColor());
                candies[x][j].setIcon(candies[x-1][j].getIcon());
                candies[x][j].setBehavior(candies[x-1][j].getBehavior());
                candies[x][j].setType(candies[x-1][j].getType());
            }
        }
        for (int column=FHCol;column<Hcount+HCol;++column)
        {
            newCandy=RandomCandy();
            candies[0][column].setColor(newCandy.getColor());
            candies[0][column].setIcon(newCandy.getIcon());
            candies[0][column].setBehavior("Simple");
            candies[0][column].setType(newCandy.getType());
        }
    }


    public static void SpecialDelRow(Candy[][] candies,int FHrow,boolean SpecialInRow){
        for (int x=FHrow;x>0;--x)
        {
            for (int j=0;j<candies.length;++j)
            {
                if(SpecialInRow&&j==HCol)
                    continue;
                candies[x][j].setColor(candies[x-1][j].getColor());
                candies[x][j].setIcon(candies[x-1][j].getIcon());
                candies[x][j].setBehavior(candies[x-1][j].getBehavior());
                candies[x][j].setType(candies[x-1][j].getType());
            }
        }
        Candy newCandy;
        for (int column=0;column<candies.length;++column)
        {
            newCandy=RandomCandy();
            if (SpecialInRow&&column==HCol)
                continue;
            candies[0][column].setColor(newCandy.getColor());
            candies[0][column].setIcon(newCandy.getIcon());
            candies[0][column].setBehavior("Simple");
            candies[0][column].setType(newCandy.getType());
        }
    }



    public static void DeleteCol(Candy [][] candies){

        if(Vcount==4&&FindSpecialInCol(candies)) {
            if (flag)
                Score+=5;
            ScoreLbL.setText(String.valueOf(Score));
            if(FindHInCol(candies))
            {
                SpecialDelRow(candies,FindH,false);
                AddSpecialInCol(candies);
                DefaultDeletionCol(candies,VRow+Vcount-2,Vcount-2);
            }
            else {
                SpecialDelCol(candies, VCol,false);
            }
        }
        else if(FindExplosiveInCol(candies)){
            if (flag)
                Score+=15;
            ScoreLbL.setText(String.valueOf(Score));
            DeleteExplosive(candies,VEXRow,VEXCol);
        }
        else if(Vcount==5){
            if (flag)
                Score+=5;
            ScoreLbL.setText(String.valueOf(Score));
            AddExplosiveInCol(candies);
            DefaultDeletionCol(candies,VRow+Vcount-2,Vcount-1);
        }
        else if(Vcount==4) {
            if (flag)
                Score+=5;
            ScoreLbL.setText(String.valueOf(Score));
            AddSpecialInCol(candies);
            DefaultDeletionCol(candies,VRow+Vcount-2,Vcount-1);
        }
        else if(FindSpecialInCol(candies)) {
            if (flag)
                Score+=10;
            ScoreLbL.setText(String.valueOf(Score));
            if(FindHInCol(candies))
            {
                SpecialDelRow(candies,FindH,false);
                DefaultDeletionCol(candies,VRow+Vcount-1,Vcount-1);
            }
            else {
                SpecialDelCol(candies,VCol,false);
            }
        }
        else {
            if (flag)
                Score+=5;
            ScoreLbL.setText(String.valueOf(Score));
            DefaultDeletionCol(candies,VRow+Vcount-1,Vcount);
        }
        Vcheck=false;
    }

    public static void DeleteExplosive(Candy[][] candies,int Row,int Col)
    {
            if (0<Col-1 && 0<Row-1 && Col+1<candies.length &&Row+1< candies.length)
            {
                Bomb(candies,Row+1,Col-1,3,3);
            }
            else if(0<Row-1 && Col+1< candies.length && Row+1< candies.length)
            {
                Bomb(candies,Row+1,Col,2,3);
            }
            else if(0<Col-1&&Col+1< candies.length&&Row+1< candies.length)
            {
                Bomb(candies,Row+1,Col-1,3,2);
            }
            else if(0<Col-1&&0<Row-1&&Row+1< candies.length){
                Bomb(candies,Row+1,Col-1,2,3);
            }
            else if(0<Col-1 && 0<Row-1 &&Col+1< candies.length){
                Bomb(candies,Row,Col-1,3,2);
            }
            else if(0>Col-1&&Row+1>= candies.length){
                Bomb(candies,Row,Col,2,2);
            }
            else if(Row-1<0&&Col-1<0)
            {
                Bomb(candies,Row+1,Col,2,2);
            }
            else if(Col+1>= candies.length&&Row-1<0){
                Bomb(candies,Row+1,Col-1,2,2);
            }
            else if(Row+1>= candies.length && Col+1>= candies.length)
            {
                Bomb(candies,Row,Col-1,2,2);
            }
    }


    public static void Bomb(Candy[][] candies,int Row,int Col,int HCOUNT,int VCOUNT)
    {
        for (int k=0;k<VCOUNT;++k)
        {
            for (int x=Row;x>0;--x)//Defaul Deletion
            {
                for (int j=Col;j<HCOUNT+Col;++j)
                {
                    candies[x][j].setColor(candies[x-1][j].getColor());
                    candies[x][j].setIcon(candies[x-1][j].getIcon());
                    candies[x][j].setBehavior(candies[x-1][j].getBehavior());
                    candies[x][j].setType(candies[x-1][j].getType());
                }
            }
            Candy newCandy;
            for (int column=Col;column<HCOUNT+Col;++column)
            {
                newCandy=RandomCandy();
                candies[0][column].setColor(newCandy.getColor());
                candies[0][column].setIcon(newCandy.getIcon());
                candies[0][column].setBehavior("Simple");
                candies[0][column].setType(newCandy.getType());
            }
        }
    }

    public static void AddExplosiveInCol(Candy[][] candies)
    {
        ExplosiveCandy newCandy=Explosive(candies[VRow][VCol].getColor());
        candies[VRow+Vcount-1][VCol].setColor(newCandy.getColor());
        candies[VRow+Vcount-1][VCol].setIcon(newCandy.getIcon());
        candies[VRow+Vcount-1][VCol].setBehavior("Explosive");
        candies[VRow+Vcount-1][VCol].setType("");
    }

    public static boolean FindExplosiveInCol(Candy[][] candies){
        for (int i=VRow;i<Vcount+VRow;++i)
            if(candies[i][VCol].getBehavior().equals("Explosive"))
            {
                VEXRow=i;
                VEXCol=VCol;
                return true;
            }
        return false;
    }

    public static void AddSpecialInCol(Candy[][] candies)
    {
        SpecialCandy newCandy=Special(candies[VRow][VCol].getColor(),"v");
        candies[VRow+Vcount-1][VCol].setColor(newCandy.getColor());
        candies[VRow+Vcount-1][VCol].setIcon(newCandy.getIcon());
        candies[VRow+Vcount-1][VCol].setBehavior(newCandy.getBehavior());
        candies[VRow+Vcount-1][VCol].setType("v");
    }


    public static boolean FindHInCol(Candy[][] candies)
    {
        for (int i=VRow;i<Vcount+VRow;++i)
            if (candies[i][VCol].getType().equals("h"))
            {
                FindH=i;
                return true;
            }
        return false;
    }

    public static boolean FindSpecialInCol(Candy [][] candies)
    {
        for (int i=VRow;i<Vcount+VRow;++i)
            if (candies[i][VCol].getBehavior().equals("Special"))
                return true;
            return false;
    }


    public static void DefaultDeletionCol(Candy[][] candies,int FVrow,int FVcount)
    {
        Candy newCandy;
        for(int num=0;num<FVcount;++num)
        {
            for (int Row=FVrow;Row>0;--Row)
            {
                candies[Row][VCol].setColor(candies[Row-1][VCol].getColor());
                candies[Row][VCol].setIcon(candies[Row-1][VCol].getIcon());
                candies[Row][VCol].setBehavior(candies[Row-1][VCol].getBehavior());
                candies[Row][VCol].setType(candies[Row-1][VCol].getType());
            }
            newCandy=RandomCandy();
            candies[0][VCol].setColor(newCandy.getColor());
            candies[0][VCol].setIcon(newCandy.getIcon());
            candies[0][VCol].setBehavior(newCandy.getBehavior());
            candies[0][VCol].setType(newCandy.getType());
        }
    }


        public static void SpecialDelCol(Candy [][] candies,int FVcol,boolean SpecialAtLastCol){
        Candy newCandy;
        for (int i=0;i<candies.length;++i){
            if(SpecialAtLastCol&&i==candies.length-1)
                break;
            newCandy=RandomCandy();
            candies[i][FVcol].setColor(newCandy.getColor());
            candies[i][FVcol].setIcon(newCandy.getIcon());
            candies[i][FVcol].setBehavior(newCandy.getBehavior());
            candies[i][FVcol].setType(newCandy.getType());
        }
    }



    public static ExplosiveCandy Explosive(String Color){
        switch (Color){
            case "Red":return new ExplosiveCandy("Red","resources/Wrapped_red.png","Explosive","");
            case "Blue":return new ExplosiveCandy("Blue","resources/Wrapped_blue.png","Explosive","");
            case "Orange":return new ExplosiveCandy("Orange","resources/Wrapped_orange.png","Explosve","");
            case "Green":return new ExplosiveCandy("Green","resources/Wrapped_green.png","Explosve","");
        }
        return null;
    }



    public static SpecialCandy Special(String Color,String Type){
        switch (Color){
            case "Red":
                if (Type.equals("v"))
                    return new SpecialCandy("Red","resources/Striped_red_v.png","Special","v");
                else
                    return new SpecialCandy("Red","resources/Striped_red_h.png","Special","h");
            case "Blue":
                if (Type.equals("v"))
                    return new SpecialCandy("Blue","resources/Striped_blue_v.png","Special","v");
                else
                    return new SpecialCandy("Blue","resources/Striped_blue_h.png","Special","h");
            case "Orange":
                if (Type.equals("v"))
                    return new SpecialCandy("Orange","resources/Striped_orange_v.png","Special","v");
                else
                    return new SpecialCandy("Orange","resources/Striped_orange_h.png","Special","h");
            case "Green":
                if (Type.equals("v"))
                    return new SpecialCandy("Green","resources/Striped_green_v.png","Special","v");
                else
                    return new SpecialCandy("Green","resources/Striped_green_h.png","Special","h");
        }
        return null;
    }


    public static Candy CustomCandy(String Candy){
        switch (Candy){
            case "SCR": return new Candy("Red","resources/Red.png","Simple","");
            case "SCG": return new Candy("Green", "resources/Green.png","simple","");
            case "SCB": return new Candy("Blue","resources/Blue.png","Simple","");
            case "SCY": return new Candy("Orange","resources/Orange.png","Simple","");
            case "LRR": return new SpecialCandy("Red","resources/Striped_red_h.png","Special","h");
            case "LRG": return new SpecialCandy("Green","resources/Striped_green_h.png","Special","h");
            case "LRB": return new SpecialCandy("Blue","resources/Striped_blue_h.png","Special","h");
            case "LRY": return new SpecialCandy("Orange","resources/Striped_orange_h.png","Special","h");
            case "LCR": return new SpecialCandy("Red","resources/Striped_red_v.png","Special","v");
            case "LCG": return new SpecialCandy("Green","resources/Striped_green_v.png","Special","v");
            case "LCB": return new SpecialCandy("Blue","resources/Striped_blue_v.png","Special","v");
            case "LCY": return new SpecialCandy("Orange","resources/Striped_orange_v.png","Special","v");
            case "RCR": return new ExplosiveCandy("Red","resources/Wrapped_red.png","Explosive","");
            case "RCG": return new ExplosiveCandy("Green","resources/Wrapped_green.png","Explosve","");
            case "RCB": return new ExplosiveCandy("Blue","resources/Wrapped_blue.png","Explosive","");
            case "RCY": return new ExplosiveCandy("Orange","resources/Wrapped_orange.png","Explosve","");
        }
         return null;
    }

    public static Candy RandomCandy()
    {
        Random rand =new Random();
        switch (rand.nextInt(4))
        {
            case 0: return new Candy("Green", "resources/Green.png","simple","");

            case 1: return new Candy("Orange","resources/Orange.png","Simple","");

            case 2: return new Candy("Red","resources/Red.png","Simple","");

            case 3: return new Candy("Blue","resources/Blue.png","Simple","");
        }
        return null;
    }


    public static void RandomStart(int row,int col,Candy [][] candies)
    {
        Random rand =new Random();
        switch (rand.nextInt(4))
        {
            case 0: candies[row][col] =new Candy("Green", "resources/Green.png","simple","");
                break;
            case 1: candies[row][col] =new Candy("Orange","resources/Orange.png","Simple","");
                break;
            case 2: candies[row][col] =new Candy("Red","resources/Red.png","Simple","");
                break;
            case 3: candies[row][col] =new Candy("Blue","resources/Blue.png","Simple","");
        }
    }

}
