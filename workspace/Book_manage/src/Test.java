import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test {
	
	static Scanner scan = new Scanner(System.in);
	static String filePath="C:\\test\\book.txt";
	static int book_count;
	static int lineNumber;
	
	public static void main(String[] args) throws IOException {
		
		printmenu();
		
		int choice = -1;
	
		while ( choice != 0)
		{
			choice = inputchoice();
			switch(choice)
			{
			
			case 1:
				printbooks();
				break;
			case 2:
				search_all_book();
				break;
			case 3:
				insertbook();
				break;
			case 4:
				deletebook();
				break;
			case 0:
				System.out.println("�����մϴ�.");
				System.exit(0);
				break;
			default :
				System.out.println("�ٽ� �Է��� �ּ���");
			}
		}
	
	}

	private static int inputchoice() {
		
		int choice=-1;
		
		System.out.println("���� �ϼ��� :");
		choice = scan.nextInt();
		
		System.out.println("������ �޴� : " + choice);
		System.out.println();
		return choice;
	}
	
	private static void printmenu()
	{
		System.out.println("���� ���� ���α׷��Դϴ�.");
		System.out.println("1. ��ü ��� ���");
		System.out.println("2. ���� �˻�");
		System.out.println("3. �ű� ���� �߰�");
		System.out.println("4. ���� ���� ���");
		System.out.println("0. ���α׷� ����");
	}

	private static void deletebook() throws IOException{
		String tmpFilePath = filePath +".tmp";
		int count = 1;
	 
		 System.out.println("�����Ͻ� å ��ȣ�� �Է����ּ���(�� ��ȣ �Է�) : " );
		 int booknumber = scan.nextInt();
		      
		 System.out.println("�Է��Ͻ� å ��ȣ : "+booknumber);
		   
		 BufferedReader br = new BufferedReader(new FileReader(filePath));
		 BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));
		      
		 String str="";
		      
		 while((str = br.readLine()) != null){
			 if(count != booknumber){
		     bw.write(str);
		     bw.write("\r\n");
		     }
			 count ++;
			 }
		 bw.close();
		 br.close();
		      
		 FileInputStream fis = new FileInputStream(tmpFilePath);
		 FileOutputStream fos = new FileOutputStream(filePath);
		      
		 int data = 0;
		 while((data = fis.read()) != -1){
		 fos.write(data);
		 }
		 fis.close();
		 fos.close();
		      
		 File f = new File(tmpFilePath);
		 f.deleteOnExit();
		 }

	private static void insertbook() throws IOException {
		System.out.println("�߰��� å�� ������ �Է��� �ּ��� ");
		
		scan.nextLine();
		System.out.println("å �̸� : ");
		String name = scan.nextLine();

		System.out.println("��    �� : ");
		String Author = scan.nextLine();

		System.out.println("���ǻ� : ");
		String publisher = scan.nextLine();

		System.out.println("��    �� : ");
		String price = scan.nextLine();

		System.out.println("å �̸� "+name);
		System.out.println("å ���� "+Author);
		System.out.println("å ���ǻ� "+publisher);
		System.out.println("å ����"+price);
		
		System.out.println("�Է��� ���� �½��ϱ�? 1(Y)/2(N)" );
		System.out.println("�޴��� �̵��Ͻ÷��� 3�� �Է��� �ּ���" );
		int input;
		input = scan.nextInt();
		
		if(input == 2)
		{
			System.out.println("�ٽ� �Է��մϴ�.");
			insertbook();
		}
		else if(input == 3)
		{
			System.out.println("�޴��� �̵��մϴ�.");
			inputchoice();
		}
		else
		{		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
		
		bw.write(name + "\t" + Author + "\t" + publisher + "\t" + price);
		bw.newLine();
		bw.close();
		System.out.println("�Է� �Ǿ����ϴ�.");
		}
		
	}

	private static void printbooks() throws FileNotFoundException {
		scan.nextLine();
		System.out.println("å ���");
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		//��ο� �ִ� txt���� �б�
		lineNumber = 1;
		//�� ��ȣ
		book_count = 0;
		
		String str = "";
		try {
			while((str=br.readLine())!=null)
			{
				System.out.println(lineNumber + " . " + str);
				book_count ++; //�Էµ� �� �Ǽ� ǥ��
				lineNumber ++; //���� �� ��ȣ �ο�
			}
			System.out.println("�� �Էµ� �� �� : " + book_count);
		} catch (IOException e) {
			System.out.println("å ������ ã�� �� �����ϴ�.");
			e.printStackTrace();
		}
	}

	private static void search_all_book() throws FileNotFoundException {
		
		scan.nextLine();
		
		System.out.print("Ű���带 �Է��� �ּ��� : ");
		String Keyword = scan.nextLine();
		lineNumber = 1;
		book_count = 0;
		
		String str = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		

		try {
			while((str=br.readLine())!=null)
			{
				if(str.contains(Keyword)){
					System.out.println( lineNumber + " . " + str);
					book_count ++;
				}
				lineNumber ++;
			}
			System.out.println("��ġ�ϴ� �ڷ� �� : " + book_count);
			br.close();
		} catch (IOException e) {
			System.out.println("å ������ ã�� �� �����ϴ�.");
			e.printStackTrace();
		}
		
	}

}