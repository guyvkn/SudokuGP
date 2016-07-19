package SodukoGame;



import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/*This class geting a randomaly board from uor database 
 * of boards to solve.
 * The RandomNum will be the number of the board in the file
 */
public class GetBoard {
	int RandomNum;
	ArrayList<Integer> array = new ArrayList<Integer>();

	public GetBoard(int randomNum) {
		RandomNum = randomNum;

		//URL path = GetBoard.class.getResource("board.txt");
		//System.out.println(path);
		int flag=1,NumOfBoardInFile=0,read;
		try {
			File f = new File("C:/Users/Guy Vaknin/Desktop/Work-space/Java/SodukoSCE/SodukoSCE/bin/SodukoGame/board.txt");
			FileReader r =new FileReader(f);
			BufferedReader br = new BufferedReader(r); 
			String str;
			while((str=br.readLine())!=null  && flag==1)
			{
				if(str.startsWith("G"))
				{
					if(this.RandomNum==NumOfBoardInFile)
					{
						for(int j=0;j<9;j++)
						{
							//str=br.readLine();
							for(int i=0;i<9;i++)
							{
								read=br.read();
								array.add(Character.getNumericValue(read));
							}	
						}
						flag=0;
					}
					else
						NumOfBoardInFile++;	
				}
				
			}
			
		}
		catch (IOException ex){
			System.out.println("File not found");
		}
	}
	public void PrintArray()
	{
		System.out.println(this.array);

	}
	public static void main(String[] args)
	{
		GetBoard g=new GetBoard(0);
		g.PrintArray();
	}

}



