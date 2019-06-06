import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.stream.Stream;
import java.nio.file.Paths;

enum Color
{
    Green,Yellow,Orange,Red,Danger;
};

public class WhoIsh
{
    private static final int _180 = 180;
	Color color;

    public WhoIsh(String colour)
    {
        switch(colour.charAt(0))
        {
            case 'G':
                this.color = Color.Green;
                break;
            case 'Y':
                this.color = Color.Yellow;
                break;
            case 'O':
                this.color = Color.Orange;
                break;
            case 'R':
                this.color = Color.Red;
                break;
            case 'D':
                this.color = Color.Danger;
                break;
        }
    }

    public static String getLine(String filePath, int line) throws IOException
    {
        try (Stream<String> lines = Files.lines(Paths.get(filePath)))
        {
            return lines.skip(line).findFirst().get();
        }
    }

    public static Color returnEle(boolean Diabetes, boolean Gender, boolean Smoker, int Age, int SBP, int Cholesterol, String fileName)
    {
        int Position = 0;
        if(Diabetes == false)
            Position += 320;
        if(Gender)
            Position += 160;
        if(Smoker)
            Position += 80;
        Position += ((70-(Age - (Age%10)))*2);
        Position += (_180 - (SBP - (SBP % 20)))/4;
        Position += (Cholesterol - 4);
        try
        {
            int position2 = Position;
			return new WhoIsh(getLine(fileName, position2)).color;
        }
        catch(IOException e)
        {
            // System.out.println("PRINTING NOT CORRECT");
            return Color.Danger;
        }
    }
    
    public static Color returnEle(boolean Diabetes, boolean Gender, boolean Smoker, int Age, int SBP, String fileName)
    {
        int Position = 0;

        if(!Diabetes)
            Position += 64;
        if(Gender)
            Position += 32;
        if(Smoker)
            Position += 16;

        Position += ((-0.4*Age + 28));
        Position += ((180 - SBP)/20);

        try
        {
            int position2 = Position;
            return new WhoIsh(getLine(fileName, position2)).color;
        }
        catch(IOException e)
        {
            System.out.println("PRINTING NOT CORRECT");
            return Color.Danger;
        }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("DIABETES (Y/N): ");
        Boolean Db = false, Gn = false,Sm = false;
        int Ag = 70, SBP = 180, Chl = 4; 
        String sw = sc.nextLine();
        if(sw.equals("Y") || sw.equals("y"))
            Db = true;
        else
            Db = false;
        System.out.print("GENDER (M/F): ");
        sw = sc.nextLine();
        if(sw.equals("M") || sw.equals("m"))
            Gn = false;
        else
            Gn = true;
        System.out.print("SMOKER (Y/N): ");
        sw = sc.nextLine();
        if(sw.equals("Y") || sw.equals("y"))
            Sm = true;
        else
            Sm = false;
        System.out.print("AGE : ");
        int swInt = Integer.parseInt(sc.nextLine());
        if(swInt < 40)
            Ag = 40;
        else if(swInt > 79)
            Ag = 70;
        else
            Ag = swInt;
        System.out.print("SBP :");
        swInt = Integer.parseInt(sc.nextLine());
        if(swInt > 180)
            SBP = 180;
        else if(swInt < 120)
            SBP = 120;
        else
            SBP = swInt;
        System.out.print("CHOLESTEROL :");
        swInt = Integer.parseInt(sc.nextLine());
        if(swInt > 8)
            Chl = 8;
        else if(swInt < 4)
            Chl = 4;
        else
            Chl = swInt;
        System.out.println(Chl ==0 ? returnEle(Db,Gn,Sm,Ag,SBP,"data_withoutchol.txt") : returnEle(Db,Gn,Sm,Ag,SBP,Chl,"data.txt"));
        sc.close();
    }
}